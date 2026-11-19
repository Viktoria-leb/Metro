package toWriteFileJson;

import model.MetroLines;
import model.StationMetro;
import parsingData.ParsingHtmlFormat;

import java.util.*;
import java.util.stream.Collectors;

public class JsonWithStationAndLines {

    private static final Map<String, Object> metro;
    private static final List<StationMetro> stationMetros;
    private static final Set<MetroLines> lines;

    static {
        stationMetros = ParsingHtmlFormat.getStationLine();
        lines = ParsingHtmlFormat.getLineNamesSet();
        metro = new HashMap<>();
    }

    public static Map<String, Object> getMetro() {
        return completionMap();
    }

    public static List<StationMetro> getStationMetros() {
        return stationMetros;
    }

    private static Map<String, Object> completionMap() {
        metro.put("lines", lines);
        metro.put("stations", numberLineAndStations());
        return metro;
    }

    private static Map<String, List<String>> numberLineAndStations() {
        return stationMetros.stream().collect(Collectors.groupingBy(StationMetro::getLine))
                .entrySet().stream().collect(Collectors.toMap(
                        n -> n.getKey().number(),
                        z -> {
                            List<String> listNameStation = new ArrayList<>();
                            z.getValue().
                                    forEach(n -> listNameStation
                                            .add(n.getName()));
                            return listNameStation;
                        })
                );
    }
}
