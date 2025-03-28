import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Installer {
    public static void main(String[] args) {

        StringBuilder log = new StringBuilder();


        String gamesPath = "C://Games"; // Для Windows


        //  Директория Games
        File gamesDir = new File(gamesPath);
        if (!gamesDir.exists()) {
            boolean created = gamesDir.mkdir();
            if (created) {
                log.append("Директория ").append(gamesDir.getAbsolutePath()).append(" успешно создана.\n");
            } else {
                log.append("Не удалось создать директорию ").append(gamesDir.getAbsolutePath()).append(".\n");
            }
        } else {
            log.append("Директория ").append(gamesDir.getAbsolutePath()).append(" уже существует.\n");
        }

        // Папки в Games
        String[] subDirs = {"src", "res", "savegames", "temp"};
        for (String dirName : subDirs) {
            File subDir = new File(gamesDir, dirName);
            if (subDir.mkdir()) {
                log.append("Директория ").append(subDir.getAbsolutePath()).append(" успешно создана.\n");
            } else {
                log.append("Не удалось создать директорию ").append(subDir.getAbsolutePath()).append(".\n");
            }
        }

        // Папки в src: main, test
        File srcDir = new File(gamesDir, "src");
        String[] srcSubDirs = {"main", "test"};
        for (String dirName : srcSubDirs) {
            File subDir = new File(srcDir, dirName);
            if (subDir.mkdir()) {
                log.append("Директория ").append(subDir.getAbsolutePath()).append(" успешно создана.\n");
            } else {
                log.append("Не удалось создать директорию ").append(subDir.getAbsolutePath()).append(".\n");
            }
        }

        // Создаем файлы в src/main
        File mainDir = new File(srcDir, "main");
        String[] mainFiles = {"Main.java", "Utils.java"};
        for (String fileName : mainFiles) {
            File file = new File(mainDir, fileName);
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

        // Папки в res: drawables, vectors, icons
        File resDir = new File(gamesDir, "res");
        String[] resSubDirs = {"drawables", "vectors", "icons"};
        for (String dirName : resSubDirs) {
            File subDir = new File(resDir, dirName);
            if (subDir.mkdir()) {
                log.append("Директория ").append(subDir.getAbsolutePath()).append(" успешно создана.\n");
            } else {
                log.append("Не удалось создать директорию ").append(subDir.getAbsolutePath()).append(".\n");
            }
        }

        // Файл temp.txt в директории temp
        File tempDir = new File(gamesDir, "temp");
        File tempFile = new File(tempDir, "temp.txt");
        try {
            if (tempFile.createNewFile()) {
                log.append("Файл ").append(tempFile.getAbsolutePath()).append(" успешно создан.\n");
            } else {
                log.append("Не удалось создать файл ").append(tempFile.getAbsolutePath()).append(".\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(tempFile.getAbsolutePath()).append(": ").append(e.getMessage()).append(".\n");
        }

        // Логирование в файл temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в файл " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Ошибка при записи лога в файл: " + e.getMessage());
        }
    }
}