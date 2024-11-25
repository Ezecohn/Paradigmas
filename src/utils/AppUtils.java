package utils;
import java.util.List;
import utils.AppFun;

import feed.Article;
import namedEntities.NamedEntity;
import utils.Config;
import utils.FeedsData;

public class AppUtils {

    public static void handleHelpArgument(String[] args, List<FeedsData> feedsDataArray) {
        for (String arg : args) {
            if (arg.equals("-h") || arg.equals("--help")) {
                AppFun.printHelp(feedsDataArray);
                System.exit(0);
            }
        }
    }

    public static String getHeuristicName(String[] args) {
        String heuristicName = "Capital"; // Default heuristic name
        for (int i = 0; i < args.length; i++) {
            if ((args[i].equals("-ne") || args[i].equals("--named-entity")) && i + 1 < args.length) {
                heuristicName = args[i + 1];
                break;
            }
        }
        return heuristicName;
    }

    public static String getStatsFormat(String[] args) {
        String statsFormat = "cat"; // Default stats format
        for (int i = 0; i < args.length; i++) {
            if ((args[i].equals("-sf") || args[i].equals("--stats-format")) && i + 1 < args.length) {
                statsFormat = args[i + 1];
                break;
            }
        }
        return statsFormat;
    }

    public static void processFeeds(Config config, List<FeedsData> feedsDataArray, List<Article> allArticles) {
        if (!config.getPrintFeed()) {
            if (config.getFeedKey() == null) {
                for (FeedsData feedData : feedsDataArray) {
                    try {
                        AppFun.parserFeed(feedData, allArticles);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            } else {
                for (FeedsData feedData : feedsDataArray) {
                    if (feedData.getLabel().equals(config.getFeedKey())) {
                        try {
                            AppFun.parserFeed(feedData, allArticles);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                }
            }
        }
    }

    public static void applyHeuristicAndPrintEntities(String heuristicName, List<Article> allArticles) {
        System.out.println("Using heuristic: " + heuristicName);
        for (Article article : allArticles) {
            AppFun.heuristic(heuristicName, article);
            List<NamedEntity> titleEntities = article.getTitleEntities();
            if (titleEntities != null) {
                AppFun.printNamedEntities(titleEntities);
            }

            List<NamedEntity> descriptionEntities = article.getDescriptionEntities();
            if (descriptionEntities != null) {
                AppFun.printNamedEntities(descriptionEntities);
            }
        }
    }

    public static void printStatistics(List<Article> allArticles, String statsFormat) {
        System.out.println("\nStats:");
        System.out.println("-".repeat(80));
        StatisticsCalculator.computeAndPrintStatistics(allArticles, statsFormat);
    }
}