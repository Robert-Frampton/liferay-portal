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

<%@ include file="/html/portlet/unit_converter/init.jsp" %>

<%
int type = ParamUtil.getInteger(request, "type");
int fromId = ParamUtil.getInteger(request, "fromId");
int toId = ParamUtil.getInteger(request, "toId");
double fromValue = ParamUtil.getDouble(request, "fromValue");

Conversion conversion = ConverterUtil.getConversion(type, fromId, toId, fromValue);
%>

<portlet:renderURL var="unitURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="struts_action" value="/unit_converter/view" />
</portlet:renderURL>

<aui:form action="<%= unitURL %>" id="fm" method="post" name="fm">
	<aui:container>
		<aui:row>
			<aui:col md="1">
				<liferay-ui:message key="from" />
			</aui:col>

			<aui:col md="6">
				<aui:input label="" name="fromValue" size="30" type="number" value="<%= conversion.getFromValue() %>" />
			</aui:col>

			<aui:col md="5">
				<aui:select label="" name="fromId">
					<c:if test="<%= type == 0 %>">
						<aui:option selected="<%= (fromId == 0) %>" value="0" label="meter"/>
						<aui:option selected="<%= (fromId == 1) %>" value="1" label="millimeter" />
						<aui:option selected="<%= (fromId == 2) %>" value="2" label="centimeter" />
						<aui:option selected="<%= (fromId == 3) %>" value="3" label="kilometer" />
						<aui:option selected="<%= (fromId == 4) %>" value="4" label="foot" />
						<aui:option selected="<%= (fromId == 5) %>" value="5" label="inch" />
						<aui:option selected="<%= (fromId == 6) %>" value="6" label="yard" />
						<aui:option selected="<%= (fromId == 7) %>" value="7" label="mile" />
						<aui:option selected="<%= (fromId == 8) %>" value="8" label="cubit" />
						<aui:option selected="<%= (fromId == 9) %>" value="9" label="talent" />
						<aui:option selected="<%= (fromId == 10) %>" value="10" label="handbreath" />
					</c:if>
					<c:if test="<%= type == 1 %>">
						<aui:option selected="<%= (fromId == 0) %>" value="0" label="square-kilometer" />
						<aui:option selected="<%= (fromId == 1) %>" value="1" label="square-meter" />
						<aui:option selected="<%= (fromId == 2) %>" value="2" label="square-centimeter" />
						<aui:option selected="<%= (fromId == 3) %>" value="3" label="square-millimeter" />
						<aui:option selected="<%= (fromId == 4) %>" value="4" label="square-foot" />
						<aui:option selected="<%= (fromId == 5) %>" value="5" label="square-inch" />
						<aui:option selected="<%= (fromId == 6) %>" value="6" label="square-yard" />
						<aui:option selected="<%= (fromId == 7) %>" value="7" label="square-mile" />
						<aui:option selected="<%= (fromId == 8) %>" value="8" label="hectare" />
						<aui:option selected="<%= (fromId == 9) %>" value="9" label="acre" />
					</c:if>
					<c:if test="<%= type == 2 %>">
						<aui:option selected="<%= (fromId == 0) %>" value="0" label="Liter" />
						<aui:option selected="<%= (fromId == 1) %>" value="1" label="Cubic Centimeter" />
						<aui:option selected="<%= (fromId == 2) %>" value="2" label="Cubic Inch (Liquid Measure)" />
						<aui:option selected="<%= (fromId == 3) %>" value="3" label="Pint (Dry Measure)" />
						<aui:option selected="<%= (fromId == 4) %>" value="4" label="Cor (Homer)" />
						<aui:option selected="<%= (fromId == 5) %>" value="5" label="Lethek" />
						<aui:option selected="<%= (fromId == 6) %>" value="6" label="Ephah" />
						<aui:option selected="<%= (fromId == 7) %>" value="7" label="Seah" />
						<aui:option selected="<%= (fromId == 8) %>" value="8" label="Omer" />
						<aui:option selected="<%= (fromId == 9) %>" value="9" label="Cab" />
						<aui:option selected="<%= (fromId == 10) %>" value="10" label="Bath" />
						<aui:option selected="<%= (fromId == 11) %>" value="11" label="Hin" />
						<aui:option selected="<%= (fromId == 12) %>" value="12" label="Log" />
					</c:if>
					<c:if test="<%= type == 3 %>">
						<aui:option selected="<%= (fromId == 0) %>" value="0" label="kilogram" />
						<aui:option selected="<%= (fromId == 1) %>" value="1" label="pound" />
						<aui:option selected="<%= (fromId == 2) %>" value="2" label="ton" />
						<aui:option selected="<%= (fromId == 3) %>" value="3" label="talent" />
						<aui:option selected="<%= (fromId == 4) %>" value="4" label="mina" />
						<aui:option selected="<%= (fromId == 5) %>" value="5" label="shekel" />
						<aui:option selected="<%= (fromId == 6) %>" value="6" label="pim" />
						<aui:option selected="<%= (fromId == 7) %>" value="7" label="beka" />
						<aui:option selected="<%= (fromId == 8) %>" value="8" label="gerah" />
					</c:if>
					<c:if test="<%= type == 4 %>">
						<aui:option selected="<%= (fromId == 0) %>" value="0" label="Kelvin" />
						<aui:option selected="<%= (fromId == 1) %>" value="1" label="Celcius" />
						<aui:option selected="<%= (fromId == 2) %>" value="2" label="Fahrenheit" />
						<aui:option selected="<%= (fromId == 3) %>" value="3" label="Rankine" />
						<aui:option selected="<%= (fromId == 4) %>" value="4" label="Réaumure" />
					</c:if>
				</aui:select>
			</aui:col>
		</aui:row>

		<aui:row>
			<aui:col md="1">
				<liferay-ui:message key="to" />
			</aui:col>

			<aui:col md="6">
				<aui:input label="" name="to_value" size="30" type="number" value="<%= conversion.getToValue() %>" />
			</aui:col>

			<aui:col md="5">
				<aui:select label="" name="toId">
					<c:if test="<%= type == 0 %>">
						<aui:option selected="<%= (toId == 0) %>" value="0" label="meter"/>
						<aui:option selected="<%= (toId == 1) %>" value="1" label="millimeter" />
						<aui:option selected="<%= (toId == 2) %>" value="2" label="centimeter" />
						<aui:option selected="<%= (toId == 3) %>" value="3" label="kilometer" />
						<aui:option selected="<%= (toId == 4) %>" value="4" label="foot" />
						<aui:option selected="<%= (toId == 5) %>" value="5" label="inch" />
						<aui:option selected="<%= (toId == 6) %>" value="6" label="yard" />
						<aui:option selected="<%= (toId == 7) %>" value="7" label="mile" />
						<aui:option selected="<%= (toId == 8) %>" value="8" label="cubit" />
						<aui:option selected="<%= (toId == 9) %>" value="9" label="talent" />
						<aui:option selected="<%= (toId == 10) %>" value="10" label="handbreath" />
					</c:if>
					<c:if test="<%= type == 1 %>">
						<aui:option selected="<%= (toId == 0) %>" value="0" label="square-kilometer" />
						<aui:option selected="<%= (toId == 1) %>" value="1" label="square-meter" />
						<aui:option selected="<%= (toId == 2) %>" value="2" label="square-centimeter" />
						<aui:option selected="<%= (toId == 3) %>" value="3" label="square-millimeter" />
						<aui:option selected="<%= (toId == 4) %>" value="4" label="square-foot" />
						<aui:option selected="<%= (toId == 5) %>" value="5" label="square-inch" />
						<aui:option selected="<%= (toId == 6) %>" value="6" label="square-yard" />
						<aui:option selected="<%= (toId == 7) %>" value="7" label="square-mile" />
						<aui:option selected="<%= (toId == 8) %>" value="8" label="hectare" />
						<aui:option selected="<%= (toId == 9) %>" value="9" label="acre" />
					</c:if>
					<c:if test="<%= type == 2 %>">
						<aui:option selected="<%= (toId == 0) %>" value="0" label="Liter" />
						<aui:option selected="<%= (toId == 1) %>" value="1" label="Cubic Centimeter" />
						<aui:option selected="<%= (toId == 2) %>" value="2" label="Cubic Inch (Liquid Measure)" />
						<aui:option selected="<%= (toId == 3) %>" value="3" label="Pint (Dry Measure)" />
						<aui:option selected="<%= (toId == 4) %>" value="4" label="Cor (Homer)" />
						<aui:option selected="<%= (toId == 5) %>" value="5" label="Lethek" />
						<aui:option selected="<%= (toId == 6) %>" value="6" label="Ephah" />
						<aui:option selected="<%= (toId == 7) %>" value="7" label="Seah" />
						<aui:option selected="<%= (toId == 8) %>" value="8" label="Omer" />
						<aui:option selected="<%= (toId == 9) %>" value="9" label="Cab" />
						<aui:option selected="<%= (toId == 10) %>" value="10" label="Bath" />
						<aui:option selected="<%= (toId == 11) %>" value="11" label="Hin" />
						<aui:option selected="<%= (toId == 12) %>" value="12" label="Log" />
					</c:if>
					<c:if test="<%= type == 3 %>">
						<aui:option selected="<%= (toId == 0) %>" value="0" label="kilogram" />
						<aui:option selected="<%= (toId == 1) %>" value="1" label="pound" />
						<aui:option selected="<%= (toId == 2) %>" value="2" label="ton" />
						<aui:option selected="<%= (toId == 3) %>" value="3" label="talent" />
						<aui:option selected="<%= (toId == 4) %>" value="4" label="mina" />
						<aui:option selected="<%= (toId == 5) %>" value="5" label="shekel" />
						<aui:option selected="<%= (toId == 6) %>" value="6" label="pim" />
						<aui:option selected="<%= (toId == 7) %>" value="7" label="beka" />
						<aui:option selected="<%= (toId == 8) %>" value="8" label="gerah" />
					</c:if>
					<c:if test="<%= type == 4 %>">
						<aui:option selected="<%= (toId == 0) %>" value="0" label="Kelvin" />
						<aui:option selected="<%= (toId == 1) %>" value="1" label="Celcius" />
						<aui:option selected="<%= (toId == 2) %>" value="2" label="Fahrenheit" />
						<aui:option selected="<%= (toId == 3) %>" value="3" label="Rankine" />
						<aui:option selected="<%= (toId == 4) %>" value="4" label="Réaumure" />
					</c:if>
				</aui:select>
			</aui:col>
		</aui:row>

		<aui:row>
			<aui:col md="1">
				<liferay-ui:message key="type" />
			</aui:col>

			<aui:col md="6">
				<aui:select label="" name="type" id="type">
					<aui:option selected="<%= (type == 0) %>" value="0" label="length" />
					<aui:option selected="<%= (type == 1) %>" value="1" label="area" />
					<aui:option selected="<%= (type == 2) %>" value="2" label="volume" />
					<aui:option selected="<%= (type == 3) %>" value="3" label="mass" />
					<aui:option selected="<%= (type == 4) %>" value="4" label="temperature" />
				</aui:select>
			</aui:col>
		</aui:row>

		<aui:button type="submit" value="convert" />
		
	</aui:container>
</aui:form>

<aui:script>
	var lengthArray = [
		new Option(0, '<%= UnicodeLanguageUtil.get(request, "meter") %>'),
		new Option(1, '<%= UnicodeLanguageUtil.get(request, "millimeter") %>'),
		new Option(2, '<%= UnicodeLanguageUtil.get(request, "centimeter") %>'),
		new Option(3, '<%= UnicodeLanguageUtil.get(request, "kilometer") %>'),
		new Option(4, '<%= UnicodeLanguageUtil.get(request, "foot") %>'),
		new Option(5, '<%= UnicodeLanguageUtil.get(request, "inch") %>'),
		new Option(6, '<%= UnicodeLanguageUtil.get(request, "yard") %>'),
		new Option(7, '<%= UnicodeLanguageUtil.get(request, "mile") %>'),
		new Option(8, '<%= UnicodeLanguageUtil.get(request, "cubit") %>'),
		new Option(9, '<%= UnicodeLanguageUtil.get(request, "talent") %>'),
		new Option(10, '<%= UnicodeLanguageUtil.get(request, "handbreath") %>')
	];

	var areaArray = [
		new Option(0, '<%= UnicodeLanguageUtil.get(request, "square-kilometer") %>'),
		new Option(1, '<%= UnicodeLanguageUtil.get(request, "square-meter") %>'),
		new Option(2, '<%= UnicodeLanguageUtil.get(request, "square-centimeter") %>'),
		new Option(3, '<%= UnicodeLanguageUtil.get(request, "square-millimeter") %>'),
		new Option(4, '<%= UnicodeLanguageUtil.get(request, "square-foot") %>'),
		new Option(5, '<%= UnicodeLanguageUtil.get(request, "square-inch") %>'),
		new Option(6, '<%= UnicodeLanguageUtil.get(request, "square-yard") %>'),
		new Option(7, '<%= UnicodeLanguageUtil.get(request, "square-mile") %>'),
		new Option(8, '<%= UnicodeLanguageUtil.get(request, "hectare") %>'),
		new Option(9, '<%= UnicodeLanguageUtil.get(request, "acre") %>')
	];

	var volumeArray = [
		new Option(0, 'Liter'),
		new Option(1, 'Cubic Centimeter'),
		new Option(2, 'Cubic Inch (Liquid Measure)'),
		new Option(3, 'Pint (Dry Measure)'),
		new Option(4, 'Cor (Homer)'),
		new Option(5, 'Lethek'),
		new Option(6, 'Ephah'),
		new Option(7, 'Seah'),
		new Option(8, 'Omer'),
		new Option(9, 'Cab'),
		new Option(10, 'Bath'),
		new Option(11, 'Hin'),
		new Option(12, 'Log')
	];

	var massArray = [
		new Option(0, '<%= UnicodeLanguageUtil.get(request, "kilogram") %>'),
		new Option(1, '<%= UnicodeLanguageUtil.get(request, "pound") %>'),
		new Option(2, '<%= UnicodeLanguageUtil.get(request, "ton") %>'),
		new Option(3, '<%= UnicodeLanguageUtil.get(request, "talent") %>'),
		new Option(4, '<%= UnicodeLanguageUtil.get(request, "mina") %>'),
		new Option(5, '<%= UnicodeLanguageUtil.get(request, "shekel") %>'),
		new Option(6, '<%= UnicodeLanguageUtil.get(request, "pim") %>'),
		new Option(7, '<%= UnicodeLanguageUtil.get(request, "beka") %>'),
		new Option(8, '<%= UnicodeLanguageUtil.get(request, "gerah") %>')
	];

	var temperatureArray = [
		new Option(0, 'Kelvin'),
		new Option(1, 'Celcius'),
		new Option(2, 'Fahrenheit'),
		new Option(3, 'Rankine'),
		new Option(4, 'Réaumure')
	];

	<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />fromValue);
	</c:if>
</aui:script>

<aui:script use="aui-io-request,aui-parse-content">
	var form = A.one('#<portlet:namespace />fm');

	form.on(
		'submit',
		function(event) {
			var uri = form.getAttribute('action');
			var parentNode = form.get('parentNode');

			parentNode.plug(A.Plugin.ParseContent);

			A.io.request(
				uri,
				{
					form: {
						id: form
					},
					on: {
						success: function(event, id, obj) {
							var responseData = this.get('responseData');

							parentNode.setContent(responseData);
						}
					}
				}
			);

			event.halt();
		}
	);
</aui:script>

<aui:script use="aui-node">
	var selectType = A.one('#<portlet:namespace />type');

	selectType.on(
		'change',
		function(event) {
			var selectTypeTarget = event.currentTarget;

				if (selectTypeTarget.get('value') == 0) {
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />fromId, lengthArray);
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />toId, lengthArray);
				}
				else if (selectTypeTarget.get('value') == 1) {
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />fromId, areaArray);
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />toId, areaArray);
				}
				else if (selectTypeTarget.get('value') == 2) {
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />fromId, volumeArray);
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />toId, volumeArray);
				}
				else if (selectTypeTarget.get('value') == 3) {
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />fromId, massArray);
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />toId, massArray);
				}
				else if (selectTypeTarget.get('value') == 4) {
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />fromId, temperatureArray);
					Liferay.Util.setBox(document.<portlet:namespace />fm.<portlet:namespace />toId, temperatureArray);
				}
			}
		);

</aui:script>