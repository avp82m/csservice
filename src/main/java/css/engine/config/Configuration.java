package css.engine.config;

import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import css.engine.Inicializer;
import css.engine.config.sources.FirstLevel;
import css.engine.config.sources.IConfigurationSource;
import css.engine.config.sources.SecondLevel;

@Component
public class Configuration {
	private static final Logger log = LoggerFactory.getLogger(Inicializer.class);
	private static HashMap<String,String> params = new HashMap<String,String>();
	private static HashMap<String,String> tmpParams = new HashMap<String,String>();
	
	
	@Autowired
	private Environment env;
	
	
	private static HashMap<String,String> getHParams() throws Exception{
		HashMap<String,String> hp	=	params;
		
		if(hp.isEmpty()) {
			log.warn("Основные настройки не загружены. Смотрим во временные.");
			hp	=	tmpParams;
			if(hp.isEmpty()) {
				throw new Exception("Hастройки не загружены");
			}
		}
		return hp;
	}
	
	public static String getParam(String key) {
		
		HashMap<String, String> hp;
		try {
			hp = getHParams();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
		
		if(!hp.containsKey(key.toUpperCase())) {
			log.error("Настройка {} не найдена",key.toUpperCase());
			return null;
		}
		
		return hp.get(key);
	}
	
	
	public static HashMap<String,String> getParams(String key) {
		HashMap<String, String> hp;
		try {
			hp = getHParams();
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
		
		HashMap<String,String> r	=	new HashMap<String,String>();
		for(String k: hp.keySet()) {
			if (k.length()>key.length()) {
				if(k.substring(0, key.length()).equalsIgnoreCase(key)) {
					r.put(k, hp.get(k));
				}
			}
		}
	
		
		if(r.size()==0) {
			log.error("Настройки {}* не найдены",key.toUpperCase());
			return null;
		}
		
		return r;
	}
	
	private HashMap<String,String> getFromSource(IConfigurationSource source){
		return source.getParams();
	}
	
	
	@Scheduled(fixedDelay=60_000)
	public void reReconfigured() {
		log.debug("Перезагрузка настроек");
		tmpParams.putAll(getFromSource(new FirstLevel(env)));
		tmpParams.putAll(getFromSource(new SecondLevel(env)));
		
		
		params = tmpParams;
		
	}
}
