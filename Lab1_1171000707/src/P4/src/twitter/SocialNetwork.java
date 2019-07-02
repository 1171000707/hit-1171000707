/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.src.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	try {
    		Map<String,Set<String>> follow = new HashMap<>();
    		Set<String> person = new HashSet<>();//存放所有的作者
    		for(int i = 0;i<tweets.size();i++) {
    			person.add(tweets.get(i).getAuthor().toLowerCase());	
    		}
    		for(String name: person) {
    			List<Tweet> works = Filter.writtenBy(tweets,name); //找的每个作者写过的推文
    			Set<String> re = Extract.getMentionedUsers(works);
    			if(re.contains(name)) {
    				re.remove(name);//若返回的Set中包括作者本人，则将其去掉
    			}
    			follow.put(name,re);//添加推文中被@的人
    		}
    		return follow;
    	}catch(Exception e) {
    		 throw new RuntimeException("not implemented");
    	}
       
    }
    //功能：返回与名为name的作者写的推文带有相同话题的推文List
    //辅助MyGuessFollowsGraph方法找到和每个用户相关联的人
    public static List<Tweet> MyContaining(List<Tweet> tweets, List<String> words,String name) {
    	try {
    		List<Tweet> result = new ArrayList<Tweet>();
    		
    		Pattern pattern =Pattern.compile("#([A-Za-z]+)");//构建正则表达式，用来筛选除作者name的推文外其他推文的标签
    		for(int i = 0;i<tweets.size();i++) {
    			ArrayList<String> s = new ArrayList<>();
    			if(!tweets.get(i).getAuthor().equals(name)) {
    				Matcher topic = pattern.matcher(tweets.get(i).getText());
        			while(topic.find()) {
        				String temp = new String(topic.group(0).toString().toLowerCase());
        				s.add(temp); //将话题以小写形式写入集合中
        			}
        		end:	for(int k=0;k<s.size();k++){ //将作者name所带的标签与其他作者的标签进行比较，找到有相同标签的推文
        				for(int j = 0;j <words.size();j++) {
        					if(s.get(k).equalsIgnoreCase(words.get(j))) {
        						result.add(tweets.get(i));
        						break end;
        					}
        				}
        			}	
        		}
    			}	
       		return result;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	} 
    }
    //功能：找到每个用户可能追随的人，包括作者在推文中@的用户以及
    //和作者的推文带有相同标签的用户（若两人的推文带有相同标签，则两人有可能会互相影响，或者互相关注）
   public static Map<String,Set<String>> MyGuessFollowsGraph(List<Tweet> tweets){
	   try {
   		Map<String,Set<String>> Myfollow = new HashMap<>();
   		Set<String> person = new HashSet<>();//用来存放所有的作者
   		for(int i = 0;i<tweets.size();i++) {
   			person.add(tweets.get(i).getAuthor().toLowerCase());	
   		}
   		for(String name: person) {
   			List<Tweet> works = Filter.writtenBy(tweets,name); //找到每个作者写过的推文
   			Pattern pattern =Pattern.compile("#([A-Za-z]+)"); //构建正则表达式筛选话题
    		for(int i = 0; i<works.size();i++) {
    			Matcher topic = pattern.matcher(works.get(i).getText());
    			List<String> words = new ArrayList<>();
    			while(topic.find()) {
    				String temp = new String(topic.group(0).toString().toLowerCase());
    				words.add(temp); //将话题以小写形式写入集合中
    			}
    			Set<String> relation = Extract.getMentionedUsers(works);//先将作者推文中@的人加入relation
    			if(words.size() != 0) { //如果作者name的推文中带有标签，则找到和作者name带有相同标签的人加入到relation中
    				List<Tweet> sametopic = MyContaining(tweets,words,name);
        			for(int j = 0;j <sametopic.size();j++) {
        				relation.add(sametopic.get(j).getAuthor());	
        			}
    			}
    			if(relation.contains(name)) {
    				relation.remove(name);//如果列表中含有作者本人，则移除
    			}
    			Myfollow.put(name,relation);//添加推文中被@的人以及和作者推文带有相同话题的人
    		}	
   		}
   		return Myfollow;
   	}catch(Exception e) {
   		 throw new RuntimeException("not implemented");
   	}  
   }
	/**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     */
   public static List<String> influencers(Map<String, Set<String>> followsGraph) {
    	try {
    		List<String> influencers = new ArrayList<>();
    		Map<String,Integer> influenceKey = new HashMap<>();//存放每个人的影响力
    		Set<String> names = followsGraph.keySet(); //得到所有的作者名
    		for(String author:names) {
    			for(String person:followsGraph.get(author)) {//对某个作者的所有@的人加入计算影响力的map中
    				if(!influenceKey.containsKey(person)) {
    					influenceKey.put(person,1);//若该人第一次被@，则影响力为1
    				}
    				else {
    					influenceKey.put(person,influenceKey.get(person)+1);//不是第一次被@，则在原来的影响力上再加1
    				}
    			}
    		}
    		Set<String> peoples = influenceKey.keySet();//得到所有的用户
    		ArrayList<String> key = new ArrayList<String>();//得到所有的用户
    		for(String people: peoples) {
				key.add(people);
			}
    		String temp;
    		for(int i = 0;i<key.size()-1;i++) { //给得到的每个用户的影响力按降序排序
    			int k = i;
    			for(int j =i;j<key.size();j++) {
    				if(influenceKey.get(key.get(j)) > influenceKey.get(key.get(k)))
    					k = j;
    			}
    			if(k != i) {
    				temp = key.get(k);
    				key.set(k,key.get(i));
    				key.set(i,temp);	
    			}
    		}
    		for(int i =0;i<key.size();i++) {
    			influencers.add(key.get(i));
    		}
    		return influencers;
    	}catch(Exception e) {
    		throw new RuntimeException("not implemented");
    	}
        
    }
}
