package T705;

// 方法一：每次声明10^6个空间大小的数组
public class MyHashSet {
    protected boolean[] set;

    /** Initialize your data structure here. */
    public MyHashSet() {
        this.set = new boolean[1000001];
    }

    public void add(int key) {
        this.set[key] = true;
    }

    public void remove(int key) {
        this.set[key] = false;
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return this.set[key];
    }

    public static void print(boolean[] set){
        for (int i = 1; i < set.length; i++){
            if (set[i]){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args){
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        print(myHashSet.set);
        myHashSet.add(2);      // set = [1, 2]
        print(myHashSet.set);
        System.out.println(myHashSet.contains(1)); // 返回 True
        System.out.println(myHashSet.contains(3)); // 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        print(myHashSet.set);
        System.out.println(myHashSet.contains(2)); // 返回 True
        myHashSet.remove(2);   // set = [1]
        print(myHashSet.set);
        System.out.println(myHashSet.contains(2)); // 返回 False ，（已移除）
    }
}
