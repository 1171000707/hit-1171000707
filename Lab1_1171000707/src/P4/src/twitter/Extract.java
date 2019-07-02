/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.src.twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	try {
        	Instant minTime = tweets.get(0).getTimestamp();
        	Instant maxTime = tweets.get(0).getTimestamp();
        	for(int i = 1;i<tweets.size();i++) {
        		Instant temp = tweets.get(i).getTimestamp();
        		if(temp.isBefore(minTime))
        			minTime = temp; //找到发推文的最早时间
        		if(temp.isAfter(maxTime))
        			maxTime = temp;	//找到发推文的最晚时间
        	}
        	Timespan timespan = new Timespan(minTime,maxTime);
        	return timespan; 	
        }catch(Exception e) {
        	throw new RuntimeException("not implemented");
        }
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	try {
    		Set<String> username = new HashSet<>();
    		Pattern pattern =Pattern.compile("(^|[^A-Za-z0-9_-]+)@([A-Za-z0-9_-]+)"); //构建正则表达式筛选用户名
    		for(int i = 0; i<tweets.size();i++) {
    			Matcher name = pattern.matcher(tweets.get(i).getText());
    			while(name.find()) {
    				String temp = new String(name.group(2).toString().toLowerCase());
    				username.add(temp); //将用户名以小写形式写入集合中
    			} 
    		}
    		return username;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	}
        
    }

}
