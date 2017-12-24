package LeetCode;

import java.util.Stack;

public class MinStack {
	 /** initialize your data structure here. */
	Stack<Integer> stack;
	Stack<Integer> minstack;
    public MinStack() {
        stack=new Stack<>();
        minstack=new Stack<>(); 
    }
    
    public void push(int x) {
        stack.push(x);
        if(minstack.isEmpty()||minstack.peek()>=x)
        	minstack.push(x);
    }
    
    public void pop() {
        if(stack.peek().equals(minstack.peek()))
        	minstack.pop();
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinStack minStack=new MinStack();
		minStack.push(512);
		minStack.push(-1024);
		minStack.push(-1024);
		minStack.push(512);
		minStack.pop();
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.getMin());
	}

}
