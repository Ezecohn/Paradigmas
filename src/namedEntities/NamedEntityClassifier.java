package namedEntities;

import org.json.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;

public class NamedEntityClassifier {
    private JSONArray dictionary;

    public NamedEntityClassifier(String dictionaryJson) {
        this.dictionary = new JSONArray(dictionaryJson);
    }Pattern pattern = Pattern.compile("\"([^\"]+)\"");

    public NamedEntity classify(String candidate) {
        for (int i = 0; i < dictionary.length(); i++) {
            JSONObject entry = dictionary.getJSONObject(i);
            JSONArray keywords = entry.getJSONArray("keywords");

            for (int j = 0; j < keywords.length(); j++) {
                if (keywords.getString(j).equalsIgnoreCase(candidate)) {
                    // Found a match, classify the candidate
                    Category category = Category.valueOf(entry.getString("Category").toUpperCase());
                    List<String> topics = jsonArrayToList(entry.getJSONArray("Topics"));
                    List<String> attributes = jsonArrayToList(keywords);

                    switch (category) {
                        case PERSON:
                            return new Person(candidate, category, topics, attributes);
                        case LOCATION:
                            return new Location(candidate, category, topics, attributes);
                        case ORGANIZATION:
                            return new Organization(candidate, category, topics, attributes);
                        case EVENT:
                            return new Event(candidate, category, topics, attributes);
                    }
                }
            }
        }

        return new Other(candidate, Category.OTHER, Collections.singletonList("OTHER"), Collections.singletonList("Other"));
    }

    private List<String> jsonArrayToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.getString(i));
        }
        return list;
    }
}