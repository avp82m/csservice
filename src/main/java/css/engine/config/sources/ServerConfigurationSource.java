package css.engine.config.sources;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerConfigurationSource implements IConfigurationSource {
	private static final Logger log = LoggerFactory.getLogger(ServerConfigurationSource.class);
	
	@Override
	public HashMap<String, String> getParams() {
		log.debug("Загрузка настроек кластера");
		HashMap<String, String> tmpParams	=	new HashMap<String,String>();
		return tmpParams;
	}

}
