package T1723;

import java.util.Arrays;
import java.util.Comparator;

// 官方解法：二分查找 + 回溯 + 剪枝
public class Solution2 {
    public int minimumTimeRequired(int[] jobs, int k) {
        // 从小到大排列
        Arrays.sort(jobs);

        // 从大到小排列
        int low = 0, high = jobs.length - 1;
        while (low < high) {
            int tmp = jobs[low];
            jobs[low] = jobs[high];
            jobs[high] = tmp;

            low++;
            high--;
        }

//        for (int j: jobs){
//            System.out.print(j + " ");
//        }
//        System.out.println();

        // left是jobs中的最大值，right是jobs的总和
        int l = jobs[0], r = Arrays.stream(jobs).sum();
        while (l < r) {
            int mid = (l + r) / 2;
            if (check(jobs, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    public boolean check(int[] jobs, int k, int limit) {
        // k个工人的工作量
        int[] workloads = new int[k];
        return backtrack(jobs, workloads, 0, limit);
    }

    public boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if (i >= jobs.length) {
            return true;
        }

        int cur = jobs[i];
        for (int j = 0; j < workloads.length; j++) {
            if (workloads[j] + cur <= limit) {
                workloads[j] += cur;
                // 分配下一个工作
                if (backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }
            // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
            // 或者当前工作恰能使该工人的工作量达到了上限
            // 这两种情况下我们无需尝试继续分配工作
            if (workloads[j] == 0 || workloads[j] + cur == limit) {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] jobs = {3, 2, 3};
        int k = 3;

        int[] jobs2 = {1, 2, 4, 7, 8};
        int k2 = 2;

        Solution2 solut2 = new Solution2();
        System.out.println(solut2.minimumTimeRequired(jobs, k));
    }
}
