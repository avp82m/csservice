package css.engine.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionAppServerPool implements IConnection {
	private static final Logger log = LoggerFactory.getLogger(ConnectionAppServerPool.class);
	private Integer priority	=	0;
	private String name	=	"";
	
	@Override
	public Boolean checkConnection() {
		return true;
	}

	@Override
	public void setPriority(Integer priority) {
		this.priority=priority;
	}

	@Override
	public Integer getPriority() {
		return priority;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

}
