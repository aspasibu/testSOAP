package org.aspasibu.logitest;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

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

	private static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	// then use Spring BeanUtils to copy and ignore null
	public static void copyProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

}
