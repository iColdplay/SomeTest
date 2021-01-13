public class InterruptTest {

    public static void main(String[] args) {
        InterruptTest t1 = new InterruptTest();
        t1.test();

    }

    private void test(){
        SomeThread someThread = new SomeThread();
        someThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        someThread.interrupt();
    }

    class SomeThread extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println("isInterrupted: " + isInterrupted());
            while (true) {
                System.out.println("gonna do something..., isInterrupted: " + isInterrupted());
                try {
                    long temp = System.currentTimeMillis();
                    while (System.currentTimeMillis() - temp < 5000){
                        continue;
                    }
                    System.out.println("something finished, isInterrupted: " + isInterrupted());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("isInterrupted after caught the exception: " + isInterrupted());
                }
            }

        }
    }
}
