import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class DirectoryUtils {

    private String dirName;

    public static void createDirectory(String dirName) throws IOException {
        Files.createDirectory(Path.of(dirName));
    }

    public static void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    System.out.println(path);
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        System.out.println(path);
        Files.delete(path);
    }


    // Fazer método para listar os diretórios
    public static void listDirectory() {

    }

}