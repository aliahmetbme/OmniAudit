// ALİ AHMET ERDOĞDU - 20220601405
// MUHAMMED SAİT DOKUR - 20200601022
// BARIŞ CAN CEYLAN - 20190601053
// BATUHAN CAN - 20200601051
// OmniAudit Project - System Health and Audit Tool
// SE 311 - Software Engineering 

import java.util.Arrays;
import java.util.List;

// ======================== FILE: Client.java ========================


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
         * STEP 2: SCENARIO 1 - Local Windows Machine
         * We choose a local check. By default, it initializes the Windows adapter.
         */
        SystemCheckProcess checkLocalWindows = new LocalSystemCheck("Windows");
        checkLocalWindows.setRootNode(myComputer);
        SystemAdminFacade adminWindows = new SystemAdminFacade(checkLocalWindows);

        /*
         * STEP 3: SCENARIO 2 - Remote Linux Server
         * We choose a remote check. It establishes SSH and initializes the Linux adapter.
         */
        SystemCheckProcess checkRemoteLinux = new RemoteSystemCheck("Linux");
        checkRemoteLinux.setRootNode(myComputer);
        SystemAdminFacade adminLinux = new SystemAdminFacade(checkRemoteLinux);

        /*
         * STEP 4: SCENARIO 3 - Local macOS Workstation
         */
        SystemCheckProcess checkLocalMac = new LocalSystemCheck("macOS");
        checkLocalMac.setRootNode(myComputer);
        SystemAdminFacade adminMac = new SystemAdminFacade(checkLocalMac);

        /*
         * STEP 5: FIRE THE SYSTEM! (Testing all scenarios with different tasks)
         */

        System.out.println("\n========================================================");
        System.out.println(" SCENARIO 1: LOCAL WINDOWS MACHINE (Security Audit)     ");
        System.out.println("========================================================");
        adminWindows.executeAudit(Arrays.asList("Security"));

        System.out.println("\n========================================================");
        System.out.println(" SCENARIO 2: REMOTE LINUX SERVER (Optimization & Performance) ");
        System.out.println("========================================================");
        adminLinux.executeAudit(Arrays.asList("Optimization", "Performance"));

        System.out.println("\n========================================================");
        System.out.println(" SCENARIO 3: LOCAL macOS WORKSTATION (Comprehensive Audit)");
        System.out.println("========================================================");
        adminMac.executeAudit(Arrays.asList("Security", "Optimization", "Performance"));
        
        System.out.println("\n=== All system audits completed successfully! ===");
        
        System.out.println("\n\n========================================================");
        System.out.println(" STRESS TEST 1: Factory Pattern Rejection (Unknown OS)  ");
        System.out.println("========================================================");
        try {
            System.out.println("Attempting to audit a PlayStation5...");
            SystemCheckProcess checkPS5 = new LocalSystemCheck("PlayStation5");
        } catch (IllegalArgumentException e) {
            System.out.println("[FACTORY GUARD TRIGGERED] Expected Error Caught: " + e.getMessage());
        }

        System.out.println("\n========================================================");
        System.out.println(" STRESS TEST 2: MacroCommand Pattern (Deep Queue)       ");
        System.out.println("========================================================");
        adminWindows.executeAudit(Arrays.asList("Security", "Security", "Performance", "Security"));

        System.out.println("\n========================================================");
        System.out.println(" STRESS TEST 3: Composite & Visitor Pattern Synergy     ");
        System.out.println("========================================================");
        System.out.println("Hot-plugging 3 brand new CPUs into the existing Motherboard dynamically...");
        myMotherboard.addComponent(new CPU());
        myMotherboard.addComponent(new CPU());
        myMotherboard.addComponent(new CPU());
        
        System.out.println("Running Security Audit again. The Visitor should automatically find and scan the 3 new CPUs.");
        adminWindows.executeAudit(Arrays.asList("Security"));
        
        System.out.println("\n=== OmniAudit Shutdown Sequence Complete ===");
    }
}