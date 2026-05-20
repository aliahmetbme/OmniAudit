package process;

import os.MetricsAdapterFactory;

public class LocalSystemCheck extends SystemCheckProcess {

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