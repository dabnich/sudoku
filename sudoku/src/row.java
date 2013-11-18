import java.util.ArrayList;


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
	
	public int completePair(){
		if(missingSize>=3){
			ArrayList<String[]> pair = new ArrayList<String[]> ();
			for(int g=0; g<Constants.maxCols; g++){
				if(grid[g].empty){
					for(int m=0; m<grid[g].missingSize-1; m++){
						for(int i=m+1; i<grid[g].missingSize; i++){
							pair.add(new String[] {Integer.toString(grid[g].missing[m])+grid[g].missing[i], Integer.toString(g)});
						}
					}
				}
			}
			for(int i=0; i<pair.size(); i++){
				int[] index = isPair(pair);
				if(index[0] != -1){
					grid[index[0]].setTmpValue(index[2]);
					grid[index[1]].setTmpValue(index[3]);
					updateMissing();
					int complete = complete();
					grid[index[0]].setTmpValue(0);
					grid[index[1]].setTmpValue(0);
					updateMissing();
					if(complete != -1) return complete;
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
	
	private int[] isPair(ArrayList<String[]> all){
		for(int i=0; i<all.size(); i++){
			int a=0;
			int tmpN = -1;
			for(int n=0; n<all.size(); n++){
				if(all.get(n)[0]==all.get(i)[0]){
					tmpN=n;
					a++;
				}
			}
			if(a==2){
				String num1 = Character.toString(all.get(i)[0].charAt(0));
				String num2 = Character.toString(all.get(i)[0].charAt(1));
				return new int[] {Integer.parseInt(all.get(i)[1]),
								  Integer.parseInt(all.get(tmpN)[1]), 
								  Integer.parseInt(num1), 
								  Integer.parseInt(num2)
								 };
			}
		}
		return new int[] {-1, -1, -1, -1};
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
