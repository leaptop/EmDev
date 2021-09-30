import java.util.stream.Stream;
import java.nio.file.*;
import java.io.IOException;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class Watcher {
    public static String getAstringFromTXT(String fileName) throws IOException {
        // String fileName = "C:\\Users\\stepa\\EmDev\\Task1_00\\src\\input.txt";
        // String fileName = "IN/input.txt";
        return Files.readString(Paths.get(fileName));
    }

    public static void main(String[] args) throws Exception {
        Class_0.writeTextToFile(Class_0.showANumberOfEachLetter(Class_0.getAstringFromTXT()));

        Path dirPath = Paths.get("IN");

        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
        }

        WatchService watchService = FileSystems.getDefault().newWatchService();
        dirPath.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

        for (; ; ) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (Throwable cause) {
                cause.printStackTrace();
                return;
            }
            for (WatchEvent<?> event : key.pollEvents()) {
                if (event.kind() == ENTRY_CREATE) {
                    Path filePath = ((WatchEvent<Path>) event).context();
                    System.out.println("New file '" + filePath + "! was discovered");
                    String str = Files.readString(Paths.get("IN\\" + String.valueOf(filePath)));
                    // Class_0.writeTextToFile(Class_0.showANumberOfEachLetter(str));
                    Files.writeString(Paths.get("out\\" + "processed_" + filePath + ".txt"), Class_0.showANumberOfEachLetter(str) +
                            "\n" + Class_0.showAnumberOfWordsInText(str));
                    long timed = System.currentTimeMillis();

                    try {
                        Stream<String> stream = Files.lines(filePath);
                        stream.forEach(System.out::println);
                        stream.close();

                    } catch (Throwable cause) {
                        System.out.println(cause.getMessage());
                    }

                    System.out.println("File '" + filePath + "' was successfully processed in " + (System.currentTimeMillis() - timed) + "ms");
                } else if (event.kind() == ENTRY_MODIFY) {
                    System.out.println("File '" + ((WatchEvent<Path>) event).context() + "! was modified");
                } else if (event.kind() == ENTRY_DELETE) {
                    System.out.println("File '" + ((WatchEvent<Path>) event).context() + "' was deleted");
                } else {
                    System.out.println("Unsupported event kind");
                }

            }

            if (!key.reset()) {
                break;
            }
        }
    }
}
