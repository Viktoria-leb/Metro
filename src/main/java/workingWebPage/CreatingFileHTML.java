package workingWebPage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreatingFileHTML {
    private final static String url = "https://skillbox-java.github.io/";
    private static final String pathString
            = "src/main/resources/listOfMetroLinesAndStations.html";

    {
        try {
            writingFile(readingWebPage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Document readingWebPage() throws IOException {
        return Jsoup.connect(url).get();
    }

    private static void writingFile(Document document) {
        Path pathSr = Paths.get(CreatingFileHTML.pathString);
        try (PrintWriter writer = new PrintWriter(new File(pathSr.toUri()), "UTF-8")) {
            writer.println(document);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String getPathString() {
        return pathString;
    }
}
