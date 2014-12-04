/**
 * Created by Main on 2014. 12. 2..
 */

import java.util.Scanner;
public class HotSummer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        int cases2 = cases;
        boolean[] notExceed = new boolean[cases];

        while(cases-- > 0) {

            int maxPower = sc.nextInt();

            for(int i=0; i<9; i++){
                maxPower = maxPower - sc.nextInt();
            }
            notExceed[cases] = (maxPower<0)?false:true;
        }

        while(cases2-- > 0) {
            System.out.println(notExceed[cases2] ? "YES" : "NO");
        }

    }
}