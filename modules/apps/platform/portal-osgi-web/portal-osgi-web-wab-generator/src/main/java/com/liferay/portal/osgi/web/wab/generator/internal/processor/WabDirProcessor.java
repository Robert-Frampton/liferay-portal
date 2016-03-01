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

package com.liferay.portal.osgi.web.wab.generator.internal.processor;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;

import java.io.File;
import java.io.IOException;

import java.util.Map;

/**
 * @author Gregory Amerson
 */
public class WabDirProcessor extends WabProcessor {

	public WabDirProcessor(
			ClassLoader classLoader, File dir, Map<String, String[]> parameters)
		throws IOException {

		super(classLoader, dir, parameters);

		_pluginDir = autoDeployDir();

		if (!isValidOSGiBundle()) {
			transformToOSGiBundle();
		}
	}

	protected File autoDeployDir() {
		String webContextPath = getWebContextPath();

		AutoDeploymentContext autoDeploymentContext =
			buildAutoDeploymentContextDir(webContextPath);

		executeAutoDeployers(autoDeploymentContext);

		_pluginPackage = autoDeploymentContext.getPluginPackage();

		if (_pluginPackage != null) {
			_context = _pluginPackage.getContext();
		}
		else {
			_context = autoDeploymentContext.getContext();
		}

		return _file;
	}

	protected AutoDeploymentContext buildAutoDeploymentContextDir(
		String context) {

		AutoDeploymentContext autoDeploymentContext =
			new AutoDeploymentContext();

		autoDeploymentContext.setContext(context);

		autoDeploymentContext.setFile(_file);

		return autoDeploymentContext;
	}

}