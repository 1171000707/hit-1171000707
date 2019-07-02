package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	FriendshipGraph graph = new FriendshipGraph();
	Person a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14;
	//@Test
    public void testFriendshipGraph() {
    	a1 = new Person("a1"); //生成不同的person
		 a2 = new Person("a2");
		 a3 = new Person("a3");
		 a4 = new Person("a4");
		 a5 = new Person("a5");
		 a6 = new Person("a6");
		 a7 = new Person("a7");
		 a8 = new Person("a8");
		 a9 = new Person("a9");
		 a10 = new Person("a10");
		 a11 = new Person("a11");
		 a12 = new Person("a12");
		 a13 = new Person("a13");
		 a14 = new Person("a14");
   	graph.addVertex(a1); //向图中添加点
   	graph.addVertex(a2);
   	graph.addVertex(a3);
   	graph.addVertex(a4);
   	graph.addVertex(a5);
   	graph.addVertex(a6);
   	graph.addVertex(a7);
   	graph.addVertex(a8);
   	graph.addVertex(a9);
   	graph.addVertex(a10);
   	graph.addVertex(a11);
   	graph.addVertex(a12);
   	graph.addVertex(a13);
   	graph.addVertex(a14);
   	
   	graph.addEdge(a1,a2); //向图中添加边
   	graph.addEdge(a1,a3);
   	graph.addEdge(a3,a1);
   	graph.addEdge(a4,a2);
   	graph.addEdge(a1,a5);
   	graph.addEdge(a5,a1);
   	graph.addEdge(a6,a1);
   	graph.addEdge(a3,a6);
   	graph.addEdge(a6,a3);
   	graph.addEdge(a5,a6);
   	graph.addEdge(a6,a5);
   	graph.addEdge(a3,a5);
   	graph.addEdge(a4,a6);
   	graph.addEdge(a6,a4);
   	graph.addEdge(a7,a9);
   	graph.addEdge(a9,a7);
   	graph.addEdge(a9,a10);
   	graph.addEdge(a9,a8);
   	graph.addEdge(a8,a9);
   	graph.addEdge(a7,a8);
   	graph.addEdge(a8,a7);
   	graph.addEdge(a5,a11);
   	graph.addEdge(a11,a5);
   	graph.addEdge(a11,a12);
   	graph.addEdge(a12,a11);
   	graph.addEdge(a12,a13);
   	graph.addEdge(a13,a12);
   	graph.addEdge(a13,a14);
   	graph.addEdge(a14,a13);
   	graph.addEdge(a14,a8);
   	graph.addEdge(a8,a14);
    	assertEquals(graph.getDistance(a1, a2),1); //测试计算的距离是否正确，若正确，不仅说明计算距离的正确性，还可以说明
    	                                            //person()方法，addEdge()以及addVertex()的正确性
    	assertTrue(graph.getDistance(a2, a1) == -1);
    	assertTrue(graph.getDistance(a1, a4) == 3);
    	assertTrue(graph.getDistance(a3, a10) == 8);
    	assertTrue(graph.getDistance(a3, a4) == 2);
    	assertTrue(graph.getDistance(a7, a10) == 2);
    	assertTrue(graph.getDistance(a10, a7) == -1);
    	assertTrue(graph.getDistance(a8, a5) == 5);
    	assertTrue(graph.getDistance(a5, a2) == 2);
    	assertTrue(graph.getDistance(a1, a8) == 6);
    	assertTrue(graph.getDistance(a3, a2) == 2);
    	assertTrue(graph.getDistance(a10, a5) == -1);
    	assertTrue(graph.getDistance(a1, a10) == 8);
    	assertTrue(graph.getDistance(a11, a9) == 5);
    	assertTrue(graph.getDistance(a4, a10) == 9);
    	assertTrue(graph.getDistance(a3, a10) == 8);
    }
}
