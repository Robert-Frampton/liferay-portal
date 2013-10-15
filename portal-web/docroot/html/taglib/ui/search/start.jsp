<%--
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
--%>

<%@ include file="/html/taglib/ui/search/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");

Group group = themeDisplay.getScopeGroup();

String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = null;

if (portletResponse != null) {
	LiferayPortletResponse liferayPortletResponse = (LiferayPortletResponse)portletResponse;

	portletURL = liferayPortletResponse.createLiferayPortletURL(PortletKeys.SEARCH, PortletRequest.RENDER_PHASE);
}
else {
	portletURL = new PortletURLImpl(request, PortletKeys.SEARCH, plid, PortletRequest.RENDER_PHASE);
}

portletURL.setParameter("struts_action", "/search/search");
portletURL.setParameter("redirect", currentURL);
portletURL.setPortletMode(PortletMode.VIEW);
portletURL.setWindowState(WindowState.MAXIMIZED);

pageContext.setAttribute("portletURL", portletURL);
%>

<form action="<%= portletURL.toString() %>" method="get" name="<%= randomNamespace %><%= namespace %>fm" onSubmit="<%= randomNamespace %><%= namespace %>search(); return false;">
<liferay-portlet:renderURLParams varImpl="portletURL" />

<aui:nav-bar cssClass="search-toolbar">
	<aui:nav-bar-search cssClass="pull-right">
		<div class="form-search">
			<aui:select cssClass="search-scope" inlineField="<%= true %>" label="" name="groupId" title="scope">
				<aui:option label="everything" selected="<%= groupId == 0 %>" value="0" />
				<aui:option label='<%= "this-" + (group.isOrganization() ? "organization" : "site") %>' selected="<%= groupId != 0 %>" value="<%= group.getGroupId() %>" />
			</aui:select>

			<liferay-ui:input-search cssClass="input-append search-keywords" name="keywords" title="search" />
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:script>
	function <%= randomNamespace %><%= namespace %>search() {
		var keywords = document.<%= randomNamespace %><%= namespace %>fm.<%= namespace %>keywords.value;

		keywords = keywords.replace(/^\s+|\s+$/, '');

		if (keywords != '') {
			submitForm(document.<%= randomNamespace %><%= namespace %>fm);
		}
	}
</aui:script>