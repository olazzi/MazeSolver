// Enes YARDIM 
//section02
import java.util.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
public class deneme1
{
	static int path_counter=0;
	static int top;
	static int treasure_counter=0;
	static String paths[][] = new String[100][100];
	static class Stack {//stack class to store paths
	    static final int MAX = 1000;
	   
	   
	    String a[] = new String[MAX]; // Maximum size of Stack
	 
	    boolean isEmpty()//check stack is empty
	    {
	        return (top < 0);
	    }
	    Stack()
	    {
	        top = -1;
	    }
	 
	    boolean push(String x)//insert elemnt to the stack
	    {
	        if (top >= (MAX - 1)) {
	            System.out.println("Stack Overflow");
	            return false;
	        }
	        else {
	            a[++top] = x;
	           // System.out.println(x + " pushed into stack");
	            return true;
	        }
	    }
	 
	    String pop()//pop the top element of stack
	    {
	        if (top < 0) {
	            System.out.println("Stack Underflow");
	            return null;
	        }
	        else {
	        	String x = a[top--];
	            return x;
	        }
	    }
	 
	    String peek()//returning to top element of the stack
	    {
	        if (top < 0) {
	            System.out.println("Stack Underflow");
	            return null;
	        }
	        else {
	        	String x = a[top];
	            return x;
	        }
	    }
	    
	    void print(){//printing stack
	    for(int i = 0;i<=top;i++){
	      System.out.print(a[i]);
	    
	    }
	    System.out.println("\n");
	  }
	    void copy(String arr[][]){//copy found paths to array
		    for(int i=0; i<top; i++){
		    	arr[path_counter][i]=a[i];
		    	path_counter++;
		    }
		  }
	}
	
	
	
	static String[] arr_way;
	//Function return true if given parameter is lower-case
	static boolean isLowerCase(String input) {
		if(input.equals("a")||input.equals("b")||input.equals("c")||input.equals("d")||input.equals("e")||input.equals("f")||input.equals("g")||input.equals("h")||
				input.equals("i")||input.equals("j")||input.equals("k")||input.equals("l")||input.equals("m")||input.equals("n")||input.equals("o")||input.equals("p")||
				input.equals("q")||input.equals("r")||input.equals("s")||input.equals("t")||input.equals("u")||input.equals("v")||input.equals("w")||input.equals("x")||
				input.equals("y")||input.equals("z")) {
			return true;
		}else {
			return false;
		}
		
	}
	static Stack s = new Stack();
	
	// Function that returns true if valid
	static boolean isValid(String[][] maze_t, int m, int n, int x, int y, String prevC, String newC)
	{
		if(x < 0 || x >= m || y < 0 || y >= n || isLowerCase(maze_t[x][y])==false
		|| maze_t[x][y].equals(newC))
			return false;
		return true;
	}


	// function to find ways to treasures
	static void find_path(String[][] maze_t, int m, int n, int x, int y, String prevC, String newC)
	{
		Vector<Point> queue = new Vector<Point>();

		//starting position
		queue.add(new Point(x, y));

		// mark the way passed
		maze_t[x][y] = newC;

		
		//if queue is not empty
		while(queue.size() > 0)
		{
			
			Point currPixel = queue.get(queue.size() - 1);
			queue.remove(queue.size() - 1);

			int posX = currPixel.x;
			int posY = currPixel.y;

			//checking if directions are valid: right-left-up-down
			if(isValid(maze_t, m, n, posX + 1, posY, prevC, newC))
			{
				// if valid and enqueue
				s.push(maze_t[posX][posY]);
				maze_t[posX + 1][posY] = newC;
				queue.add(new Point(posX + 1, posY));
				
			}

			if(isValid(maze_t, m, n, posX-1, posY, prevC, newC))
			{
				s.push(maze_t[posX-1][posY]);
				maze_t[posX-1][posY]= newC;
				queue.add(new Point(posX-1, posY));
				
			}

			if(isValid(maze_t, m, n, posX, posY + 1, prevC, newC))
			{
				
				s.push(maze_t[posX][posY+1]);
				if(maze_t[posX][posY+2].equals("E")) {//checking right if there is E as a treasure 
					treasure_counter++;//add 1 to to treasure counter
					maze_t[posX][posY+2]="F";//mark treasure not to find again
					s.push("E");//add E to path
					s.print();
					while(s.isEmpty()==false) {//make stack empty
						s.pop();
					}
					
					
				}
				if(maze_t[posX-1][posY+1].equals("E")) {//checking down if there is E as a treasure
					treasure_counter++;//add 1 to to treasure counter
					maze_t[posX-1][posY+1]="F";//mark treasure not to find again
					s.push("E");//add E to path
					s.print();
					while(s.isEmpty()==false) {//make stack empty
						s.pop();
					}
					
				}
				if(maze_t[posX+1][posY+1].equals("E")) {//checking up if there is E as a treasure
					treasure_counter++;//add 1 to to treasure counter
					maze_t[posX+1][posY+1]="F";//mark treasure not to find again
					s.push("E");//add E to path
					s.print();
					while(s.isEmpty()==false) {//make stack empty
						s.pop();
					}
					
				}
				if(maze_t[posX][posY].equals("E")) {//checking left if there is E as a treasure
					treasure_counter++;//add 1 to to treasure counter
					maze_t[posX][posY-1]="F";//mark treasure not to find again
					s.push("E");//add E to path
					s.print();
					while(s.isEmpty()==false) {//make stack empty
						s.pop();
					}
					
				}
			
				
				maze_t[posX][posY + 1]= newC;
				queue.add(new Point(posX, posY + 1));
			}

			if(isValid(maze_t, m, n, posX, posY-1, prevC, newC))
			{
				s.push(maze_t[posX][posY-1]);			
				maze_t[posX][posY-1]= newC;
				queue.add(new Point(posX, posY-1));
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc2 = new Scanner(System.in);
		String filename = sc2.nextLine();
		File myObj = new File(filename);
	      Scanner sc = new Scanner(myObj);
	
	 int rows = 11;
	    int columns =16;
	    String [][] maze_t = new String[rows][columns];
		    while(sc.hasNextLine()) {
		       for (int i=0; i<maze_t.length; i++) {
		          String[] line = sc.nextLine().trim().split("");
		          for (int j=0; j<line.length; j++) {
		        	  maze_t[i][j] = (line[j]);
		          }
		       }
		    }
	
		// Row
		int m = 11;
		// Column
		int n = 16;
		// Coordinate to start
		int x = 1;
		int y = 0;
		// Current point
		String prevC = maze_t[x][y];
		String newC = "7";
		find_path(maze_t, m, n, x, y, prevC, newC);
	System.out.println(treasure_counter + " treasures are found.");
	//System.out.println("Paths are:");
	
	
	}
}

