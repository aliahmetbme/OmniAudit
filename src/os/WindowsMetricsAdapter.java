package os;

public class WindowsMetricsAdapter implements SystemMetricsProvider {

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