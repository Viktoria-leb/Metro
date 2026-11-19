package parsingData;

import java.nio.file.Path;
import java.util.List;

public interface ReadingFilesAndWritingData {
    List<String> readAllLineOfFiles(List<Path> pathList, List<String> listString);

}
