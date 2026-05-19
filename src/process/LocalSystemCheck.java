package process;

import os.MetricsAdapterFactory;

public class LocalSystemCheck extends SystemCheckProcess {

    @Override
    protected void setup() {
        System.out.println("\n--- Step 1: Setup (Local Machine) ---");
        System.out.println("Initializing connection to the local machine...");
        // Simulating a local Windows environment via our Factory
        this.metricsProvider = MetricsAdapterFactory.getAdapter("Windows");
    }
}