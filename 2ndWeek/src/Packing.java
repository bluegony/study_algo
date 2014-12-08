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

            for (int i=0; i < thingsCount+1; i++) {
//                logln( name[i] +" weight : "+   weight[i] +" value : "+ value[i]+"");
            }

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

        for(int i=0; i<index ; i++) log("    ");
        logln("index : " + index + " , maxWeight =  " + maxWeight);

        int key = makeKey(index, maxWeight);

        if(resultMap.get(key)!=null) {
            for (int i = 0; i < index; i++) log("    ");
            logln("using cache, return " + resultMap.get(key));
            return resultMap.get(key);
        }

        if(maxWeight<1){
            resultMap.put(key,0);
            return 0;
        }
        if(value[index+1] == -1) {
            if (weight[index] > maxWeight){
                for(int i=0; i<index ; i++) log("    ");
                logln("exclude1 index " + index + ", return " + 0);
                resultMap.put(key, 0);
                return 0;
            }
            else {
                for(int i=0; i<index ; i++) log("    ");
                logln("include1 index " + index + ", return " + value[index]);
                resultMap.put(key, value[index]);

                Set<Integer>set = new HashSet<Integer>();
                set.add(index);
                resultSetMap.put(key,set);
                logln("key1 : " + key + " set : " + set);

                return value[index];
            }
        }

        int afterExcludeThisValue = getBestPack(index+1,maxWeight);
        logln("");

        if(maxWeight < weight[index]){
            for(int i=0; i<index ; i++) log("    ");
            logln("exclude2 index " + index + ", return " + afterExcludeThisValue);
            logln("");
            resultMap.put(key, afterExcludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight));
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);
            logln("key2 : " + key + " set : " + set);

            return afterExcludeThisValue;
        }

        int afterIncludeThisValue = getBestPack(index+1,maxWeight-weight[index]);
        logln("");


        for(int i=0; i<index ; i++) log("    ");
        logln("comparing " + afterExcludeThisValue + " and " + value[index] + "+" + afterIncludeThisValue);

        if (value[index] + afterIncludeThisValue < afterExcludeThisValue){  // 이번 index는 제외
            for(int i=0; i<index ; i++) log("    ");
            logln("exclude3 index " + index + ", return " + afterExcludeThisValue);
            logln("");
            resultMap.put(key, afterExcludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight));
            logln(oldset);
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);
            logln("key3 : " + key + " set : " + set);

            return afterExcludeThisValue;

        } else {
            for(int i=0; i<index ; i++) log("    ");
            logln("include3 index " + index + ", return " + value[index] + "+" + afterIncludeThisValue);
            logln("");
            resultMap.put(key, value[index] + afterIncludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight-weight[index]));
            logln(oldset);
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            set.add(index);
            resultSetMap.put(key,set);
            logln("key4 : " + key + " set : " + set);

            return value[index] + afterIncludeThisValue;
        }


    }
    
}