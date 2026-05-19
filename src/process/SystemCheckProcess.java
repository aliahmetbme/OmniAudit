package process;

import hardware.HardwareComponent;
import os.SystemMetricsProvider;
import tasks.SystemTaskVisitor;
import java.util.ArrayList;
import java.util.List;


public abstract class SystemCheckProcess {
    // Dependencies injected from other parts of our architecture
    protected SystemMetricsProvider metricsProvider;
    protected HardwareComponent rootNode;
    protected List<SystemTaskVisitor> tasks = new ArrayList<>();

    public void setRootNode(HardwareComponent rootNode) {
        this.rootNode = rootNode;
    }

    public HardwareComponent getRootNode() {
        return rootNode;
    }

    public void addTask(SystemTaskVisitor task) {
        this.tasks.add(task);
    }

    /*
     * TEMPLATE METHOD: The core skeleton of our algorithm.
     * Declared as 'final' so that no subclass can change the order of operations!
     */
    public final void runCheck() {
        setup();
        collectData();
        checkData();
        performAnalysis();
        generateReport();
    }

    // Step 1: Abstract setup (must be implemented by concrete subclasses like Local or Remote)
    protected abstract void setup();

    // Step 2: Data Collection (Uses the Adapter Pattern)
    protected void collectData() {
        System.out.println("\n--- Step 2: Data Collection ---");
        if (metricsProvider != null) {
            metricsProvider.getSystemData();
            metricsProvider.getMemoryUsage();
            metricsProvider.getProcessUsage();
        }
    }

    // Step 3: Data Check
    protected void checkData() {
        System.out.println("\n--- Step 3: Data Check ---");
        System.out.println("Checking if all collected data is valid and uncorrupted...");
    }

    // Step 4: Perform Analysis (Uses the Visitor and Composite Patterns)
    protected void performAnalysis() {
        System.out.println("\n--- Step 4: Perform Analysis ---");
        System.out.println("Hardware tree is ready for visitors. Delegation to MacroCommand will follow.");
    }

    // Step 5: Generate Report
    protected void generateReport() {
        System.out.println("\n--- Step 5: Generate Report ---");
        System.out.println("System audit report successfully generated and saved.\n");
    }
}