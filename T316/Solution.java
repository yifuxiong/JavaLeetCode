package T316;

import java.util.Vector;

public class Solution {
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i ++){
            num[s.charAt(i) - 'a'] ++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i =0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (!vis[ch - 'a']){
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch){
                    if (num[sb.charAt(sb.length()-1) - 'a'] > 0){
                        vis[sb.charAt(sb.length() -1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() -1);
                    }else{
                        break;
                    }
                }
                vis[ch - 'a'] = true;
                sb.append(ch);
            }
            num[ch - 'a'] -= 1;
        }
        return sb.toString();
    }

    // 我的思路
    public String removeDuplicateLetters2(String s) {
        StringBuffer ret = new StringBuffer();

        Vector<Character> vector = new Vector<>();
        for (int i = 0; i < s.length(); i++){
            if (!vector.contains(s.charAt(i))){
                vector.add(s.charAt(i));
            }else{
                int start = vector.indexOf(s.charAt(i));
                if (start + 1 < vector.size() && (vector.get(start) > vector.get(start+1) || vector.get(vector.size()-1) > s.charAt(i))){
                    vector.removeElementAt(start);
                    vector.add(s.charAt(i));
                }
            }
            System.out.println(vector);
        }

        for (Character i: vector){
            ret.append(i);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String s = "bcabc";
        String s2 = "cbacdcbc";
        String s3 = "cdadabcc";

        Solution solut = new Solution();
        // System.out.println(solut.removeDuplicateLetters(s3));
        System.out.println(solut.removeDuplicateLetters2(s));
    }
}
