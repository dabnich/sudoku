public class box {
	grid[] grid = new grid[Constants.maxBoxes];
	public box(grid grid[]){
		this.grid = grid;
	}
	
	public box(int value[]){
		for(int i=0; i<Constants.maxBoxes; i++){
			this.grid[i] = new grid(value[i]);
		}
	}
}
