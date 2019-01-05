package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class MagicDictionary {
    /**
     * Initialize your data structure here.
     */
    Set<String> set = new HashSet<>();

    public MagicDictionary() {

    }

    /**
     * Build a dictionary through a list of words
     */
    public void buildDict(String[] dict) {
        for (String s : dict)
            set.add(s);
    }

    /**
     * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
     */
    public boolean search(String word) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            char old = sb.charAt(i);
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != old) {
                    sb.setCharAt(i, c);
                    if (set.contains(sb.toString()))
                        return true;
                }
                sb.setCharAt(i, old);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
