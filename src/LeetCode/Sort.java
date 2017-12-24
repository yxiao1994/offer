package LeetCode;

import java.util.Arrays;
import java.util.Random;

public class Sort {
	/**
	 * 堆排序
	 * @param a
	 */
    public void heapSort(int[] a){
    	BuildHeap(a);
    	int n=a.length;
    	for(int i=n-1;i>0;i--){
    		exchange(a, 0, i);
    		MaxHeapify(a, 0, i);
    	}
    }
   /**
    * 建堆
    * @param a
    */
    private void BuildHeap(int[] a){
    	int n=a.length;
    	for(int i=n/2-1;i>=0;i--){
    		MaxHeapify(a, i, n);
    	}
    }
    private int leftchild(int n){
    	return 2*n+1;
    }
    /**
     * 维护最大堆
     * @param a
     * @param i
     * @param n
     */
    private void MaxHeapify(int[] a,int i,int n){
    	while(i<n){
    		int child=leftchild(i);
    		if(child>=n)
    			break;
    		if(child+1<n&&a[child+1]>a[child])
    			child++;
    		if(a[child]>a[i]){
    			exchange(a, i, child);
    			i=child;
    		}
    		else break;    		
    	}   	
    }
    private void exchange(int[] a,int i,int j){
    	int temp=a[i];
    	a[i]=a[j];
    	a[j]=temp;
    }
    /**
     * 冒泡排序
     * @param a
     */
    public void BubbleSort(int[] a){
    	int n=a.length;
    	for(int i=0;i<n-1;i++){
    		for(int j=0;j<n-i-1;j++){
    			if(a[j]>a[j+1])
    				exchange(a, j, j+1);
    		}
    	}
    }
    /**
     * 快速排序
     * @param a
     */
    public void QuickSort(int[] a){
    	QuickSort(a,0,a.length-1);
    }
    private void QuickSort(int[] a,int p,int r){
    	if(p<r){
    		int q = partition(a,p,r);
    		QuickSort(a, p, q-1);
    		QuickSort(a, q+1, r);
    	}
    }
    private int partition(int[] a,int p,int r){
    	int i=p-1;
    	for(int j=p;j<r;j++){
    		if(a[j]<=a[r]){
    			i++;
    			exchange(a, i, j);
    		}
    	}
    	exchange(a, i+1, r);
    	return i+1;
    }
    /**
     * 归并排序
     * @param a
     */
    public void MergeSort(int[] a){
    	MergeSort(a,0,a.length-1);
    }
    private void MergeSort(int[] a,int p,int r){
    	if(p<r){
    		int q=(p+r)/2;
    		MergeSort(a,p,q);
    		MergeSort(a, q+1, r);
    		merge(a,p,q,r);
    	}
    }
    private void merge(int[] a,int p,int q,int r){
    	int n1=q-p+1;
    	int n2=r-q;
    	int[] L=new int[n1+1];
    	int[] R=new int[n2+1];
    	L[n1]=R[n2]=Integer.MAX_VALUE;
    	System.arraycopy(a, p, L, 0, n1);
    	System.arraycopy(a, q+1, R, 0, n2);
    	for(int i=0,j=0,k=p;k<=r;k++){
    		if(L[i]<R[j]){
    			a[k]=L[i];
    			i++;
    		}
    		else{
    			a[k]=R[j];
    			j++;
    		}
    	}
    }
    /**
     * 插入排序
     * @param a
     */
    public void InsertionSort(int[] a){
    	int n=a.length;
    	for(int i=1;i<n;i++){
    		int key=a[i];
    		int j=i-1;
    		while(j>=0&&a[j]>key){
    			a[j+1]=a[j];
    			j--;
    		}
    		a[j+1]=key;
    	}
    }
    /**
     * 数组中前k小的数
     * @param a
     * @param k
     * @return
     */
    public int[] findtopk(int[] a,int k){
    	int n=a.length;
    	if(k<=0||k>n)
    		throw new RuntimeException();
    	int[] res=new int[k];
    	System.arraycopy(a,0, res, 0, k);
    	BuildHeap(res);
    	for(int i=k;i<n;i++){
    		if(a[i]<res[0]){
    			res[0]=a[i];
    			MaxHeapify(res,0,k);
    		}
    	}
    	return res;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random=new Random();
		int[] a=new int[100];
		for(int i=0;i<100;i++)
			a[i]=random.nextInt(100);
		Sort sort=new Sort();
		//System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(sort.findtopk(a, 5)));
		sort.BubbleSort(a);
		System.out.println(Arrays.toString(a));
	}

}
