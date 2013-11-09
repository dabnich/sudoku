
public class row {
	grid[] grid = new grid[Constants.maxCols];
	int[] missing = new int[Constants.maxCols];
	int missingSize = Constants.maxCols;
	public row(grid grid[]){
		this.grid = grid;
	}
	
	public row(){
		for(int i=0; i<Constants.maxCols; i++){
			this.grid[i] = new grid();
		}
		updateMissing();
	};
	
	public row(int value[]){
		for(int i=0; i<Constants.maxCols; i++){
			this.grid[i] = new grid(value[i]);
		}
		updateMissing();
	}
	
	void updateMissing(){
		int n=0;
		for(int no=Constants.minValue; no<=Constants.maxValue; no++){
			if(!inArray(no)) {
				missing[n] = no;
				n++;
			}
		}
		missingSize = n;
	}
	
	private boolean inArray(int value){
		for(int i=0; i<grid.length; i++){
			if(value==grid[i].value) return true;
		}
		return false;
	}
}
