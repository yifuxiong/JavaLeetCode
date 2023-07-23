package T335;

public class Solution {
    // 分类讨论
    public boolean isSelfCrossing(int[] distance) {
        int n = distance.length;
        if (n < 4) {
            return false;
        }

        for (int i = 3; i < n; i++) {
            // 第一种情况
            if (distance[i] >= distance[i - 2] && distance[i - 1] <= distance[i - 3]) {
                return true;
            }
            // 第二种情况
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] + distance[i - 4] >= distance[i - 2]) {
                return true;
            }
            // 第三种情况
            if (i >= 5 && distance[i - 1] <= distance[i - 3] && distance[i - 3] >= distance[i - 5] && distance[i - 2] >= distance[i - 4] && distance[i] + distance[i - 4] >= distance[i - 2] && distance[i - 1] + distance[i - 5] >= distance[i - 3]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] distance = {2, 1, 1, 2};
        int[] distance2 = {1, 2, 3, 4};
        int[] distance3 = {1, 1, 1, 1};
        int[] distance4 = {1, 1, 2, 2, 3, 3, 4, 4, 10, 4, 4, 3, 3, 2, 2, 1, 1};

        Solution solut = new Solution();
        System.out.println(solut.isSelfCrossing(distance4));
    }
}
