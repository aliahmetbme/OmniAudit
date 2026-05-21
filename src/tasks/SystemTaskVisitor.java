package tasks;

import hardware.CPU;
import hardware.Memory;
import hardware.NIC;
import hardware.Disk;

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