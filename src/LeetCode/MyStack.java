package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
	Queue<Integer> queue1=new LinkedList<>();
	Queue<Integer> queue2=new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while(!queue1.isEmpty())
        	queue2.offer(queue1.poll());
        Queue<Integer> temp=queue1;
        queue1=queue2;
        queue2=temp;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
       return queue1.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
