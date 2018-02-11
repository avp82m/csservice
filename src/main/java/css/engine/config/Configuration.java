package css.engine.config;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import css.engine.Inicializer;
import css.engine.config.sources.AppConfigurationSource;
import css.engine.config.sources.IConfigurationSource;
import css.engine.config.sources.ServerConfigurationSource;

@Component
public class Configuration {
	private static final Logger log = LoggerFactory.getLogger(Inicializer.class);
	private static HashMap<String,String> params = new HashMap<String,String>();
	
	HashMap<String,String> getFromSource(IConfigurationSource source){
		return source.getParams();
	}
	
	
	@Scheduled(fixedDelay=1000)
	public void reReconfigured() {
		log.debug("Перезагрузка настроек");
		HashMap<String,String> tmpParams = new HashMap<String,String>();
		tmpParams.putAll(getFromSource(new AppConfigurationSource()));
		//tmpParams.putAll(getFromSource(new ServerConfigurationSource()));
		params = tmpParams;
	}
}
