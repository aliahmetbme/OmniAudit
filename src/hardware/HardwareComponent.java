package hardware;

import tasks.SystemTaskVisitor;

/**
 * Why we did this:
 * This is the "Common Contract" at the very top of our Composite structure
 * In the future, massive groups like Computer or Motherboard, as well as a single CPU, will derive from this interface.
 * This way, the Visitor will be able to enter by calling the accept method, no matter where it is in the tree.
 */
public interface HardwareComponent {
    void accept(SystemTaskVisitor visitor);
    void getMetrics();
}

