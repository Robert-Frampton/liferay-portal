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

package com.liferay.portal.service.persistence.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.BaseModel;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class ReverseTableMapping<L extends BaseModel<L>, R extends BaseModel<R>>
	implements TableMapping<L, R> {

	public ReverseTableMapping(TableMapping<R, L> tableMapping) {
		_tableMapping = tableMapping;
	}

	@Override
	public boolean addTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapping.addTableMapping(rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public boolean containsTableMapping(
			long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapping.containsTableMapping(
			rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public int deleteLeftPrimaryKeyTableMappings(long leftPrimaryKey)
		throws SystemException {

		return _tableMapping.deleteRightPrimaryKeyTableMappings(leftPrimaryKey);
	}

	@Override
	public int deleteRightPrimaryKeyTableMappings(long rightPrimaryKey)
		throws SystemException {

		return _tableMapping.deleteLeftPrimaryKeyTableMappings(rightPrimaryKey);
	}

	@Override
	public boolean deleteTableMapping(long leftPrimaryKey, long rightPrimaryKey)
		throws SystemException {

		return _tableMapping.deleteTableMapping(
			rightPrimaryKey, leftPrimaryKey);
	}

	@Override
	public List<L> getLeftBaseModels(
			long rightPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException {

		return _tableMapping.getRightBaseModels(
			rightPrimaryKey, start, end, obc);
	}

	@Override
	public long[] getLeftPrimaryKeys(long rightPrimaryKey)
		throws SystemException {

		return _tableMapping.getRightPrimaryKeys(rightPrimaryKey);
	}

	@Override
	public TableMapping<R, L> getReverseTableMapping() {
		return _tableMapping;
	}

	@Override
	public List<R> getRightBaseModels(
			long leftPrimaryKey, int start, int end, OrderByComparator obc)
		throws SystemException {

		return _tableMapping.getLeftBaseModels(leftPrimaryKey, start, end, obc);
	}

	@Override
	public long[] getRightPrimaryKeys(long leftPrimaryKey)
		throws SystemException {

		return _tableMapping.getLeftPrimaryKeys(leftPrimaryKey);
	}

	@Override
	public boolean matches(String leftColumnName, String rightColumnName) {
		return _tableMapping.matches(rightColumnName, leftColumnName);
	}

	private TableMapping<R, L> _tableMapping;

}