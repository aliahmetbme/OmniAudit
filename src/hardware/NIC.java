package hardware;

import tasks.SystemTaskVisitor;
public class NIC implements HardwareComponent{

    @Override
    public void accept(SystemTaskVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void getMetrics() {
        System.out.println("NIC: Providing hardware metrics...");
    }
}