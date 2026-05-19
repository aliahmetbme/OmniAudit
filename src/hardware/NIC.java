package hardware;

import tasks.SystemTaskVisitor;
public class NIC implements HardwareComponent{

    @Override
    public void accept(SystemTaskVisitor visitor) {
        visitor.visit(this);
    }
}