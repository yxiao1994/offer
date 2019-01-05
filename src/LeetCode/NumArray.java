package LeetCode;

class NumArray {
    private int[] nums;
    private int[] block;
    private int len;

    public NumArray(int[] nums) {
        this.nums = nums;
        if (nums.length == 0)
            return;
        int n = nums.length;
        len = (int) Math.ceil(Math.sqrt(n));
        block = new int[len];
        for (int i = 0; i < n; i++)
            block[i / len] += nums[i];
    }

    public void update(int i, int val) {
        int bn = i / len;
        block[bn] = block[bn] + val - nums[i];
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        int startblock = i / len, endblock = j / len;
        if (startblock == endblock) {
            for (int k = i; k <= j; k++)
                sum += nums[k];
            return sum;
        }
        for (int k = i; k < nums.length && k < (startblock + 1) * len; k++)
            sum += nums[k];
        for (int k = startblock + 1; k < endblock; k++)
            sum += block[k];
        for (int k = endblock * len; k < nums.length && k <= j; k++)
            sum += nums[k];
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
