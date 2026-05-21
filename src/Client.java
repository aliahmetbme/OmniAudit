// ALİ AHMET ERDOĞDU
// MUHAMMED SAİT DOKUR
// BARIŞ CAN CEYLAN
// BATUHAN CAN
// OmniAudit Project - System Health and Audit Tool
// SE 311 - Software Engineering 

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
    }
}