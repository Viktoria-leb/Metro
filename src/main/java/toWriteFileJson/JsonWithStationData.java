package toWriteFileJson;

import model.*;
import parsingData.ParsingCSVFormat;
import parsingData.ParsingHtmlFormat;
import parsingData.ParsingJsonFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonWithStationData {
    private static final Map<String, Object> metro2;
    private static final List<StationMetro> stationMetros;
    private static final List<StationDate> stationDates;
    private static List<StationDepth> stationDepths;
    private static final List<JsonStationData> jsonStationData;

    static {
        metro2 = new HashMap<>();
        jsonStationData = new ArrayList<>();
        stationMetros = ParsingHtmlFormat.getStationLine();
        stationDates = ParsingCSVFormat.getStationDates();
        try {
            stationDepths = ParsingJsonFormat.getStationDepths();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> getMetro2() {
        return completionMap();
    }

    private static Map<String, Object> completionMap() {
        metro2.put("stations", getJsonStationData());
        return metro2;
    }

    public static List<JsonStationData> getJsonStationData() {
        theDataSet();
        addingDateAndDepth();
        return jsonStationData;
    }

    private static void theDataSet() {
        Map<String, String> nameStationNameLine =
                stationMetros.stream().collect(Collectors.toMap(
                        StationMetro::getName,
                        StationMetro::getLine,
                        (existing, replacement) ->
                                new MetroLines(existing.name()
                                        + ", " + replacement.name(),
                                        existing.number()
                                                + "," + replacement.number())

                )).entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        z -> z.getValue().name(),
                        (existing, replacement)
                                -> existing + ", " + replacement
                ));
        nameStationNameLine.forEach((key, value) -> jsonStationData
                .add(new JsonStationData(key, value,
                        null, null, false)));
    }

    private static void addingDateAndDepth() {
        for (JsonStationData json : jsonStationData) {
            String name = json.getName().replaceAll("ё", "е").trim();
            for (StationDate datest : stationDates) {
                if (datest.name().equals(name)) {
                    json.setDate(datest.date());
                }
            }
            for (StationDepth devastation : stationDepths) {
                if (devastation.name().equals(name)) {
                    if (devastation.depth() != 0.0) {
                        json.setDepth(devastation.depth());
                    }
                }
            }
            for (StationMetro station : stationMetros) {
                if (station.getName().equals(json.getName())) {
                    json.setHasConnection(station.isConnection());
                }
            }
        }
    }
}
