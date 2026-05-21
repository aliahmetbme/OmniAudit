import java.util.ArrayList;
import java.util.List;

// ======================== FILE: admin/Command.java ========================

public interface Command {
    /*
     * Why we did this: This interface turns a request/action into a stand-alone object.
     * It allows us to parameterize methods with different requests, queue them, and execute them.
     */

    void execute();

}
// ======================== FILE: admin/MacroCommand.java ========================


class MacroCommand implements Command {
    final private List<Command> commands = new ArrayList<>();

    public void addCommand(Command c) {
        commands.add(c);
    }

    public void removeCommand(Command c) {
        commands.remove(c);
    }

    @Override
    public void execute() {
        // Iterates over all queued commands and executes them in order
        for (Command c : commands) {
            c.execute();
        }
    }


}
// ======================== FILE: admin/TaskCommand.java ========================


class TaskCommand implements Command {

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