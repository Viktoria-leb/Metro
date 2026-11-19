import model.StationDate;
import model.StationDepth;
import parsingData.ParsingCSVFormat;
import parsingData.ParsingHtmlFormat;
import parsingData.ParsingJsonFormat;

import stationSearchEngine.SearchOutput;
import toWriteFileJson.JsonWithStationAndLines;
import toWriteFileJson.JsonWithStationData;
import toWriteFileJson.WritingJson;
import workingWithArchive.ArchiveBypass;

import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() throws Exception {
        new ArchiveBypass();
        System.out.println("CSV files : " + ArchiveBypass.getCsvFile().size());
        System.out.println("Json files : " + ArchiveBypass.getJsonFile().size());

        new ParsingJsonFormat();
        System.out.println("___________Json________________");
        List<String> jsonData = ParsingJsonFormat.getJsonData();
        System.out.println(jsonData.toString());
        System.out.println(jsonData.size());
        List<StationDepth> stationDepths = ParsingJsonFormat.getStationDepths();
        System.out.println(stationDepths.size());
        System.out.println(stationDepths);


        new ParsingCSVFormat();
        System.out.println("______________________CSV______________");
        List<String> csvData = ParsingCSVFormat.getCsvData();
        System.out.println(csvData);
        System.out.println(csvData.size());
        List<StationDate> stationDates = ParsingCSVFormat.getStationDates();
        System.out.println(stationDates.size());
        System.out.println(stationDates);

        System.out.println("________________HTML____________________");
        new ParsingHtmlFormat();
        System.out.println(ParsingHtmlFormat.getStationLine());
        System.out.println(ParsingHtmlFormat.getStationLine().size());

        System.out.println("________________WritingJson ________________");
        System.out.println(JsonWithStationAndLines.getStationMetros());
        System.out.println(JsonWithStationData.getJsonStationData());

        WritingJson.writingJson();

        System.out.println("_________________Поиск слова_______________");
        while (true) {
            System.out.println("Введите данные ");
            String text = new Scanner(System.in).nextLine().trim();
            SearchOutput.Output(text);
        }
    }

}
