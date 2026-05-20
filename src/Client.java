import admin.SystemAdminFacade;
import hardware.CPU;
import hardware.Computer;
import hardware.ISABus;
import hardware.Memory;
import hardware.Motherboard;
import hardware.NIC;
import hardware.Disk;
import process.LocalSystemCheck;
import process.SystemCheckProcess;
import process.RemoteSystemCheck;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        System.out.println("=== OmniAudit System Health & Audit Tool Initializing ===\n");

        /*
         * STEP 1: Build the Hardware Tree (COMPOSITE PATTERN)
         * We are creating the physical structure: Computer -> Motherboard -> (CPUs, Disks, Memory, ISABus -> NICs)
         */
        System.out.println("[MAIN] Building the hardware composite tree...");
        Computer myComputer = new Computer();
        Motherboard myMotherboard = new Motherboard();
        ISABus myIsaBus = new ISABus();

        // Plural nodes as described in the requirements: "Motherboard, CPUs, Disks, Memory and Network Interfaces"
        CPU myCpu1 = new CPU();
        CPU myCpu2 = new CPU();
        Memory myMemory = new Memory();
        Disk myDisk1 = new Disk();
        Disk myDisk2 = new Disk();
        NIC myNic = new NIC();

        // Assembling the parts
        myIsaBus.addComponent(myNic);
        
        myMotherboard.addComponent(myCpu1);
        myMotherboard.addComponent(myCpu2);
        myMotherboard.addComponent(myMemory);
        myMotherboard.addComponent(myDisk1);
        myMotherboard.addComponent(myDisk2);
        myMotherboard.addComponent(myIsaBus);
        
        myComputer.addComponent(myMotherboard); // Root node is ready!


        /*
         * STEP 2: Choose the System Check Process (TEMPLATE METHOD PATTERN)
         * We choose a local check. If we wanted remote, we would just say "new RemoteSystemCheck()".
         */
        SystemCheckProcess checkProcessForLocalMac = new LocalSystemCheck("macOS");
        checkProcessForLocalMac.setRootNode(myComputer);


        SystemCheckProcess checkProcess = new LocalSystemCheck();
        checkProcess.setRootNode(myComputer);

        SystemCheckProcess checkRemoteProcess = new RemoteSystemCheck();
        checkRemoteProcess.setRootNode(myComputer);

        /*
         * STEP 3: Give the controls to the Administrator (FACADE PATTERN)
         * The admin doesn't need to know how the tree was built or how the template works.
         */
        SystemAdminFacade adminPanel = new SystemAdminFacade(checkProcess);
        SystemAdminFacade adminPanelRemote = new SystemAdminFacade(checkRemoteProcess);
        SystemAdminFacade systemAdminLocalMac = new SystemAdminFacade(checkProcessForLocalMac);

        /*
         * STEP 4: Define the tasks (COMMAND & VISITOR PATTERNS)
         * The admin simply creates a list of strings for the desired audits.
         * Including our creative "Performance" task!s
         */
        List<String> desiredTasks = Arrays.asList("Security");
        
        List<String> desiredTasksRemote = Arrays.asList("Security", "Optimization", "Performance");

        /*
         * STEP 5: FIRE THE SYSTEM!
         * One single method call triggers the entire pipeline.
         */

        System.out.println("\n=== Executing Local System Check ===");
        adminPanel.executeAudit(desiredTasks);
        System.out.println("\n=== Executing Local System Check (Linux) ===");
        System.out.println("\n-----------------------------------");
        adminPanelRemote.executeAudit(desiredTasksRemote);
        System.out.println("\n=== Executing Local System Check (Mac) ===");
        System.out.println("\n-----------------------------------");
        systemAdminLocalMac.executeAudit(desiredTasks);
    }
}