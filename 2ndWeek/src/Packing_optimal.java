import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Packing_optimal on 2014. 12. 9..
 */
public class Packing_optimal {
    public static String[] name = new String[101];
    public static int[] weight = new int[101];
    public static int[] value = new int[101];
    public static HashMap<Integer,Integer> resultMap;
    public static Set<Integer> set;

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

            int thingsCount = sc.nextInt();
            int maxWeight = sc.nextInt();

            resultMap = new HashMap<Integer,Integer>();

            for (int i=0; i < thingsCount; i++) {
                name[i] = sc.next();
                weight[i] = sc.nextInt();
                value[i] = sc.nextInt();
            }
            value[thingsCount] = -1;

            int result =  pack(0, maxWeight);
            set  = new HashSet<Integer>();
            reconstruct(0,maxWeight);

            System.out.println( result + " " + set.size() );
            for(Integer a : set) {
                System.out.println(name[a]);
            }
        }

    }

    public static int makeKey(int index, int maxWeight) {
        return index * 10000 + maxWeight;
    }

    public static int pack(int index, int maxWeight){


        int key = makeKey(index, maxWeight);

        if(value[index] == -1){
            resultMap.put(key, 0);
            return 0;
        }

        if(resultMap.get(key)!=null) {
            return resultMap.get(key);
        }


        int afterExcludeThisValue = pack(index+1,maxWeight);

        if(maxWeight < weight[index]){
            resultMap.put(key, afterExcludeThisValue);

            return afterExcludeThisValue;
        }

        int afterIncludeThisValue = pack(index+1 , maxWeight - weight[index]);

        if (value[index] + afterIncludeThisValue < afterExcludeThisValue){  // 이번 index는 제외
            resultMap.put(key, afterExcludeThisValue);

            return afterExcludeThisValue;

        } else {
            resultMap.put(key, value[index] + afterIncludeThisValue);

            return value[index] + afterIncludeThisValue;
        }


    }

    static void reconstruct(int index, int maxWeight) {
        if(value[index] == -1) return;
        if(pack(index,maxWeight) == pack(index+1,maxWeight))
            reconstruct(index+1, maxWeight);
        else {
            set.add(index);
            reconstruct(index+1, maxWeight - weight[index]);
        }
    }

}