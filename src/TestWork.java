public class TestWork {
    public static void main(String[] args) {
        Work work = new Work();
        Work work1 = new Work();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                Work.staticSyncMethod();
            }
        };
        t1.start();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                work.syncMethod();
                work1.syncMethod();
            }
        };
        t2.start();
//        Thread t3 = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                Work.staticMethod();
//            }
//        };
//        t3.start();
//        Thread t4 = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                work.syncMethod();
//            }
//        };
//        t4.start();
    }
}
