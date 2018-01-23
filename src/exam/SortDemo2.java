package exam;

public class SortDemo2 {

	public static void main(String[] args) {
		int[] aa = {1,3,7,5,2,4};
		quick(aa);
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i]);
		}
	}
	
	public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(key<=array[hi]&&lo<hi){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&lo<hi){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }
	
	public static int partition2(int[] array, int lo, int hi){
		return hi;
	}
    
    public static void sort(int[] array,int lo ,int hi){
        if(lo<hi){
	        int index=partition(array,lo,hi);
	        sort(array,lo,index-1);
	        sort(array,index+1,hi); 
        }
    }

	 /**
	  * 快速排序
	  * @param numbers 带排序数组
	  */
     public static void quick(int[] numbers)
     {	
    	//查看数组是否为空
         if(numbers.length > 0){
        	 sort(numbers, 0, numbers.length-1);
         }
     }

}
