public class HelloJni {
    native void printHello();

    static {
        // System.load must enter to all path
        System.loadLibrary("hellojni");
    }
}
