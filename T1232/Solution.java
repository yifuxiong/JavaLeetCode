package T1232;

public class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;
        if (len == 2){
            return true;
        }

        // 计算初始斜率
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];

        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        double k;
        if (x2 - x1 == 0){
            k = Integer.MAX_VALUE;
        }else{
            k = (double)(y2 - y1) / (double)(x2 - x1);
        }


        for (int i = 2; i< len; i++){
            int a1 = coordinates[i-1][0];
            int b1 = coordinates[i-1][1];

            int a2 = coordinates[i][0];
            int b2 = coordinates[i][1];

            double l;
            if (a2 - a1 == 0){
                l = Integer.MAX_VALUE;
            }else{
                l = (double)(b2 - b1) / (double)(a2 - a1);
            }

            if (l != k){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int[][] coordinates2 = {{1, 1}, {2, 2}, {3, 4}, {4, 5}, {6, 7}};
        int[][] coordinates3 = {{0, 0}, {0, 1}, {0, -1}};
        int[][] coordinates4 = {{-4,-3},{1,0},{3,-1},{0,-1},{-5,2}};
        int[][] coordinates5  = {{0, 0}, {0, 5}, {5, 5}, {5, 0}};

        Solution solut = new Solution();
        System.out.println(solut.checkStraightLine(coordinates3));
    }
}
