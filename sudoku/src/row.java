
public class row {
	grid[] grid = new grid[Constants.maxBoxes];
	int[] missing = new int[Constants.maxRows];
	int missingSize = 9;
	public row(grid grid[]){
		this.grid = grid;
	}
	
	public row(int value[]){
		for(int i=0; i<Constants.maxBoxes; i++){
			this.grid[i] = new grid(value[i]);
		}
		updateMissing();
	}
	
	public void setGrid(int value, int index){
		grid[index].setValue(value);
		updateMissing();
	}
	
	public int getGrid(int index){
		return grid[index].value;
	}
	
	void updateMissing(){
		int n=0;
		for(int i=0; i<9; i++) missing[i]=0;
		for(int no=Constants.minValue; no<=Constants.maxValue; no++){
			if(!inArray(no)) {
				missing[n] = no;
				n++;
			}
		}
		missingSize = n;
	}
	
	
	public int complete(){
		int sum=0;
		for(int i=0; i<Constants.maxCols; i++){
			if(grid[i].missingSize==1 && grid[i].empty) {
				setGrid(grid[i].missing[0], i);
				updateMissing();
				return i;
			}
		}
		for(int i=0; i<Constants.maxCols; i++){
			sum += grid[i].missingSize;
		}
		int[] missingAll = new int[sum];
		int a=0;
		for(int n=0; n<Constants.maxCols; n++){
			for(int i=0; i<grid[n].missingSize; i++){
				if(grid[n].empty){
					missingAll[a] = grid[n].missing[i];
					a++;
				}
			}
		}
		int complete = 0;
		for(int no=Constants.minValue; no<=Constants.maxValue; no++){
			if(numValue(no, missingAll)==1){ 
				complete = no;
				break;
			}
		}
		if(complete!=0){
			for(int i=0; i<Constants.maxCols; i++){
				if(inArray(complete, grid[i].missing)){
					setGrid(complete, i);
					updateMissing();
					return i;
				}
			}
		}
		return -1;
	}
	
	private int numValue(int value, int array[]){
		int a=0;
		for(int i=0; i<array.length; i++){
			if(value==array[i]) a++;
		}
		return a;
	}
	

	private boolean inArray(int value, int array[]){
		for(int i=0; i<array.length; i++){
			if(value==array[i]) return true;
		}
		return false;
	}
	
	
	private boolean inArray(int value){
		for(int i=0; i<grid.length; i++){
			if(value==grid[i].value) return true;
		}
		return false;
	}
}
