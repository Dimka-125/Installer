import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
    // Метод для сохранения объекта GameProgress в файл
    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress); // Сериализация объекта
            System.out.println("Файл сохранения успешно создан: " + filePath);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    // Метод для архивации файлов сохранений
    public static void zipFiles(String zipFilePath, List<String> filesToZip) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            for (String filePath : filesToZip) {
                try (FileInputStream fis = new FileInputStream(filePath)) {
                    ZipEntry entry = new ZipEntry(new File(filePath).getName());
                    zos.putNextEntry(entry); // Добавление новой записи в архив

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length); // Запись данных в архив
                    }

                    zos.closeEntry(); // Закрытие текущей записи
                    System.out.println("Файл добавлен в архив: " + filePath);
                } catch (IOException e) {
                    System.out.println("Ошибка при добавлении файла в архив: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Путь к папке savegames (замените на ваш путь)
        String saveGamesPath = "C://Games/savegames"; // Для Windows
        // String saveGamesPath = "/Users/admin/Games/savegames"; // Для macOS/Linux

        // Создаем три экземпляра класса GameProgress
        GameProgress game1 = new GameProgress(100, 5, 1, 1000.0);
        GameProgress game2 = new GameProgress(80, 3, 2, 2000.0);
        GameProgress game3 = new GameProgress(60, 2, 3, 3000.0);

        // Сохраняем объекты в файлы
        String save1 = saveGamesPath + "/save1.dat";
        String save2 = saveGamesPath + "/save2.dat";
        String save3 = saveGamesPath + "/save3.dat";

        saveGame(save1, game1);
        saveGame(save2, game2);
        saveGame(save3, game3);

        // Создаем список файлов для архивации
        List<String> filesToZip = new ArrayList<>();
        filesToZip.add(save1);
        filesToZip.add(save2);
        filesToZip.add(save3);

        // Путь к архиву
        String zipFilePath = saveGamesPath + "/saves.zip";

        // Архивируем файлы
        zipFiles(zipFilePath, filesToZip);

        // Удаляем файлы сохранений, которые не лежат в архиве
        for (String filePath : filesToZip) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл удален: " + filePath);
            } else {
                System.out.println("Не удалось удалить файл: " + filePath);
            }
        }
    }
}
