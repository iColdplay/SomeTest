import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    int index = 0;

    class Worker implements Runnable{

        CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await(); // 等待其它线程
                System.out.println(Thread.currentThread().getName() + "启动@" + System.currentTimeMillis());
                for (int i = 0; i < 10000000; i++) {
                    index++;
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void doTest() throws InterruptedException {
        final int N = 5; // 线程数
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for(int i=0;i<N;i++){
            new Thread(new Worker(cyclicBarrier)).start();
        }

        Thread.sleep(5000);
        System.out.println(index);
    }

    public static void main(String[] args) throws InterruptedException {
        TestCyclicBarrier testCyclicBarrier = new TestCyclicBarrier();
        testCyclicBarrier.doTest();
    }
}
