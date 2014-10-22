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
--%>

<%@ include file="/html/portlet/journal/init.jsp" %>

<%
JournalArticle article = (JournalArticle)request.getAttribute(WebKeys.JOURNAL_ARTICLE);

boolean smallImage = BeanParamUtil.getBoolean(article, request, "smallImage");

String defaultLanguageId = (String)request.getAttribute("edit_article.jsp-defaultLanguageId");

boolean useSmallImageURL = (article != null) && Validator.isNotNull(article.getSmallImageURL());
%>

<liferay-ui:error-marker key="errorSection" value="abstract" />

<aui:model-context bean="<%= article %>" defaultLanguageId="<%= defaultLanguageId %>" model="<%= JournalArticle.class %>" />

<h3><liferay-ui:message key="abstract" /></h3>

<liferay-ui:error exception="<%= ArticleSmallImageNameException.class %>">

	<%
	String[] imageExtensions = PrefsPropsUtil.getStringArray(PropsKeys.JOURNAL_IMAGE_EXTENSIONS, StringPool.COMMA);
	%>

	<liferay-ui:message key="image-names-must-end-with-one-of-the-following-extensions" /> <%= StringUtil.merge(imageExtensions, ", ") %>.
</liferay-ui:error>

<liferay-ui:error exception="<%= ArticleSmallImageSizeException.class %>">

	<%
	long imageMaxSize = PrefsPropsUtil.getLong(PropsKeys.JOURNAL_IMAGE_SMALL_MAX_SIZE);
	%>

	<liferay-ui:message arguments="<%= TextFormatter.formatStorageSize(imageMaxSize, locale) %>" key="please-enter-a-small-image-with-a-valid-file-size-no-larger-than-x" translateArguments="<%= false %>" />
</liferay-ui:error>

<aui:fieldset>
	<aui:input label="summary" name="description" />

	<div id="<portlet:namespace />smallImageContainer">
		<div class="lfr-journal-small-image-header">
			<aui:input label="use-small-image" name="smallImage" />
		</div>

		<div class="lfr-journal-small-image-content toggler-content-collapsed">
			<aui:row>
				<c:if test="<%= smallImage && (article != null) %>">
					<aui:col width="<%= 50 %>">
						<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="preview" />" class="lfr-journal-small-image-preview" src="<%= HtmlUtil.escapeAttribute(article.getArticleImageURL(themeDisplay)) %>" />
					</aui:col>
				</c:if>

				<aui:col width="<%= (smallImage && (article != null)) ? 50 : 100 %>">
					<aui:fieldset>
						<aui:input cssClass="lfr-journal-small-image-type lfr-journal-small-image-url" inlineField="<%= true %>" label="small-image-url" name="smallImageType" type="radio" checked="<%= useSmallImageURL %>"/>

						<aui:input cssClass="lfr-journal-small-image-value" disabled="<%= !useSmallImageURL %>" inlineField="<%= true %>" label="" name="smallImageURL" title="small-image-url" />
					</aui:fieldset>

					<aui:fieldset>
						<aui:input cssClass="lfr-journal-small-image-type lfr-journal-small-image-file" inlineField="<%= true %>" label="small-image" name="smallImageType" type="radio" checked="<%= !useSmallImageURL %>"/>

						<aui:input cssClass="lfr-journal-small-image-value" disabled="<%= useSmallImageURL %>" inlineField="<%= true %>" label="" name="smallFile" type="file" />
					</aui:fieldset>
				</aui:col>
			</aui:row>
		</div>
	</div>

	<aui:script use="aui-toggler">
		var container = $('#<portlet:namespace />smallImageContainer');

		var urlType = container.find('.lfr-journal-small-image-url');

		var urlValue = container.find('#<portlet:namespace />smallImageURL');
		var fileValue = container.find('#<portlet:namespace />smallFile');

		function updateDisabledFields() {
			var isURL = urlType.prop('checked');

			urlValue.prop('disabled', !isURL);
			fileValue.prop('disabled', isURL);
		}

		container.on('change', '.lfr-journal-small-image-type', updateDisabledFields);

		new A.Toggler(
			{
				animated: true,
				content: '#<portlet:namespace />smallImageContainer .lfr-journal-small-image-content',
				expanded: <%= smallImage %>,
				header: '#<portlet:namespace />smallImageContainer .lfr-journal-small-image-header',
				after: {
					expandedChange: function(event) {
						var expanded = this.get('expanded');

						if (!expanded) {
							urlValue.prop('disabled', true);
							fileValue.prop('disabled', true);
						}
						else {
							updateDisabledFields();
						}
					}
				}
			}
		);
	</aui:script>
</aui:fieldset>