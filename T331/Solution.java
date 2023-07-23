package T331;


public class Solution {
    // 方法一：结束遍历时判断入度是否等于出度
    public boolean isValidSerialization(String preorder) {
        // 单个"#"直接返回true
        if (preorder.equals("#")) {
            return true;
        }

        String[] codes = preorder.split(",");
        int n = codes.length;
        int indegree = 0, outdegree = 0;
        for (int i = 0; i < n; i++) {
            // 如果是根节点
            if (i == 0) {
                // preorder表达式并不是单个"#"，但是根节点却是"#"
                if (codes[i].equals("#")) {
                    return false;
                } else {
                    outdegree += 2;
                    continue;  // 这里必须要有个continue，根节点判断完直接下一个节点
                }
            }
            if (codes[i].equals("#")) {
                indegree += 1;
            } else {
                indegree += 1;
                outdegree += 2;
            }
            // 表达式都没遍历完，入度就比出度大了
            if (i != n - 1 && indegree >= outdegree) {
                return false;
            }
        }
        return indegree == outdegree;
    }

    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        String preorder2 = "1,#";
        String preorder3 = "9,#,#,1";

        Solution solut = new Solution();
        System.out.println(solut.isValidSerialization(preorder));
    }
}
