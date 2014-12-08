/**
 * Created by Main on 2014. 12. 2..
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;


// 오답.
// 언제 오답인지 찾지 못해서 못고침 -_-

public class WildCard_re {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int patternCount = sc.nextInt();

        while(patternCount-- > 0) {
//            String wildcardString = sc.next();

            String wildcardString = sc.next().replaceAll("[*]+","*");
            String[] wildStringArray = wildcardString.split("[*]");

//            System.out.println(wildcardString);
//            System.out.println(Arrays.toString(wildStringArray));

            int compareCount = sc.nextInt();
            TreeSet<String> set = new TreeSet<String>();

            while(compareCount-- > 0) {
                String testString = sc.next();

//                if((wildcardString.length()==wildStringArray[0].length()) && (wildcardString.length()!=testString.length()))
//                    continue;

//                int length = wildcardString.length();
//                boolean checklength = false;
//                if(wildcardString.charAt(wildcardString.length() - 1) != '*')
//                    checklength = true;



                if(checkWholeString(wildStringArray, testString, wildcardString.charAt(0)=='*',wildcardString.charAt(wildcardString.length()-1)=='*')) {
                    set.add(testString);
                }
            }

            for(String result : set){
                System.out.println(result);
            }
        }

    }

    static boolean checkWholeString(String[] wsArray, String ts, boolean firstA, boolean lastA) {

        char[] testCharArray = ts.toCharArray();
        int index = 0;

//        for(String ws : wsArray)
        int wLength = wsArray.length;
        for(int i=0; i<wsArray.length; i++)
        {
            String ws = wsArray[i];
//            System.out.println("ws = "+ws);
            if(ws.length()>0) {
//                System.out.println(ws+" not empty!!!");
                char[] wcArray = ws.toCharArray();
                char[] tcArray = ts.toCharArray();
                int result = checkPartialString(wcArray, tcArray, index);
//                System.out.println(result);
                if (result == -1)
                    return false;
                else if(!firstA && i==0 && result!=0){  // 첫글자가 *이 아닌데 처음 찾은곳이 0이 아닐경우
                    return false;

                } else if(!lastA && (i==wsArray.length-1) && (result+wcArray.length!=tcArray.length) ){   // 끝글자가 *이 아닌데 마지막 찾은곳이 문자열 끝이 아닐경우
                    return false;
                }
                else{
//                    System.out.println(result + " , " + wcArray.length);
                    index = result + wcArray.length;

                }
            }
            else
//                System.out.println("empty!!!");
            ;
        }

//            System.out.println(index);


        return true;

    }

    // tcArray의 startIndex부터 시작해서 wcArray와 같은 문자열이 존재하는지 조사하고 그 시작점을 return
    static int checkPartialString(char[] wcArray, char[] tcArray, int startIndex) {
        int tLength = tcArray.length;
        int wLength = wcArray.length;
        int i = 0;
        int j = 0;
        int result = -1;
        for(j = startIndex; j<tLength - wLength +1; j++) {
//            System.out.println("j = " + j);
            for (i = 0; i < wLength; i++) {
//                System.out.println(i + " , " +j + " , " +wcArray[i] + " , " +tcArray[j+i]);

                if(wcArray[i]=='?' || wcArray[i]==tcArray[j+i]) {
                    if (i+1 == wLength) {
                        result = j;
//                        System.out.println( " return " + String.valueOf(tcArray) + " , " +String.valueOf(wcArray) + " , " +startIndex + " , " +result);
                        return result;
                    }
                    else{

//                        break;
                    }
                }

                else{

                    break;
                    }

            }
        }
//        System.out.println(" return " + String.valueOf(tcArray) + " , " +String.valueOf(wcArray) + " , " +startIndex + " , " +result);
        return result;

    }
}

