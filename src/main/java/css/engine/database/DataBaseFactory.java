package css.engine.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import css.engine.config.Configuration;

public class DataBaseFactory {
	private static final Logger log = LoggerFactory.getLogger(DataBaseFactory.class);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static IConnection getConnection(String filter){
		log.debug("Ищем доступное подключение");
		HashMap<String,String> connections	=	Configuration.getParams(filter);
		HashMap<Integer,ParamConnection> tmpConnections	=	new HashMap<Integer,ParamConnection>();
		log.debug("Всего настроено подключений: {}",connections.size());
		
		for(String key: connections.keySet()) {
			log.debug("Извлекаем приоритет из: {} разделяя по символу '.'",key);
			log.debug("Разделили на {} значений",key.split("\\.").length);
			int numConnection = Integer.parseInt(key.split("\\.")[3]);
			log.debug("Получили приоритет {}",numConnection);
			tmpConnections.put(numConnection, new ParamConnection(key.split("\\.")[4], connections.get(key)));
		}
		
		List<Integer> priorityConnection=new ArrayList(tmpConnections.keySet());
		Collections.sort(priorityConnection);
		IConnection realConnection	=	null;
		for(Integer i: priorityConnection) {
			log.debug("Проверяем возможность подключения по приоритету({}): {}",i,tmpConnections.get(i).toString());
			ParamConnection c		=	tmpConnections.get(i);	
			if(c.getType().equalsIgnoreCase("ConnectionAppServerPool")) {
				realConnection	=	new ConnectionAppServerPool();
			}
			
			if(realConnection!=null) {
				if(!realConnection.checkConnection()) {
					log.error("Не смогли подключиться ({}): {} ищем следующее подключение",i,tmpConnections.get(i).toString());
					realConnection	=	null;
				}else {
					if(i==1)	log.debug("DataBase настроен на целевое подключение, {}: {}",c.getType(),c.getValue());
					
					realConnection.setPriority(i);
					realConnection.setName(tmpConnections.get(i).toString());
					break;
				}
			}
		}
		
		if(realConnection==null) {
			realConnection	=	new ConnectionDefault();
		}
		
		
		
		return realConnection;
	}
}
