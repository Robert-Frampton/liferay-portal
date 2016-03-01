package com.liferay.portal.osgi.web.wab.generator.internal.handler;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import org.eclipse.osgi.storage.url.reference.Handler;

/**
 * @author Gregory Amerson
 */
public class WabDirHandler extends Handler {

	public WabDirHandler(String installURL) {
		super(installURL);
	}

	@Override
	public URLConnection openConnection(URL url) throws IOException {
		return super.openConnection(url);
	}

}