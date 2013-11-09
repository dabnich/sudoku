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
	
	public void setRow(int index, int values[]){
		row[index] = new row(values);
	}
	
	public void setCol(int index, int values[]){
		col[index] = new col(values);
	}
}
