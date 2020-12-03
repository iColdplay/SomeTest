public class WhyThreadNotStart {

    public static void main(String[] args) {
        WhyThreadNotStart obj = new WhyThreadNotStart();
        obj.test();
    }

    private void test(){
        ReadThread readThread = new ReadThread();
        readThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after 10 seconds now close the origin readThread");

        readThread.control = false;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 3 seconds now try to reboot read thread");

        ReadThread thread = new ReadThread();
        thread.start();
    }

    private class ReadThread extends Thread{

        public volatile boolean control = true;

        @Override
        public void run() {
            super.run();
            System.out.println("read thread running");
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    control = false;
                    break;
                }
            }
            System.out.println("read thread stopped");
        }
    }
}

