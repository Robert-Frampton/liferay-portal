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

package com.liferay.portal.servlet.filters.util;

import com.liferay.portal.kernel.servlet.BrowserSniffer;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ugurcan Cetin
 */
public class BrowserIdCacheFileNameContributor
	implements CacheFileNameContributor {

	@Override
	public String getParameterName() {
		return "browserId";
	}

	@Override
	public String getParameterValue(HttpServletRequest request) {
		String browserId = ParamUtil.getString(request, "browserId");

		if (_validBrowserIds.contains(browserId)) {
			return browserId;
		}
		else {
			return null;
		}
	}

	private static final List<String> _validBrowserIds = Arrays.asList(
		BrowserSniffer.BROWSER_ID_EDGE, BrowserSniffer.BROWSER_ID_FIREFOX,
		BrowserSniffer.BROWSER_ID_IE, BrowserSniffer.BROWSER_ID_OTHER);

}