package parsingData;

import model.StationDepth;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import workingWithArchive.ArchiveBypass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ParsingJsonFormat implements ReadingFilesAndWritingData {
    private static List<String> jsonData;

    {
        jsonData = readAllLineOfFiles(ArchiveBypass.getJsonFile(), jsonData);
    }

    @Override
    public List<String> readAllLineOfFiles(List<Path> pathList,
                                           List<String> listString) {
        listString = new ArrayList<>();
        pathList = ArchiveBypass.getJsonFile();
        for (Path path : pathList) {
            try {
                listString.add(Files.readString(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listString;
    }

    public static List<StationDepth> getStationDepths() throws Exception {
        return deletingRepetitions(parseStationDepths(getJsonData()));
    }

    public static List<String> getJsonData() {
        return jsonData;
    }

    private static List<StationDepth> parseStationDepths(List<String> jsonString) throws Exception {
        List<StationDepth> stationDepths = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        for (String string : jsonString) {
            JSONArray jsonData = (JSONArray) jsonParser.parse(string);
            for (Object depthInfo : jsonData) {
                JSONObject stationDepth = (JSONObject) depthInfo;
                String name = (String) stationDepth.get("station_name");
                String name2 = name.replaceAll("ё", "e");
                String depth = (String) stationDepth.get("depth");
                String depth1 = depth.replace(",", ".");
                String depth2 = depth1.replace("?", "-0.0");
                stationDepths.add(new StationDepth(name2, Double.parseDouble(depth2)));
            }
        }
        return stationDepths;
    }

    private static List<StationDepth> deletingRepetitions(List<StationDepth> stationDepths) {
        Set<StationDepth> nnn = new HashSet<>(stationDepths);
        stationDepths.clear();
        stationDepths.addAll(nnn);
        for (int i = 0; i < stationDepths.size(); i++) {
            double d = stationDepths.get(i).depth();
            String name = stationDepths.get(i).name();
            stationDepths.removeIf(e -> name.equals(e.name())
                    && !name.equals("Арбатская")
                    && !name.equals("Смоленская")
                    && d > e.depth());
        }
        return stationDepths;

    }
}


