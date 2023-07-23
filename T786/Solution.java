package T786;

import java.util.PriorityQueue;

public class Solution {
    // 暴力法 + 优先队列
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1, o2) -> {
            double s1 = (double) o1[0] / o1[1];
            double s2 = (double) o2[0] / o2[1];
            // 从大到小
            if (s1 > s2) {
                return -1;
            } else {
                return 1;
            }
        });

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
                if (queue.size() > k) {
                    queue.poll();  // 这里poll出来的是当前最大的元素
                }
            }
        }

        return queue.poll();
    }

    // 宫水三叶：多路归并
    public int[] kthSmallestPrimeFraction2(int[] arr, int k) {
        // 注意这个优先队列中存储的是索引
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            double s1 = (double) arr[o1[0]] / arr[o1[1]];
            double s2 = (double) arr[o2[0]] / arr[o2[1]];
            // 从小到大
            if (s1 < s2) {
                return -1;
            } else {
                return 1;
            }
        });

        for (int i = 1; i < arr.length; i++) {
            queue.offer(new int[]{0, i});
        }

        while (k > 1) {
            int[] poll = queue.poll();
            int i = poll[0], j = poll[1];
            if (i + 1 < j) {
                queue.add(new int[]{i + 1, j});
            }
            k--;
        }

        int[] poll = queue.poll();
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }

    // 官方：二分 + 双指针
    public int[] kthSmallestPrimeFraction3(int[] arr, int k) {
        int n = arr.length;
        double left = 0.0, right = 1.0;
        while (true) {
            double mid = (left + right) / 2;
            int i = -1, count = 0;
            // 记录最大的分数
            int x = 0, y = 1;

            for (int j = 1; j < n; ++j) {
                while ((double) arr[i + 1] / arr[j] < mid) {
                    ++i;
                    if (arr[i] * y > arr[j] * x) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                count += i + 1;
            }

            if (count == k) {
                return new int[]{x, y};
            }
            if (count < k) {
                left = mid;
            } else {
                right = mid;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5};
        int k = 3;

        int[] arr2 = {1, 7};
        int k2 = 1;

        Solution solut = new Solution();
        int[] ans = solut.kthSmallestPrimeFraction(arr, k);
        System.out.println(ans[0] + "/" + ans[1]);

        int[] ans2 = solut.kthSmallestPrimeFraction2(arr, k);
        System.out.println(ans2[0] + "/" + ans2[1]);

        int[] ans3 = solut.kthSmallestPrimeFraction3(arr, k);
        System.out.println(ans3[0] + "/" + ans3[1]);
    }
}
