package feed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import namedEntities.NamedEntity;
import namedEntities.NamedEntityClassifier;
import namedEntities.heuristics.CapitalizedExcludeWordsHeuristic;
import namedEntities.heuristics.CapitalizedWordHeuristic;
import namedEntities.heuristics.CapitalizedExcludeWordsHeuristic;
import namedEntities.heuristics.QuotedPhraseHeuristic;
import namedEntities.heuristics.TwoToFiveInitialsHeuristic;

public class Article {
    // TODO
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

    public void applyHeuristicCapitalize() throws IOException {
        CapitalizedWordHeuristic heuristic = new CapitalizedWordHeuristic();
        NamedEntityClassifier classifier = new NamedEntityClassifier(
                Files.readString(Path.of("src/data/dictionary.json")));
    
        List<String> titleCandidates = heuristic.extractCandidates(this.title);
        List<String> descriptionCandidates = heuristic.extractCandidates(this.description);
    
        List<NamedEntity> titleEntities = new ArrayList<>();
        for (String candidate : titleCandidates) {
         //   System.out.println("Extracted word from title: " + candidate);  // Print the extracted word
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                titleEntities.add(entity);
            }
        }
        this.setTitleEntities(titleEntities);  // Moved this line here
    
        List<NamedEntity> descriptionEntities = new ArrayList<>();
        for (String candidate : descriptionCandidates) {
          //  System.out.println("Extracted word from description: " + candidate);  // Print the extracted word
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                descriptionEntities.add(entity);
            }
        }
        this.setDescriptionEntities(descriptionEntities);  // Moved this line here
    }

        public void applyHeuristicQuotedPhrase() throws IOException {
        QuotedPhraseHeuristic heuristic = new QuotedPhraseHeuristic();
        NamedEntityClassifier classifier = new NamedEntityClassifier(Files.readString(Path.of("src/data/dictionary.json")));
        List<String> titleCandidates = heuristic.extractCandidates(this.title);
        List<String> descriptionCandidates = heuristic.extractCandidates(this.description);
        List<NamedEntity> titleEntities = new ArrayList<>();
        List<NamedEntity> descriptionEntities = new ArrayList<>();

        for (String candidate : titleCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                titleEntities.add(entity);
            }
        }

        for (String candidate : descriptionCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                descriptionEntities.add(entity);
            }
        }

        this.setTitleEntities(titleEntities);
        this.setDescriptionEntities(descriptionEntities);
    }


    public void applyHeuristicCapitalizeExclude() throws IOException {
        CapitalizedExcludeWordsHeuristic heuristic = new CapitalizedExcludeWordsHeuristic();
        NamedEntityClassifier classifier = new NamedEntityClassifier(Files.readString(Path.of("src/data/dictionary.json")));
        List<String> titleCandidates = heuristic.extractCandidates(this.title);
        List<String> descriptionCandidates = heuristic.extractCandidates(this.description);
        List<NamedEntity> titleEntities = new ArrayList<>();
        List<NamedEntity> descriptionEntities = new ArrayList<>();

        for (String candidate : titleCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                titleEntities.add(entity);
            }
        }

        for (String candidate : descriptionCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                descriptionEntities.add(entity);
            }
        }

        this.setTitleEntities(titleEntities);
        this.setDescriptionEntities(descriptionEntities);
    }

    public void applyHeuristicTwoToFiveInitials() throws IOException {
        TwoToFiveInitialsHeuristic heuristic = new TwoToFiveInitialsHeuristic();
        NamedEntityClassifier classifier = new NamedEntityClassifier(Files.readString(Path.of("src/data/dictionary.json")));
        List<String> titleCandidates = heuristic.extractCandidates(this.title);
        List<String> descriptionCandidates = heuristic.extractCandidates(this.description);
        List<NamedEntity> titleEntities = new ArrayList<>();
        List<NamedEntity> descriptionEntities = new ArrayList<>();

        for (String candidate : titleCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                titleEntities.add(entity);
            }
        }

        for (String candidate : descriptionCandidates) {
            NamedEntity entity = classifier.classify(candidate);
            if (entity != null) {
                descriptionEntities.add(entity);
            }
        }

        this.setTitleEntities(titleEntities);
        this.setDescriptionEntities(descriptionEntities);
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