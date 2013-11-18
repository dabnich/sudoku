public class area {
	row[] row = new row[Constants.maxRows];
	col[] col = new col[Constants.maxCols];
	box[] box = new box[Constants.maxBoxes];
	int missingSize = Constants.maxRows*Constants.maxCols;
	
	/*public area(){
		for(int i=0; i<Constants.maxRows; i++){
			row[i] = new row();
		}
		for(int i=0; i<Constants.maxCols; i++){
			col[i] = new col();
		}
	}*/
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
		updateMissing();
		updateMissingSize();
	}
	
	public void updateMissing(){
		int n=0;
		for(int r=0; r<Constants.maxRows; r++){
			for(int c=0; c<Constants.maxCols; c++){
				if(row[r].grid[c].empty){
					n=0;
					int numBox = numBox(r,c);
					int numInBox = numInBox(r,c);
					for(int i=0; i<row[r].missingSize; i++){
						row[r].grid[c].missing[i] = row[r].missing[i];
						col[c].grid[r].missing[i] = row[r].missing[i];
						box[numBox].grid[numInBox].missing[i] = row[r].missing[i];
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
	
	public void updateMissingSize(){
		int sum=0;
		for(int i=0; i<row.length; i++){
			sum = sum+row[i].missingSize;
		}
		missingSize = sum;
	}
	
	
	public int[] completeRow(int r){
		updateMissing();
		int c=-1;
		if(row[r].missingSize>0){
			c = row[r].complete();
			if(c == -1){
				c = row[r].completePair();
			}
			if(c != -1){
				col[c].setGrid(row[r].getGrid(c), r);
				box[numBox(r,c)].setGrid(row[r].getGrid(c), numInBox(r, c));
				updateMissing();
				updateMissingSize();
				//System.out.print(r+"\t"+c+"\t"+row[r].getGrid(c)+"\n");
				return new int[] {r, c, row[r].getGrid(c)};
			}
			return new int[] {0,0,0};
		}
		return new int[] {0,0,0};
	}
	
	public int[] completeBox(int b){
		updateMissing();
		int g=-1;
		if(box[b].missingSize>0){
			g = box[b].complete();
			if(g == -1){
				g = box[b].completePair();
			}
			if(g != -1){
				row[rowBox(b,g)].setGrid(box[b].getGrid(g), colBox(b,g) );
				col[colBox(b,g)].setGrid( box[b].getGrid(g), rowBox(b,g) );
				updateMissing();
				updateMissingSize();
				//System.out.print(rowBox(b,g)+"\t"+colBox(b,g)+"\t"+box[b].getGrid(g)+"\n");
				return new int[] {rowBox(b,g), colBox(b,g), box[b].getGrid(g)};
			}
			return new int[] {0,0,0};
		}
		return new int[] {0,0,0};
	}
	
	public boolean completeCol(int c){
		updateMissing();
		int r=-1;
		if(col[c].missingSize>0){
			r = col[c].complete();
			if(r == -1){
				r = col[c].completePair();
			}
			if(r != -1){
				row[r].setGrid(col[c].getGrid(r), c);
				box[numBox(r,c)].setGrid(col[c].getGrid(r), numInBox(r, c));
				updateMissing();
				updateMissingSize();
				return true;
			}
			return false;
		}
		return false;
	}
	

	public boolean complete(){
		int n=0;
		while(n<200 && missingSize>0){
			for(int i=0; i<Constants.maxRows; i++){	
				completeBox(i);
				completeRow(i);
				completeCol(i);
			}
			n++;
		}
		if(missingSize==0)return true;
		return false;
	}
	
	public void drawTable(){
		for(int r=0; r<Constants.maxRows; r++){
			for(int c=0; c<Constants.maxCols; c++){
				System.out.print(row[r].getGrid(c)+"\t");
			}
			System.out.print("\n");
		}
		System.out.print(missingSize);
	}

	public int rowBox(int numBox, int grid){
		int rowStart = numBox/3*3;
		int row = rowStart+grid/3;
		return row;
	}
	
	public int colBox(int numBox, int grid){
		int colStart = (numBox - numBox/3*3)*3;
		int col = colStart + (grid - grid/3*3);
		return col;
	}
	
	public int numBox(int row, int col){
		return (int)(col/3)+(int)(row/3)*3;
	}
	
	public int numInBox(int row, int col){
		int rowStart = (int)(row/3)*3;
		int colStart = (int)(col/3)*3;
		return col-colStart+(row-rowStart)*3;
	}
	
	
	public void setRow(int index, int values[]){
		row[index] = new row(values);
	}
	
	public void setCol(int index, int values[]){
		col[index] = new col(values);
	}
}
