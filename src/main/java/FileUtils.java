import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {
    // Variáveis de escopo Global
    private String nome;
    private List<String> lista;
    private String message;

    // Diretórios Padrões para o LOG e o Arquivo de Config
    private String defaultLog = "log-";
    private String defaultMessage = ("Program Started at:");
    private ArrayList<String> foldersList = new ArrayList<String>();

    public void createTxt(List<String> lista) throws IOException {
        TimeUtils.setNowDate();
        nome = defaultLog + TimeUtils.time;
        Path path = Paths.get(nome + ".txt");
        Files.write(path, lista, StandardCharsets.UTF_8);
    }

    public void createTxt() throws IOException {
        TimeUtils.setNowDate();
        nome = defaultLog + TimeUtils.time;
        Path path = Paths.get(nome + ".txt");
        Files.write(path, Collections.singleton(defaultMessage + TimeUtils.getActionTime()), StandardCharsets.UTF_8);
    }

    public void createFile(String nome, String type) throws IOException {
        try {
            Path path = Paths.get(nome + type);
            Files.createFile(path);
        } catch (FileAlreadyExistsException e){
            System.out.println(nome + " File" + " Already Exists.");
        }
    }

    public void readTxt(String nomeArquivo) throws IOException {
        Path path = Paths.get(nomeArquivo + ".txt");
        String dados = String.valueOf(Files.readAllLines(path));
        System.out.println(dados);
    }

    public void writeFile(String message) throws IOException {
        try {
            Files.write(Paths.get(defaultLog + TimeUtils.time + ".txt"),
                    ("\n" + message + TimeUtils.getActionTime()).getBytes(), StandardOpenOption.APPEND);
        }catch (
        IOException e) {
            Files.write(Paths.get(defaultLog + TimeUtils.time + ".txt"),
                    ("There was an error opening the file" + TimeUtils.getActionTime()).getBytes(), StandardOpenOption.APPEND);
        }
    }
}