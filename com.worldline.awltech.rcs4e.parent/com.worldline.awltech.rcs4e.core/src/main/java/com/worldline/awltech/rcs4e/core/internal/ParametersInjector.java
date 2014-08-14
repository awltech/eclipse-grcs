package com.worldline.awltech.rcs4e.core.internal;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.worldline.awltech.rcs4e.core.Parameter;
import com.worldline.awltech.rcs4e.core.Parameterized;

/**
 * Helper class in charge of the injection
 * 
 * @author mvanbesien
 * 
 */
public final class ParametersInjector {

	private ParametersInjector() {
	}

	/**
	 * Injects the parameters passed as parameter, in the Runnable
	 * implementation
	 * 
	 * @param implementation
	 * @param parameterList
	 */
	public static void inject(Object implementation, Parameter[] parameterList) {
		if (implementation == null) {
			return;
		}

		Class<?> clazz = implementation.getClass();
		Map<String, Field> fields = new HashMap<String, Field>();

		for (Field field : clazz.getDeclaredFields()) {
			if (field.getAnnotation(Parameterized.class) != null) {
				Parameterized annotation = field.getAnnotation(Parameterized.class);
				String value = annotation.value();
				fields.put(value, field);
			}
		}

		for (Parameter parameter : parameterList) {
			Field field = fields.get(parameter.getKey());
			if (field != null) {

				Object value = parameter.getValue();
				if (value != null && value.getClass().isAssignableFrom(field.getType())) {
					boolean accessible = field.isAccessible();
					field.setAccessible(true);
					try {
						field.set(implementation, value);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(IStatus.WARNING, Activator.PLUGIN_ID,
										"Failed to inject information in field " + clazz.getName() + "."
												+ field.getName(), e));
					}
					field.setAccessible(accessible);
				}
			}
		}
	}
}
