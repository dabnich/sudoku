import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class tests {

	int [][] data = new int[Constants.maxRows][Constants.maxCols];

	@Test
	public void table(){
		setData();
		area area = new area(data);
		assertEquals(area.row[8].grid[8].value, area.box[8].grid[8].value);
		assertEquals(area.row[3].grid[8].value, area.col[8].grid[3].value);
	}
	
	@Test
	public void missing(){
		setData();
		area area = new area(data);
		assertEquals(area.row[0].missingSize, 4);
		assertEquals(area.row[6].missingSize, 7);
		assertEquals(area.row[0].missing[0], 5);
		assertEquals(area.row[5].missing[area.row[5].missingSize-1], 9);
		assertEquals(area.col[0].missingSize, 6);
	}
	
	@Test
	public void areaMissing(){
		setData();
		area area = new area(data);
		area.updateMissing();
		for(int i=0; i<area.row[4].grid[6].missingSize; i++){
			//System.out.print(area.row[4].grid[6].missing[i]+"\n");
		}
		assertEquals(area.row[4].grid[6].missingSize, 3);
	}
	
	@Test
	public void completeRow(){
		setData();
		area area = new area(data);
		area.updateMissing();
		for(int i=0; i<Constants.maxCols; i++){
			System.out.print(area.row[2].grid[i].value+"\t");
		}
		System.out.print("\n");
		assertEquals(area.row[2].complete(), true);
		//assertEquals(area.row[6].grid[1].value, 2);
		
		
		for(int i=0; i<Constants.maxCols; i++){
			System.out.print(area.row[2].grid[i].value+"\t");
		}
		
	}
	
	void setData(){
		data[0] = new int[] {1,7,4,0,0,2,0,3,0};
		data[1] = new int[] {0,0,2,0,6,0,0,1,0};
		data[2] = new int[] {6,0,0,0,0,3,0,0,0};
		data[3] = new int[] {0,0,0,0,0,0,0,9,0};
		data[4] = new int[] {2,0,0,1,0,9,0,0,7};
		data[5] = new int[] {0,8,0,0,0,0,0,0,0};
		data[6] = new int[] {0,0,0,8,0,0,0,0,1};
		data[7] = new int[] {0,9,0,0,4,0,5,0,0};
		data[8] = new int[] {0,1,0,2,0,0,6,8,4};
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
