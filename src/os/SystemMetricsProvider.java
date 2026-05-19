package os;

public interface SystemMetricsProvider {
    /*
     * This is the unified interface expected by our core logic.
     * The core system does not care about the underlying OS APIs.
     */
    void getSystemData();
    void getMemoryUsage();
    void getProcessUsage();
}