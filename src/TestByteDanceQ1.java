public class TestByteDanceQ1 {
    private int object;
    public static void main(String[] args) {
        TestByteDanceQ1 tq = new TestByteDanceQ1();

        tq.testSomething(1);
    }

    private void testSomething(int m){

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100; i++) {
                    object++;
                }
            }
        };t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100; i++) {
                    object++;
                }
            }
        };t2.start();
        Thread t3 = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100; i++) {
                    object++;
                }
            }
        };t3.start();
        Thread t4 = new Thread(){
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 100; i++) {
                    object++;
                }
            }
        };t4.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(object);
    }

}
