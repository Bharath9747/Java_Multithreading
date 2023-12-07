import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main2 {

/*
    The BlockingQueue interface is part of the java.util.concurrent package.

    If a prod thread tries to put an element in a full BlockingQueue,
        it gets blocked and stays blocked until a cons removes an element.
    Similarly, if a cons thread tries to take an element from an empty BlockingQueue,
     it gets blocked and remains blocked until a prod adds an element.

     four Methods : put() and take() add(E e) and remove()
     Interrupted Exception
     put(E e) : element to be added
     E take() : head of the queue.


    ArrayBlockingQueue uses the array data structure as a buffer.
        Since it is an array, its capacity is fixed after declaration.
        It provides fairness as an option.
        This means threads are given access to the buffer on a first-come, first-serve basis.
        Fairness is off by default. It can be turned on by placing the boolean value true inside the constructor.

        Poison element: This element signals the end of Production-Consumption activity,
            in the below example, 4 is the poison element.

     */
    public static void main(String[] args)
    {
        BlockingQueue<Integer> que
                = new ArrayBlockingQueue<>(4);
        prod p1 = new prod(que);
        cons c1 = new cons(que);
        Thread pThread = new Thread(p1);
        Thread cThread = new Thread(c1);
        pThread.start();
        cThread.start();
    }

}

class prod implements Runnable {

    BlockingQueue<Integer> obj;

    public prod(BlockingQueue<Integer> obj)
    {
        this.obj = obj;
    }

    @Override public void run()
    {
        for (int i = 1; i <= 4; i++) {
            try {
                obj.put(i);
                System.out.println("Produced " + i);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class cons implements Runnable {

    BlockingQueue<Integer> obj;

    int taken = -1;

    public cons(BlockingQueue<Integer> obj)
    {
        this.obj = obj;
    }

    @Override public void run()
    {
        while (taken != 4) {
            try {
                taken = obj.take();
                System.out.println("Consumed " + taken);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

