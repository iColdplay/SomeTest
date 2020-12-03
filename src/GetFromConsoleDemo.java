import java.util.Scanner;

public class GetFromConsoleDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine();
            System.out.println("input: " + input);
        }
    }

}
