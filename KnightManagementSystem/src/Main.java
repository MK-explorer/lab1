import command.*;
import manager.KnightManager;
import menu.Menu;
import Util.MyLogger;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = MyLogger.getLogger();
        Scanner scanner = new Scanner(System.in);
        KnightManager manager = new KnightManager();

        logger.info("Запуск програми");
        logger.info("Knight Management System запущена");
        logger.info("Logger інціалізований");

        Menu menu = new Menu(scanner);
// В Main.java додай це для тестування:
      //  try {
            // Навмисна помилка для тесту
         //   int test = 10 / 0;
       // } catch (Exception e) {
       //     MyLogger.logException("Критична помилка: ", e);
      //  }
        try {
            // register commands
            menu.register(1, new CreateKnightCommand(manager, scanner));
            menu.register(2, new ListKnightsCommand(manager));
            menu.register(3, new SelectKnightCommand(manager, scanner));
            menu.register(4, new ViewKnightStatsCommand(manager, scanner));
            menu.register(5, new AddItemCommand(manager, scanner));
            menu.register(6, new ManageEquipmentCommand(manager, scanner));
            menu.register(7, new SortByWeightCommand(manager, scanner));
            menu.register(8, new FindByPriceRangeCommand(manager, scanner));
            menu.register(9, new ManageHorseCommand(manager, scanner));
            menu.register(10, new ManageSquireCommand(manager, scanner));
            menu.register(11, new CalculateTotalValueCommand(manager, scanner));
            menu.register(12, new CalculateTotalWeightCommand(manager, scanner));
            menu.register(13, new SaveCommand(manager, scanner));
            menu.register(14, new LoadCommand(manager, scanner));
            menu.register(15, new ExitCommand());

            logger.info("Усі команди зареєстровані");

            System.out.println("Welcome to Knight Management System");
            menu.start();

        } catch (Exception e) {
            MyLogger.logException(" критична помилка в Main: ", e);
            logger.severe("Збій програми!");
        } finally {
            scanner.close();
            logger.info("Закінчення програми");
        }
    }
}