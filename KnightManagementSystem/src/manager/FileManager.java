package manager;

import model.Knight;
import Util.MyLogger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class FileManager {

    private static final Random RNG = new Random();
    private static final Path DATA_DIR = Path.of("data");

    static {
        try {
            ensureDataDirExists();
        } catch (IOException e) {
            MyLogger.logException(" Помилка створення папки data: ", e);
        }
    }

    private static void ensureDataDirExists() throws IOException {
        if (!Files.exists(DATA_DIR)) {
            Files.createDirectories(DATA_DIR);
            MyLogger.logInfo(" Папка data створена");
        }
    }

    public static String saveKnightsToRandomFile(List<Knight> knights) throws IOException {
        if (knights == null || knights.isEmpty()) {
            MyLogger.logSevere(" Критично: Спроба збереження пустого списку!");
            throw new IllegalArgumentException("Knights list cannot be null or empty");
        }

        try {
            int num = 1000 + RNG.nextInt(9000);
            String filename = String.format("knights_%d.dat", num);
            Path filePath = DATA_DIR.resolve(filename);

            saveKnightsToFile(knights, filePath.toString());
            MyLogger.logInfo(" Випадковий файл створений: " + filePath);
            return filePath.toString();
        } catch (Exception e) {
            MyLogger.logException(" Помилка при збереженні випадкового файлу: ", e);
            throw e;
        }
    }

    public static void saveKnightsToFile(List<Knight> knights, String path) throws IOException {
        if (knights == null) {
            MyLogger.logSevere("Критично: Knights list is null!");
            throw new IllegalArgumentException("Knights list cannot be null");
        }
        if (path == null || path.trim().isEmpty()) {
            MyLogger.logSevere("Критично: Path is null or empty!");
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        try {
            Path p = Path.of(path);

            if (p.getParent() != null && !Files.exists(p.getParent())) {
                Files.createDirectories(p.getParent());
                MyLogger.logInfo("Директорії створені: " + p.getParent());
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(p.toFile()))) {
                oos.writeObject(knights);
                MyLogger.logInfo("Збережено " + knights.size() + " лицарів у " + path);
            }
        } catch (IOException e) {
            MyLogger.logException("Критично: Помилка збереження файлу " + path + ": ", e);
            throw e;
        }
    }

    public static List<Knight> loadKnightsFromFile(String path) throws IOException, ClassNotFoundException {
        if (path == null || path.trim().isEmpty()) {
            MyLogger.logSevere("Критично: Path is null or empty!");
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        try {
            File f = new File(path);
            if (!f.exists()) {
                MyLogger.logSevere("Критично: Файл не знайден: " + path);
                throw new FileNotFoundException("File not found: " + path);
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                Object obj = ois.readObject();

                if (!(obj instanceof List)) {
                    MyLogger.logSevere("Критично: Невірний формат файлу!");
                    throw new IllegalArgumentException(
                            "Invalid file format: expected List, got " + obj.getClass().getName()
                    );
                }

                @SuppressWarnings("unchecked")
                List<Knight> knights = (List<Knight>) obj;
                MyLogger.logInfo(" Завантажено " + knights.size() + " лицарів з " + path);
                return knights;

            }
        } catch (IOException | ClassNotFoundException e) {
            MyLogger.logException("Критично: Помилка завантаження файлу " + path + ": ", e);
            throw e;
        }
    }

    public static boolean fileExists(String path) {
        return path != null && Files.exists(Path.of(path));
    }

    public static boolean deleteFile(String path) throws IOException {
        try {
            if (path == null || path.trim().isEmpty()) {
                MyLogger.logWarning("Спроба видалення файлу з пустим шляхом");
                return false;
            }
            boolean deleted = Files.deleteIfExists(Path.of(path));
            if (deleted) {
                MyLogger.logInfo(" Файл видалений: " + path);
            } else {
                MyLogger.logWarning(" Файл не знайдений для видалення: " + path);
            }
            return deleted;
        } catch (Exception e) {
            MyLogger.logException(" Помилка видалення файлу: ", e);
            throw e;
        }
    }
}