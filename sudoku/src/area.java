public class area {
	row[] row = new row[Constants.maxRows];
	col[] col = new col[Constants.maxCols];
	box[] box = new box[Constants.maxBoxes];
	
	public area(){
		for(int i=0; i<Constants.maxRows; i++){
			row[i] = new row();
		}
		for(int i=0; i<Constants.maxCols; i++){
			col[i] = new col();
		}
	}
	public area(int values[][]){
		for(int i=0; i<values.length; i++){
			row[i] = new row(values[i]);
		}
		for(int i=0; i<values[0].length; i++){
			int[] tmpArray = new int[Constants.maxRows];
			for(int n=0; n<Constants.maxRows; n++){
				tmpArray[n] = values[n][i];
				col[i] = new col(tmpArray);
			}
		}
		int rowStart = 0;
		int colStart = 0;
		int[] tmpBox = new int[Constants.maxBoxes];
		int nBox = 0;
		while(rowStart<Constants.maxRows){
			colStart = 0;
			while(colStart<Constants.maxCols){
				int n = 0;
				for(int i=0; i<Constants.maxBoxes; i++){
					tmpBox[i] = 0;
				}
				for(int row=rowStart; row<rowStart+3; row++){
					for(int col=colStart; col<colStart+3; col++){
						tmpBox[n] = values[row][col];
						n++;
					}
				}
				box[nBox] = new box(tmpBox);
				nBox++;
				colStart = colStart + 3;
			}
			rowStart = rowStart + 3;
		}
	}
	
	public void updateMissing(){
		for(int r=0; r<Constants.maxRows; r++){
			for(int c=0; c<Constants.maxCols; c++){
				if(row[r].grid[c].empty){
					int n=0;
					int numBox = numBox(r,c);
					int numInBox = numInBox(r,c);
					for(int i=0; i<row[r].missingSize; i++){
						row[r].grid[c].missing[i] = row[r].missing[n];
						col[c].grid[r].missing[i] = row[r].missing[n];
						box[numBox].grid[numInBox].missing[i] = row[r].missing[n];
						n++;
					}
					for(int i=0; i<col[c].missingSize; i++){
						row[r].grid[c].missing[n] = col[c].missing[i];
						col[c].grid[r].missing[n] = col[c].missing[i];
						box[numBox].grid[numInBox].missing[n] = col[c].missing[i];
						n++;
					}
					for(int i=0; i<box[numBox].missingSize; i++){
						box[numBox].grid[numInBox].missing[n] = box[numBox].missing[i];
						col[c].grid[r].missing[n] = box[numBox].missing[i];
						row[r].grid[c].missing[n] = box[numBox].missing[i];
						n++;
					}
					row[r].grid[c].checkMissing();
					col[c].grid[r].checkMissing();
					box[numBox].grid[numInBox].checkMissing();
				}
			}
		}
	}
	
	private int numBox(int row, int col){
		return (int)(col/3)+(int)(row/3)*3;
	}
	
	private int numInBox(int row, int col){
		int rowStart = (int)(row/3)*3;
		int colStart = (int)(col/3)*3;
		return col-colStart+(int)((row-rowStart)/3)*3;
	}
	
	
	public void setRow(int index, int values[]){
		row[index] = new row(values);
	}
	
	public void setCol(int index, int values[]){
		col[index] = new col(values);
	}
}
