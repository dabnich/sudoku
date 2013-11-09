public class col {
	grid[] grid = new grid[Constants.maxRows];
	//int solutions[];
	public col(grid grid[]){
		this.grid = grid;
	}
	
	public col(){
		for(int i=0; i<Constants.maxRows; i++){
			this.grid[i] = new grid();
		}
	};
	
	public col(int value[]){
		for(int i=0; i<Constants.maxRows; i++){
			this.grid[i] = new grid(value[i]);
		}
	}
}