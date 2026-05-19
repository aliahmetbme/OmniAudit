package process;

import os.MetricsAdapterFactory;

public class RemoteSystemCheck extends SystemCheckProcess {

    @Override
    protected void setup() {
        System.out.println("\n--- Step 1: Setup (Remote Server) ---");
        System.out.println("Establishing secure SSH connection to the remote server...");
        // Simulating a remote Linux environment via our Factory
        this.metricsProvider = MetricsAdapterFactory.getAdapter("Linux");
    }
}