package css.engine.config.sources;

import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

public class FirstLevelSource implements IConfigurationSource {
	private static final Logger log = LoggerFactory.getLogger(FirstLevelSource.class);

	private Environment env;
	
	public FirstLevelSource(Environment env){
		this.env=env;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public HashMap<String, String> getParams() {
		log.debug("Загрузка настроек первого уровня");
		HashMap<String, String> tmpParams = new HashMap();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
            		for (String key: ((MapPropertySource) propertySource).getSource().keySet()){
            			tmpParams.put(key.toUpperCase(),env.getProperty(key));
            			log.debug("Загружена {}: {}",key.toUpperCase(),tmpParams.get(key.toUpperCase()));
            		}
            }
        }
        
		return tmpParams;
	}
	
}
