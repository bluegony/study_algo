import java.util.*;

/**
 * Created by Packing on 2014. 12. 5..
 */
//class BestPack {
//    public int value;
//    public Set<int> set;
//}
public class Packing {


//    public static int[][] cache =  new int[101][1001];
    public static String[] name = new String[101];
    public static int[] weight = new int[101];
    public static int[] value = new int[101];
    public static HashMap<Integer,Integer> resultMap;
    public static HashMap<Integer,Set<Integer>> resultSetMap;

    public static boolean showDebugLog = true;
    public static void log(Object string) {
        if(showDebugLog) System.out.print(string);
    }

    public static void logln(Object string) {
        if(showDebugLog)
        {
                System.out.println(string);
        }
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

            System.out.println("result is " + getBestPack(0, maxWeight) + " " + resultSetMap.size());
            System.out.println(resultMap.get(makeKey(0,maxWeight)));

            for(Integer a : resultSetMap.get(makeKey(0,maxWeight)))
            {

                System.out.println(name[a]);
            }

//                for(int i=0;i<testString.length();i++)
//                    Arrays.fill(cache[i], -1);
//                if (getBestPack(0, 0) == 1) {
//                    set.add(testString);
//                }
//            for (String result : set) {
//                logln(result);
//            }
        }

    }
    public static int makeKey(int index, int maxWeight) {
        return index * 1000 + maxWeight;
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



        if(maxWeight<1)
        {
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

                Set<Integer> set = resultSetMap.get(key);

//                logln("key" + key + "set" + set.size());
                if (set==null) {
                    set = new HashSet<Integer>();
                    set.add(index);
                    resultSetMap.put(key,set);
                } else {
                    set.add(index);
                }
                logln("key" + key + "set" + set.size());
                for(Integer a : set) {
                    logln(a);
                }
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

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight-weight[index]));
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);

            return afterExcludeThisValue;
        }

        int afterIncludeThisValue = getBestPack(index+1,maxWeight-weight[index]);
        logln("");


        for(int i=0; i<index ; i++) log("    ");
        logln("comparing " + afterExcludeThisValue + " and " + value[index] + "+" + afterIncludeThisValue);

        if (value[index] + afterIncludeThisValue < afterExcludeThisValue){  // 이번 index는 제외
            for(int i=0; i<index ; i++) log("    ");
            logln("exclude2 index " + index + ", return " + afterExcludeThisValue);
            logln("");
            resultMap.put(key, afterExcludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight-weight[index]));
            logln(oldset);
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
                set.addAll(oldset);
            resultSetMap.put(key, set);

            logln("key" + key + "set" + set.size());
            for(Integer a : set) {
                logln(a);
            }
            return afterExcludeThisValue;

        } else {
            for(int i=0; i<index ; i++) log("    ");
            logln("include2 index " + index + ", return " + value[index] + "+" + afterIncludeThisValue);
            logln("");
            resultMap.put(key, value[index] + afterIncludeThisValue);

            Set<Integer> oldset = resultSetMap.get(makeKey(index+1,maxWeight-weight[index]));
            logln(oldset);
            Set<Integer> set = new HashSet<Integer>();
            if (oldset!=null)
               set.addAll(oldset);
            set.add(index);
            resultSetMap.put(key,set);

            logln("key" + key + "set" + set.size());
            for(Integer a : set) {
                logln(a);
            }

            return value[index] + afterIncludeThisValue;
        }


    }
    
}