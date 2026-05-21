package hardware;

import tasks.SystemTaskVisitor;
import java.util.ArrayList;
import java.util.List;

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