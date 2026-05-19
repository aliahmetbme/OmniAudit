package admin;

public interface Command {
    /*
     * Why we did this: This interface turns a request/action into a stand-alone object.
     * It allows us to parameterize methods with different requests, queue them, and execute them.
     */

    void execute();

}