package os;

public class LinuxMetricsAdapter implements SystemMetricsProvider {

    @Override
    public void getSystemData() {
        // Simulates the uname() API
        System.out.println("Linux API: Executing uname() to get system information...");
    }

    @Override
    public void getMemoryUsage() {
        // Simulates reading from the /proc file system
        System.out.println("Linux API: Reading /proc/meminfo for memory usage...");
    }

    @Override
    public void getProcessUsage() {
        // Simulates reading from the /proc file system
        System.out.println("Linux API: Reading /proc/stat for process usage...");
    }
}