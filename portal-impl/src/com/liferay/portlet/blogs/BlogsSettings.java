/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.blogs;

import com.liferay.portal.kernel.settings.FallbackKeys;
import com.liferay.portal.kernel.settings.LocalizedValuesMap;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.TypedSettings;
import com.liferay.portal.kernel.util.PropsKeys;

/**
 * @author Iván Zaera
 */
public class BlogsSettings {

	public static FallbackKeys getFallbackKeys() {
		FallbackKeys fallbackKeys = new FallbackKeys();

		fallbackKeys.add(
			"emailEntryAddedBody", PropsKeys.BLOGS_EMAIL_ENTRY_ADDED_BODY);
		fallbackKeys.add(
			"emailEntryAddedEnabled",
			PropsKeys.BLOGS_EMAIL_ENTRY_ADDED_ENABLED);
		fallbackKeys.add(
			"emailEntryAddedSubject",
			PropsKeys.BLOGS_EMAIL_ENTRY_ADDED_SUBJECT);
		fallbackKeys.add(
			"emailEntryUpdatedBody", PropsKeys.BLOGS_EMAIL_ENTRY_UPDATED_BODY);
		fallbackKeys.add(
			"emailEntryUpdatedEnabled",
			PropsKeys.BLOGS_EMAIL_ENTRY_UPDATED_ENABLED);
		fallbackKeys.add(
			"emailEntryUpdatedSubject",
			PropsKeys.BLOGS_EMAIL_ENTRY_UPDATED_SUBJECT);
		fallbackKeys.add(
			"emailFromAddress", PropsKeys.BLOGS_EMAIL_FROM_ADDRESS,
			PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		fallbackKeys.add(
			"emailFromName", PropsKeys.BLOGS_EMAIL_FROM_NAME,
			PropsKeys.ADMIN_EMAIL_FROM_NAME);

		return fallbackKeys;
	}

	public BlogsSettings(Settings settings) {
		_typedSettings = new TypedSettings(settings);
	}

	public LocalizedValuesMap getEmailEntryAddedBody() {
		return _typedSettings.getLocalizedValuesMap("emailEntryAddedBody");
	}

	public String getEmailEntryAddedBodyXml() {
		LocalizedValuesMap emailEntryAddedBodyMap = getEmailEntryAddedBody();

		return emailEntryAddedBodyMap.getLocalizationXml();
	}

	public boolean getEmailEntryAddedEnabled() {
		return _typedSettings.getBooleanValue("emailEntryAddedEnabled");
	}

	public LocalizedValuesMap getEmailEntryAddedSubject() {
		return _typedSettings.getLocalizedValuesMap("emailEntryAddedSubject");
	}

	public String getEmailEntryAddedSubjectXml() {
		LocalizedValuesMap emailEntryAddedSubjectMap =
			getEmailEntryAddedSubject();

		return emailEntryAddedSubjectMap.getLocalizationXml();
	}

	public LocalizedValuesMap getEmailEntryUpdatedBody() {
		return _typedSettings.getLocalizedValuesMap("emailEntryUpdatedBody");
	}

	public String getEmailEntryUpdatedBodyXml() {
		LocalizedValuesMap emailEntryUpdatedBodyMap =
			getEmailEntryUpdatedBody();

		return emailEntryUpdatedBodyMap.getLocalizationXml();
	}

	public boolean getEmailEntryUpdatedEnabled() {
		return _typedSettings.getBooleanValue("emailEntryUpdatedEnabled");
	}

	public LocalizedValuesMap getEmailEntryUpdatedSubject() {
		return _typedSettings.getLocalizedValuesMap("emailEntryUpdatedSubject");
	}

	public String getEmailEntryUpdatedSubjectXml() {
		LocalizedValuesMap emailEntryUpdatedSubjectMap =
			getEmailEntryUpdatedSubject();

		return emailEntryUpdatedSubjectMap.getLocalizationXml();
	}

	public String getEmailFromAddress() {
		return _typedSettings.getValue("emailFromAddress");
	}

	public String getEmailFromName() {
		return _typedSettings.getValue("emailFromName");
	}

	private TypedSettings _typedSettings;

}