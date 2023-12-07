import java.util.concurrent.*;

public class Main4 {
    /*
    As opposed to the cached thread pool, this one is using an unbounded queue with a fixed number of never-expiring threads.
    Therefore, instead of an ever-increasing number of threads,
    the fixed thread pool tries to execute incoming tasks with a fixed amount of threads.
    When all threads are busy, then the executor will queue new tasks.
    This way, we have more control over our program’s resource consumption.

    As a result, fixed thread pools are better suited for tasks with unpredictable execution times

    Cached thread pools will continue to create more and more threads in extreme circumstances,
    so, practically, they will never reach a saturation point.
    Similarly, fixed thread pools will continue to add more and more tasks in their queue.
    Therefore, the fixed pools also will never reach a saturation point.

    As both pools won’t be saturated, when the load is exceptionally high,
        they will consume a lot of memory for creating threads or queuing tasks.
    Adding insult to the injury, cached thread pools will also incur a lot of processor context switches.

    Definition :
    The newFixedThreadPool() method of Executors class creates a thread pool
        that reuses a fixed number of threads operating off a shared unbounded queue.
    At any point, at most n Threads will be active processing tasks.
    If additional tasks are submitted when all threads are active, they will wait in the queue until a thread is available.
    */
    public static void main(String[] args) {
        ThreadFactory ThreadFactory = Executors.defaultThreadFactory();
        ExecutorService excr = Executors.newFixedThreadPool(5 , ThreadFactory);
        ThreadPoolExecutor mypool = (ThreadPoolExecutor) excr;
        excr.submit(new Threadimpl());
        System.out.println("size of pool: " + mypool.getPoolSize());
        excr.shutdown();
    }
    static class Threadimpl implements Runnable {
        public void run() {
            try {
                Long num = (long) (Math.random() * 30);
                System.out.println("Thread Name: " +Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(num);
                System.out.println("after sleep Thread Name: " +Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
