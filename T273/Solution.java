package T273;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    Map<Integer, String> number = new HashMap<>();
    Map<Integer, String> suffix = new HashMap<>();

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        number.put(0, "");
        number.put(1, "One");
        number.put(2, "Two");
        number.put(3, "Three");
        number.put(4, "Four");
        number.put(5, "Five");
        number.put(6, "Six");
        number.put(7, "Seven");
        number.put(8, "Eight");
        number.put(9, "Nine");
        number.put(10, "Ten");
        number.put(11, "Eleven");
        number.put(12, "Twelve");
        number.put(13, "Thirteen");
        number.put(14, "Fourteen");
        number.put(15, "Fifteen");
        number.put(16, "Sixteen");
        number.put(17, "Seventeen");
        number.put(18, "Eighteen");
        number.put(19, "Nineteen");
        number.put(20, "Twenty");
        number.put(30, "Thirty");
        number.put(40, "Forty");
        number.put(50, "Fifty");
        number.put(60, "Sixty");
        number.put(70, "Seventy");
        number.put(80, "Eighty");
        number.put(90, "Ninety");

        suffix.put(0, "");
        suffix.put(1, "Thousand");
        suffix.put(2, "Million");
        suffix.put(3, "Billion");

        StringBuffer sb = new StringBuffer();
        sb.append(num);
        String str = sb.toString();

        int n = str.length();
        int k = n / 3;
        int yu = n % 3;
        // 头
        StringBuffer ans = new StringBuffer();
        if (yu == 1) {
            ans.append(number.get(Integer.parseInt(sb.substring(0, yu))) + " " + suffix.get(k));
        } else if (yu == 2) {
            int ii = Integer.parseInt(sb.substring(0, yu));
            if (ii >= 20) {
                ans.append(number.get(ii / 10 * 10));
                if (ii % 10 > 0){
                    ans.append(" ");
                }
                ans.append(number.get(ii % 10) + " ");
                ans.append(suffix.get(k));
            } else if (ii > 0){
                ans.append(number.get(ii) + " ");
                ans.append(suffix.get(k));
            }else if (ii == 0){
                ans.append(number.get(ii));
            }
        }

        int i = 0;
        while (i < k) {
            ans.append(" ");
            int ii = Integer.parseInt(sb.substring(i * 3 + yu, (i + 1) * 3 + yu));
            int hundred = ii / 100;
            if (hundred > 0) {
                ans.append(number.get(hundred) + " " + "Hundred ");
            }
            ii = ii % 100;
            if (ii >= 20) {
                ans.append(number.get(ii / 10 * 10));
                if (ii % 10 > 0){
                    ans.append(" ");
                }
                ans.append(number.get(ii % 10) + " ");
                ans.append(suffix.get(k - i - 1));
            } else if (ii > 0){
                ans.append(number.get(ii) + " ");
                ans.append(suffix.get(k - i - 1));
            } else if (ii == 0){
                ans.append(number.get(ii));
                if (hundred != 0){
                    ans.append(suffix.get(k - i - 1));
                }
            }
            i++;
        }

        return ans.toString().trim();
    }

    public static void main(String[] args) {
        int num = 35;
        int num2 = 50868;
        int num3 = 1111000011;
        int num4 = 2147483647;

        Solution solut = new Solution();
        System.out.println(solut.numberToWords(num3));
    }
}
