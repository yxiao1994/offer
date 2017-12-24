package LeetCode;

import java.util.HashMap;
import java.util.Set;

class WordDictionary {
	TrieNode root;
    class TrieNode{
    	private char c;
    	private HashMap<Character, TrieNode> child=new HashMap<>();
    	private boolean isEnd;
    	public void setEnd(){
    		isEnd=true;
    	}
    	public boolean isEnd(){
    		return isEnd;
    	}
    	public TrieNode(){
    		
    	}
    	public void put(char c){
    		child.put(c, new TrieNode());
    	}
    	public boolean contains(char c){
    		return child.containsKey(c);
    	}
    	public TrieNode get(char c){
    		return child.get(c);
    	}
    	public Set<Character> keyset(){
    		return child.keySet();
    	}
    }
    /** Initialize your data structure here. */
    public WordDictionary() {
        root=new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node=root;
        for(int i=0;i<word.length();i++){
        	char c=word.charAt(i);
        	if(!node.contains(c))
        		node.put(c);
        	node=node.get(c);
        }
        node.setEnd();
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }
    private boolean search(String word,int pos,TrieNode node){
    	if(pos==word.length()){
    		if(node.isEnd)
    			return true;
    		else return false;
    	}
    	char c=word.charAt(pos);
    	if(node.contains(c)){
    		if(pos==word.length()-1&&node.get(c).isEnd())
    			return true;
    		return search(word,pos+1,node.get(c));
    	}
    	else if(c=='.'){
    		Set<Character> set=node.keyset();
    		for(char key:set){
    			if(search(word, pos+1, node.get(key)))
    				return true;
    		}
    		return false;
    	}
    	else return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
