package T748;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> lmap = new HashMap<>();
        for (int i = 0; i < licensePlate.length(); i++) {
            if (licensePlate.charAt(i) >= 'a' && licensePlate.charAt(i) <= 'z') {
                char ch = licensePlate.charAt(i);
                lmap.put(ch, lmap.getOrDefault(ch, 0) + 1);
            }
            if (licensePlate.charAt(i) >= 'A' && licensePlate.charAt(i) <= 'Z') {
                char ch = (char) (licensePlate.charAt(i) + 32);
                lmap.put(ch, lmap.getOrDefault(ch, 0) + 1);
            }
        }
        System.out.println(lmap);

        Map<Integer, String> ans = new HashMap<>();
        int[] wcnt = new int[26];
        boolean flag;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Arrays.fill(wcnt, 0);
            for (int j = 0; j < word.length(); j++) {
                wcnt[word.charAt(j) - 'a']++;
            }

            flag = true;
            for (Character key : lmap.keySet()) {
                int index = key - 'a';
                if (lmap.get(key) > wcnt[index]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.put(i, word);
            }
        }
        System.out.println(ans);

        int minIndex = ans.size();
        int minLength = 15;
        String ret = "";
        for (Map.Entry<Integer, String> entry : ans.entrySet()) {
            int index = entry.getKey();
            String str = entry.getValue();
            if (str.length() < minLength) {
                minLength = str.length();
                minIndex = index;
                ret = str;
            } else if (str.length() == minLength && index < minIndex) {
                minIndex = index;
                ret = str;
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String licensePlate = "1s3 PSt";
        String[] words = {"step", "steps", "stripe", "stepple"};

        String licensePlate2 = "1s3 456";
        String[] words2 = {"looks", "pest", "stew", "show"};

        String licensePlate3 = "Ah71752";
        String[] words3 = {"suggest", "letter", "of", "husband", "easy", "education", "drug", "prevent", "writer", "old"};

        String licensePlate4 = "OgEu755";
        String[] words4 = {"enough", "these", "play", "wide", "wonder", "box", "arrive", "money", "tax", "thus"};

        String licensePlate5 = "iMSlpe4";
        String[] words5 = {"claim", "consumer", "student", "camera", "public", "never", "wonder", "simple", "thought", "use"};

        Solution solut = new Solution();
        System.out.println(solut.shortestCompletingWord(licensePlate2, words2));
    }
}
