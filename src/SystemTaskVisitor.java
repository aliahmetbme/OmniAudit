
// ======================== FILE: tasks/SystemTaskVisitor.java ========================


/**
 * VISITOR PATTERN: Visitor Interface
 * Defines the contract for external analytical operations (Security, Optimization).
 * Enables the addition of new diagnostic algorithms without modifying the physical 
 * HardwareComponent classes. Works via the Double Dispatch mechanism.
 */
public interface SystemTaskVisitor {
// We open a separate visit method for each physical piece of hardware.
// Visitor needs to know how to visit each specific leaf node

    void visit(CPU cpu);
    void visit(Memory memory);
    void visit(NIC nic);
    void visit(Disk disk);
}
// ======================== FILE: tasks/SecurityAuditorVisitor.java ========================


class SecurityAuditorVisitor implements SystemTaskVisitor {

    /*
     * Why we did this: This is a concrete visitor representing a specific operation.
     * It encapsulates the security audit logic for each hardware element,
     * ensuring we don't pollute the hardware classes with analysis algorithms.
     */

    @Override
    public void visit(CPU cpu) {
        System.out.println("SecurityAuditor: Checking CPU for unpatched vulnerabilities...");
    }

    @Override
    public void visit(Memory memory) {
        System.out.println("SecurityAuditor: Scanning Memory for malicious injections...");
    }

    @Override
    public void visit(NIC nic) {
        // As requested in the project description:
        System.out.println("SecurityAuditor: Checking NIC for open ports...");
    }

    @Override
    public void visit(Disk disk) {
        System.out.println("SecurityAuditor: Scanning Disk for unencrypted sensitive files and malware...");
    }
}
// ======================== FILE: tasks/ResourceOptimizerVisitor.java ========================


class ResourceOptimizerVisitor implements SystemTaskVisitor {

    @Override
    public void visit(CPU cpu) {
        System.out.println("ResourceOptimizer: Analyzing CPU thread allocation and bottlenecks...");
    }

    @Override
    public void visit(Memory memory) {
        // As requested in the project description:
        System.out.println("ResourceOptimizer: Identifying processes hogging memory...");
    }

    @Override
    public void visit(NIC nic) {
        System.out.println("ResourceOptimizer: Analyzing network bandwidth usage...");
    }

    @Override
    public void visit(Disk disk) {
        System.out.println("ResourceOptimizer: Identifying processes hogging disk I/O and scanning for fragmentation...");
    }
}
// ======================== FILE: tasks/PerformanceAnalyzerVisitor.java ========================


class PerformanceAnalyzerVisitor implements SystemTaskVisitor {

    @Override
    public void visit(CPU cpu) {
        System.out.println("PerformanceAnalyzer: Measuring CPU clock speed, temperature, and cache efficiency...");
    }

    @Override
    public void visit(Memory memory) {
        System.out.println("PerformanceAnalyzer: Testing memory read/write latency and dual-channel throughput...");
    }

    @Override
    public void visit(NIC nic) {
        System.out.println("PerformanceAnalyzer: Running packet loss tests and estimating latency (ping)...");
    }

    @Override
    public void visit(Disk disk) {
        System.out.println("PerformanceAnalyzer: Measuring sequential and random Disk read/write speeds...");
    }
}
