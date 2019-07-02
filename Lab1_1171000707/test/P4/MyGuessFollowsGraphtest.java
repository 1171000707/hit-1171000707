package P4;
import static org.junit.Assert.*;

import java.time.Instant;
import java.util.*;
import org.junit.Test;

import P4.src.twitter.SocialNetwork;
import P4.src.twitter.Tweet;
public class MyGuessFollowsGraphtest {
	private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d7 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d8 = Instant.parse("2016-02-17T11:00:00Z");
	private static final Tweet tweet1 = new Tweet(1, "a1", "@a2 @a3 is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "a2", "@a8 rivest talk in 30 minutes #hype #rivest", d2);
    private static final Tweet tweet3 = new Tweet(3, "a3", "is it reasonable to talk about rivest so much #hype?", d3);
    private static final Tweet tweet4 = new Tweet(4, "a4", "rivest talk in 30 minutes #hype #rivest", d4);
    private static final Tweet tweet5 = new Tweet(5, "a5", "is it reasonable to talk about rivest so much?", d5);
    private static final Tweet tweet6 = new Tweet(6, "a6", "@a7 rivest talk in 30 minutes #hype", d6);
    private static final Tweet tweet7 = new Tweet(7, "a7", "@a2 is it reasonable to talk about rivest so much?", d7);
    private static final Tweet tweet8 = new Tweet(8, "a8", "@a4 rivest talk in 30 minutes #rivest", d8);
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    @Test
    public void testGuessFollowsGraphEmpty() {
    	List<Tweet> tweets = new ArrayList<>();
    	tweets.add(tweet1); //将所有用户的推文加入List
    	tweets.add(tweet2);
    	tweets.add(tweet3);
    	tweets.add(tweet4);
    	tweets.add(tweet5);
    	tweets.add(tweet6);
    	tweets.add(tweet7);
    	tweets.add(tweet8);
        Map<String, Set<String>> followsGraph = SocialNetwork.MyGuessFollowsGraph(tweets);
        assertTrue(followsGraph.get(tweet1.getAuthor()).size() == 2); //测试每个用户追随的人的个数是否和期望的个数相同
        assertTrue(followsGraph.get(tweet2.getAuthor()).size() == 4);
        assertTrue(followsGraph.get(tweet3.getAuthor()).size() == 3);
        assertTrue(followsGraph.get(tweet4.getAuthor()).size() == 4);
        assertTrue(followsGraph.get(tweet5.getAuthor()).size() == 0);
        assertTrue(followsGraph.get(tweet6.getAuthor()).size() == 4);
        assertTrue(followsGraph.get(tweet7.getAuthor()).size() == 1);
        assertTrue(followsGraph.get(tweet8.getAuthor()).size() == 2);
    }    
}
