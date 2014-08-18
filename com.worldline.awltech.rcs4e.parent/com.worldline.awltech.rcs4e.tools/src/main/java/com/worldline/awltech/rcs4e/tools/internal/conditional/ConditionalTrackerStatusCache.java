package com.worldline.awltech.rcs4e.tools.internal.conditional;

import java.util.HashSet;
import java.util.Set;

/**
 * Cache used to store information, so dialogs for a given key are only opened
 * one by Eclipse session...
 * 
 * @author mvanbesien
 * 
 */
public enum ConditionalTrackerStatusCache {
	INSTANCE;

	private Set<String> openedDialogs = new HashSet<>();

	public boolean touch(String id) {
		boolean touched = openedDialogs.contains(id);
		if (!touched) {
			this.openedDialogs.add(id);
		}
		return touched;
	}

}
