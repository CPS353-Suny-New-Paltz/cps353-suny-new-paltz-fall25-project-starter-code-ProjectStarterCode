package numberlettercountdatastoring;

import project.annotations.ProcessAPIPrototype;

public class DataStoreApiPrototype {
	
	@ProcessAPIPrototype
	public void prototype(DataStoreApi dataapi) {
		
		
		dataapi.insertRequest(new DataRequest(123));
	}
	
	public void result(String result) {
		this.result(result);
	}
	
	public void sent(SendInfo sent) {
		this.sent(sent);
	}
	
	public void format(FormatData format) {
		this.format(format);
	}
	
	public void recieve(RecieveInfo recieve) {
		 this.recieve(recieve);
	}
	
	public void serialize(Serialize serialize) {
		this.serialize(serialize);
	}
	//delimiter is for the user to see from the output like 123 to one hundred,(space)	
}
