package css.engine.database;

public interface IConnection {
	
	Boolean checkConnection();
	
	void setPriority(Integer priority);
	
	Integer getPriority();
	
	String getName();
	
	void setName(String name);
}
