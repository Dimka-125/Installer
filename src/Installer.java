import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Installer {
    // StringBuilder для логирования
    private static final StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        // Путь к директории
        String gamesPath = "C://Games"; // Для Windows

        // Список директорий
        ArrayList<String> directories = new ArrayList<>();
        directories.add(gamesPath);
        directories.add(gamesPath + "/src");
        directories.add(gamesPath + "/src/main");
        directories.add(gamesPath + "/src/test");
        directories.add(gamesPath + "/res");
        directories.add(gamesPath + "/res/drawables");
        directories.add(gamesPath + "/res/vectors");
        directories.add(gamesPath + "/res/icons");
        directories.add(gamesPath + "/savegames");
        directories.add(gamesPath + "/temp");

        // Список файлов
        ArrayList<String> files = new ArrayList<>();
        files.add(gamesPath + "/src/main/Main.java");
        files.add(gamesPath + "/src/main/Utils.java");
        files.add(gamesPath + "/temp/temp.txt");

        // Создаем директории
        createDirectories(directories);

        // Создаем файлы
        createFiles(files);

        // Записываем лог в файл
        writeLogToFile(gamesPath + "/temp/temp.txt", log.toString());
    }


    // Метод для  директорий

    private static void createDirectories(ArrayList<String> directories) {
        for (String path : directories) {
            File dir = new File(path);
            if (!dir.exists()) {
                boolean created = dir.mkdir();
                if (created) {
                    log.append("Директория ").append(dir.getAbsolutePath()).append(" успешно создана.\n");
                } else {
                    log.append("Не удалось создать директорию ").append(dir.getAbsolutePath()).append(".\n");
                }
            } else {
                log.append("Директория ").append(dir.getAbsolutePath()).append(" уже существует.\n");
            }
        }
    }


    // Метод для  файлов

    private static void createFiles(ArrayList<String> files) {
        for (String path : files) {
            File file = new File(path);
            try {
                if (file.createNewFile()) {
                    log.append("Файл ").append(file.getAbsolutePath()).append(" успешно создан.\n");
                } else {
                    log.append("Не удалось создать файл ").append(file.getAbsolutePath()).append(".\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла ").append(file.getAbsolutePath()).append(": ").append(e.getMessage()).append(".\n");
            }
        }
    }


    //Метод для записи лога

    private static void writeLogToFile(String filePath, String logContent) {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(logContent);
            System.out.println("Лог успешно записан в файл " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога в файл: " + e.getMessage());
        }
    }
}