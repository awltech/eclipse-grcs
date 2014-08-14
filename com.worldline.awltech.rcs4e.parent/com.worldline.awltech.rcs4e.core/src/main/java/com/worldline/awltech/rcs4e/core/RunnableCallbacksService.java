package com.worldline.awltech.rcs4e.core;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.worldline.awltech.rcs4e.core.internal.ParametersInjector;
import com.worldline.awltech.rcs4e.core.internal.RunnableCallbacksRegistry;

/**
 * Singleton implementation of Runnable Callback Service.
 * 
 * This implementation is in charge of retrieving
 * 
 * @author mvanbesien
 * 
 */
public enum RunnableCallbacksService {

	/**
	 * Singleton Instance
	 */
	INSTANCE;

	/**
	 * Underlying static executor service, to parallely run callbacks.
	 */
	private static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

	/**
	 * Runs the callbacks matching with proposed name and parameters list
	 * 
	 * @param name
	 * @param parametersList
	 */
	public void run(String name, Set<Parameter> parametersList) {
		this.run(name, parametersList.toArray(new Parameter[parametersList.size()]));
	}

	/**
	 * Runs the callbacks matching with proposed name and parameters list
	 * 
	 * @param name
	 * @param parametersList
	 */
	public void run(String name, Parameter... parameterList) {

		for (Runnable implementation : RunnableCallbacksRegistry.INSTANCE.getImplementation(name)) {
			if (implementation == null) {
				return;
			}

			if (parameterList != null) {
				ParametersInjector.inject(implementation, parameterList);
			}

			Thread thread = new Thread(implementation);
			thread.setDaemon(true);
			thread.setName("RunnableCallback-" + System.currentTimeMillis());

			try {
				RunnableCallbacksService.newFixedThreadPool.execute(implementation);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Runs the callbacks matching with proposed name and parameters list
	 * @param name
	 * @param parametersList
	 */
	public void run(String name, Parameters parameterList) {
		this.run(name, parameterList.get());
	}

}
