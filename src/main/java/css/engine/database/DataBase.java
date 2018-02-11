package css.engine.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataBase {
	private static final Logger log = LoggerFactory.getLogger(DataBase.class);
	private static IConnection connection = null;
	private static DataBase instance = null;
	
	private DataBase(){}
	
	public static DataBase getInstance() {
		if (instance==null) {
			instance = new DataBase();
			connection	=	DataBaseFactory.getConnection("css.database.connection");
		}	
		return instance;
	}
	
	
	public static IConnection getConnection() {
		if (connection==null) {
			DataBaseFactory.getConnection("css.database.connection");
		}	
		return connection;
	}
	
	@Scheduled(fixedDelay=5_000)
	public static void reConnection() {
		if(connection!=null&&connection.getPriority()!=1) {
			log.error("DataBase использует нецелевое подключение({}), пытаемся вернуться",connection.getName());
			connection	=	DataBaseFactory.getConnection("css.database.connection.1");
		}
	}
	
	
	
	
}
