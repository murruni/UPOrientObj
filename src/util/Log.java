package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import persistence.DBManager;

public class Log {
	private static final Logger log = LogManager.getLogger(DBManager.class.getName());

	public static void logger(String msg, Object e) {
		log.error(msg, e);
	}
}
