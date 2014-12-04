/**
 * Created by Main on 2014. 12. 2..
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.TreeSet;
public class WildCard_RegEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int patternCount = sc.nextInt();


        int patternIndex = patternCount;
        while(patternIndex-- > 0) {
           String wildcardString = sc.next().replace("?", ".").replaceAll("[*]+",".*");

            Pattern p = Pattern.compile(wildcardString);

            int compareCount = sc.nextInt();
            TreeSet<String> set = new TreeSet<String>();

            while(compareCount-- > 0) {
                String testString = sc.next();
                if(checkWildcard(p,testString)) {
                    set.add(testString);    // 같은입력이 반복되면 한번만 보여준다.
                }
            }

            for(String result : set){
                System.out.println(result);
            }
        }

    }

     static boolean checkWildcard(Pattern p, String ts) {
        Matcher m = p.matcher(ts);
        return m.matches();
    }
}


