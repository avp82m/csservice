package css.engine.config.sources;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import css.engine.database.DataBase;
import css.engine.database.IConnection;

public class SecondLevel implements IConfigurationSource {
	private static final Logger log = LoggerFactory.getLogger(SecondLevel.class);
	
	
	
	public SecondLevel(Environment env){}
	
	@Override
	public HashMap<String, String> getParams() {
		log.debug("Загрузка настроек второго уровня");
		IConnection connection = DataBase.getInstance().getConnection();
		HashMap<String, String> tmpParams	=	new HashMap<String,String>();
		return tmpParams;
	}

}
