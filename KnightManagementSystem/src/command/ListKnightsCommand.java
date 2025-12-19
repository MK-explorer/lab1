package command;

import manager.KnightManager;

import java.util.List;
import model.Knight;

public class ListKnightsCommand implements Command {
    private final KnightManager manager;

    public ListKnightsCommand(KnightManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        List<Knight> list = manager.getKnights();
        if (list.isEmpty()) {
            System.out.println("Поки що немає створених лицарів.");
            return;
        }
        System.out.println(" Лицарі ");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, list.get(i));
        }
    }
}
