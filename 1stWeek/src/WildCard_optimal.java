/**
 * Created by Main on 2014. 12. 3..
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class WildCard_optimal {
    public static String wildcardString;
    public static String testString;
    public static int[][] cache =  new int[101][101];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int patternCount = sc.nextInt();

        while (patternCount-- > 0) {
            WildCard_optimal.wildcardString = sc.next();

            int compareCount = sc.nextInt();
            TreeSet<String> set = new TreeSet<String>();

            while (compareCount-- > 0) {
                WildCard_optimal.testString = sc.next();

                for(int i=0;i<testString.length();i++)
                    Arrays.fill(WildCard_optimal.cache[i],-1);

                if (WildCard_optimal.matchMemoized(0, 0) == 1) {
                    set.add(WildCard_optimal.testString);
                }
            }

            for (String result : set) {
                System.out.println(result);
            }
        }

    }
    public static int matchMemoized(int w, int s) {
        int ret = WildCard_optimal.cache[w][s];
        if( ret != -1)
        {
            return ret;
        }
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
        return WildCard_optimal.cache[w][s] = 0;
    }
}