package manager;

import model.Knight;
import Util.MyLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class KnightManager {
    private final List<Knight> knights;

    public KnightManager() {
        this.knights = new ArrayList<>();
        MyLogger.logInfo(" KnightManager ініціалізований");
    }

    public List<Knight> getKnights() {
        return Collections.unmodifiableList(knights);
    }

    public void addKnight(Knight k) {
        if (k == null) {
            MyLogger.logSevere(" Критично: Спроба додати null лицаря!");
            return;
        }
        try {
            knights.add(k);
            MyLogger.logInfo("Лицар доданий: " + k.getName() + " (рівень: " + k.getLevel() + ")");
        } catch (Exception e) {
            MyLogger.logException(" Помилка при додаванні лицаря: ", e);
        }
    }

    public Optional<Knight> findByName(String name) {
        if (name == null || name.isEmpty()) {
            MyLogger.logWarning(" Пошук з пустим іменем");
            return Optional.empty();
        }
        try {
            var result = knights.stream()
                    .filter(k -> k.getName().equalsIgnoreCase(name))
                    .findFirst();
            if (result.isPresent()) {
                MyLogger.logInfo(" Лицар знайдений: " + name);
            } else {
                MyLogger.logWarning("⚠ Лицар не знайдений: " + name);
            }
            return result;
        } catch (Exception e) {
            MyLogger.logException(" Помилка пошуку лицаря: ", e);
            return Optional.empty();
        }
    }

    public Optional<Knight> getByIndex(int index) {
        try {
            if (index < 0 || index >= knights.size()) {
                MyLogger.logWarning(" Невірний індекс: " + index);
                return Optional.empty();
            }
            return Optional.of(knights.get(index));
        } catch (Exception e) {
            MyLogger.logException(" Помилка отримання лицаря по індексу: ", e);
            return Optional.empty();
        }
    }

    public void removeByIndex(int index) {
        try {
            if (index >= 0 && index < knights.size()) {
                String name = knights.get(index).getName();
                knights.remove(index);
                MyLogger.logInfo("Лицар видален: " + name);
            } else {
                MyLogger.logWarning(" Невірний індекс для видалення: " + index);
            }
        } catch (Exception e) {
            MyLogger.logException(" Помилка видалення лицаря: ", e);
        }
    }

    public int size() {
        return knights.size();
    }

    public void clear() {
        try {
            int count = knights.size();
            knights.clear();
            MyLogger.logInfo("Очищено " + count + " лицарів");
        } catch (Exception e) {
            MyLogger.logException(" Помилка очищення списку: ", e);
        }
    }
}