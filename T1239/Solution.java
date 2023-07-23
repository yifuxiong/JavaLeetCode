package T1239;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int ans = 0;

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) {
                    // 若mask已有ch，则说明s含有重复字母，无法构成可行解
                    mask = 0;
                    break;
                }
                // 将ch加入mask中
                mask |= 1 << ch;
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }
        backTrace(masks, 0, 0);
        return ans;
    }

    public void backTrace(List<Integer> masks, int pos, int mask) {
        // 遍历到最后一个字符串，得到结果。退出递归。
        if (pos == masks.size()) {
            ans = Math.max(ans, Integer.bitCount(mask));
            return;
        }
        if ((mask & masks.get(pos)) == 0) {
            // mask和masks[pos]无公共元素
            backTrace(masks, pos + 1, mask | masks.get(pos));
        }
        backTrace(masks, pos + 1, mask);
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("un");
        arr.add("iq");
        arr.add("ue");

        Solution solut = new Solution();
        System.out.println(solut.maxLength(arr));
    }
}
