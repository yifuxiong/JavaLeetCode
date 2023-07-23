package T303;

public class NumArray {
    protected int[] sums;
    protected int len;

    public NumArray(int[] nums) {
        this.len = nums.length;
        this.sums = new int[this.len + 1];

        // 初始化第一个
        this.sums[0] = 0;
        for (int i = 0; i < this.len; i++) {
            this.sums[i + 1] = this.sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        // sums[j + 1]其实是加到nums[j]，sums[i]是加到nums[i-1]
        // 因此sums[j+1]-sums[i]等于nums[i]+...+sums[j]
        return this.sums[j + 1] - sums[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        int i = 0, j = 2;
        int i2 = 2, j2 = 5;
        int i3 = 0, j3 = 5;

        NumArray na = new NumArray(nums);
        System.out.println(na.sumRange(i, j));
        System.out.println(na.sumRange(i2, j2));
        System.out.println(na.sumRange(i3, j3));
    }
}
