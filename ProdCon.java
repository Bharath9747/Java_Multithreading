
public class ProdCon {
    int tot =3,pt=0;
    public void produce() throws InterruptedException {

            synchronized (this){
                if (tot==pt)
                    wait();
                System.out.println("Producer produced...");
                pt++;
                notify();
                Thread.sleep(1000);

            }

    }

    public void consume() throws InterruptedException {
            synchronized (this){
                if (pt==0)
                    wait();
                System.out.println("Consumer Consumed...");
                pt--;
                notify();
                Thread.sleep(1000);
            }

    }
}
