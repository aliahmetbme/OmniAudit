package os;

public class WindowsMetricsAdapter implements SystemMetricsProvider {

    @Override
    public void getSystemData() {
        // Simulates the Windows GetSystemInfo API
        System.out.println("Windows API: Calling GetSystemInfo()...");
    }

    @Override
    public void getMemoryUsage() {
        // Simulates the Windows GlobalMemoryStatusEx API
        System.out.println("Windows API: Calling GlobalMemoryStatusEx()...");
    }

    @Override
    public void getProcessUsage() {
        // Simulates the Windows GetProcessTimes API
        System.out.println("Windows API: Calling GetProcessTimes()...");
    }
}