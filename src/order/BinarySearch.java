package order;

public class BinarySearch {
    private static int[] arr = {1,2,8,8,8,100,158,1001,1009};
    public static void main(String[] args) {
        int index = findFirstGT(4);
        System.out.println(index);
    }
    //2分查找
    public static int simpleBinary(int value){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){//这里是<=，不要写成<,考虑数组只有一个元素的情况
            int mid = start + ((end - start)>>1);//移位运算比除法运算效率高，但是优先级要小于4则运算
            if(arr[mid] == value){
                return mid;
            }else if(arr[mid] > value){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
    //二分查找递归实现
    public static int simpleBinaryRec(int value){
        int start = 0;
        int end = arr.length-1;
        return binarySearch(start,end,value);
    }
    public static int binarySearch(int start, int end, int value){
        if(start > end){ return -1;}//之所以不加=，是考虑了数组只有一个元素的情况
        int mid = start + ((end - start) >> 1);
        if(arr[mid] == value){
            return mid;
        }else if(arr[mid] > value){
            return binarySearch(start, mid -1, value);
        }else {
            return binarySearch(mid + 1, end, value);
        }
    }
    //查询第一个满足添加的值位置
    public static int findFirstValue(int value){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = start + ((end - start) >> 1);
            if(arr[mid] > value){
                end = mid -1;
            }else if(arr[mid] < value){
                start = mid + 1;
            }else{
                if(mid == 0 || arr[mid - 1] != value){return mid;}//当mid为第一个，或者mid的前一个不等于value，则都能确定mid为第一个满足条件的位置
                end = mid - 1;
            }
        }
        return -1;
    }
    //查找最后一个满足条件的值位置，和以上方式类似
    public static int findLastValue(int value){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = start + ((end - start) >> 1);
            if(arr[mid] > value){
                end = mid - 1;
            }else if(arr[mid] < value){
                start = mid + 1;
            }else{
                if(mid == arr.length - 1 || arr[mid+1] != value){return mid;}
                start = mid + 1;
            }
        }
        return -1;
    }
    //找到第一个大于等于指定值的位置，和以上方式类似
    public static int findFirstGT(int value){
        int start = 0;
        int end = arr.length - 1;
        while(start <= end){
            int mid = start + ((end - start) >> 1);
            if(arr[mid] < value){
                start = mid + 1;
            }else{//arr[mid] >= value情况下
                if(mid == 0 || arr[mid-1] < value){return mid;}
                end = mid - 1;
            }
        }
        return -1;
    }
}
