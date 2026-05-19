package hardware;

import tasks.SystemTaskVisitor;

public class CPU implements HardwareComponent {
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