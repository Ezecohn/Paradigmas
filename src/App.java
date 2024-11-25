import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import feed.Article;
import feed.FeedParser;
import namedEntities.NamedEntity;
import utils.AppFun;
import utils.AppUtils;
import utils.Config;
import utils.FeedsData;
import utils.JSONParser;
import utils.UserInterface;

public class App {
    public static void main(String[] args) {
        List<FeedsData> feedsDataArray = new ArrayList<>();
        try {
            feedsDataArray = JSONParser.parseJsonFeedsData("src/data/feeds.json");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        UserInterface ui = new UserInterface();
        Config config = ui.handleInput(args);

        AppUtils.handleHelpArgument(args, feedsDataArray);

        String heuristicName = AppUtils.getHeuristicName(args);
        String statsFormat = AppUtils.getStatsFormat(args);

        run(config, heuristicName, feedsDataArray, statsFormat);
    }

    private static void run(Config config, String heuristicName, List<FeedsData> feedsDataArray, String statsFormat) {
        if (feedsDataArray == null || feedsDataArray.isEmpty()) {
            System.out.println("No feeds data found");
            return;
        }

        List<Article> allArticles = new ArrayList<>();
        if (config.getFeedKey() != null) {
            System.out.println(config.getFeedKey());
        }
        AppFun.printFeed(config, feedsDataArray, allArticles);

        if (config.getComputeNamedEntities()) {
            AppUtils.processFeeds(config, feedsDataArray, allArticles);
            AppUtils.applyHeuristicAndPrintEntities(heuristicName, allArticles);
            AppUtils.printStatistics(allArticles, statsFormat);
        }
    }
}