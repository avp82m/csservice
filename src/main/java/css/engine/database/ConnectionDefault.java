package css.engine.database;

public class ConnectionDefault implements IConnection {

	@Override
	public Boolean checkConnection() {
		return true;
	}


	@Override
	public void setPriority(Integer priority) {}

	@Override
	public Integer getPriority() {
		return 0;
	}


	@Override
	public String getName() {
		return "Заглушка";
	}


	@Override
	public void setName(String name) {}

}
