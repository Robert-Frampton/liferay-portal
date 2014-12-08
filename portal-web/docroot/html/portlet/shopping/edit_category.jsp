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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

ShoppingCategory category = (ShoppingCategory)request.getAttribute(WebKeys.SHOPPING_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId");

long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId", ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);
%>

<portlet:actionURL var="editCategoryURL">
	<portlet:param name="struts_action" value="/shopping/edit_category" />
</portlet:actionURL>

<aui:form action="<%= editCategoryURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveCategory();" %>'>
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="categoryId" type="hidden" value="<%= categoryId %>" />
	<aui:input name="parentCategoryId" type="hidden" value="<%= parentCategoryId %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title="category"
	/>

	<liferay-ui:error exception="<%= CategoryNameException.class %>" message="please-enter-a-valid-name" />

	<c:if test="<%= parentCategoryId != ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>">
		<div class="breadcrumbs">
			<%= ShoppingUtil.getBreadcrumbs(parentCategoryId, renderRequest, renderResponse) %>
		</div>
	</c:if>

	<aui:model-context bean="<%= category %>" model="<%= ShoppingCategory.class %>" />

	<aui:fieldset>
		<c:if test="<%= category != null %>">

			<%
			String parentCategoryName = "";

			try {
				ShoppingCategory parentCategory = ShoppingCategoryServiceUtil.getCategory(parentCategoryId);

				parentCategoryName = parentCategory.getName();
			}
			catch (NoSuchCategoryException nsce) {
			}
			%>

			<div class="form-group">
				<aui:input label="parent-category" name="parentCategoryName" type="resource" value="<%= parentCategoryName %>" />

				<aui:button id="selectCategoryButton" value="select" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "removeCategory();" %>' value="remove" />
			</div>

			<div id="<portlet:namespace />mergeParentCheckboxDiv"
				<c:if test="<%= category.getParentCategoryId() == ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>">
					style="display: none;"
				</c:if>
			>
				<aui:input name="mergeWithParentCategory" type="checkbox" />
			</div>
		</c:if>

		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" cssClass="lfr-input-text-container" name="name" />

		<aui:input cssClass="lfr-textarea-container" name="description" />

		<c:if test="<%= category == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions
					modelName="<%= ShoppingCategory.class.getName() %>"
				/>
			</aui:field-wrapper>
		</c:if>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	function <portlet:namespace />saveCategory() {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('<%= Constants.CMD %>').val('<%= (category == null) ? Constants.ADD : Constants.UPDATE %>');

		submitForm(form);
	}

	function <portlet:namespace />removeCategory() {
		var $ = AUI.$;

		var form = $(document.<portlet:namespace />fm);

		form.fm('parentCategoryId').val('<%= ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>');

		form.fm('parentCategoryName').val('');

		$('#<portlet:namespace />mergeParentCheckboxDiv').addClass('hide');

		$('#<portlet:namespace />mergeWithParentCategory').prop('checked', false);
	}

	AUI.$('#<portlet:namespace />selectCategoryButton').on(
		'click',
		function(event) {
			Liferay.Util.selectEntity(
				{
					dialog: {
						constrain: true,
						modal: true,
						width: 680
					},
					id: '<portlet:namespace />selectCategory',
					title: '<liferay-ui:message arguments="category" key="select-x" />',
					uri: '<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="struts_action" value="/shopping/select_category" /><portlet:param name="categoryId" value="<%= String.valueOf(parentCategoryId) %>" /></portlet:renderURL>'
				},
				function(event) {
					var form = AUI.$(document.<portlet:namespace />fm);

					var parentCategoryId = event.categoryid;

					form.fm('parentCategoryId').val(parentCategoryId);

					form.fm('parentCategoryName').val(event.name);

					if (parentCategoryId != <%= ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID %>) {
						$('#<portlet:namespace />mergeParentCheckboxDiv').removeClass('hide');
					}
				}
			);
		}
	);
</aui:script>