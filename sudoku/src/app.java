import java.util.Random;

public class app {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] data = new int[Constants.maxRows][Constants.maxCols];
		data[0] = new int[] {1,7,4,0,0,2,0,3,0};
		data[1] = new int[] {0,0,2,0,6,0,0,1,0};
		data[2] = new int[] {6,0,0,0,0,3,0,0,0};
		data[3] = new int[] {0,0,0,0,0,0,0,9,0};
		data[4] = new int[] {2,0,0,1,0,9,0,0,7};
		data[5] = new int[] {0,8,0,0,0,0,0,0,0};
		data[6] = new int[] {0,0,0,8,0,0,0,0,1};
		data[7] = new int[] {0,9,0,0,4,0,5,0,0};
		data[8] = new int[] {0,1,0,2,0,0,6,8,4};
		//data = createValues();
		area area = new area(data);
		area.updateMissing();
		System.out.print(area.box[4].grid[5].value);
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
