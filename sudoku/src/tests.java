import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class tests {

	int [][] data = new int[Constants.maxRows][Constants.maxCols];

	//@Test
	public void table(){
		setData();
		area area = new area(data);
		assertEquals(area.row[8].grid[8].value, area.box[8].grid[8].value);
		assertEquals(area.row[3].grid[8].value, area.col[8].grid[3].value);
		assertEquals(area.box[2].grid[0].empty, true);
		assertEquals(area.box[2].grid[1].empty, false);
		assertEquals(area.box[2].grid[0].empty, true);
	}
	
	//@Test
	public void missing(){
		setData();
		area area = new area(data);
		assertEquals(area.row[0].missingSize, 4);
		assertEquals(area.row[6].missingSize, 7);
		assertEquals(area.row[0].missing[0], 5);
		assertEquals(area.row[5].missing[area.row[5].missingSize-1], 9);
		assertEquals(area.row[0].missingSize, 4);
		assertEquals(area.row[1].missingSize, 6);
		assertEquals(area.row[2].missingSize, 7);
		assertEquals(area.row[3].missingSize, 8);
		assertEquals(area.row[4].missingSize, 5);
		assertEquals(area.row[5].missingSize, 8);
		assertEquals(area.row[6].missingSize, 7);
		assertEquals(area.row[7].missingSize, 6);
		assertEquals(area.row[8].missingSize, 4);
		assertEquals(area.col[0].missingSize, 6);
		assertEquals(area.col[1].missingSize, 5);
		assertEquals(area.col[2].missingSize, 7);
		assertEquals(area.col[3].missingSize, 6);
		assertEquals(area.col[4].missingSize, 7);
		assertEquals(area.col[5].missingSize, 6);
		assertEquals(area.col[6].missingSize, 7);
		assertEquals(area.col[7].missingSize, 5);
		assertEquals(area.col[8].missingSize, 6);
		assertEquals(area.box[0].missingSize, 4);
		assertEquals(area.box[1].missingSize, 6);
		assertEquals(area.box[2].missingSize, 7);
		assertEquals(area.box[3].missingSize, 7);
		assertEquals(area.box[4].missingSize, 7);
		assertEquals(area.box[5].missingSize, 7);
		assertEquals(area.box[6].missingSize, 7);
		assertEquals(area.box[7].missingSize, 6);
		assertEquals(area.box[8].missingSize, 4);
	}
	
	@Test public void colMissing(){
		setData();
		area area = new area(data);
		int missingSize = area.col[0].missingSize;
		area.row[0].setGrid(1, 0);
		area.col[0].setGrid(1, 0);
		area.col[0].setGrid(3, 2);
		area.col[0].setGrid(5, 3);
		area.col[0].setGrid(6, 4);
		area.updateMissing();
		assertEquals(area.col[0].getGrid(0), 1);
		assertEquals(area.col[0].missingSize, missingSize-4);
		
	}
	
	
	
	@Test public void numBox(){
		setData();
		area area = new area(data);
		
		assertEquals(area.numBox(3, 6), 5);
		assertEquals(area.numBox(8, 6), 8);
		
		assertEquals(area.numInBox(6, 6), 0);
		assertEquals(area.numInBox(1, 7), 4);
		
		
	}
	
	
	//@Test
	public void gridMissing(){
		setData();
		area area = new area(data);
		area.updateMissing();
		assertEquals(area.row[0].grid[3].missingSize, 2);
		assertEquals(area.row[0].grid[4].missingSize, 3);
		assertEquals(area.row[0].grid[6].missingSize, 2);
		assertEquals(area.row[0].grid[8].missingSize, 4);
		assertEquals(area.row[2].grid[1].missingSize, 1);
		assertEquals(area.row[0].grid[8].missing[0], 5);
		
		assertEquals(area.col[0].grid[1].missingSize, 4);
		assertEquals(area.col[0].grid[1].missing[0], 3);
		assertEquals(area.col[6].grid[2].missingSize, 5);
		assertEquals(area.col[6].grid[2].missing[2], 7);
		
		assertEquals(area.box[0].grid[3].missingSize, 4);
		
	}
	
	//@Test
	public void areaMissing(){
		setData();
		area area = new area(data);
		area.updateMissing();
		assertEquals(area.row[4].grid[6].missingSize, 3);
	}
	
	
	public void completeRow(){
		setData();
		area area = new area(data);
		area.updateMissing();
		assertEquals(area.row[2].complete(), true);
	}
	
	@Test
	public void completeBox(){
		setData();
		area area = new area(data);
		area.updateMissing();
		//assertEquals(area.box[1].grid[7].value, 0);
		//assertEquals(area.box[1].complete(), true);
		//assertEquals(area.box[1].grid[7].value, 1);
	}
	
	@Test
	public void rowColBox(){
		setData();
		area area = new area(data);
		assertEquals(area.rowBox(3, 2), 3);
		assertEquals(area.colBox(3,2), 2);
		assertEquals(area.rowBox(7,4), 7);
		assertEquals(area.colBox(7,4), 4);
	}
	
	@Test
	public void complete(){
		setData();
		area area = new area(data);
		int missingCol = area.col[4].missingSize;
		int missingRow = area.row[1].missingSize;
		int missingBox = area.box[1].missingSize;
		area.completeRow(1);
		assertEquals(7, area.col[4].grid[1].value);
		area.col[4].setGrid(7, 1);
		assertEquals(missingCol-1, area.col[4].missingSize);
		assertEquals(missingRow-1, area.row[1].missingSize);
		assertEquals(missingBox-1, area.box[1].missingSize);
	}
	

	
	
	
	private boolean inArray(int value, int array[]){
		for(int i=0; i<array.length; i++){
			if(value==array[i]) return true;
		}
		return false;
	}
	
	void setData(){
		data[0] = new int[] {0,3,0,0,8,0,6,0,0};
		data[1] = new int[] {4,6,0,5,0,1,0,0,0};
		data[2] = new int[] {0,0,1,0,0,0,0,0,2};
		data[3] = new int[] {0,0,0,4,3,0,0,6,0};
		data[4] = new int[] {0,0,3,8,9,7,4,0,0};
		data[5] = new int[] {0,5,0,0,1,6,0,0,0};
		data[6] = new int[] {2,0,0,0,0,0,5,0,0};
		data[7] = new int[] {0,0,0,1,0,9,0,2,6};
		data[8] = new int[] {0,0,9,0,2,0,0,7,0};
	}
	
	void setData2(){
		data[0] = new int[] {7,3,5,0,8,2,6,0,1};
		data[1] = new int[] {4,6,2,5,7,1,0,0,0};
		data[2] = new int[] {0,0,1,0,0,0,7,5,2};
		data[3] = new int[] {0,7,0,4,3,5,2,6,0};
		data[4] = new int[] {6,2,3,8,9,7,4,1,5};
		data[5] = new int[] {0,5,4,2,1,6,0,0,7};
		data[6] = new int[] {2,0,0,0,0,0,5,0,0};
		data[7] = new int[] {0,4,7,1,5,9,0,2,6};
		data[8] = new int[] {5,0,9,0,2,0,0,7,0};
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
