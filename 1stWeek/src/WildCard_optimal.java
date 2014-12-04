/**
 * Created by Main on 2014. 12. 3..
 */

import java.util.Scanner;
import java.util.TreeSet;
public class WildCard_optimal {
    private static String wildcardString;
    private static String testString;
    private static int[][] cache =  new int[101][101];

    public static void main(String[] args) {
//        this.cache = new int[101][101];
//        for(int i=0;i<101;i++)
//            for(int j=0;j<101;j++)
//                Main.cache[i][j] = -1;
        Scanner sc = new Scanner(System.in);
        int patternCount = sc.nextInt();

        while (patternCount-- > 0) {
            WildCard_optimal.wildcardString = sc.next();
//            System.out.println(Arrays.toString(wildStringArray));

            int compareCount = sc.nextInt();
            TreeSet<String> set = new TreeSet<String>();

            while (compareCount-- > 0) {
                WildCard_optimal.testString = sc.next();
                if (matchMemoized(0, 0) == 1) {
                    set.add(WildCard_optimal.testString);
                }
//                if (match(wildcardString, testString)) {
//                    set.add(Main.testString);
//                }
            }

            for (String result : set) {
                System.out.println(result);
            }
        }

    }
    public static int matchMemoized(int w, int s) {
        int ret = WildCard_optimal.cache[w][s];
        if( ret != 0)
        {
            System.out.println(ret+"!!!");
            return ret;
        }
//        String S = this.testString;
//        String W = this.wildcardString;
        char[] W = WildCard_optimal.wildcardString.toCharArray();
        char[] S = WildCard_optimal.testString.toCharArray();
        int wSize = W.length;
        int sSize = S.length;

        while(s < sSize && w < wSize && (W[w] == '?' || W[w] == S[s])) {
            ++w; ++s;
        }


        if( w == wSize )
            return WildCard_optimal.cache[w][s] = (s == sSize)?1:0;

        if(W[w] == '*')
            for(int skip = 0; skip+s <= sSize; ++skip)
                if(matchMemoized(w+1,s+skip) == 1)
                    return WildCard_optimal.cache[w][s] = 1;
        return WildCard_optimal.cache[w][s] = -1;
    }
    public static boolean match(String ws, String ts){
        char[] w = ws.toCharArray();
        char[] s = ts.toCharArray();
        int pos = 0;
        int wSize = w.length;
        int sSize = s.length;
        while(pos < sSize && pos < wSize && (w[pos] == '?' || w[pos] == s[pos]))
            ++pos;

        if(pos == wSize)
            return (pos == sSize);

        if(w[pos]=='*')
            for(int skip = 0; pos+skip <= sSize; ++skip)
                if(match(ws.substring(pos+1), ts.substring(pos+skip)))
                    return true;
        return false;
    }
}