
/**
 * Created by LunchBox_re on 2014. 12. 11..
 */
import java.util.Scanner;

public class LunchBox_re {

    static int[] cookTime;
    static int[] eatTime;
    static int[] eatTime2;
    static int[] cookOrder;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        while (cases-- > 0) {

            int count = sc.nextInt();
            cookTime = new int[count];
            eatTime = new int[count];
            cookOrder = new int[count];
            eatTime2 = new int[count];

            for (int i = 0; i < count; i++) {
                cookTime[i] = sc.nextInt();
            }

            for (int i = 0; i < count; i++) {
                int e = sc.nextInt();
                eatTime[i] = e;
                eatTime2[i] = e;
                cookOrder[i] = -1;
            }

            for(int i = 0; i< count; i++){
                int maxIndex = -1;
                for(int j=0; j<count; j++){
                    if(maxIndex == -1) maxIndex = j;
                    if(eatTime2[j] > eatTime2[maxIndex]) maxIndex =j;

                }
                eatTime2[maxIndex] = 0;
                cookOrder[i] = maxIndex;
            }

            int maxTime = 0;
            int cookTimeSum = 0;
            for(int i = 0; i< count; i++) {
                int next = cookOrder[i];
                cookTimeSum += cookTime[next];
                int newTime = cookTimeSum + eatTime[next];
                if(newTime > maxTime)
                    maxTime = newTime;
            }

            System.out.println(maxTime);
        }

    }
}