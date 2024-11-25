package utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import namedEntities.Category;
import namedEntities.NamedEntity;
import feed.Article;

public class StatisticsCalculator {

    public static void computeAndPrintStatistics(List<Article> articles, String format) {
        if (format.equals("topic")) {
            computeAndPrintStatisticsByTopic(articles);
        } else {
            computeAndPrintStatisticsByCategory(articles);
        }
    }

    private static void computeAndPrintStatisticsByCategory(List<Article> articles) {
        Map<Category, Map<String, Integer>> categoryCounts = new HashMap<>();
        for (Article article : articles) {
            for (NamedEntity entity : article.getTitleEntities()) {
                categoryCounts.putIfAbsent(entity.getCategory(), new HashMap<>());
                categoryCounts.get(entity.getCategory()).put(entity.getName(), categoryCounts.get(entity.getCategory()).getOrDefault(entity.getName(), 0) + 1);
            }
            for (NamedEntity entity : article.getDescriptionEntities()) {
                categoryCounts.putIfAbsent(entity.getCategory(), new HashMap<>());
                categoryCounts.get(entity.getCategory()).put(entity.getName(), categoryCounts.get(entity.getCategory()).getOrDefault(entity.getName(), 0) + 1);
            }
        }
        System.out.println("Statistics by Category:");
        for (Map.Entry<Category, Map<String, Integer>> entry : categoryCounts.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (Map.Entry<String, Integer> countEntry : entry.getValue().entrySet()) {
                System.out.println(countEntry.getKey() + " (" + countEntry.getValue() + ")");
            }
            System.out.println();
        }
    }
    
    
    

    private static void computeAndPrintStatisticsByTopic(List<Article> articles) {
        Map<String, Map<String, Integer>> topicCounts = new HashMap<>();
        for (Article article : articles) {
            for (NamedEntity entity : article.getTitleEntities()) {
                for (String topic : entity.getTopics()) {
                    topicCounts.putIfAbsent(topic, new HashMap<>());
                    topicCounts.get(topic).put(entity.getName(), topicCounts.get(topic).getOrDefault(entity.getName(), 0) + 1);
                }
            }
            for (NamedEntity entity : article.getDescriptionEntities()) {
                for (String topic : entity.getTopics()) {
                    topicCounts.putIfAbsent(topic, new HashMap<>());
                    topicCounts.get(topic).put(entity.getName(), topicCounts.get(topic).getOrDefault(entity.getName(), 0) + 1);
                }
            }
        }
        System.out.println("Statistics by Topic:");
        for (Map.Entry<String, Map<String, Integer>> entry : topicCounts.entrySet()) {
            System.out.println("Topic: " + entry.getKey());
            for (Map.Entry<String, Integer> countEntry : entry.getValue().entrySet()) {
                System.out.println(countEntry.getKey() + " (" + countEntry.getValue() + ")");
            }
            System.out.println();
        }
    }
    
}
