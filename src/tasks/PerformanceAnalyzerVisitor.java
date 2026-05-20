package tasks;

import hardware.CPU;
import hardware.Memory;
import hardware.NIC;
import hardware.Disk;

public class PerformanceAnalyzerVisitor implements SystemTaskVisitor {

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
