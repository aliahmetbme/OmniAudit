import java.util.List;

// ======================== FILE: admin/SystemAdminFacade.java ========================


/**
 * FACADE PATTERN: Subsystem Orchestrator
 * Simplifies the execution interface for the client.
 * Hides the complex instantiation of the hardware composite tree, 
 * OS metric adapters, and task queuing (Command Pattern) behind a unified boundary.
 */
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
            } else if (taskName.equalsIgnoreCase("Performance")) {
                visitor = new PerformanceAnalyzerVisitor();
            }

            if (visitor != null) {
                // Wrap in a Command and add to Macro Queue (Satisfying the Command Pattern logic)
                macroQueue.addCommand(new TaskCommand(rootNode, visitor));
            }
        }

        // Configure the analysis step to use our MacroCommand
        checkProcess.setAnalysisCommand(macroQueue);

        System.out.println("[FACADE] Tasks are queued and configured on the check process.");
        System.out.println("[FACADE] Starting the Template Method framework...");

        // 2. Fire the 5-step framework
        checkProcess.runCheck();

        System.out.println("[FACADE] System audit pipeline completed successfully!");
    }
}