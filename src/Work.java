public class Work {

    public static void staticSyncMethod() {
        System.out.println("staticSyncMethod start");
        long temp = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (System.currentTimeMillis() - temp > 1000) {
                temp = System.currentTimeMillis();
                System.out.println("staticSyncMethod running...");
                i = i + 1;
                if (i == 10) {
                    break;
                }
            }
        }
        System.out.println("staticSyncMethod end");
    }

    public synchronized void syncMethod() {
        System.out.println("syncMethod start");
        long temp = System.currentTimeMillis();
        int i = 0;
        while (true) {
            if (System.currentTimeMillis() - temp > 1000) {
                temp = System.currentTimeMillis();
                System.out.println("syncMethod running...");
                i = i + 1;
                if (i == 10) {
                    break;
                }
            }
        }
        System.out.println("syncMethod end");
    }

}
