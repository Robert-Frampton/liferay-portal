<%--
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

@generated
--%>

<%@ include file="/html/taglib/taglib-init.jsp" %>

<%
java.lang.Number animationTime = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:carousel:animationTime")), 0.5);
java.lang.String cssClass = GetterUtil.getString((java.lang.String)request.getAttribute("aui:carousel:cssClass"));
java.lang.String height = GetterUtil.getString((java.lang.String)request.getAttribute("aui:carousel:height"));
java.lang.String id = GetterUtil.getString((java.lang.String)request.getAttribute("aui:carousel:id"));
java.lang.Number intervalTime = GetterUtil.getNumber(String.valueOf(request.getAttribute("aui:carousel:intervalTime")), 2);
boolean pauseOnHover = GetterUtil.getBoolean(String.valueOf(request.getAttribute("aui:carousel:pauseOnHover")), false);
java.lang.String width = GetterUtil.getString((java.lang.String)request.getAttribute("aui:carousel:width"));
Map<String, Object> dynamicAttributes = (Map<String, Object>)request.getAttribute("aui:carousel:dynamicAttributes");
Map<String, Object> scopedAttributes = (Map<String, Object>)request.getAttribute("aui:carousel:scopedAttributes");
%>

<%@ include file="/html/taglib/aui/carousel/init-ext.jspf" %>