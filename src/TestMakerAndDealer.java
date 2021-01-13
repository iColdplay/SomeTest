import java.util.concurrent.LinkedBlockingDeque;

public class TestMakerAndDealer {

    static LinkedBlockingDeque<String> linkedQueue = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        Thread m1 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    linkedQueue.offer("message from 1");
                }
            }
        };
        m1.start();


        Thread m2 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    linkedQueue.offer("message from 2");
                }
            }
        };
        m2.start();


        Thread m3 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    linkedQueue.offer("message from 3");
                }
            }
        };
        m3.start();

        Thread m4 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    linkedQueue.offer("message from 4");
                }
            }
        };
        m4.start();

        Thread dealer = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        String message = linkedQueue.take();
                        System.out.println(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }; dealer.start();
    }


}
