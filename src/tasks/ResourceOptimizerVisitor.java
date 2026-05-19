package tasks;

import hardware.CPU;
import hardware.Memory;
import hardware.NIC;

public class ResourceOptimizerVisitor implements SystemTaskVisitor {

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
}