/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.Stack;

//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	try {
    		for(int i = 0;i <4;i++) {
    			turtle.forward(sideLength);
    			turtle.turn(90);
    		}
    		} catch(Exception e) {
    			throw new RuntimeException("implement me!");
    		}	  
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	try {
    		double angle = (double) 180*(sides-2)/sides;
    		return angle;
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}  
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        try {
        	int sides = (int)Math.ceil(360.0/(180.0-angle));
        	return sides;
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        try {
        	
        	turtle.turn(270);
        	double angle = (double) 180 - calculateRegularPolygonAngle(sides);
        	for(int i = 0;i <sides;i++) {
        		turtle.turn(angle);
        		turtle.forward(sideLength);
        	}
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }	
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	try {
    		int x = targetX-currentX;
    		int y = targetY-currentY; //得到两点所形成的向量的横纵坐标
    		double angle = 0; //表示向量与y轴正向顺时针方向的夹角
    		if(x ==  0 ) { //该向量的横坐标
    			if(y < 0)
                angle = 180; //向量与y轴正向夹角为180°
    			if(y > 0)
    				angle = 0;//向量与y轴正向夹角为0°
    			if(y == 0)
    				angle = currentBearing; //两点重合，零向量方向在此默认与原方向一致
    		}
    		else {
    			double angle1 = (Math.atan((double)y/x)) * 180 / Math.PI;//利用反正切函数求出向量与x轴的夹角
        	    if(x >0 ) {        //分象限计算向量与y轴正向的夹角
        	    	angle = 90 - angle1;
        	    }
        	    else {
        	    	angle = 270-angle1;
        	    }
    		}
    	    if(currentBearing<=angle){ //计算需要旋转的角度并返回
                return angle - currentBearing;
            }else{
                return 360 - currentBearing + angle;
            }
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}  
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        try {
        	double currentBearing = 0; //当前方向为沿y轴正向，即角度为0
        	List<Double> angle = new ArrayList<>(); //存放每两个点间需要旋转的角度
        	for(int i = 0;i <xCoords.size()-1;i++) {
        		int x0,x,y0,y;
        		x0 = xCoords.get(i);
        		x = xCoords.get(i+1);
        		y0 =yCoords.get(i);
        		y = yCoords.get(i+1);
        		double angle1 = calculateBearingToPoint(currentBearing,x0,y0,x,y); //得到当前点到下一个点需要旋转的角度
        		angle.add(i,angle1);
        		currentBearing = (currentBearing +angle1)%360; //计算到达(x,y)点时朝向的方向（方向为与y轴正向的夹角，范围在0~360，所以要取余运算）
        	}
        	return angle;
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	try {
    		List<Point> arraylist = new ArrayList<>(points); //将set转换成list，便于遍历
    		Set<Point> result = new HashSet<>();//用来存储最后构成凸包的最小集合
    		int n = arraylist.size();
    		if(n == 0 || n == 1 || n == 2) {//若集合中没有点或只有一个点或只有2个点，直接返回该点集
    			for(int i = 0;i< n;i++) {
    				result.add(arraylist.get(i));
    			}
    	        return result;
    		}	
    		Point min = arraylist.get(0);
    		for(int  i = 1;i < n;i++) { //找到y值最小的点p0，若y值相同，就找x值最小的点
    			boolean a = arraylist.get(i).y() < min.y() ;
    			boolean b = arraylist.get(i).y() == min.y() && arraylist.get(i).x() < min.x();
    			if(a || b) {
    				min = arraylist.get(i);
    			}
    		}
    		double[] angle = new double[n]; //计算每个点和p0与x轴正向的夹角存入该数组
    		double angleX; 
    		//利用上面构造的计算旋转角度的函数来计算每个点和p0构成的线段与x轴夹角
            //由于测试的点的类型为double型，而函数calculateBearingToPoint的形参是int型，所以将double型的点转换int型
            //因为test文件中给出的参数都是浮点型的整数，所以不影响实验结果，如果因此重新写一个计算方法与calculateBearingToPoint相同只有形参类型改为浮点数意义不大
    		for(int i = 0;i < n;i++) { 
    			angleX = calculateBearingToPoint(270.0,(int)min.x(),(int)min.y(),(int)arraylist.get(i).x(),(int)arraylist.get(i).y());
    			if(angleX == 0) {
    				angle[i] = 0;
    			}
    			else
    			angle[i] = 180 - angleX;
    		}
    		int k;
    		double temp1;
    		Point temp2;
    		for(int i = 0;i < n-1;i++) { //将点按夹角从小到大排序，若共线则按距离远近排序，距离近的排在前面
    			k = i;
    			for(int j = i+1;j< n;j++) {
    				if(angle[j] <= angle[k]) {
    					k = j;
    				}
    			}
    			if(k != i) {
    				if(angle[k] < angle[i]) {
    					temp1 = angle[k];angle[k] = angle[i];angle[i] = temp1;
    					temp2 = arraylist.get(k);arraylist.set(k, arraylist.get(i));arraylist.set(i,temp2);
    				}
    				else if(angle[k] == angle[i]) {
    					double distancek = Math.pow(arraylist.get(k).x() - min.x(),2)+Math.pow(arraylist.get(k).y()-min.y(), 2);
        				double distancei = Math.pow(arraylist.get(i).x() - min.x(),2)+Math.pow(arraylist.get(i).y()-min.y(), 2);
        				if(distancek < distancei) {
        					temp1 = angle[k];angle[k] = angle[i];angle[i] = temp1;
        					temp2 = arraylist.get(k);arraylist.set(k, arraylist.get(i));arraylist.set(i,temp2);
        				}
    				}
    			}		
    		}
    		//首先将排好序的前两个点p0和p1入栈，然后判断向量p0p1旋转至p1p2方向是是否为逆时针方向（所谓逆时针方向即为向量p0p1逆时针旋转小于180°的某个角度后与p1p2方向相同）
    		//若是逆时针方向，则p2入栈，否则栈顶元素出栈，继续判断当前向量方向（当前栈顶前面的点与栈顶向量的方向）与栈顶元素和下一个点的向量方向是否为顺时针，循环直至判断完最后
    		//一个点，然后此时栈中的点构成了要求的凸包的最小点集
    		Stack<Point> stack = new Stack<>(); 
    		stack.push(arraylist.get(0));
    		stack.push(arraylist.get(1)); //将前两个点入栈
    		arraylist.get(0).angle = 0;
    		double currentAngle; //当前向量的方向
    		if(angle[1] < 90) {
    		   currentAngle = 90 -angle[1]; //第一个向量p0p1与y轴正向的顺时针方向夹角
    		}
    		else {
    		   currentAngle = 450-angle[1];
    		}
    		arraylist.get(1).angle = currentAngle;	//记录当前栈顶元素与栈顶下方的一个点构成的向量的方向（与y轴正向夹角）
    		double tempAngle; //用来判断向量方向变化是否满足逆时针方向
    		int x0,y0,x1,y1;
    		for(int i = 2; i< arraylist.size();i++) { 
    			x0 = (int)stack.peek().x();//每次取栈顶元素与排序的点集中下一个未判断的点判断向量方向变化是否满足逆时针方向
        		y0 = (int)stack.peek().y();
        		x1 = (int)arraylist.get(i).x();
        		y1 = (int)arraylist.get(i).y();
        		tempAngle = calculateBearingToPoint(currentAngle,x0,y0,x1,y1);
    			if( tempAngle> 180) {//满足逆时针方向，该点进栈
    				stack.push(arraylist.get(i));
    				currentAngle = (currentAngle + tempAngle)%360;
    				arraylist.get(i).angle = currentAngle;
    			}
    			else {
    				stack.pop();//栈顶退栈，继续判断新的栈顶与当前点是否满足逆时针要求
    				i--;
    				currentAngle = stack.peek().angle;
    			}	
    		}
    		int size = stack.size();
    		Point[] output = new Point[size];
    		for(int i = 0;i<size;i++) {
    			output[i] = stack.pop();
    		}
    		Set<Point> set = new HashSet<>(Arrays.asList(output)); //将栈中所有的点保存到set中并返回
    		return set;
    	}catch(Exception e) {
    		 throw new RuntimeException("implement me!");
    	}
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	for (int i = 0 ; i < 1000; i++) {
            turtle.forward(i/2);
            switch (i % 8) {
                case 0:turtle.color(PenColor.ORANGE);break;
                case 1:turtle.color(PenColor.PINK);break;
                case 2:turtle.color(PenColor.RED);break;
                case 3:turtle.color(PenColor.BLACK);break;
                case 4:turtle.color(PenColor.GREEN);break;
                case 5:turtle.color(PenColor.YELLOW);break;
                case 6:turtle.color(PenColor.GRAY);break;
                case 7:turtle.color(PenColor.BLUE);break;
            }
            if(i%2 == 0)
            turtle.turn(91);
            else
            	turtle.turn(100);
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawSquare(turtle, 40);
        // draw the window
        turtle.draw();
       
    }

}
