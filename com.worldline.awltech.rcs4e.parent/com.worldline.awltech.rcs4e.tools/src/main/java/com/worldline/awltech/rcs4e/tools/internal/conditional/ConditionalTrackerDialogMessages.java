/**
 *
 */
package com.worldline.awltech.rcs4e.tools.internal.conditional;

import java.util.Locale;
import java.util.ResourceBundle;

import java.text.MessageFormat;

/**
 * Enumeration containing internationalisation-related messages and API.
 * 
 * @generated com.worldline.awltech.i18ntools.wizard
 */
public enum ConditionalTrackerDialogMessages {
	DONTSHOW_BUTTON("DONTSHOW_BUTTON"), YES("YES"), NO("NO")
	;

	/*
	 * Value of the key
	 */
	private final String messageKey;

	/*
	 * Constant ResourceBundle instance
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("ConditionalTrackerDialogMessages",
			Locale.getDefault());

	/**
	 * Private Enumeration Literal constructor
	 * 
	 * @param messageKey
	 *            value
	 */
	private ConditionalTrackerDialogMessages(final String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the message associated with the current value
	 */
	public String value() {
		if (ConditionalTrackerDialogMessages.RESOURCE_BUNDLE == null
				|| !ConditionalTrackerDialogMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return ConditionalTrackerDialogMessages.RESOURCE_BUNDLE.getString(this.messageKey);
	}

	/**
	 * Formats and returns the message associated with the current value.
	 * 
	 * @see java.text.MessageFormat
	 * @param parameters
	 *            to use during formatting phase
	 * @return formatted message
	 */
	public String value(final Object... args) {
		if (ConditionalTrackerDialogMessages.RESOURCE_BUNDLE == null
				|| !ConditionalTrackerDialogMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return MessageFormat.format(ConditionalTrackerDialogMessages.RESOURCE_BUNDLE.getString(this.messageKey), args);
	}

}
