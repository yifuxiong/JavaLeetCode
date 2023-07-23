package T488;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    int ans = Integer.MAX_VALUE;

    public int findMinStep(String board, String hand) {
        // hand中'R','Y','B','G','W'，5中颜色的数量
        Map<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < hand.length(); i++) {
            hashMap.put(hand.charAt(i), hashMap.getOrDefault(hand.charAt(i), 0) + 1);
        }

        backTrack2(board, hashMap, 0, "");
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 消除连续3个以上相同的字符
    public String getNewStr(String board) {
        if (board.length() < 3) {
            return board;
        }

        int count = 1;
        int i = 1;
        while (i < board.length()) {
            if (board.charAt(i) == board.charAt(i - 1)) {
                count++;
                i++;
            } else {
                if (count >= 3) {
                    board = board.substring(0, i - count) + board.substring(i);
                    i = 1;
                } else {
                    i++;
                }
                count = 1;
            }
        }
        // 最后一个字符串由于i越界了，没办法过滤，这里要多加一段代码
        if (count >= 3) {
            board = board.substring(0, i - count) + board.substring(i);
        }
        return board;
    }

    // TLE超时
    public void backTrack(String board, String hand, int index, int times, String lastStr) {
        if (board.length() == 0) {
            ans = Math.min(ans, times);
        }

        for (int i = index; i < hand.length(); i++) {
            // 剪枝
            // 1.插入head[i]之后生成新的字符串与上一个相同，直接continue；
            // 2.插入head[i]，新字符串中没有连续3个相同字符出现，讨论特殊情况；
            // 特殊情况：这种字符串，lastStr = "G"，这次插入一个'G'，变为"GG"；
            //      最后一次插入'G'，才变为可抵消的"GGG"。

            for (int j = 0; j < board.length(); j++) {
                StringBuffer sb = new StringBuffer();
                if (j == 0) {
                    sb.append(hand.charAt(i));
                    sb.append(board);
                } else if (j > 0 && j < board.length() - 1) {
                    sb.append(board.substring(0, j));
                    sb.append(hand.charAt(i));
                    sb.append(board.substring(j));
                } else if (j == board.length() - 1) {
                    sb.append(board);
                    sb.append(hand.charAt(i));
                }
                String newBoard = sb.toString();
                // System.out.println(newBoard);
                String getBoard = getNewStr(newBoard);

                if (newBoard.equals(lastStr)) {
                    continue;
                }

                times += 1;
                backTrack(getBoard, hand, i + 1, times, newBoard);
                times -= 1;
            }
        }
    }

    public void backTrack2(String board, Map<Character, Integer> hashMap, int times, String lastStr) {
        if (board.length() == 0) {
            ans = Math.min(ans, times);
        }

        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            // 剪枝
            char color = entry.getKey();
            if (hashMap.get(color) <= 0) {
                continue;
            }

            for (int j = 0; j < board.length(); j++) {
                StringBuffer sb = new StringBuffer();
                if (j == 0) {
                    sb.append(color);
                    sb.append(board);
                } else if (j > 0 && j < board.length() - 1) {
                    sb.append(board.substring(0, j));
                    sb.append(color);
                    sb.append(board.substring(j));
                } else if (j == board.length() - 1) {
                    sb.append(board);
                    sb.append(color);
                }
                String newBoard = sb.toString();
                if (newBoard.equals(lastStr)) {
                    continue;
                }
                System.out.println(newBoard);

                hashMap.put(color, hashMap.get(color) - 1);
                times += 1;
                backTrack2(getNewStr(newBoard), hashMap, times, newBoard);
                times -= 1;
                hashMap.put(color, hashMap.get(color) + 1);
            }
        }
    }

    public static void main(String[] args) {
        String board = "WRRBBW", hand = "RB";
        String board2 = "WWRRBBWW", hand2 = "WRBRW";
        String board3 = "G", hand3 = "GGGGG";
        String board4 = "RBYYBBRRB", hand4 = "YRBGB";
        String board5 = "RRWWRRBBRR", hand5 = "WB";

        Solution solut = new Solution();
        System.out.println(solut.findMinStep(board5, hand5));
    }
}
