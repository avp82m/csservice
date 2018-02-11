package css.engine.config;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import css.engine.Inicializer;
import css.engine.config.sources.FirstLevelSource;
import css.engine.config.sources.IConfigurationSource;
import css.engine.config.sources.SecondLevelSource;

@Component
public class Configuration {
	private static final Logger log = LoggerFactory.getLogger(Inicializer.class);
	private static HashMap<String,String> params = new HashMap<String,String>();
	
	@Autowired
	private Environment env;
	
	HashMap<String,String> getFromSource(IConfigurationSource source){
		return source.getParams();
	}
	
	
	@Scheduled(fixedDelay=1000)
	public void reReconfigured() {
		log.debug("Перезагрузка настроек");
		HashMap<String,String> tmpParams = new HashMap<String,String>();
		tmpParams.putAll(getFromSource(new FirstLevelSource(env)));
		tmpParams.putAll(getFromSource(new SecondLevelSource(env)));
		params = tmpParams;
	}
}
