package css.engine.database;

public class ParamConnection {
	private String type	=	"";
	private String value	=	"";
	
	ParamConnection(String type,String value){
		this.type = type;
		this.value = value;
	}
	
	
	public String getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "Connection {type='"+getType()+"', value='"+getValue()+"'}";
	}
	
}
