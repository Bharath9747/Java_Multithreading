public class Main1 {
//    The join() method makes a calling Thread enters into waiting for the state
//    until the Thread on which join() is called completes its execution.

//join(long millis)
//join(long millis,int nanos)

//    If two Threads call the join() method on each other
//    then both threads enter into waiting for state and wait for each other forever.

//    A waiting Thread can be interrupted by another Thread using the interrupt() method.
//    Once the waiting Thread is interrupted, it immediately comes out of the waiting state and moves into Ready/Runnable state and
//    waits for the Thread Scheduler to allocate the processor to begin the execution again.
    public static void main(String[] args) throws InterruptedException {
        ChildThread th1 = new ChildThread();
        th1.start();
//        th1.join();
//        th1.join(1500);
//        th1.join(1000,10000);
        th1.join();
        System.out.println("main thread completed");
    }
}
class ChildThread extends Thread
{
    public void run()
    {
        for(int i=1; i<=4; i++)
        {

            try{
                Thread.sleep(500);
            } catch(Exception e){
                System.out.println(e);
            }
            System.out.println("child thread execution - " + i);
        }
    }
}
