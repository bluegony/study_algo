import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by BoardCover2 on 2014. 12. 10..
 */
public class BoardCover2 {

//    public static String[] name = new String[101];
//    public static int[] weight = new int[101];
//    public static int[] value = new int[101];
//    public static HashMap<Integer,Integer> resultMap;
//    public static Set<Integer> set;

    public static char[][] board;
    public static char[][] block;
    public static char[][] block2;
    public static char[][] block3;
    public static char[][] block4;


    public static boolean showDebugLog = false;
    public static void log(String string) {
        if(showDebugLog)
            System.out.print(string);
    }
    public static void logln(String string) {
        if(showDebugLog)
            System.out.println(string);
    }
    public static void logLoop(String string, int times) {
        if(showDebugLog)
            for(int i=0; i<times ; i++)
                System.out.print(string);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        while (cases-- > 0) {

            int H = sc.nextInt();
            int W = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();

            for(int i=0; i < H ; i++) {
                String a = sc.next();
                board[H] = a.toCharArray(); // char[H][W]
            }

            for(int i=0; i < R ; i++) {
                String a = sc.next();
                block[R] = a.toCharArray(); // char[R][C
            }
            block2 = new char[C][R];
            block3 = new char[R][C];
            block4 = new char[C][R];


//            resultMap = new HashMap<Integer,Integer>();

//            for (int i=0; i < thingsCount; i++) {
//                name[i] = sc.next();
//                weight[i] = sc.nextInt();
//                value[i] = sc.nextInt();
//            }
//            value[thingsCount] = -1;
//
//            int result =  pack(0, maxWeight);
//            set  = new HashSet<Integer>();
//            reconstruct(0,maxWeight);
//
//            System.out.println( result + " " + set.size() );
//            for(Integer a : set) {
//                System.out.println(name[a]);
//            }
        }

    }
}
