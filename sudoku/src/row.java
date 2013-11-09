
public class row {
	grid[] grid = new grid[Constants.maxCols];
	//int solutions[];
	public row(grid grid[]){
		this.grid = grid;
	}
	
	public row(){
		for(int i=0; i<Constants.maxCols; i++){
			this.grid[i] = new grid();
		}
	};
	
	public row(int value[]){
		for(int i=0; i<Constants.maxCols; i++){
			this.grid[i] = new grid(value[i]);
		}
	}
	
	//public void add(int index, int value)
}
