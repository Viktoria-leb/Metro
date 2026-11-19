package model;

public record StationDepth(String name, Double depth) {


    @Override
    public String toString() {
        return name + ": " +
                depth;
    }

}
