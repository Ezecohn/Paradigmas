package namedEntities.heuristics;

import java.text.Normalizer;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.*;

public class CapitalizedExcludeWordsHeuristic {

    public List<String> extractCandidates(String text) throws IOException {
        List<String> candidates = new ArrayList<>();

        JSONObject obj = new JSONObject(Files.readString(Path.of("src/data/dictexclude.json")));
        JSONArray dict = obj.getJSONArray("label");

        List<String> list = new ArrayList<>();
        for (int i = 0; i < dict.length(); i++) {
            list.add(dict.getString(i));
        }

        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", "");

        Pattern pattern = Pattern.compile("\\b[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*\\b");
                                        
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String match = matcher.group();
            if (!list.contains(match)) {
                candidates.add(match);
            }
        }
        return candidates;
    }
}
