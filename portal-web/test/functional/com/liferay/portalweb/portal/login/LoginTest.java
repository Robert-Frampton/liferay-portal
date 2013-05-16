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

package com.liferay.portalweb.portal.login;

import com.liferay.portalweb.portal.BaseTestCase;
import com.liferay.portalweb.portal.util.BrowserCommands;
import com.liferay.portalweb.portal.util.RuntimeVariables;

/**
 * @author Brian Wing Shun Chan
 */
public class LoginTest extends BaseTestCase {
	public void testLogin() throws Exception {
		int label = 1;

		while (label >= 1) {
			switch (label) {
			case 1:
				selenium.selectWindow("null");
				selenium.selectFrame("relative=top");
				selenium.setDefaultTimeout();
				selenium.setDefaultTimeoutImplicit();
				selenium.open("/web/guest/home");
				selenium.waitForVisible("//input[@id='_58_login']");
				selenium.type("//input[@id='_58_login']",
					RuntimeVariables.replace("test@liferay.com"));
				selenium.type("//input[@id='_58_password']",
					RuntimeVariables.replace("test"));
				selenium.waitForVisible("//input[@id='_58_rememberMeCheckbox']");
				selenium.click("//input[@id='_58_rememberMeCheckbox']");
				selenium.waitForVisible("//button[contains(.,'Sign In')]");
				assertEquals(RuntimeVariables.replace("Sign In"),
					selenium.getText("//button[contains(.,'Sign In')]"));
				selenium.clickAt("//button[contains(.,'Sign In')]",
					RuntimeVariables.replace("Sign In"));
				selenium.waitForPageToLoad("30000");

				boolean iAgreePresent = selenium.isElementPresent(
						"//button[contains(.,'I Agree')]");

				if (!iAgreePresent) {
					label = 2;

					continue;
				}

				assertEquals(RuntimeVariables.replace("I Agree"),
					selenium.getText("//button[contains(.,'I Agree')]"));
				selenium.clickAt("//button[contains(.,'I Agree')]",
					RuntimeVariables.replace("I Agree"));
				selenium.waitForPageToLoad("30000");

			case 2:

				boolean reminderQuestionPresent = selenium.isElementPresent(
						"//input[@id='reminderQueryAnswer']");

				if (!reminderQuestionPresent) {
					label = 3;

					continue;
				}

				selenium.type("//input[@id='reminderQueryAnswer']",
					RuntimeVariables.replace("test"));
				selenium.waitForVisible("//button[contains(.,'Save')]");
				assertEquals(RuntimeVariables.replace("Save"),
					selenium.getText("//button[contains(.,'Save')]"));
				selenium.clickAt("//button[contains(.,'Save')]",
					RuntimeVariables.replace("Save"));
				selenium.waitForPageToLoad("30000");

			case 3:
				BrowserCommands.setBrowserOption();

			case 100:
				label = -1;
			}
		}
	}
}