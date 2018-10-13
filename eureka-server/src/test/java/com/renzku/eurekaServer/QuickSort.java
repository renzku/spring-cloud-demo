package com.renzku.eurekaServer;
import java.util.Arrays;

/**
 * 基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此实现整个数据变成有序序列。
 */
public class QuickSort {
    private static int MAXNUM = 10;
    public static void main(String[] args) {
        int[] array = new int[MAXNUM];
        for(int i = 0; i < MAXNUM; i++){
            array[i] = (int) (100 * Math.random() + 1);
        }
        System.out.println("Original array:");//打印排序前的数组
        System.out.println(Arrays.toString(array));

        System.out.println("QuickSort:");//打印排序后的数组
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int left, int right){
        if(left < right){//如果只有1个元素就不执行，2个及以上元素才会执行
            int pivot = array[left];	//设置为基准值
            int low = left;		//左指针
            int high = right;	//右指针
            while(low < high){
                while(low < high && array[high] >= pivot)	//右指针指向的值大于等于基准值
                    high--;	//右指针左移，直到指向的值小于基准值
                array[low] = array[high];	//将左指针指向的值和该值交换

                while(low < high && array[low] <= pivot)	//左指针指向的值小于等于基准值
                    low++;	//左指针右移，直到指向的值大于基准值
                array[high] = array[low];	//将该值与右指针指向的值交换
            }
            array[low] = pivot;	//将基准值填入正确的位置，这样得到基准值左边的数据都小于它，右边同理
            System.out.println("pivot = " + pivot + "," + Arrays.toString(array));
            quickSort(array, left, low - 1);
            quickSort(array, low + 1, right);
        }
    }
}
