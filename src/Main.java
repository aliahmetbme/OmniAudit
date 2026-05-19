import admin.SystemAdminFacade;
import hardware.CPU;
import hardware.Computer;
import hardware.ISABus;
import hardware.Memory;
import hardware.Motherboard;
import hardware.NIC;
import process.LocalSystemCheck;
import process.SystemCheckProcess;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== OmniAudit System Health & Audit Tool Initializing ===\n");

        /*
         * STEP 1: Build the Hardware Tree (COMPOSITE PATTERN)
         * We are creating the physical structure: Computer -> Motherboard -> (CPU, Memory, ISABus -> NIC)
         */
        System.out.println("[MAIN] Building the hardware composite tree...");
        Computer myComputer = new Computer();
        Motherboard myMotherboard = new Motherboard();
        ISABus myIsaBus = new ISABus();

        CPU myCpu = new CPU();
        Memory myMemory = new Memory();
        NIC myNic = new NIC();

        // Assembling the parts
        myIsaBus.addComponent(myNic);
        myMotherboard.addComponent(myCpu);
        myMotherboard.addComponent(myMemory);
        myMotherboard.addComponent(myIsaBus);
        myComputer.addComponent(myMotherboard); // Root node is ready!


        /*
         * STEP 2: Choose the System Check Process (TEMPLATE METHOD PATTERN)
         * We choose a local check. If we wanted remote, we would just say "new RemoteSystemCheck()".
         */
        SystemCheckProcess checkProcess = new LocalSystemCheck();
        checkProcess.setRootNode(myComputer);


        /*
         * STEP 3: Give the controls to the Administrator (FACADE PATTERN)
         * The admin doesn't need to know how the tree was built or how the template works.
         */
        SystemAdminFacade adminPanel = new SystemAdminFacade(checkProcess);


        /*
         * STEP 4: Define the tasks (COMMAND & VISITOR PATTERNS)
         * The admin simply creates a list of strings for the desired audits.
         */
        List<String> desiredTasks = Arrays.asList("Security", "Optimization");


        /*
         * STEP 5: FIRE THE SYSTEM!
         * One single method call triggers the entire pipeline.
         */
        adminPanel.executeAudit(desiredTasks);
    }
}