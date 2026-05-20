package hardware;

import tasks.SystemTaskVisitor;

public class Disk implements HardwareComponent {

    @Override
    public void accept(SystemTaskVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("Disk: Providing hardware metrics...");
    }
}
