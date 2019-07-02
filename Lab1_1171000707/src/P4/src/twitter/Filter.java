/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.src.twitter;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
    	try {
    		List<Tweet> sameName = new ArrayList<>();//存放username写的所有推文
    		for(int i = 0;i <tweets.size();i++) {
    			if(tweets.get(i).getAuthor().contentEquals(username)) { //利用tweet的得到作者名字的方法直接得到每篇推文的
    				                                                   //作者，然后和username比较，相同则可将该篇推文加入列表
    				sameName.add(tweets.get(i));
    			}
    		}
    		return sameName;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	}  
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
    	try {
    		List<Tweet> time = new ArrayList<>();//存放符合要求的推文
    		boolean a,b;
    		for(int i = 0;i <tweets.size();i++) {//判断每篇推文的发表时间是否在timespan的时间段内，若在，则加入集合
    			a = timespan.getStart().isBefore(tweets.get(i).getTimestamp());
    			b = timespan.getEnd().isAfter(tweets.get(i).getTimestamp());
    			if(a && b) {
    				time.add(tweets.get(i));
    			}
    		}
    		return time;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	}   
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
    	try {
    		List<Tweet> result = new ArrayList<Tweet>();//存放带有words里相同字符串的推文
    		String []s;
    		for(int i = 0;i<tweets.size();i++) {
    			String tweet = tweets.get(i).getText();
    			s= tweet.split("\\W+"); //将每一篇推文分割成一个个单词字符串
               end: for(int k=0;k<s.length;k++)  {
    				for(int j = 0;j <words.size();j++){ //然后在这些单词中匹配是否有和words中相同的字符串
    					if(s[k].equalsIgnoreCase(words.get(j))) {
    						result.add(tweets.get(i));//若找到有相同的字符串，将推文加入集合，并结束本轮循环，进入下一次循环
    						break end;
    					}
    				}
    			}
    			
    		}
       		return result;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	} 
    }
   
}
