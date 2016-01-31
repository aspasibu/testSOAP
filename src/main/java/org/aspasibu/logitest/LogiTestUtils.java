package org.aspasibu.logitest;

/**
 * @author andrey.spasibukhov@yandex.ru
 *
 *         Utility class for app
 */
public class LogiTestUtils {

	public static String convertMillisToStrHours(long time) {
		long seconds = time / 1000;

		long hours = seconds / 3600;
		long minutes = seconds / 60;
		minutes -= hours * 60;
		seconds -= hours * 3600 + minutes * 60;

		return String.format("%d h. %d min. %d sec.", hours, minutes, seconds);
	}

}
