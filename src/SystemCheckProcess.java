import java.util.ArrayList;
import java.util.List;

// ======================== FILE: process/SystemCheckProcess.java ========================



/**
 * TEMPLATE METHOD PATTERN: Abstract Class
 * Provides the rigid, invariant 5-step skeleton for the auditing process.
 * The core algorithm (runCheck) is declared final to prevent subclass interference,
 * while allowing environment-specific initialization via the abstract setup() hook.
 * Follows the Hollywood Principle (Inversion of Control).
 */
public abstract class SystemCheckProcess {
    // Dependencies injected from other parts of our architecture
    protected String osType;
    protected SystemMetricsProvider metricsProvider;
    protected HardwareComponent rootNode;
    protected List<SystemTaskVisitor> tasks = new ArrayList<>();
    private Command analysisCommand;

    // Parameterized constructor to dynamically define OS type and initialize its adapter
    public SystemCheckProcess(String osType) {
        this.osType = osType;
        this.metricsProvider = MetricsAdapterFactory.getAdapter(osType);
    }

    public void setRootNode(HardwareComponent rootNode) {
        this.rootNode = rootNode;
    }

    public HardwareComponent getRootNode() {
        return rootNode;
    }

    public void addTask(SystemTaskVisitor task) {
        this.tasks.add(task);
    }

    public void setAnalysisCommand(Command analysisCommand) {
        this.analysisCommand = analysisCommand;
    }

    public Command getAnalysisCommand() {
        return this.analysisCommand;
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
        if (analysisCommand != null) {
            System.out.println("Executing configured analyses via Command Pattern...");
            analysisCommand.execute();
        } else {
            System.out.println("No analyses configured.");
        }
    }

    // Step 5: Generate Report
    protected void generateReport() {
        System.out.println("\n--- Step 5: Generate Report ---");
        System.out.println("System audit report successfully generated and saved.\n");
    }
}
// ======================== FILE: process/LocalSystemCheck.java ========================


class LocalSystemCheck extends SystemCheckProcess {

    // Default constructor for backward compatibility
    public LocalSystemCheck() {
        super("Windows");
    }

    // Parameterized constructor to dynamically define OS type
    public LocalSystemCheck(String osType) {
        super(osType);
    }

    @Override
    protected void setup() {
        System.out.println("\n--- Step 1: Setup (Local Machine) ---");
        System.out.println("Initializing connection to the local machine...");
    }
}
// ======================== FILE: process/RemoteSystemCheck.java ========================


class RemoteSystemCheck extends SystemCheckProcess {

    // Default constructor for backward compatibility
    public RemoteSystemCheck() {
        super("Linux");
    }

    // Parameterized constructor to dynamically define OS type
    public RemoteSystemCheck(String osType) {
        super(osType);
    }

    @Override
    protected void setup() {
        System.out.println("\n--- Step 1: Setup (Remote Server) ---");
        System.out.println("Establishing secure SSH connection to the remote server...");
    }
}