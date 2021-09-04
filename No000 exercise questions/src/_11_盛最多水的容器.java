/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 * @author: HatcherCheung
 * Date:  2021/9/4
 */
public class _11_盛最多水的容器 {
    public int maxArea(int[] height) {
        // 双指针法
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        // 记录当前面积
        int currentMax = 0;
        // 记录最大面积
        int max = 0;
        // 如果左右指针不相遇就一直循环
        while (leftIndex < rightIndex) {
            // 因为水的面积由最短的长度决定，所以应该用最短的高度 * 两指针的距离
            currentMax = Math.min(height[leftIndex],height[rightIndex]) * (rightIndex - leftIndex);
            // 比较最大面积和当前面积的大小，取最大的
            max = Math.max(currentMax,max);
            // 哪边短就移哪边
            if (height[leftIndex] <= height[rightIndex]) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        return max;
    }
}
