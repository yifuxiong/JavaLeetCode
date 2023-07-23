package T991;

public class Solution {
    // 逆向思维
    public int brokenCalc(int startValue, int target) {
        if (target <= startValue) {
            return startValue - target;
        }

        // System.out.print(target + " -> ");
        int ans = 0;
        while (target > startValue) {
            if (target % 2 != 0) {
                target += 1;
            } else {
                target /= 2;
            }
            // System.out.print(target + " -> ");
            ans++;
        }
        if (target != startValue) {
            // System.out.print(startValue);
            ans += (startValue - target);
        }
        // System.out.println();
        return ans;
    }

    public static void main(String[] args) {
        int startValue = 2, target = 3;
        int startValue2 = 5, target2 = 8;
        int startValue3 = 3, target3 = 10;
        int startValue4 = 1024, target4 = 1;

        Solution solut = new Solution();
        System.out.println(solut.brokenCalc(startValue, target));
    }
}
