import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class reader {
	
	public String filename;
	public int size;
	
	public reader(String filename){
		this.filename = filename;
		this.size = (int)(new File(new File("").getAbsolutePath()+"\\"+filename).length());
	}
	
	public int[][] toArray(){
		if(size!=0){
			char [] txt = new char[size];
			try {
				FileReader reader = new FileReader(filename);
				try{
					reader.read(txt, 0, size);
				}
				catch(IOException e){
					e.getMessage();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int n=0;
			int[][] data = new int[Constants.maxRows][Constants.maxCols];
			for(int r=0; r<Constants.maxRows; r++){
				for(int c=0; c<Constants.maxCols; c++){
					data[r][c] = Character.getNumericValue(txt[n]);
					n++;
				}
			}
			return data;
		}
		int[][] a = new int [1][1];
		a[0][0] = 0;
		return a;

	}
	
	public String toString(){
		if(size!=0){
			char [] txt = new char[size];
			try {
				FileReader reader = new FileReader(filename);
				try{
					reader.read(txt, 0, size);
				}
				catch(IOException e){
					e.getMessage();
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new String(txt);
		}
		return "";
	}
	
	
}
