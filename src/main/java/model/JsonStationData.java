package model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class JsonStationData {
    private final String name;
    private final String line;
    private String date;
    private Double depth;
    private boolean hasConnection;


    public JsonStationData(String name, String line, String date, Double depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public String getDate() {
        return date;
    }

    public Double getDepth() {
        return depth;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        if (date == null && depth == null) {
            return "name " + name + "\n" +
                    "line " + line + "\n" +
                    "hasConnection " + hasConnection + "\n";
        } else if (date != null && depth == null) {
            return "name " + name + "\n" +
                    "line " + line + "\n" +
                    "date " + date + "\n" +
                    "hasConnection " + hasConnection + "\n";
        } else if (date == null && depth != null) {
            return "name " + name + "\n" +
                    "line " + line + "\n" +
                    "depth " + depth + "\n" +
                    "hasConnection " + hasConnection + "\n";
        }
        return "name " + name + "\n" +
                "line " + line + "\n" +
                "date " + date + "\n" +
                "depth " + depth + "\n" +
                "hasConnection " + hasConnection + "\n";
    }
}

