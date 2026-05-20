package process;

import os.MetricsAdapterFactory;

public class RemoteSystemCheck extends SystemCheckProcess {

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