package exam;

public class SortDemo {

	public static void main(String[] args) {
		int[] aa = {1,3,2,5,6,4,8,1,20};
		//Buddle(aa);
		quick(aa);
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i]);
		}
	}
	
	public static void Buddle(int[] aa){
		int m;
		for (int i = 0; i < aa.length; i++) {
			for (int j = 0; j < aa.length-i-1; j++) {
				if(aa[j]<aa[j+1]){
					m=aa[j];
					aa[j]=aa[j+1];
					aa[j+1]=m;
				}
			}
		}
		for (int i = 0; i < aa.length; i++) {
			System.out.println(aa[i]);
		}
	}
	
	/**
	 * 把整个序列看做一个数组，把第零个位置看做中轴，和最后一个比，如果比它小交换，比它大不做任何处理；
	 * 交换了以后再和小的那端比，比它小不交换，比他大交换。这样循环往复，一趟排序完成，左边就是比中轴小的，
	 * 右边就是比中轴大的，然后再用分治法，分别对这两个独立的数组进行排序。
	 * 
     * 查找出中轴（默认是最低位low）的在numbers数组排序后所在位置
     * 
     * @param numbers 带查找数组
     * @param low   开始位置
     * @param high  结束位置
     * @return  中轴所在位置
     */
    public static int getMiddle(int[] numbers, int low,int high)
    {
        int temp = numbers[low]; //数组的第一个作为中轴
        while(low < high){
	        while(numbers[high] > temp){
	            high--;
	        }
	        numbers[low] = numbers[high];//比中轴小的记录移到低端
	        while(numbers[low] < temp){
	            low++;
	        }
	        numbers[high] = numbers[low] ; //比中轴大的记录移到高端
        }
        numbers[low] = temp ; //中轴记录到尾
        return low ; // 返回中轴的位置
    }
    
    /**
     * @param numbers 带排序数组
     * @param low  开始位置
     * @param high 结束位置
     */
    public static void quickSort(int[] numbers,int low,int high)
    {
        if(low < high){
        	int middle = getMiddle(numbers,low,high); //将numbers数组进行一分为二
        	quickSort(numbers, low, middle-1);   //对低字段表进行递归排序
        	quickSort(numbers, middle+1, high); //对高字段表进行递归排序
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
        	 quickSort(numbers, 0, numbers.length-1);
         }
     }

}
