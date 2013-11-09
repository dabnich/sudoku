public class grid {
	boolean empty = true;
	public int value = 0;
	public int x = 0;
	public int y = 0;
	public grid(){
		this.value=0;
	}
	public grid(int value){
		if(value>=Constants.minValue && value<=Constants.maxValue) {
			this.value = value;
			this.empty = false;
		}
	}
	
	public grid(int value, int x, int y){
		if(value>=Constants.minValue && value<=Constants.maxValue) {
			this.value = value;
			this.empty = false;
		}
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
}
