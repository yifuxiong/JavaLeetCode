package T207;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // 建图
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        return false;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        Solution solut = new Solution();
        System.out.println(solut.canFinish(numCourses, prerequisites));
    }
}
