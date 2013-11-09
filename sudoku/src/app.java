import java.util.Random;

public class app {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] data = new int[Constants.maxRows][Constants.maxCols];
		data = createValues();
		area area = new area(data);
		System.out.print(area.box[3].grid[2].value);

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
