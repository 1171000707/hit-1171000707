package P1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MagicSquare {
      public static boolean isNumber(String str) { //判断每个分割的字符串是否为整数
    	  Pattern pattern = Pattern.compile("[0-9]*");
    	  Matcher isnumber = pattern.matcher(str);
    	  if(!isnumber.matches()) {
    		  return false;
    	  }
    	  return true;
      }
	 static boolean isLegalMagicSquare(String fileName)  throws IOException {
		try {
			FileReader file = new FileReader(fileName);
		    BufferedReader buffer = new BufferedReader(file); //从文件中读取数据
		    ArrayList<String> str = new ArrayList<String>(); //建立动态字符串数组存储读取的每一行的内容
		    String line = null;
		    while((line = buffer.readLine()) != null){
		    	str.add(new String(line)); //按行读取，加入字符串数组
		    }
		    int n = str.size();	 //获得矩阵的行数
		    file.close();
		    buffer.close();	
		    String [][]da = new String[n][n];//存放分割完后每一个字符串
			int [][]data = new int[n][n]; //将字符串转换成int型后的数据存入该int型数组
			int count = 0;
			int row = n;
			int column = 0;
			while(count < row) {
			    String s = str.get(count);
				String[] sl =  s.split("\t"); //分割矩阵每一行的数据
				column = sl.length; //矩阵的列数
				if(row != column) { //行列不相等或者每两个数不是由\t分割的，直接返回
					System.out.println("不是矩阵！");
					return false;
				}
				for(int i = 0;i < column;i++) {
					da[count][i] = sl[i];
				}
				count++;
			}
			for(int i = 0;i < row;i++) {
				for(int j = 0;j< column;j++) {
					if(!isNumber(da[i][j])) { //判断该字符串表示的是否为正整数，若不是，则返回
						System.out.println("不是正整数！");
						return false;
					}	
					data[i][j] = Integer.valueOf(da[i][j]); //将读取的数据转换为int型
				}
			}
			int sum = 0;
			for(int i = 1;i<row;i++) {   //判断矩阵所有行的和是否相等
				int judge = 0;
				for(int j = 0;j < column;j++) {
					 judge += data[i][j];
				}
				if(i == 0) {
					sum = judge;
					continue;
				}
				if(sum != judge) {
					return false;
				}
			}
			for(int j = 0;j<column;j++) {   //判断矩阵每一列的和是否和第一行相同
				int judge = 0;
				for(int i = 0;i < row;i++) {
					 judge += data[i][j];
				}
				if(sum != judge) {
					return false;
				}
			}
			int judge1 = 0;
			//System.out.println(judge);
			for(int i = 0;i<row;i++) {  //判断两个对角线的和是否和第一行相同
				for(int j = 0;j < column;j++) {
					if((i + j) ==( column-1)) {
						judge1 += data[i][j];
					}	
				}
				judge += data[i][i];
			}
			if(judge != sum || judge1 != sum) {
				return false;
			}
				
		    
	}catch (IOException e) {
		e.printStackTrace();
    }
		return true;	
	}
	 public static boolean generateMagicSquare(int n) throws IOException{
		if((n%2 == 0) || (n < 0)) { //如果n为偶数或负数，无法生成矩阵，会出现越界问题，直接返回
			System.out.println(false);
			return false;
		 }
		 int magic[][] = new int[n][n];
		 int row = 0,col = n / 2,i,j,square = n*n;
		 for(i = 1;i <= square;i++) {
			 magic[row][col] = i; //将1放在第一行最中间的位置
			 if(i%n == 0)//若要放的位置已经填好了整数，或上一个整数在幻方的右上角，则当前要放的整数应该在该位置的正下方
				 row++;
			 else {
				 if(row == 0) //若行数已经减为0，则重新从最底行开始
					 row = n-1;
				 else
					 row--;//行数沿自下向上的方向变化
				 if(col == (n-1)) //若列数已经增加到最后一列，则从第一列重新开始
					 col = 0;
				 else
				 col++;//列数沿自左向右的方向变化
			 }
		 }
		 File file = new File("src/P1/txt/6.txt");//将生成的矩阵写入文件
		 file.createNewFile();
		 FileWriter output = new FileWriter(file);
		 
		 for(i = 0 ;i< n;i++) {
			for(j = 0;j < n;j++) {
				System.out.print(magic[i][j] + "\t");
				output.write(magic[i][j]+"\t");
			}	
			System.out.println();
			output.write("\r\n");
		 }
		 output.close();
		 return true;
	 }
	public static void main(String[] args) throws IOException {
		
		System.out.println("第一个：");
		boolean a = isLegalMagicSquare("src/P1/txt/1.txt");
		System.out.println(a);
		System.out.println("第二个：");
		boolean b = isLegalMagicSquare("src/P1/txt/2.txt");
		System.out.println(b);
		System.out.println("第三个：");
		boolean c = isLegalMagicSquare("src/P1/txt/3.txt");
		System.out.println(c);
		System.out.println("第四个：");
		boolean d = isLegalMagicSquare("src/P1/txt/4.txt");
		System.out.println(d);
		System.out.println("第五个：");
		boolean e = isLegalMagicSquare("src/P1/txt/5.txt");
		System.out.println(e);
		generateMagicSquare(7);
		boolean f = isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println("构造的矩阵"+f);
	}
}
