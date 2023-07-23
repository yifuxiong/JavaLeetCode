package T1239;

import java.util.ArrayList;
import java.util.List;

// 我自己写一遍
public class Solution2 {
    private int ans = 0;

    public int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();
        for (String s : arr) {
            int mask = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i) - 'a';
                if (((mask >> ch) & 1) != 0) {
                    // 说明s中本身就有重复字符
                    mask = 0;
                    break;
                }
                mask |= (1 << ch);
            }
            if (mask > 0) {
                masks.add(mask);
            }
        }
        backTrace(masks, 0, 0);
        return ans;
    }

    // 注意这里，mask并不是十进制而是二进制的数
    public void backTrace(List<Integer> masks, int pos, int mask) {
        if (pos == masks.size()) {
            // Integer.bitCount()返回一个int类型数的【二进制位】有多少个1
            ans = Math.max(ans, getBitCount(mask));
            return;
        }
        if ((masks.get(pos) & mask) == 0) {
            // mask与masks中无相同字符，则进行下一个mask
            backTrace(masks, pos + 1, mask | masks.get(pos));
        }
        // 否则去掉masks.get(pos)
        backTrace(masks, pos + 1, mask);
    }

    public int getBitCount(int mask) {
        int len = 0;
        while (mask > 0) {
            len += (mask & 1);
            mask >>= 1;
        }
        return len;
    }

    public static void main(String[] args) {
        List<String> arr = new ArrayList<>();
        arr.add("un");
        arr.add("iq");
        arr.add("ue");

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.maxLength(arr));
    }
}
