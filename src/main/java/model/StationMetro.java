package model;

public class StationMetro {
    private final MetroLines line;
    private final String name;
    private boolean connection;

    public StationMetro(MetroLines line, String name) {
        this.line = line;
        this.name = name;
        this.connection = false;
    }

    public MetroLines getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return line.toString() +
                " имя станции " + name +
                " Переход " + connection + "\n";
    }
}
