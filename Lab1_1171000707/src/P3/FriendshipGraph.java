package P3;
import java.util.*;
public class FriendshipGraph {
	public ArrayList<Person> fgraph = new ArrayList<>();//存储关系图
	public void addVertex(Person name) {  //添加点
		if(fgraph.contains(name)) {
			System.out.println("此人已出现在关系网中！");
			System.exit(0);
		}
		 fgraph.add(name);	 
	}
    public void addEdge(Person name1,Person name2) {  //添加边
    	
    	if(name1.friend.contains(name2.getName())) {
    		System.out.println("关系网中两人之间已建立该条边！");
    		System.exit(0);
    	}
    	if(name1.equals(name2)) {
    		System.out.println("两人是同一个人，输入错误！");
    		System.exit(0);
    	}		
    	name1.friend.add(name2.getName());
    	name2.Alone(); //表示第二个人在图中不是孤立点
    }
    public int getDistance(Person name1,Person name2) { //计算两人距离
    	for(int i =0 ;i <fgraph.size();i++) {
    		fgraph.get(i).unVisit();
    		fgraph.get(i).setDistance();
    	}
    	Queue<Person> queue = new LinkedList<>();
    	//System.out.println("两人"+name1.name+name2.name);
    	if(name2.isAlone() == 1) { //name2是孤立点，没有朋友
    		return -1;
    	}
    	else if(name1.friend.contains(name2.getName())) { //name1和name2是直接朋友
    		return 1; 
    	}
    	else if(name1.getName().equals(name2.getName()) == true) {  //name1和name2是同一个人
    		return 0;
    	}
    	else { //建立队列利用广搜算出两者的最短距离
    		name1.Visit();
    		for(int i =0;i<fgraph.size();i++) {
    			if(name1.friend.contains(fgraph.get(i).getName())) { //两人不是直接朋友，name1的朋友入队
    				queue.add(fgraph.get(i));
    				fgraph.get(i).Visit();
    				fgraph.get(i).CalculateDistance(); //name1与name2的距离增加1
    			}
    		}
    		while(queue.size() > 0 ) {
    			Person head = queue.poll(); //队首出队
    			if(head.friend.contains(name2.getName())) { //若原队首的朋友包括name2，则name1与name2的距离在原来基础上加一并返回结果
					head.CalculateDistance();
    				return head.getDistance();
				}
    			else {
    				for(int i = 0;i<fgraph.size();i++) { //若原队首的朋友不包含name2，则队首朋友中未入队的人入队，name1与name2的距离加1
        				if(head.friend.contains(fgraph.get(i).getName()) && fgraph.get(i).isVisit() == 0) {
        					queue.add(fgraph.get(i));
        					fgraph.get(i).Visit();
        					fgraph.get(i).Setdistance(head.getDistance());
        				}	
        			}
    			}
    		}	
    	}
		return -1;//若name2不是孤立点，并且name1和name2没有路径可以连接二者
    	
    }
	public static void main(String[] args) {

		FriendshipGraph graph = new FriendshipGraph();
	    Person rachel = new Person("Rachel"); 
	    Person ross = new Person("Rachel"); 
	    Person ben = new Person("Ben");
	    Person kramer = new Person("Kramer");
	    graph.addVertex(rachel);
	    graph.addVertex(ross);
	    graph.addVertex(ben);
	    graph.addVertex(kramer);
	    graph.addEdge(rachel, ross);
	    graph.addEdge(ross, rachel);
	    graph.addEdge(ross, ben);
	    graph.addEdge(ben, ross);
	    System.out.println(graph.getDistance(rachel, ross));
	    System.out.println(graph.getDistance(rachel, ben)); 
	    System.out.println(graph.getDistance(rachel, rachel));
	    System.out.println(graph.getDistance(rachel, kramer)); 
		
	   	}
}
