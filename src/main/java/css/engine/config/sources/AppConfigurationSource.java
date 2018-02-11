package css.engine.config.sources;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


public class AppConfigurationSource implements IConfigurationSource {
	private static final Logger log = LoggerFactory.getLogger(AppConfigurationSource.class);
	
	@Override
	public HashMap<String, String> getParams() {
		log.debug("Загрузка настроек приложения");
		HashMap<String, String> tmpParams	=	new HashMap<String,String>();
		tmpParams.put("name", env.getProperty("main.name"));
		return tmpParams;
	}
	
}
