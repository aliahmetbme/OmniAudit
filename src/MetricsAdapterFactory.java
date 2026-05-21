// ALİ AHMET ERDOĞDU - 20220601405
// MUHAMMED SAİT DOKUR - 20200601022
// BARIŞ CAN CEYLAN - 20190601053
// BATUHAN CAN - 20200601051
// OmniAudit Project - System Health and Audit Tool
// SE 311 - Software Engineering 

// ======================== FILE: os/MetricsAdapterFactory.java ========================

/**
 * SIMPLE FACTORY PATTERN: Creational Utility
 * Dynamically resolves and instantiates the correct OS adapter at runtime.
 * Ensures the core auditing engine remains entirely OS-Agnostic by injecting 
 * the appropriate SystemMetricsProvider based on the execution context.
 */
public class MetricsAdapterFactory {

    /*
     * Why we did this: This factory method ensures that the client code
     * does not need to know which specific adapter class to instantiate.
     * It creates the correct OS adapter dynamically at runtime.
     */
    public static SystemMetricsProvider getAdapter(String osType) {
        if (osType == null) {
            return null;
        }

        if (osType.equalsIgnoreCase("Windows")) {
            return new WindowsMetricsAdapter();
        } else if (osType.equalsIgnoreCase("Linux")) {
            return new LinuxMetricsAdapter();
        } else if (osType.equalsIgnoreCase("macOS")) {
            return new MacOSMetricsAdapter();
        }

        throw new IllegalArgumentException("Unsupported Operating System: " + osType);
    }
}
// ======================== FILE: os/SystemMetricsProvider.java ========================

interface SystemMetricsProvider {
    /*
     * This is the unified interface expected by our core logic.
     * The core system does not care about the underlying OS APIs.
     */
    void getSystemData();
    void getMemoryUsage();
    void getProcessUsage();
}
// ======================== FILE: os/WindowsMetricsAdapter.java ========================

class WindowsMetricsAdapter implements SystemMetricsProvider {

    @Override
    public void getSystemData() {
        GetSystemInfo();
    }

    @Override
    public void getMemoryUsage() {
        GlobalMemoryStatusEx();
    }

    @Override
    public void getProcessUsage() {
        GetProcessTimes();
    }

    private void GetSystemInfo() {
        System.out.println("Windows API: Calling GetSystemInfo()...");
    }

    private void GlobalMemoryStatusEx() {
        System.out.println("Windows API: Calling GlobalMemoryStatusEx()...");
    }

    private void GetProcessTimes() {
        System.out.println("Windows API: Calling GetProcessTimes()...");
    }
}
// ======================== FILE: os/LinuxMetricsAdapter.java ========================

class LinuxMetricsAdapter implements SystemMetricsProvider {

    @Override
    public void getSystemData() {
        uname();
    }

    @Override
    public void getMemoryUsage() {
        readProcMeminfo();
    }

    @Override
    public void getProcessUsage() {
        readProcStat();
    }

    private void uname() {
        System.out.println("Linux API: Executing uname() to get system information...");
    }

    private void readProcMeminfo() {
        System.out.println("Linux API: Reading /proc/meminfo for memory usage...");
    }

    private void readProcStat() {
        System.out.println("Linux API: Reading /proc/stat for process usage...");
    }
}
// ======================== FILE: os/MacOSMetricsAdapter.java ========================

class MacOSMetricsAdapter implements SystemMetricsProvider {

    @Override
    public void getSystemData() {
        System.out.println("macOS API: Getting system data...");
    }

    @Override
    public void getMemoryUsage() {
        System.out.println("macOS API: Getting memory usage...");
    }

    @Override
    public void getProcessUsage() {
        System.out.println("macOS API: Getting process usage...");
    }
}