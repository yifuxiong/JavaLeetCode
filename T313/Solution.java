package T313;

import java.util.*;

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> heap = new PriorityQueue<>();
        seen.add(1L);
        heap.offer(1L);

        int ugly = 0;
        for (int i = 0; i < n; i++){
            long curr = heap.poll();
            ugly = (int) curr;
            for (int prime: primes){
                long next = curr * prime;
                if (seen.add(next)){
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2, 7, 13, 19};

        int n2 = 1;
        int[] primes2 = {2, 3, 5};

        Solution solut = new Solution();
        System.out.println(solut.nthSuperUglyNumber(n, primes));
    }
}
