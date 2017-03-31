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

package com.liferay.friendly.url.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.friendly.url.model.FriendlyURLEntry;
import com.liferay.friendly.url.model.FriendlyURLEntryLocalization;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the local service interface for FriendlyURLEntryLocalization. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see FriendlyURLEntryLocalizationLocalServiceUtil
 * @see com.liferay.friendly.url.service.base.FriendlyURLEntryLocalizationLocalServiceBaseImpl
 * @see com.liferay.friendly.url.service.impl.FriendlyURLEntryLocalizationLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface FriendlyURLEntryLocalizationLocalService
	extends BaseLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FriendlyURLEntryLocalizationLocalServiceUtil} to access the friendly url entry localization local service. Add custom service methods to {@link com.liferay.friendly.url.service.impl.FriendlyURLEntryLocalizationLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public FriendlyURLEntryLocalization addFriendlyURLEntryLocalization(
		FriendlyURLEntry friendlyURLEntry, java.lang.String urlTitle,
		java.lang.String languageId) throws PortalException;

	/**
	* Adds the friendly url entry localization to the database. Also notifies the appropriate model listeners.
	*
	* @param friendlyURLEntryLocalization the friendly url entry localization
	* @return the friendly url entry localization that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FriendlyURLEntryLocalization addFriendlyURLEntryLocalization(
		FriendlyURLEntryLocalization friendlyURLEntryLocalization);

	/**
	* Creates a new friendly url entry localization with the primary key. Does not add the friendly url entry localization to the database.
	*
	* @param friendlyURLEntryLocalizationId the primary key for the new friendly url entry localization
	* @return the new friendly url entry localization
	*/
	public FriendlyURLEntryLocalization createFriendlyURLEntryLocalization(
		long friendlyURLEntryLocalizationId);

	public FriendlyURLEntryLocalization deleteFriendlyURLEntryLocalization(
		FriendlyURLEntry friendlyURLEntry, java.lang.String languageId)
		throws PortalException;

	/**
	* Deletes the friendly url entry localization from the database. Also notifies the appropriate model listeners.
	*
	* @param friendlyURLEntryLocalization the friendly url entry localization
	* @return the friendly url entry localization that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public FriendlyURLEntryLocalization deleteFriendlyURLEntryLocalization(
		FriendlyURLEntryLocalization friendlyURLEntryLocalization);

	/**
	* Deletes the friendly url entry localization with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param friendlyURLEntryLocalizationId the primary key of the friendly url entry localization
	* @return the friendly url entry localization that was removed
	* @throws PortalException if a friendly url entry localization with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public FriendlyURLEntryLocalization deleteFriendlyURLEntryLocalization(
		long friendlyURLEntryLocalizationId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FriendlyURLEntryLocalization fetchFriendlyURLEntryLocalization(
		FriendlyURLEntry friendlyURLEntry, java.lang.String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FriendlyURLEntryLocalization fetchFriendlyURLEntryLocalization(
		long companyId, long groupId, long classNameId, long classPK,
		java.lang.String languageId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FriendlyURLEntryLocalization fetchFriendlyURLEntryLocalization(
		long friendlyURLEntryLocalizationId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FriendlyURLEntryLocalization fetchFriendlyURLEntryLocalization(
		long groupId, java.lang.String urlTitle, java.lang.String languageId)
		throws PortalException;

	/**
	* Returns the friendly url entry localization with the primary key.
	*
	* @param friendlyURLEntryLocalizationId the primary key of the friendly url entry localization
	* @return the friendly url entry localization
	* @throws PortalException if a friendly url entry localization with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FriendlyURLEntryLocalization getFriendlyURLEntryLocalization(
		long friendlyURLEntryLocalizationId) throws PortalException;

	/**
	* Updates the friendly url entry localization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param friendlyURLEntryLocalization the friendly url entry localization
	* @return the friendly url entry localization that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public FriendlyURLEntryLocalization updateFriendlyURLEntryLocalization(
		FriendlyURLEntryLocalization friendlyURLEntryLocalization);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of friendly url entry localizations.
	*
	* @return the number of friendly url entry localizations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFriendlyURLEntryLocalizationsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFriendlyURLEntryLocalizationsCount(
		FriendlyURLEntry friendlyURLEntry);

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.friendly.url.model.impl.FriendlyURLEntryLocalizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.friendly.url.model.impl.FriendlyURLEntryLocalizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FriendlyURLEntryLocalization> getFriendlyURLEntryLocalizations(
		FriendlyURLEntry friendlyURLEntry);

	/**
	* Returns a range of all the friendly url entry localizations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.friendly.url.model.impl.FriendlyURLEntryLocalizationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of friendly url entry localizations
	* @param end the upper bound of the range of friendly url entry localizations (not inclusive)
	* @return the range of friendly url entry localizations
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FriendlyURLEntryLocalization> getFriendlyURLEntryLocalizations(
		int start, int end);

	public List<FriendlyURLEntryLocalization> updateFriendlyURLEntryLocalizations(
		FriendlyURLEntry friendlyURLEntry,
		Map<Locale, java.lang.String> urlTitleMap) throws PortalException;

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void deleteFriendlyURLEntryLocalizations(
		FriendlyURLEntry friendlyURLEntry) throws PortalException;
}