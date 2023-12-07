public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ProdCon pc = new ProdCon();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<100;i++)
                    pc.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<100;i++)

                    pc.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
