package order;
/**
 * 8大排序算法
 */
public class TypicalOrder {
    public static void main(String[] args) {
        int[] intArr = {7,8,1,4,3,5,5,6,9,0};
        //choiceOrder(intArr);
        //insertOrder(intArr);
        //bubblingOrder(intArr);
        //bucketOrder(intArr);
        //fastOrder(intArr,0, intArr.length - 1);
        //mergeSort(intArr);
        shellOrder(intArr);
        for(int i = 0; i < intArr.length; i++){
            System.out.print(intArr[i]);
        }
    }

    /**
     * 选择排序：每次重数列中选择一个最小的与指针处交换
     * @param intArr
     */
    public static void choiceOrder(int[] intArr){
        for(int i = 0;i < intArr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < intArr.length; j++) {
                if (intArr[j] < intArr[min]) {
                    min = j;
                }
            }
            if(min != i){
                int temp = intArr[min];
                intArr[min] = intArr[i];
                intArr[i] = temp;
            }
        }
    }

    /**
     * 插入排序：指针处数据与指针前面数列倒着比较，插入其中
     * @param intArr
     */
    public static void insertOrder(int[] intArr){
        for(int i = 1; i < intArr.length; i++){
            for(int j = i; j > 0; j--){
                if(intArr[j] >=  intArr[j - 1]){
                    break;
                }
                int temp = intArr[j];
                intArr[j] = intArr[j - 1];
                intArr[j - 1] = temp;
            }
        }
    }

    /**
     * 冒泡排序：大的往后面走
     */
    public static void bubblingOrder(int[] intArr){
        for(int i = 0; i < intArr.length - 1; i++){
            for(int j = 0; j < intArr.length - 1 -i; j++){
                if(intArr[j] > intArr[j + 1]){
                    int temp = intArr[j];
                    intArr[j] = intArr[j+1];
                    intArr[j+1] = temp;
                }
            }
        }
    }

    /**
     * 桶排序：桶排序要先知道该数列中的最大值来节约空间
     * @param intArr
     */
    public static void bucketOrder(int[] intArr){
        //先找出最大值
        int max = intArr[0];
        for(int i = 1; i < intArr.length; i++){
            if(max < intArr[i]){
                max = intArr[i];
            }
        }
        //建立一个以排序数列最大值+1为大小的的桶
        int a[] = new int[max+1];
        //开始装桶
        for(int i = 0; i < intArr.length; i++){
            a[intArr[i]]++;
        }
        //输出结果
        for(int j = 0;j < a.length; j ++){
            while(a[j] != 0){
                System.out.print(j);
                a[j]--;
            }
        }
        System.out.println();
    }

    /**
     * 快速排序：取一个值，使小于该值的全都堆到左边，大于等于该值的全都堆到右边，完成后对左右两边进行递归操作
     * @param intArr
     */
    public static void fastOrder(int[] intArr,int start, int end){
        if(start < end){//当前后索引碰头时结束递归，这里千万别用while
            int index = getIndex(intArr,start,end);
            fastOrder(intArr,start,index - 1);
            fastOrder(intArr,index + 1,end);
        }
    }
    //进行一遍排序，将数据以mid为标准分割在两边，并返回分割处索引
    public static int getIndex(int[] intArr,int start,int end){
        int mid = intArr[start];
        while(start < end){
            while(start < end && intArr[end] >= mid){
                end--;
            }
            if(start < end){
                intArr[start] = intArr[end];
                start++;
            }

            while(start < end && intArr[start] <= mid){
                start++;
            }
            if(start < end){
                intArr[end] = intArr[start];
                end --;
            }
        }
        //此时start=end，同时该处的值是重复的，填充上之前的挖槽数据
        intArr[start] = mid;
        return start;
    }

    /**
     * 归并排序：先不断差分，再分别排序，再合并排序后各集合
     * @param intArr
     */
    public static void mergeSort(int[] intArr){
        int[] tempArr = new int[intArr.length];//初始化一个临时数组，大小和待排序数组一样
        mSort(intArr,tempArr,0,intArr.length-1);
    }
    public static void mSort(int[] intArr,int[] tempArr, int start, int end){
        if(start >= end){
            return;
        }
        int mid = (start + end) / 2;
        //排序左边
        mSort(intArr,tempArr,start,mid);
        //排序右边
        mSort(intArr,tempArr,mid + 1,end);
        //合并
        merge(intArr,tempArr,start,mid,end);
    }
    public static void merge(int[] intArr, int[] tempArr, int start, int mid, int end){
        int i = start;
        int j = mid + 1;
        for(int k = start; k <= end; k++){
            tempArr[k] = intArr[k];
        }
        for(int k = start; k <= end; k++){
            if(i > mid){//左边耗尽
                intArr[k] = tempArr[j++];
            }else if(j > end){//右边耗尽
                intArr[k] = tempArr[i++];
            }else if(tempArr[i] < tempArr[j]){//左边小于右边，则选左边
                intArr[k] = tempArr[i++];
            }else{
                intArr[k] = tempArr[j++];
            }
        }
    }

    /**
     * 希尔排序
     * @param intArr
     */
    public static void shellOrder(int[] intArr){
        int gap = intArr.length/2;
        while(gap > 0){
            for(int i = gap;i < intArr.length; i++){
                int j = i;
                int curr = intArr[i];
                while(j >= gap && curr < intArr[j-gap]){
                    intArr[j] = intArr[j-gap];//小的赋值给大的
                    j = j - gap;
                }
                intArr[j] = curr;
            }
            gap = gap/2;
        }
    }
}
