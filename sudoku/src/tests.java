import static org.junit.Assert.*;

import org.junit.*;
import java.util.Random;




public class tests {

	int [][] data = new int[Constants.maxRows][Constants.maxCols];

	@Test
	public void box(){
		data = createValues();
		area area = new area(data);
		assertEquals(area.row[8].grid[8].value, area.box[8].grid[8].value);
		assertEquals(area.row[3].grid[8].value, area.col[8].grid[3].value);
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
