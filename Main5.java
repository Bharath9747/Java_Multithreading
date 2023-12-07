import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main5 {
    /*
    A task that returns a result and may throw an exception.
    Implementors define a single method with no arguments called call.
    The Callable interface is similar to Runnable,
        in that both are designed for classes whose instances are potentially executed by another thread.
        A Runnable, however, does not return a result and cannot throw a checked exception.

    The Executors class contains utility methods to convert from other common forms to Callable classes.

    Callable is similar to Runnable, in that it encapsulates a task that is meant to run on another thread,
    whereas a Future is used to store a result obtained from a different thread.
    In fact, the Future can be made to work with Runnable as well,
     which is something that will become clear when Executors come into the picture.


     public boolean cancel(boolean mayInterrupt): Used to stop the task.
        It stops the task if it has not started. If it has started, it interrupts the task only if mayInterrupt is true.
     public Object get() throws InterruptedException, ExecutionException: Used to get the result of the task.
        If the task is complete, it returns the result immediately,
        otherwise it waits till the task is complete and then returns the result.
     public boolean isDone(): Returns true if the task is complete and false otherwise
    */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++)
        {
            Callable callable = new CallableExample();

            randomNumberTasks[i] = new FutureTask(callable);

            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        for (int i = 0; i < 5; i++)

            System.out.println(randomNumberTasks[i].get());


    }
}
class CallableExample implements Callable
{
    public Object call() throws Exception
    {
        Random generator = new Random();
        Integer randomNumber = generator.nextInt(5);
        Thread.sleep(randomNumber * 1000);
        return randomNumber;
    }

}