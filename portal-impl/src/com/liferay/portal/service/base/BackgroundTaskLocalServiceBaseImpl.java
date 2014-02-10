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

package com.liferay.portal.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BackgroundTaskLocalService;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.BackgroundTaskPersistence;
import com.liferay.portal.service.persistence.LockFinder;
import com.liferay.portal.service.persistence.LockPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the background task local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.BackgroundTaskLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.BackgroundTaskLocalServiceImpl
 * @see com.liferay.portal.service.BackgroundTaskLocalServiceUtil
 * @generated
 */
public abstract class BackgroundTaskLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements BackgroundTaskLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.BackgroundTaskLocalServiceUtil} to access the background task local service.
	 */

	/**
	 * Adds the background task to the database. Also notifies the appropriate model listeners.
	 *
	 * @param backgroundTask the background task
	 * @return the background task that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BackgroundTask addBackgroundTask(BackgroundTask backgroundTask)
		throws SystemException {
		backgroundTask.setNew(true);

		return backgroundTaskPersistence.update(backgroundTask);
	}

	/**
	 * Creates a new background task with the primary key. Does not add the background task to the database.
	 *
	 * @param backgroundTaskId the primary key for the new background task
	 * @return the new background task
	 */
	@Override
	public BackgroundTask createBackgroundTask(long backgroundTaskId) {
		return backgroundTaskPersistence.create(backgroundTaskId);
	}

	/**
	 * Deletes the background task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backgroundTaskId the primary key of the background task
	 * @return the background task that was removed
	 * @throws PortalException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BackgroundTask deleteBackgroundTask(long backgroundTaskId)
		throws PortalException, SystemException {
		return backgroundTaskPersistence.remove(backgroundTaskId);
	}

	/**
	 * Deletes the background task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param backgroundTask the background task
	 * @return the background task that was removed
	 * @throws PortalException
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BackgroundTask deleteBackgroundTask(BackgroundTask backgroundTask)
		throws PortalException, SystemException {
		return backgroundTaskPersistence.remove(backgroundTask);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(BackgroundTask.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return backgroundTaskPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return backgroundTaskPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return backgroundTaskPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return backgroundTaskPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return backgroundTaskPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public BackgroundTask fetchBackgroundTask(long backgroundTaskId)
		throws SystemException {
		return backgroundTaskPersistence.fetchByPrimaryKey(backgroundTaskId);
	}

	/**
	 * Returns the background task with the primary key.
	 *
	 * @param backgroundTaskId the primary key of the background task
	 * @return the background task
	 * @throws PortalException if a background task with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public BackgroundTask getBackgroundTask(long backgroundTaskId)
		throws PortalException, SystemException {
		return backgroundTaskPersistence.findByPrimaryKey(backgroundTaskId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return backgroundTaskPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the background tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.BackgroundTaskModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of background tasks
	 * @param end the upper bound of the range of background tasks (not inclusive)
	 * @return the range of background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<BackgroundTask> getBackgroundTasks(int start, int end)
		throws SystemException {
		return backgroundTaskPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of background tasks.
	 *
	 * @return the number of background tasks
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getBackgroundTasksCount() throws SystemException {
		return backgroundTaskPersistence.countAll();
	}

	/**
	 * Updates the background task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param backgroundTask the background task
	 * @return the background task that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BackgroundTask updateBackgroundTask(BackgroundTask backgroundTask)
		throws SystemException {
		return backgroundTaskPersistence.update(backgroundTask);
	}

	/**
	 * Returns the background task local service.
	 *
	 * @return the background task local service
	 */
	public com.liferay.portal.service.BackgroundTaskLocalService getBackgroundTaskLocalService() {
		return backgroundTaskLocalService;
	}

	/**
	 * Sets the background task local service.
	 *
	 * @param backgroundTaskLocalService the background task local service
	 */
	public void setBackgroundTaskLocalService(
		com.liferay.portal.service.BackgroundTaskLocalService backgroundTaskLocalService) {
		this.backgroundTaskLocalService = backgroundTaskLocalService;
	}

	/**
	 * Returns the background task remote service.
	 *
	 * @return the background task remote service
	 */
	public com.liferay.portal.service.BackgroundTaskService getBackgroundTaskService() {
		return backgroundTaskService;
	}

	/**
	 * Sets the background task remote service.
	 *
	 * @param backgroundTaskService the background task remote service
	 */
	public void setBackgroundTaskService(
		com.liferay.portal.service.BackgroundTaskService backgroundTaskService) {
		this.backgroundTaskService = backgroundTaskService;
	}

	/**
	 * Returns the background task persistence.
	 *
	 * @return the background task persistence
	 */
	public BackgroundTaskPersistence getBackgroundTaskPersistence() {
		return backgroundTaskPersistence;
	}

	/**
	 * Sets the background task persistence.
	 *
	 * @param backgroundTaskPersistence the background task persistence
	 */
	public void setBackgroundTaskPersistence(
		BackgroundTaskPersistence backgroundTaskPersistence) {
		this.backgroundTaskPersistence = backgroundTaskPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the lock local service.
	 *
	 * @return the lock local service
	 */
	public com.liferay.portal.service.LockLocalService getLockLocalService() {
		return lockLocalService;
	}

	/**
	 * Sets the lock local service.
	 *
	 * @param lockLocalService the lock local service
	 */
	public void setLockLocalService(
		com.liferay.portal.service.LockLocalService lockLocalService) {
		this.lockLocalService = lockLocalService;
	}

	/**
	 * Returns the lock persistence.
	 *
	 * @return the lock persistence
	 */
	public LockPersistence getLockPersistence() {
		return lockPersistence;
	}

	/**
	 * Sets the lock persistence.
	 *
	 * @param lockPersistence the lock persistence
	 */
	public void setLockPersistence(LockPersistence lockPersistence) {
		this.lockPersistence = lockPersistence;
	}

	/**
	 * Returns the lock finder.
	 *
	 * @return the lock finder
	 */
	public LockFinder getLockFinder() {
		return lockFinder;
	}

	/**
	 * Sets the lock finder.
	 *
	 * @param lockFinder the lock finder
	 */
	public void setLockFinder(LockFinder lockFinder) {
		this.lockFinder = lockFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.BackgroundTask",
			backgroundTaskLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.BackgroundTask");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return BackgroundTask.class;
	}

	protected String getModelClassName() {
		return BackgroundTask.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = backgroundTaskPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portal.service.BackgroundTaskLocalService.class)
	protected com.liferay.portal.service.BackgroundTaskLocalService backgroundTaskLocalService;
	@BeanReference(type = com.liferay.portal.service.BackgroundTaskService.class)
	protected com.liferay.portal.service.BackgroundTaskService backgroundTaskService;
	@BeanReference(type = BackgroundTaskPersistence.class)
	protected BackgroundTaskPersistence backgroundTaskPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.LockLocalService.class)
	protected com.liferay.portal.service.LockLocalService lockLocalService;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = LockFinder.class)
	protected LockFinder lockFinder;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}