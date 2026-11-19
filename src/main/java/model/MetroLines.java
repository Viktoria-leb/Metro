package model;

public record MetroLines(String name,
                         String number) {
    @Override
    public String toString() {
        return "Название линии " + name +
                " Номер линии " + number + "\n";
    }
}
