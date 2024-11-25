package feed;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FeedParser {

    public static List<Article> parseXML(String xmlData) {
        List<Article> articles = new ArrayList<>();
    
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    
            // Convertir la cadena de texto a InputStream
            InputStream stream = new ByteArrayInputStream(xmlData.getBytes(StandardCharsets.UTF_8));
    
            Document doc = dBuilder.parse(stream);
            doc.getDocumentElement().normalize();
    
            NodeList nList = doc.getElementsByTagName("item");
    
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
    
                Node titleNode = eElement.getElementsByTagName("title").item(0);
                String title = titleNode != null ? titleNode.getTextContent() : "";

                Node descriptionNode = eElement.getElementsByTagName("description").item(0);
                String description = descriptionNode != null ? descriptionNode.getTextContent() : "";

    
                Node pubDateNode = eElement.getElementsByTagName("pubDate").item(0);
                String pubDate = pubDateNode != null ? pubDateNode.getTextContent() : "";
    
                Node linkNode = eElement.getElementsByTagName("link").item(0);
                String link = linkNode != null ? linkNode.getTextContent() : "";
    
                Article article = new Article(title, description, pubDate, link);
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return articles;
    }

    public static String fetchFeed(String feedURL) throws MalformedURLException, IOException, Exception {

        URL url = new URL(feedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");


        connection.setRequestProperty("User-agent", "Eze");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new Exception("HTTP error code: " + status);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            return content.toString();
        }
    }
}
