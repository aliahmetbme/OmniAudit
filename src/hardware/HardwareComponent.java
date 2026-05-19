package hardware;

import tasks.SystemTaskVisitor;

/**
 * Why we did this:
 * This is the "Common Contract" at the very top of our Composite structure
 * In the future, massive groups like Computer or Motherboard, as well as a single CPU, will derive from this interface.
 * This way, the Visitor will be able to enter by calling the accept method, no matter where it is in the tree.
 */
public interface HardwareComponent {
    /*
     * Why we did this: This is the "Common Contract" at the top of our Composite structure.
     * In the future, both massive groups like Computer or Motherboard, and a single CPU
     * will inherit from this interface. Thus, no matter where the Visitor is in the tree,
     * it can enter by calling the accept method.
     */
    void accept(SystemTaskVisitor visitor);
}

