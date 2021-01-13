import java.util.concurrent.SynchronousQueue;

public class TestSyncQueue {

    private static SynchronousQueue<Integer> queue = new SynchronousQueue<>();

    public static void main(String[] args) {

        Thread waitingThread = new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("before take");
                try {
                    Thread.sleep(5000);
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after take");
            }
        };
        waitingThread.start();

        System.out.println("before put");
        try {
            queue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after put");
    }



}
