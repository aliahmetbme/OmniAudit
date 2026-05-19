package tasks;

import hardware.CPU;
import hardware.Memory;
import hardware.NIC;

public class SecurityAuditorVisitor implements SystemTaskVisitor {

    /*
     * Why we did this: This is a concrete visitor representing a specific operation.
     * It encapsulates the security audit logic for each hardware element,
     * ensuring we don't pollute the hardware classes with analysis algorithms.
     */

    @Override
    public void visit(CPU cpu) {
        System.out.println("SecurityAuditor: Checking CPU for unpatched vulnerabilities...");
    }

    @Override
    public void visit(Memory memory) {
        System.out.println("SecurityAuditor: Scanning Memory for malicious injections...");
    }

    @Override
    public void visit(NIC nic) {
        // As requested in the project description:
        System.out.println("SecurityAuditor: Checking NIC for open ports...");
    }
}