package T566;

public class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int rows = nums.length;
        int cols = nums[0].length;
        if (rows * cols != r * c) {
            return nums;
        }

        int[][] ans = new int[r][c];
        int x = 0, y = 0;
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                ans[i][j] = nums[x][y];
                if (y < cols - 1) {  // 直到遍历完一行
                    y += 1;
                } else {  // 该换行了
                    x += 1;
                    y = (y + 1) % cols;
                }

            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2}, {3, 4}, {5, 6}};
        int r = 1, c = 6;

        Solution solut = new Solution();
        int[][] ans = solut.matrixReshape(nums, r, c);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
