package admin;

import hardware.HardwareComponent;
import tasks.SystemTaskVisitor;

public class TaskCommand implements Command {

    final private SystemTaskVisitor visitor;
    final private HardwareComponent rootNode;


    public TaskCommand(HardwareComponent rootNode, SystemTaskVisitor visitor) {
        this.rootNode = rootNode;
        this.visitor = visitor;
    }


    @Override
    public void execute() {
        // The command triggers the Visitor to traverse the hardware tree
        rootNode.accept(visitor);
    }
}