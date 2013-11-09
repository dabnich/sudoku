
public class grid {
	boolean empty = true;
	public int value = 0;
	//int solutions[];
	public grid(){
		this.value=0;
	}
	public grid(int value){
		if(value>=Constants.minValue && value<=Constants.maxValue) {
			this.value = value;
			this.empty = false;
		}
	}
}
