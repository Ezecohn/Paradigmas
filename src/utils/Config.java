package utils;

public class Config {
    private boolean printFeed = false;
    private boolean computeNamedEntities = false;
    private String feedKey;
    private String statsFormat = "cat"; // Valor por defecto
    private String heuristicName = "Capital"; // Valor por defecto para heurística

    public Config(boolean printFeed, boolean computeNamedEntities, String feedKey) {
        this.printFeed = printFeed;
        this.computeNamedEntities = computeNamedEntities;
        this.feedKey = feedKey;
    }

    public boolean getPrintFeed() {
        return printFeed;
    }

    public boolean getComputeNamedEntities() {
        return computeNamedEntities;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public String getStatsFormat() {
        return statsFormat;
    }

    public void setStatsFormat(String statsFormat) {
        this.statsFormat = statsFormat;
    }

    public String getHeuristicName() {
        return heuristicName;
    }

    public void setHeuristicName(String heuristicName) {
        this.heuristicName = heuristicName;
    }
}
