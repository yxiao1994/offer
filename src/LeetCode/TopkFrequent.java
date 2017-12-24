package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopkFrequent {

	 HashMap<Integer,Integer> map = new HashMap<>();
	    public List<Integer> topKFrequent(int[] nums, int k) {
	        for(int num:nums)
	            map.put(num, map.getOrDefault(num,0) + 1);
	        int[] candidates = new int[map.size()];
	        int i = 0;
	        for(int key: map.keySet())
	            candidates[i++] = key;
	        int[] res = new int[k];
	        System.arraycopy(candidates, 0 , res, 0, k);
	        BuildHeap(res);
	        for(int j = k; j < candidates.length; j++){
	            if(map.get(candidates[j]) > map.get(res[0])){
	            	res[0] = candidates[j];
	                MaxHeapify(res, 0);
	            }
	        }
	        List<Integer> resList = new ArrayList<>();
	        for(int num: res)
	            resList.add(num);
	        return resList;
	    }
	    private void BuildHeap(int[] nums){
	        for(int i = nums.length/2 - 1; i >= 0; i--){
	            MaxHeapify(nums, i);
	        }
	    }
	    private void MaxHeapify(int[] nums, int i){
	        int n = nums.length;
	        while(i < n){
	            int child = 2 * i + 1;
	            if(child >= n)
	                break;
	            if(child + 1 < n && map.get(nums[child+1]) < map.get(nums[child]))
	                child++;
	            if(map.get(nums[child]) < map.get(nums[i])){
	                exchange(nums, i, child);
	                i = child;
	            }
	            else break;
	        }
	    }
	    private void exchange(int[] nums, int i, int j){
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {4,1,-1,2,-1,2,3, 3 ,3};
		TopkFrequent topkFrequent = new TopkFrequent();
		System.out.println(topkFrequent.topKFrequent(nums, 2));
	}

}
