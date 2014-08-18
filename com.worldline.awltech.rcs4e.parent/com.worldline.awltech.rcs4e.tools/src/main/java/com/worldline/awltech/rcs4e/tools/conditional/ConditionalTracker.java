package com.worldline.awltech.rcs4e.tools.conditional;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.worldline.awltech.rcs4e.tools.internal.Activator;
import com.worldline.awltech.rcs4e.tools.internal.conditional.ConditionalTrackerDialog;
import com.worldline.awltech.rcs4e.tools.internal.conditional.ConditionalTrackerStatusCache;

public abstract class ConditionalTracker implements Runnable {

	private static final String NO_STARTUP_CHECK_SUFFIX = ".noStartupCheck";

	private static final String DISABLED_SUFFIX = ".disabled";

	private final String identifier;

	private final String disabledKey;

	private final String noStartupCheckKey;

	private String name;

	private static final IPreferenceStore store = Activator.getDefault().getPreferenceStore();

	public ConditionalTracker(String identifier, String name) {
		this.identifier = identifier;
		this.disabledKey = identifier.concat(DISABLED_SUFFIX);
		this.noStartupCheckKey = identifier.concat(NO_STARTUP_CHECK_SUFFIX);
		this.name = name;
	}

	protected abstract void doRun();

	@Override
	public void run() {
		if (!ConditionalTrackerStatusCache.INSTANCE.touch(this.identifier)
				&& !ConditionalTracker.store.getBoolean(this.noStartupCheckKey)) {
			Display.getDefault().syncExec(new Runnable() {
				@Override
				public void run() {
					final ConditionalTrackerDialog dialog = new ConditionalTrackerDialog(new Shell(), name);
					ConditionalTracker.store.setValue(ConditionalTracker.this.disabledKey, dialog.open() != Window.OK);
					ConditionalTracker.store.setValue(ConditionalTracker.this.noStartupCheckKey, dialog.isDontShowValue());
				}
			});
		}

		if (!ConditionalTracker.store.getBoolean(this.disabledKey)) {
			this.doRun();
		}
	}
}
