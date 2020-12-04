public class NonStopThreadDemo {

    private static ReadThread readThread = new ReadThread();

    public static void main(String[] args) {
        readThread.start();
    }

    private static void remakeConnection() {
        ReadThread readThread = new ReadThread();
        readThread.start();
    }

    static class ReadThread extends Thread {

        private volatile Thread self;

        @Override
        public void start() {
            if (self != null) {
                return;
            }
            super.start();
        }


        @Override
        public void run() {
            super.run();

            self = this;
            while (!isInterrupted()) {
                try {
                    Thread.sleep(5000);
                    System.out.println("after 5 seconds, connection is good");
                    Thread.sleep(5000);
                    System.out.println("fuck, we are damned");
                    throw new Exception("maybe it's time to reconnect");
                } catch (Exception e) {
                    System.out.println("some fucking exception happened now, we need to remakeConnection()");
                    e.printStackTrace();
                    break;
                }
            }
            self = null;
            remakeConnection();
        }

        public synchronized void stopSelf() {
            if (self != null) {
                self.interrupt();
                self = null;
            }
        }
    }

}
