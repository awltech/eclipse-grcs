package com.worldline.awltech.rcs4e.core.internal;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

/**
 * Singleton implementation in charge of parsing the extension point related to
 * Runnable Registry, and to store them in cache
 * 
 * @author mvanbesien
 * 
 */
public enum RunnableCallbacksRegistry {

	INSTANCE;

	private static final String IMPLEMENTATION = "implementation";
	private static final String IDENTIFIER = "identifier";
	private static final String CALLBACK = "callback";
	private static final String RUNNABLE_CALLBACKS = "RunnableCallbacks";
	private final Map<String, Collection<Runnable>> runnableRegistry = new HashMap<String, Collection<Runnable>>();

	private RunnableCallbacksRegistry() {
		final IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				RUNNABLE_CALLBACKS);
		if (extensionPoint != null) {
			for (final IConfigurationElement element : extensionPoint.getConfigurationElements()) {
				if (CALLBACK.equals(element.getName())) {
					final String identifier = element.getAttribute(IDENTIFIER);
					try {
						final Object implementation = element.createExecutableExtension(IMPLEMENTATION);
						if (implementation instanceof Runnable) {
							Collection<Runnable> collection = this.runnableRegistry.get(identifier);
							if (collection == null) {
								collection = new HashSet<Runnable>();
								this.runnableRegistry.put(identifier, collection);
							}
							collection.add((Runnable) implementation);
						}
					} catch (CoreException e) {
					}
				}
			}
		}
	}

	/**
	 * Returns a group of runnables, bound to this identifier
	 * 
	 * @param identifier
	 * @return
	 */
	public Collection<Runnable> getImplementation(final String identifier) {
		if (this.runnableRegistry.get(identifier) != null) {
			return Collections.unmodifiableCollection(this.runnableRegistry.get(identifier));
		}
		return Collections.emptyList();
	}
}
