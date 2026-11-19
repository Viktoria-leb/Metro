package parsingData;

import model.StationDate;
import workingWithArchive.ArchiveBypass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class ParsingCSVFormat implements ReadingFilesAndWritingData {
    private static List<String> csvData;

    {
        csvData = readAllLineOfFiles(ArchiveBypass.getCsvFile(), csvData);
    }

    @Override
    public List<String> readAllLineOfFiles(List<Path> pathList, List<String> listString) {
        listString = new ArrayList<>();
        pathList = ArchiveBypass.getCsvFile();
        for (Path path : pathList) {
            try {
                listString.add(Files.lines(path)
                        .skip(1).collect(Collectors.joining("\n")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listString;
    }

    public static List<String> getCsvData() {
        return csvData;
    }

    public static List<StationDate> getStationDates() {
        return deletingRepetitionsCSV(parseStationDate(getCsvData()));
    }

    private static List<StationDate> parseStationDate(List<String> csvString) {
        List<StationDate> stationDates = new ArrayList<>();
        for (String line : csvString) {
            String[] tokens = line.split("\n");
            for (String string : tokens) {
                String[] sr = string.split(",");
                if (sr.length != 2) {
                    System.out.println("Wrong line = " + line);
                } else {
                    stationDates.add(new StationDate(sr[0].replaceAll("ё", "е"),
                            sr[1]));
                }
            }
        }
        return stationDates;
    }

    private static List<StationDate> deletingRepetitionsCSV(List<StationDate> stationDates) {
        Map<String, String> station =
                stationDates.stream()
                        .distinct().
                        collect(Collectors.toMap(
                                StationDate::name,
                                StationDate::date,
                                (existing, replacement) -> existing + ", " + replacement
                        ));
        stationDates.clear();
        station.forEach((key, value) -> stationDates.add(new StationDate(key,
                value)));
        return stationDates;
    }
}

