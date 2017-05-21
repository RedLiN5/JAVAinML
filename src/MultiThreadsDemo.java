/**
 * Created by Leslie on 5/21/17.
 */

class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo( String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", i=" + i);
                Thread.sleep(500);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " + threadName + "interrupted.");
            System.out.println(e.toString());
        }
        System.out.println("Thread " + threadName + "exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread (this, threadName);
            t.start();
        }
    }
}


public class MultiThreadsDemo {

    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }
}
