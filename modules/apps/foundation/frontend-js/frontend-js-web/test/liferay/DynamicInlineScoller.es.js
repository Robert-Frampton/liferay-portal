'use strict';

import DynamicInlineScroll from '../../src/main/resources/META-INF/resources/liferay/DynamicInlineScroll.es';
import EventEmitter from 'metal-events/src/EventEmitter';

describe('DynamicInlineScroll', () => {
	let namespace = 'com_liferay_users_admin_web_portlet_UsersAdminPortlet';
	let dynamicInlineScroll;

	afterEach(() => dynamicInlineScroll.dispose());

	before(() => {
		document.body.innerHTML += __html__['test/liferay/fixtures/DynamicInlineScroll.html'];
	});

	beforeEach(() => {
		dynamicInlineScroll = new DynamicInlineScroll(
			{
				cur: 5,
				curParam: 'curParam',
				forcePost: false,
				formName: 'formName',
				initialPages: 20,
				jsCall: 'jsCall',
				namespace: namespace,
				pages: 400,
				randomNamespace: 'randomNamespace',
				url: '/url',
				urlAnchor: 'urlAnchor'
			}
		);
	});

	it('should pass', done => {
		done();
	});
});
