package os;

public class LinuxMetricsAdapter implements SystemMetricsProvider {

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