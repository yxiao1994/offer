package LeetCode;

import java.util.HashMap;

public class LRUCache {
	class Node{
		Node prev;
		Node next;
		int key;
		int value;
		public Node(int key,int value){
			this.key=key;
			this.value=value;
			this.prev=null;
			this.next=null;
		}
	}
	int capacity;
	Node head=new Node(0, 0);
	Node tail=new Node(0, 0);
	HashMap<Integer, Node> map=new HashMap<>();
    public LRUCache(int capacity) {
    	this.capacity=capacity;
        head.next=tail;
        tail.prev=head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
        	return -1;
        Node curr=map.get(key);
        int res=curr.value;
        curr.next.prev=curr.prev;
        curr.prev.next=curr.next;
        moveTotail(curr);
        return res;
    }
    
    public void put(int key, int value) {
        if(get(key)!=-1){
        	map.get(key).value=value;
            return;
        }
        if(map.size()==capacity){
        	map.remove(head.next.key);
        	head.next=head.next.next;
        	head.next.prev=head;
        }
        Node node=new Node(key, value);
        map.put(key, node);
        moveTotail(node);
        
    }
    private void moveTotail(Node curr){
    	Node p=tail.prev;
    	p.next=curr;
    	curr.prev=p;
        curr.next=tail;
        tail.prev=curr;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */