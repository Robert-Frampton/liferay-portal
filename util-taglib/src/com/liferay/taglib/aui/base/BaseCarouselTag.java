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

package com.liferay.taglib.aui.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

/**
 * @author Eduardo Lundgren
 * @author Bruno Basto
 * @author Nathan Cavanaugh
 * @author Julio Camarero
 * @generated
 */
public class BaseCarouselTag extends com.liferay.taglib.util.IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		setAttributeNamespace(_ATTRIBUTE_NAMESPACE);

		return super.doStartTag();
	}

	public java.lang.Object getAnimationTime() {
		return _animationTime;
	}

	public java.lang.String getCssClass() {
		return _cssClass;
	}

	public java.lang.String getHeight() {
		return _height;
	}

	public java.lang.String getId() {
		return _id;
	}

	public java.lang.Object getIntervalTime() {
		return _intervalTime;
	}

	public boolean getPauseOnHover() {
		return _pauseOnHover;
	}

	public java.lang.String getWidth() {
		return _width;
	}

	public void setAnimationTime(java.lang.Object animationTime) {
		_animationTime = animationTime;

		setScopedAttribute("animationTime", animationTime);
	}

	public void setCssClass(java.lang.String cssClass) {
		_cssClass = cssClass;

		setScopedAttribute("cssClass", cssClass);
	}

	public void setHeight(java.lang.String height) {
		_height = height;

		setScopedAttribute("height", height);
	}

	public void setId(java.lang.String id) {
		_id = id;

		setScopedAttribute("id", id);
	}

	public void setIntervalTime(java.lang.Object intervalTime) {
		_intervalTime = intervalTime;

		setScopedAttribute("intervalTime", intervalTime);
	}

	public void setPauseOnHover(boolean pauseOnHover) {
		_pauseOnHover = pauseOnHover;

		setScopedAttribute("pauseOnHover", pauseOnHover);
	}

	public void setWidth(java.lang.String width) {
		_width = width;

		setScopedAttribute("width", width);
	}

	@Override
	protected void cleanUp() {
		_animationTime = 0.5;
		_cssClass = null;
		_height = null;
		_id = null;
		_intervalTime = 2;
		_pauseOnHover = false;
		_width = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		setNamespacedAttribute(request, "animationTime", _animationTime);
		setNamespacedAttribute(request, "cssClass", _cssClass);
		setNamespacedAttribute(request, "height", _height);
		setNamespacedAttribute(request, "id", _id);
		setNamespacedAttribute(request, "intervalTime", _intervalTime);
		setNamespacedAttribute(request, "pauseOnHover", _pauseOnHover);
		setNamespacedAttribute(request, "width", _width);
	}

	protected static final String _ATTRIBUTE_NAMESPACE = "aui:carousel:";

	private static final String _END_PAGE =
		"/html/taglib/aui/carousel/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/aui/carousel/start.jsp";

	private java.lang.Object _animationTime = 0.5;
	private java.lang.String _cssClass = null;
	private java.lang.String _height = null;
	private java.lang.String _id = null;
	private java.lang.Object _intervalTime = 2;
	private boolean _pauseOnHover = false;
	private java.lang.String _width = null;

}