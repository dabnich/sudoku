public class col {
	grid[] grid = new grid[Constants.maxRows];
	int[] missing = new int[9];
	int missingSize = 9;
	public col(grid grid[]){
		this.grid = grid;
	}
	
	public col(){
		for(int i=0; i<Constants.maxRows; i++){
			this.grid[i] = new grid();
		}
		updateMissing();
	};
	
	public col(int value[]){
		for(int i=0; i<Constants.maxRows; i++){
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