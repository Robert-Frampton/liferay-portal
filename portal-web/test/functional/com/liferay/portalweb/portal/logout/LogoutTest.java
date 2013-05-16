/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portalweb.portal.logout;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class LogoutTest extends BaseTestCase {
	public void testLogout() throws Exception {
		selenium.selectWindow("null");
		selenium.selectFrame("relative=top");
		selenium.open("/web/guest/home");
		selenium.waitForVisible("//span[@class='user-full-name']");
		selenium.clickAt("//span[@class='user-full-name']",
			RuntimeVariables.replace("User Full Name"));
		selenium.waitForVisible(
			"//ul[@class='dropdown-menu']/li[@class='sign-out']/a");
		assertEquals(RuntimeVariables.replace("Sign Out"),
			selenium.getText(
				"//ul[@class='dropdown-menu']/li[@class='sign-out']/a"));
		selenium.clickAt("//ul[@class='dropdown-menu']/li[@class='sign-out']/a",
			RuntimeVariables.replace("Sign Out"));
		selenium.waitForPageToLoad("30000");
		selenium.waitForVisible("//input[@id='_58_login']");
	}
}