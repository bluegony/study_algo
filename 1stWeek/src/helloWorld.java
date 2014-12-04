/**
 * Created by Main on 2014. 12. 2..
 */
import java.util.HashMap;
import java.util.Scanner;
public class helloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        while(cases-- > 0) {
            String name = sc.next();
            System.out.println("Hello, " + name + "!");
        }
    }
}