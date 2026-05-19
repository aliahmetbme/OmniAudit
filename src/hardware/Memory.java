package hardware;

import tasks.SystemTaskVisitor;

public class Memory implements HardwareComponent{

    @Override
    public void accept(SystemTaskVisitor visitor) {
        // The visitor accepts and passes itself to it
        visitor.visit(this);
    }
}