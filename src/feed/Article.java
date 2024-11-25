package feed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.NamedEntityClassifier;
import namedEntities.heuristics.Heuristic;

public class Article {
    
    private String title;
    private String description;
    private String pubDate;
    private String link;
    private List<NamedEntity> titleEntities;
    private List<NamedEntity> descriptionEntities;

    public Article(String title, String description, String pubDate, String link) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
    }

    public void print() {
        System.out.println("Title:" + this.title);
        System.out.println("Description:" + this.description);
        System.out.println("Link:" + this.link);
        System.out.println("PubDate:" + this.pubDate);
        System.out.println("*".repeat(80));
    }

    public void applyHeuristic(Heuristic heuristic) throws IOException {
        NamedEntityClassifier classifier = new NamedEntityClassifier(
                Files.readString(Path.of("src/data/dictionary.json")));
    
        List<String> titleCandidates = heuristic.extractCandidates(this.title);
        List<String> descriptionCandidates = heuristic.extractCandidates(this.description);
    
        List<NamedEntity> titleEntities = classifyEntities(titleCandidates, classifier);
        List<NamedEntity> descriptionEntities = classifyEntities(descriptionCandidates, classifier);
    
        this.setTitleEntities(titleEntities);
        this.setDescriptionEntities(descriptionEntities);
    }

    private List<NamedEntity> classifyEntities(List<String> candidates, NamedEntityClassifier classifier) {
        List<NamedEntity> entities = new ArrayList<>();
        for (String candidate : candidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescrip() {
        return this.description;
    }

    public String getPubDate() {
        return this.pubDate;
    }

    public String getLink() {
        return this.link;
    }

    public void setTitleEntities(List<NamedEntity> titleEntities) {
        this.titleEntities = titleEntities;
    }

    public void setDescriptionEntities(List<NamedEntity> descriptionEntities) {
        this.descriptionEntities = descriptionEntities;
    }

    public List<NamedEntity> getTitleEntities() {
        return this.titleEntities;
    }

    public List<NamedEntity> getDescriptionEntities() {
        return this.descriptionEntities;
    }
}