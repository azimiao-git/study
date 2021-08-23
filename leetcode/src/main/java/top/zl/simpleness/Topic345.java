package top.zl.simpleness;

/**
 * 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 输入："leetcode"
 * 输出："leotcede"
 * <p>
 * 元音字母 a、e、i、o、u、A、E、I、O、U;
 *
 * @author zl
 * 2021/08/19
 */
public class Topic345 {


    public static void main(String[] args) {
        System.out.println(reverseVowels("aA"));
    }


    public static String reverseVowels(String str) {
        String patten = "aeiouAEIOU";
        StringBuilder result = new StringBuilder(str);
        int head = 0;
        int tail = str.length() - 1;
        while (head <= tail) {
            if (patten.contains(str.charAt(head)+"")){
                if (patten.contains(str.charAt(tail)+"")){
                    result.replace(head,head+1,str.charAt(tail)+"");
                    result.replace(tail,tail+1,str.charAt(head)+"");
                    head++;
                }
                tail--;
            }else{
                head++;
            }
        }
        return result.toString();
    }
}
