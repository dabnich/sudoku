import java.util.Random;
import java.io.*;

public class app {

	/**
	 * @param args
	 */
	

	
	
	public static void main(String[] args) {
		int[][] data = new int[Constants.maxRows][Constants.maxCols];
		data = new reader("file.txt").toArray();
		
		
		/*
		data[0] = new int[] {0,3,0,0,8,0,6,0,0};
		data[1] = new int[] {4,6,0,5,0,1,0,0,0};
		data[2] = new int[] {0,0,1,0,0,0,0,0,2};
		data[3] = new int[] {0,0,0,4,3,0,0,6,0};
		data[4] = new int[] {0,0,3,8,9,7,4,0,0};
		data[5] = new int[] {0,5,0,0,1,6,0,0,0};
		data[6] = new int[] {2,0,0,0,0,0,5,0,0};
		data[7] = new int[] {0,0,0,1,0,9,0,2,6};
		data[8] = new int[] {0,0,9,0,2,0,0,7,0};
		
		
		data[0] = new int[] {0,0,0,3,5,8,2,0,0};
		data[1] = new int[] {5,0,2,0,0,7,0,0,0};
		data[2] = new int[] {1,0,0,0,0,0,0,0,6};
		data[3] = new int[] {0,8,0,0,0,0,0,0,3};
		data[4] = new int[] {4,0,6,0,0,0,7,0,2};
		data[5] = new int[] {9,0,0,0,0,0,0,8,0};
		data[6] = new int[] {3,0,0,0,0,0,0,0,9};
		data[7] = new int[] {0,0,0,7,0,0,6,0,8};
		data[8] = new int[] {0,0,4,9,2,1,0,0,0};
		*/
		area area = new area(data);
		area.complete();
		area.drawTable();
	}
	
	




	static int[][] createValues(){
		int[][] data = new int[Constants.maxRows][Constants.maxCols];
		Random rand;
		for(int row=0; row<Constants.maxRows; row++){
			for(int col=0; col<Constants.maxCols; col++){
				rand = new Random();
				data[row][col] = rand.nextInt(10)+1;
			}
		}
		return data;
	}

}
