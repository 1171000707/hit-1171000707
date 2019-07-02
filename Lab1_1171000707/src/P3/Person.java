package P3;

import java.util.ArrayList;

public class Person {

    private String name ; //名字
    private int isvisit = 0; //广搜时是否访问该人
    private int distance = 0; //该人距离第一个人的最短距离
    ArrayList<String> friend = new ArrayList<>(); //该人的所有朋友
    static ArrayList<String> person = new ArrayList<>();
    private int alone = 1; //该人是否是孤立点
    public Person(String name){ 
        this.name = name;
        if(person.contains(name)) {
        	System.out.print("有两个重名的人!");
        	System.exit(0);
        }
        else {
        	person.add(name);
        }
    }
    public String getName() {
    	return name; //返回名字
    }
    public int isVisit() {
    	return isvisit; //广搜时返回该点是否被访问过
    }
    public void Visit() {
    	this.isvisit = 1; //标记该点被访问过
    }
    public void unVisit() {
    	this.isvisit = 0; //标记该点没被访问过
    }
    public int getDistance() {
    	return distance; //返回距离
    }
    public void setDistance() {
    	this.distance = 0;//标记距离为0
    }
    public void CalculateDistance() {
    	this.distance++; //自身距离加1
    }
    public void Setdistance(int dis) {
    	this.distance = dis+1; //利用用到达该点的前一个点给该点距离赋值
    }
    public int isAlone() {
    	return alone;//返回是否为孤立点
    }
    public void Alone() {
    	this.alone = 0;//标记该点不是孤立点
    }
}
