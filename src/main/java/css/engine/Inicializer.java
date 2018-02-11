package css.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Inicializer {
	private static final Logger log = LoggerFactory.getLogger(Inicializer.class);
	private static Boolean isInicialized = false;
	
	Inicializer(){
		log.debug("Инициализация приложения");

	}
	
	public static Boolean isInicialized() {
		return isInicialized;
	}
		
}
