package admin;

import java.util.ArrayList;
import java.util.List;

public class MacroCommand implements Command {
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