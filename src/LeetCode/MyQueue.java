package LeetCode;

import java.util.Stack;

public class MyQueue {
	private Stack<Integer> stack1=new Stack<>();
	private Stack<Integer> stack2=new Stack<>();
	private int front;
    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	if(stack1.isEmpty())
    		front=x;
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stack2.isEmpty()){
        	while(!stack1.isEmpty()){
        		int top=stack1.pop();
        		stack2.push(top);
        	}
        }
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if(stack2.isEmpty())
    		return front;
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
    	 return stack1.isEmpty()&&stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */