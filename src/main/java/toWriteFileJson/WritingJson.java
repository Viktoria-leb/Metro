package toWriteFileJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class WritingJson {
    private static final Map<String, Object> metro;
    private static final Map<String, Object> metro2;

    static {
        metro = JsonWithStationAndLines.getMetro();
        metro2 = JsonWithStationData.getMetro2();
    }

    public static void writingJson() throws Exception {
        entryInJson(metro,
                new File("src/main/resources/metro.json"));
        entryInJson(metro2,
                new File("src/main/resources/metro2.json"));
    }

    private static void entryInJson(Map<String, Object> m, File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().
                writeValue(file, m);
    }
}
