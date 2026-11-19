package stationSearchEngine;

import model.StationMetro;
import parsingData.ParsingHtmlFormat;

import java.util.*;


public class SearchOutput {
    private static final List<StationMetro> stationMetros;

    static {
        stationMetros = ParsingHtmlFormat.getStationLine();
    }

    public static void Output(String a) {
        if (inputSlovak(a)) {
            for (StationMetro stationMetro : stationMetros) {
                if (stationMetro.getName().equals(a)) {
                    inputStation(a);
                    break;
                } else if (stationMetro.getLine().number().equals(a)) {
                    searchLine("number", a);
                    break;
                } else if (stationMetro.getLine().name().equals(a)) {
                    searchLine("name", a);
                    break;
                }
            }
        } else System.out.println("Не верный ввод.");
    }

    private static void inputStation(String a) {
        String nameStation = "";
        StringBuilder numberLine = new StringBuilder();
        for (StationMetro stationMetro : stationMetros) {
            if (stationMetro.getName().equals(a)) {
                nameStation = stationMetro.getName();
                numberLine.append(stationMetro.getLine().number()).append(", ");
            }
        }
        numberLine.setLength(numberLine.length() - 2);
        System.out.println("Название станция: " + nameStation + ", "
                + "Номер Линии: " + numberLine);
    }


    private static void searchLine(String whatSearch, String input) {
        String lineName = "";
        String numberLine = "";
        StringBuilder listStation = new StringBuilder();
        for (StationMetro stationMetro : stationMetros) {
            if (stationMetro.getLine().name().equals(input) ||
                    stationMetro.getLine().number().equals(input)) {
                lineName = stationMetro.getLine().name();
                numberLine = stationMetro.getLine().number();
                listStation.append(stationMetro.getName()).append(", ");
            }
        }
        listStation.setLength(listStation.length() - 2);
        if (whatSearch.equals("number")) {
            System.out.println("Номер Линии: " + numberLine + ", " +
                    "Название линии: " + lineName + ", " +
                    "Список станций: " + listStation);
        } else {
            System.out.println("Название линии: " + lineName + ", " +
                    "Номер линии: " + numberLine + ", " +
                    " Список станций: " + listStation);
        }
    }

    private static boolean inputSlovak(String a) {
        for (StationMetro stationMetro : stationMetros) {
            if (stationMetro.getName().equals(a) ||
                    stationMetro.getLine().number().equals(a) ||
                    stationMetro.getLine().name().equals(a)) {
                return true;
            }
        }
        return false;
    }
}


