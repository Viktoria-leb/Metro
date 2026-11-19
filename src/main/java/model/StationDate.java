package model;

import java.util.Objects;

public record StationDate(String name,

                          String date) {
    public String toString() {
        return name + " "
                + date + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StationDate(String name1, String date1))) return false;
        return Objects.equals(name, name1) && Objects.equals(date, date1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date);
    }
}

