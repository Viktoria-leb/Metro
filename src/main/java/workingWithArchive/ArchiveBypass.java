package workingWithArchive;

import net.lingala.zip4j.ZipFile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ArchiveBypass {
    private static final List<Path> csvFile;
    private static final List<Path> jsonFile;

    static {
        csvFile = new ArrayList<>();
        jsonFile = new ArrayList<>();
    }

    {
        try {
            walkFileTree(unzipFolder(new ZipFile("src/main/resources/stations-data.zip")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void walkFileTree(Path path) throws IOException {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".csv")) {
                    csvFile.add(file);
                } else if (file.toString().endsWith(".json")) {
                    jsonFile.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static Path unzipFolder(ZipFile zipFile) {
        Path path = Paths.get("src/main/resources/Stations-data");
        try {
            Files.createDirectories(path);
            zipFile.extractAll(path.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static List<Path> getCsvFile() {
        return csvFile;
    }

    public static List<Path> getJsonFile() {
        return jsonFile;
    }
}