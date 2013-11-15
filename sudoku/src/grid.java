public class grid {
	boolean empty = true;
	public int value = 0;
	public int[] missing = new int[Constants.maxRows*3];
	public int missingSize = Constants.maxRows*3;
	public int x = 0;
	public int y = 0;
	public grid(){
		this.value=0;
	}
	public grid(int value){
		for(int i=0; i<Constants.maxRows*3; i++){
			missing[i] = 0;
		}
		if(value>=Constants.minValue && value<=Constants.maxValue) {
			this.value = value;
			this.empty = false;
		}
		else empty = true;
	}
	
	public grid(int value, int x, int y){
		for(int i=0; i<Constants.maxRows*3; i++){
			missing[i] = 0;
		}
		if(value>=Constants.minValue && value<=Constants.maxValue) {
			this.value = value;
			this.empty = false;
		}
		this.x = x;
		this.y = y;
	}
	
	public void setValue(int value){
		if(value>0){
			for(int i=0; i<Constants.maxRows*3; i++){
				missing[i] = 0;
			}
			missingSize = 0;
			empty = false;
			this.value = value;
		}
	}
	

	
	public void checkMissing(){
		int[] tmpMissing = new int[Constants.maxRows*3];
		for(int i=0; i<Constants.maxRows*3; i++){
			tmpMissing[i]=0;
		}
		int a = 0;
		for(int no=Constants.minValue; no<=Constants.maxValue; no++){
			int n=0;
			for(int i=0; i<Constants.maxRows*3; i++){
				if(missing[i]==no) n++;
			}
			if(n>2){
				tmpMissing[a] = no;
				a++;
			}
		}
		missingSize = a;
		missing = tmpMissing;
	}
	


	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
