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

package com.liferay.portal.kernel.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Peter Borkuti
 */
public class MapUtilLinkedHashMapTest {

	@Test
	public void testDelimiterCustom() throws Exception {
		Map<String, String> map = MapUtil.toLinkedHashMap(
			new String[] {"one,1"}, ",");

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue("1"));
	}

	@Test
	public void testDelimiterDefault() throws Exception {
		Map<String, String> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue("1"));
	}

	@Test
	public void testParamsInvalid() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(new String[] {"one"});

		Assert.assertTrue(map.isEmpty());

		map = MapUtil.toLinkedHashMap(new String[] {"one:two:three:four"});

		Assert.assertTrue(map.isEmpty());
	}

	@Test
	public void testParamsNull() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(null);

		Assert.assertTrue(map.isEmpty());
	}

	@Test
	public void testParamsTypeBoolean() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:true:boolean"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(true));
		Assert.assertTrue(map.get("one") instanceof Boolean);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:true:" + Boolean.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(true));
		Assert.assertTrue(map.get("one") instanceof Boolean);
	}

	@Test
	public void testParamsTypeComposite() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Byte.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue((byte)1));
		Assert.assertTrue(map.get("one") instanceof Byte);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Float.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue((float)1));
		Assert.assertTrue(map.get("one") instanceof Float);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Object.class.getName()});

		Assert.assertTrue(map.size() == 0);
	}

	@Test
	public void testParamsTypeDouble() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1.0:double"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1.0d));
		Assert.assertTrue(map.get("one") instanceof Double);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1.0:" + Double.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1.0d));
		Assert.assertTrue(map.get("one") instanceof Double);
	}

	@Test
	public void testParamsTypeInteger() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:int"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1));
		Assert.assertTrue(map.get("one") instanceof Integer);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Integer.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1));
		Assert.assertTrue(map.get("one") instanceof Integer);
	}

	@Test
	public void testParamsTypeLong() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:long"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1l));
		Assert.assertTrue(map.get("one") instanceof Long);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Long.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue(1l));
		Assert.assertTrue(map.get("one") instanceof Long);
	}

	@Test
	public void testParamsTypeShort() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:short"});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue((short)1));
		Assert.assertTrue(map.get("one") instanceof Short);

		map = MapUtil.toLinkedHashMap(
			new String[] {"one:1:" + Short.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue((short)1));
		Assert.assertTrue(map.get("one") instanceof Short);
	}

	@Test
	public void testParamsTypeString() throws Exception {
		Map<String, Object> map = MapUtil.toLinkedHashMap(
			new String[] {"one:X:" + String.class.getName()});

		Assert.assertTrue(map.size() == 1);
		Assert.assertTrue(map.containsKey("one"));
		Assert.assertTrue(map.containsValue("X"));
		Assert.assertTrue(map.get("one") instanceof String);
	}

	@Test
	public void testParamsZeroLength() throws Exception {
		Map<String, String> map = MapUtil.toLinkedHashMap(new String[0]);

		Assert.assertTrue(map.isEmpty());
	}

}