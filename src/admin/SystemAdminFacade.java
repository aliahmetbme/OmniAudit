package admin;

import hardware.HardwareComponent;
import process.SystemCheckProcess;
import tasks.ResourceOptimizerVisitor;
import tasks.SecurityAuditorVisitor;
import tasks.SystemTaskVisitor;
import java.util.List;

public class SystemAdminFacade {

    final private SystemCheckProcess checkProcess;

    public SystemAdminFacade(SystemCheckProcess checkProcess) {

        this.checkProcess = checkProcess;

    }

    /*
     * FACADE METHOD: The single button for the administrator!
     * Hides all the complex subsystems (Adapter, Composite, Visitor, Template Method).
     */

    public void executeAudit(List<String> taskNames) {
        System.out.println("\n[FACADE] Administrator initiated the system audit...");

        // 1. Prepare our Macro Command queue
        MacroCommand macroQueue = new MacroCommand();
        HardwareComponent rootNode = checkProcess.getRootNode();

        for (String taskName : taskNames) {
            SystemTaskVisitor visitor = null;

            if (taskName.equalsIgnoreCase("Security")) {
                visitor = new SecurityAuditorVisitor();
            } else if (taskName.equalsIgnoreCase("Optimization")) {
                visitor = new ResourceOptimizerVisitor();
            }

            if (visitor != null) {
                // Wrap in a Command and add to Macro Queue (Satisfying the Command Pattern logic)
                macroQueue.addCommand(new TaskCommand(rootNode, visitor));
            }
        }

        System.out.println("[FACADE] Tasks are queued. Starting the Template Method framework...");

        // 2. Fire the 5 - step framework
        checkProcess.runCheck();

        System.out.println("\n[FACADE] Data is ready. Executing queued tasks via MacroCommand...");
        // 3. Execute queued Macro Commands
        macroQueue.execute();
    }
}