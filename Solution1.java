import java.util.ArrayList;

public class Solution1 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer>list=new ArrayList<>();
        if(input.length<k||k==0){
            return list;
        }
        int len=input.length;
        for(int i=len/2-1;i>=0;i--)
            HeapSort(input,i,len-1);
        for(int i=len-1;i>len-k;i--) {
            int temp = input[0];
            list.add(temp);
            input[0]=input[i];
            input[i]=temp;
            HeapSort(input,0,i-1);
        }
          return list;
    }
    //堆排
    public static void HeapSort(int [] a,int pos,int len){
        int temp;
        int child;
        for(temp=a[pos];2*pos+1<=len;pos=child){
            child=2*pos+1;
            if(child<len&&a[child]>a[child+1])
                child++;
            if(a[child]<temp)
                a[pos]=a[child];
            else
                break;
        }
        a[pos]=temp;
    }
}
