
import java.util.Scanner;

public class LunchBox {

    static int[] cookTime;
    static int[] eatTime;
    static int[] criticalPath;

    public static boolean showDebugLog = false;
    public static void log(Object string) {
        if(showDebugLog)
            System.out.print(string);
    }

    public static void logln(Object string) {
        if(showDebugLog)
            System.out.println(string);
    }

    static int chooseBest(int count, int start, int min){

        int remain = 0;

        for(int i=0 ; i < count; i++) {
         criticalPath[i] = 0;
        }
        for(int i=0 ; i < count; i++) {

            if(cookTime[i]==0) continue;
            remain++;

            int min2 = start + cookTime[i] + eatTime[i];

            int min3 = start + cookTime[i];
            int minEatTime = 0;
            for( int j=0; j<count; j++) {
                if ( j==i || cookTime[j]==0) continue;
                min3 += cookTime[j];

                if(minEatTime==0) minEatTime= eatTime[j];
                else if(eatTime[j]<minEatTime) minEatTime = eatTime[j];
            }
            min3 += minEatTime;

            int maxMin = (min>min2)?min:min2;
            maxMin = (maxMin>min3)?maxMin:min3;

            criticalPath[i] = maxMin;
        }

        int best = -1;
        for(int i=0 ; i < count; i++) {
            if(criticalPath[i] == 0) continue;
            if(best == -1) best = i;
            else if(criticalPath[i] < criticalPath[best])
                best = i;
        }
        if(remain==1)
            return criticalPath[best];

        int cookTimeE = start + cookTime[best];
        int newEatTime = cookTimeE + eatTime[best];
        int minEatTime = min;
        if(newEatTime > min)
            minEatTime = newEatTime;

        cookTime[best] = 0;
        eatTime[best] = 0;

        return chooseBest(count,cookTimeE, minEatTime);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        while (cases-- > 0) {

            int count = sc.nextInt();
            cookTime = new int[count];
            eatTime = new int[count];
            criticalPath = new int[count];

            for (int i = 0; i < count; i++) {
                cookTime[i] = sc.nextInt();
            }

            for (int i = 0; i < count; i++) {
                eatTime[i] = sc.nextInt();
            }

            System.out.println(chooseBest(count, 0, 0));
        }

    }
}