package command;

import manager.KnightManager;

public class SelectEquipmentTypeCommand implements Command {
    private final KnightManager manager;

    public SelectEquipmentTypeCommand(KnightManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        System.out.println("[SelectEquipmentTypeCommand] Selecting equipment type... (stub)");
    }
}
