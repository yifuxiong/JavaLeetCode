package T8;

import java.util.HashMap;
import java.util.Map;

// 有限状态机(deterministic finite automaton, DFA)
public class Solution2 {
    public int myAtoi(String s) {
        Automaton automaton = new Automaton();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            automaton.get(s.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }

    public static void main(String[] args) {
        String s = "  -4193 with words";
        String s2 = "words and 987";
        String s3 = "-91283472332";
        String s4 = "        ";
        String s5 = "  fsgweg  gggd  fa ";
        String s6 = "- 0  123";
        String s7 = "-+12 3  ";
        String s8 = "0000-12+3423";

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.myAtoi(s));
    }
}

class Automaton {
    public int sign = 1;
    public long ans = 0;

    private String state = "start";
    private Map<String, String[]> table = new HashMap<String, String[]>() {
        {
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }
    };

    public void get(char c) {
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}
