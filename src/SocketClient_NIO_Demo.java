import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class SocketClient_NIO_Demo {

    private static ByteBuffer buffer = ByteBuffer.allocate(1024);

    private static SocketChannel socketChannel;
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            String ip = "192.168.3.13";
            int port = 5454;
            Selector selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(ip, port));
            while (!socketChannel.finishConnect()){
                Thread.sleep(10);
            }
            System.out.println("now socket finish connect");
            socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            Thread writeThread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        String input = in.nextLine();
                        System.out.println("input: " + input);
                        if(input.length() > 0){
                            write(input.getBytes());
                        }
                    }
                }

                public int write(byte[] data) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    try {
                        byteBuffer.clear();
                        int capacity = byteBuffer.capacity();
                        if (data.length <= capacity) {
                            byteBuffer.put(data);
                            byteBuffer.flip();
                            int result = socketChannel.write(byteBuffer);
                            System.out.println("write result: " + result);
                            return result;
                        }
                        // data is too long
                        int index = 0;
                        while (index < data.length) {
                            byteBuffer.clear();
                            if (index + capacity <= data.length) {
                                byteBuffer.put(data, index, capacity);
                            } else {
                                byteBuffer.put(data, index, data.length - index);
                            }
                            byteBuffer.flip();
                            index += socketChannel.write(byteBuffer);
                        }
                        return index;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return -1;
                }
            };writeThread.start();

            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        System.out.println("this client ip is:" + channel.socket().getInetAddress());
                        int lens =  channel.read(buffer);
                        System.out.println("read into buffer, lens: " + lens);
                        if(lens > 0){
                            System.out.println("buffer info: " + (new String(Arrays.copyOf(buffer.array(), lens))));
                            buffer.clear();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
