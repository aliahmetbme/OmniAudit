package os;

public class MacOSMetricsAdapter implements SystemMetricsProvider {

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