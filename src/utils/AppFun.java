package utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import feed.Article;
import feed.FeedParser;
import namedEntities.NamedEntity;
import namedEntities.heuristics.CapitalizedWordHeuristic;
import namedEntities.heuristics.QuotedPhraseHeuristic;
import namedEntities.heuristics.CapitalizedExcludeWordsHeuristic;
import namedEntities.heuristics.TwoToFiveInitialsHeuristic;
import namedEntities.heuristics.Heuristic;


public class AppFun {
    public static void parserFeed(FeedsData feedsData, List<Article> allArticles) {
        try {
            String xml = FeedParser.fetchFeed(feedsData.getUrl());
            List<Article> articles = FeedParser.parseXML(xml);
            allArticles.addAll(articles);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void printFeed(Config config, List<FeedsData> feedsDataArray, List<Article> allArticles) {
        if (config.getPrintFeed()) {
            System.out.println("Printing feed(s)");
            if (config.getFeedKey() == null) {
                for (FeedsData feedData : feedsDataArray) {
                    try {
                        parserFeed(feedData, allArticles);
                        allArticles.forEach(Article::print);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            } else {
                for (FeedsData feedData : feedsDataArray) {
                    if (feedData.getLabel().equals(config.getFeedKey())) {
                        try {
                            parserFeed(feedData, allArticles);
                            allArticles.forEach(Article::print);
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.exit(1);
                        }
                    }
                }
            }
        }
    } 

    public static void heuristic(String heuristicName, Article article) { 
        try {
            Heuristic heuristic;
            switch (heuristicName) {
                case "Capital":
                    heuristic = new CapitalizedWordHeuristic();
                    break;
                case "Quote":
                    heuristic = new QuotedPhraseHeuristic();
                    break;
                case "CapitalExclude":
                    heuristic = new CapitalizedExcludeWordsHeuristic ();
                    break;
                case "Initials":
                    heuristic = new TwoToFiveInitialsHeuristic();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown heuristic: " + heuristicName);
            }
            article.applyHeuristic(heuristic);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public static void printNamedEntities(List<NamedEntity> titleEntities) {
        for (NamedEntity entity : titleEntities) {
            System.out.println(entity.getName());
            System.out.print(entity.getCategory() + ":");
            for (int i = 0; i < entity.getTopics().size(); i++) {
                System.out.print(entity.getTopics().get(i));
                if (i < entity.getTopics().size() - 1) {
                    System.out.print(":");
                }
            }
            System.out.println();
            System.out.println();
        }
    }
 

 public static void printHelp(List<FeedsData> feedsDataArray) {
            System.out.println("Usage: make run ARGS=\"[OPTION]\"");
            System.out.println("Options:");
            System.out.println("  -h, --help: Show this help message and exit");
            System.out.println("  -f, --feed <feedKey>:                Fetch and process the feed with");
            System.out.println("                                       the specified key");
            System.out.println("                                       Available feed keys are:");
            for (FeedsData feedData : feedsDataArray) {
                System.out.println("                                       " + feedData.getLabel());
            }
            System.out.println("  -ne, --named-entity <heuristicName>: Use the specified heuristic to extract");
            System.out.println("                                       named entities");
            System.out.println("                                       Available heuristic names are:");
            System.out.println(
                    "                                       Capital: Identifies capitalized words as named entities");
                    System.out.println(
                    "                                       Quote: Identifies quoted phrases as named entities");
                    System.out.println(
                    "                                       Initials: Identifies all capitalized words from 2 to 5 letters as named entities");
                    System.out.println(
                    "                                       CapitalExclude: Identifies all capitalized words as named entities except those in the dictionary exclude");
            System.out.println("  -pf, --print-feed:                   Print the fetched feed");
            System.out.println("  -sf, --stats-format <format>:        Print the stats in the specified format");
            System.out.println("                                       Available formats are:");
            System.out.println("                                       cat: Category-wise stats");
            System.out.println("                                       topic: Topic-wise stats");
    }
}
    