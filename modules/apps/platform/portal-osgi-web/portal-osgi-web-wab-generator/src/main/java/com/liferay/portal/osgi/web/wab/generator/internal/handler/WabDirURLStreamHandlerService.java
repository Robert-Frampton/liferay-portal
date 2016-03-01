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

package com.liferay.portal.osgi.web.wab.generator.internal.handler;

import com.liferay.portal.osgi.web.wab.generator.internal.processor.WabDirProcessor;

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.url.AbstractURLStreamHandlerService;

/**
 * @author Gregory Amerson
 */
public class WabDirURLStreamHandlerService
	extends AbstractURLStreamHandlerService {

	public WabDirURLStreamHandlerService(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	@Override
	public URLConnection openConnection(URL url) {
		try {
			URL filePath = new URL(url.getFile());

			File wabDir = new File(filePath.toURI());
			
			Matcher matcher = _pattern.matcher(wabDir.getName());
			
			String contextName = wabDir.getName();

			if (matcher.matches()) {
				contextName = matcher.group(1);
			}

			Map<String, String[]> parameters = new HashMap<>();

			String[] webContextPath = {contextName};

			parameters.put("Web-ContextPath", webContextPath);

			new WabDirProcessor(_classLoader, wabDir, parameters);

			URL wabDirURL = wabDir.toURI().toURL();

			WabDirHandler wabDirHandler = new WabDirHandler(
				wabDirURL.toExternalForm());

			return wabDirHandler.openConnection(url);
		} 
		catch (IOException | URISyntaxException e) {
		}

		return null;
	}

	private static final Pattern _pattern = Pattern.compile(
		"(.*?)(-\\d+\\.\\d+\\.\\d+\\.\\d+)?");

	private final ClassLoader _classLoader;

}