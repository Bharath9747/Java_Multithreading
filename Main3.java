import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main3 {
    /*The newCachedThreadPool() method of Executors class creates a thread pool that creates new threads as needed
    but will reuse previously constructed threads when they are available.

     The cached thread pool configuration caches the threads (hence the name) for a short amount of time to reuse them for other tasks.
     As a result, it works best when we’re dealing with a reasonable number of short-lived tasks.
    Therefore, we should avoid this thread pool when the execution time is unpredictable, like IO-bound tasks.

    */

    public static void main(String[] args) {
        ExecutorService excr = Executors.newCachedThreadPool();
        ThreadPoolExecutor mypool = (ThreadPoolExecutor) excr;
        excr.submit(new Threadimpl());
        excr.submit(new Threadimpl());
        System.out.println("Total number threads scheduled : "+ mypool.getTaskCount());
        System.out.println("size of poll : " + mypool.getPoolSize());

        excr.shutdown();
    }
}
 class Threadimpl implements Runnable {

    public void run() {

        try {
            Long num = (long) (Math.random() / 30);
            System.out.println("Thread Name: " +Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(num);
            System.out.println("after sleep Thread Name: " +Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}