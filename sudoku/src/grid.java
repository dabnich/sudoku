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
	
	public void updateMissing(){
		for(int i=0; i<Constants.maxRows*3-1; i++){
			while(inArray(missing[i])){
				missing[i]=0;
			}
		}
		sort();
		int a=0;
		for(int i=Constants.maxRows*3-1; i>=0; i--){
			if(missing[i]>0) a++;
		}
		missingSize = missingSize-a;
	}
	
	public void checkMissing(){
		int[] tmpMissing = new int[Constants.maxRows*3];
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
	
	private void sort(){
		for(int n=0; n<missing.length; n++){
			for(int i=0; i<missing.length-1-n; i++){
				if(missing[i]>missing[i+1] || missing[i]==0){
					int tmp = missing[i];
					missing[i]=missing[i+1];
					missing[i+1] = tmp;
				}
			}
		}
	}
	
	private boolean inArray(int value){
		for(int i=0; i<missing.length; i++){
			if(value==missing[i] && value!=0) return true;
		}
		return false;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
