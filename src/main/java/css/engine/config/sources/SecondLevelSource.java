package css.engine.config.sources;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public class SecondLevelSource implements IConfigurationSource {
	private static final Logger log = LoggerFactory.getLogger(SecondLevelSource.class);
	
	
	
	public SecondLevelSource(Environment env){}
	
	@Override
	public HashMap<String, String> getParams() {
		log.debug("Загрузка настроек второго уровня");
		HashMap<String, String> tmpParams	=	new HashMap<String,String>();
		return tmpParams;
	}

}
