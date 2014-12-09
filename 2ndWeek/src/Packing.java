import java.util.*;

/**
 * Created by Packing on 2014. 12. 5..
 */
public class Packing {
    public static String[] name = new String[101];
    public static int[] weight = new int[101];
    public static int[] value = new int[101];
    public static HashMap<Integer,Integer> resultMap;
    public static HashMap<Integer,Set<Integer>> resultSetMap;

    public static boolean showDebugLog = false;
    public static void log(Object string) {
        if(showDebugLog)
            System.out.print(string);
    }

    public static void logln(Object string) {
        if(showDebugLog)
                System.out.println(string);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();

        while (cases-- > 0) {

            int thingsCount = sc.nextInt();
            int maxWeight = sc.nextInt();

            resultMap = new HashMap<Integer,Integer>();
            resultSetMap = new HashMap<Integer,Set<Integer>>();

            for (int i=0; i < thingsCount; i++) {
                name[i] = sc.next();
                weight[i] = sc.nextInt();
                value[i] = sc.nextInt();
            }
            value[thingsCount] = -1;


            int result =  getBestPack(0, maxWeight);
            int size = 0;
            for(Integer a : resultSetMap.get(makeKey(0,maxWeight)))
            {
                size++;
            }
            System.out.println( result + " " + size);
            for(Integer a : resultSetMap.get(makeKey(0,maxWeight)))
            {
                System.out.println(name[a]);
            }
        }

    }

    public static int makeKey(int index, int maxWeight) {
        return index * 10000 + maxWeight;
    }

    public static int getBestPack(int index, int maxWeight){


        int key = makeKey(index, maxWeight);

        if(resultMap.get(key)!=null) {
            return resultMap.get(key);
        }

        if(maxWeight<1){
            resultMap.put(key,0);
            return 0;
        }
        if(value[index+1] == -1) {
            if (weight[index] > maxWeight){
                resultMap.put(key, 0);
                return 0;
            }
            else {
                resultMap.put(key, value[index]);

                Set<Integer>set = new HashSet<Integer>();
                set.add(index);
                resultSetMap.put(key,set);

                return value[index];
            }
        }

        int afterExcludeThisValue = getBestPack(index+1,maxWeight);

        if(maxWeight < weight[index]){
            resultMap.put(key, afterExcludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight));
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);

            return afterExcludeThisValue;
        }

        int afterIncludeThisValue = getBestPack(index+1,maxWeight-weight[index]);



        if (value[index] + afterIncludeThisValue < afterExcludeThisValue){  // 이번 index는 제외
            resultMap.put(key, afterExcludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight));
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);

            return afterExcludeThisValue;

        } else {
            resultMap.put(key, value[index] + afterIncludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight-weight[index]));
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            set.add(index);
            resultSetMap.put(key,set);

            return value[index] + afterIncludeThisValue;
        }


    }
    
}