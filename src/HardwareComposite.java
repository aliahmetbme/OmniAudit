// ALİ AHMET ERDOĞDU - 20220601405
// MUHAMMED SAİT DOKUR - 20200601022
// BARIŞ CAN CEYLAN - 20190601053
// BATUHAN CAN - 20200601051
// OmniAudit Project - System Health and Audit Tool
// SE 311 - Software Engineering 

import java.util.ArrayList;
import java.util.List;

// ======================== FILE: hardware/HardwareComposite.java ========================


/**
 * COMPOSITE PATTERN: Composite Node
 * Represents the container elements in the hardware tree (e.g., Motherboard, Computer).
 * Implements the "Safety over Transparency" design principle by keeping addComponent 
 * and removeComponent methods strictly within this class, rather than the base interface.
 */
public abstract class HardwareComposite implements HardwareComponent {

    // Holds the child components (Leaf or other Composites)
    protected List<HardwareComponent> children = new ArrayList<>();

    public void addComponent(HardwareComponent component) {
        children.add(component);
    }

    public void removeComponent(HardwareComponent component) {
        children.remove(component);
    }

    @Override
    public void accept(SystemTaskVisitor visitor) {
        // Iterate through all children and pass the visitor to them
        for (HardwareComponent child : children) {
            child.accept(visitor);
        }
    }

    @Override
    public void getMetrics() {
        for (HardwareComponent child : children) {
            child.getMetrics();
        }
    }
}
// ======================== FILE: hardware/HardwareComponent.java ========================


/**
 * Why we did this:
 * This is the "Common Contract" at the very top of our Composite structure
 * In the future, massive groups like Computer or Motherboard, as well as a single CPU, will derive from this interface.
 * This way, the Visitor will be able to enter by calling the accept method, no matter where it is in the tree.
 */
interface HardwareComponent {
    void accept(SystemTaskVisitor visitor);
    void getMetrics();
}


// ======================== FILE: hardware/Computer.java ========================

class Computer extends HardwareComposite {
    // Inherits addComponent, removeComponent, and accept logic from HardwareComposite
}
// ======================== FILE: hardware/Motherboard.java ========================

class Motherboard extends HardwareComposite {
    // Inherits addComponent, removeComponent, and accept logic from HardwareComposite
}
// ======================== FILE: hardware/ISABus.java ========================

class ISABus extends HardwareComposite {
    // Inherits addComponent, removeComponent, and accept logic from HardwareComposite
}
// ======================== FILE: hardware/CPU.java ========================


class CPU implements HardwareComponent {
    // this is the leaf node of the hardware tree, it cannot have any leaf or component
    @Override
    public void accept(SystemTaskVisitor visitor) {
        // The visitor accepts and passes itself to it
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("CPU: Providing hardware metrics...");
    }
}
// ======================== FILE: hardware/Memory.java ========================


class Memory implements HardwareComponent{

    @Override
    public void accept(SystemTaskVisitor visitor) {
        // The visitor accepts and passes itself to it
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("Memory: Providing hardware metrics...");
    }
}
// ======================== FILE: hardware/NIC.java ========================

class NIC implements HardwareComponent{

    @Override
    public void accept(SystemTaskVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("NIC: Providing hardware metrics...");
    }
}
// ======================== FILE: hardware/Disk.java ========================


class Disk implements HardwareComponent {

    @Override
    public void accept(SystemTaskVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("Disk: Providing hardware metrics...");
    }
}
