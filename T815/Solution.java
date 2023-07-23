package T815;

import java.util.*;

public class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // <stop, index(list)>
        Map<Integer, List<Integer>> hashMap = new HashMap<>();
        int n = routes.length;

        // 建立bus之间的关系
        boolean[][] edge = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            int[] stops = routes[i];
            for (int j = 0; j < stops.length; j++) {
                List<Integer> list = hashMap.getOrDefault(stops[j], new ArrayList<>());
                for (int l : list) {
                    edge[i][l] = edge[l][i] = true;
                }
                list.add(i);
                hashMap.put(stops[j], list);
            }
        }
        // 打印stop和对应的bus
        System.out.println(hashMap);

        int[] dis = new int[n];
        // 初始化
        Arrays.fill(dis, -1);
        // queue中存放的是bus
        Queue<Integer> queue = new LinkedList<>();
        // hashMap中是能到达起点站的bus
        for (int bus : hashMap.getOrDefault(source, new ArrayList<>())) {
            dis[bus] = 1;
            queue.offer(bus);
        }

        while (!queue.isEmpty()) {
            // bus
            int x = queue.poll();
            // 当前bus和其他bus之间的关系
            for (int y = 0; y < n; y++) {
                // dis[y] == -1表示y这个bus到不了起点站
                if (edge[x][y] && dis[y] == -1) {
                    // 那么这个站的距离+1
                    dis[y] = dis[x] + 1;
                    queue.offer(y);
                }
            }
        }

        int ret = Integer.MAX_VALUE;
        for (int bus : hashMap.getOrDefault(target, new ArrayList<>())) {
            // 如果这个bus能到达起点站
            if (dis[bus] != -1) {
                ret = Math.min(ret, dis[bus]);
            }
        }

        return ret == Integer.MAX_VALUE ? -1 : ret;
    }


    public static void main(String[] args) {
        int[][] routes = {{1, 2, 7}, {3, 6, 7}};
        int source = 1, target = 6;
        // result 2

        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        int source2 = 15, target2 = 12;
        // result -1

        Solution solut = new Solution();
        System.out.println(solut.numBusesToDestination(routes, source, target));
    }
}
