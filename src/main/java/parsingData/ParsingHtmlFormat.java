package parsingData;

import model.MetroLines;
import model.StationMetro;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import workingWebPage.CreatingFileHTML;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ParsingHtmlFormat {
    public static Set<MetroLines> lineNamesSet = new HashSet<>();
    public static List<StationMetro> stationLine = new ArrayList<>();

    {
        try {
            parseHtmlFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseHtmlFile() throws IOException {
        new CreatingFileHTML();
        String path = CreatingFileHTML.getPathString();
        File file = new File(path);
        if (file.exists()) {
            Document doc = Jsoup.parse(file);
            Elements elements = doc.select(".js-depend");
            for (Element element1 : elements) {
                String lineNumber = element1.attr("data-depend-set")
                        .substring(6);
                String lineName = doc.getElementsByAttributeValue("data-line", lineNumber)
                        .getFirst().text();
                lineNamesSet.add(new MetroLines(lineName, lineNumber));
                for (Element element2 : element1.select(".single-station")) {
                    element1.select(".num").remove();
                    stationLine.add(new StationMetro
                            (new MetroLines(lineName, lineNumber), element2.text()));
                }
                for (Element element3 : element1.select(".single-station")) {
                    for (StationMetro stationMetro : stationLine) {
                        if (stationMetro.getName().equals(element3.text())) {
                            stationMetro.setConnection(Connection(element3));
                        }
                    }
                }
            }
        } else {
            parseHtmlFile();
        }
    }

    public static boolean Connection(Element el) {
        return el.select(".t-icon-metroln").hasAttr("title");
    }

    public static Set<MetroLines> getLineNamesSet() {
        return lineNamesSet;
    }

    public static List<StationMetro> getStationLine() {
        return stationLine;
    }
}

