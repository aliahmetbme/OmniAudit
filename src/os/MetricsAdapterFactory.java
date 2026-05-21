package os;

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