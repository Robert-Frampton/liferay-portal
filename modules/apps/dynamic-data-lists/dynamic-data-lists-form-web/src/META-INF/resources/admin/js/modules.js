;(function() {
	AUI().applyConfig(
		{
			groups: {
				ddl: {
					base: '/o/ddl-form-web/admin/js/',
					modules: {
						'liferay-ddl-form-builder': {
							path: 'form_builder.js',
							requires: [
								'aui-form-builder',
								'aui-form-builder-pages',
								'liferay-ddl-form-builder-field-support',
								'liferay-ddl-form-builder-layout-deserializer',
								'liferay-ddl-form-builder-layout-visitor',
								'liferay-ddm-form-field-types',
								'liferay-ddm-form-renderer'
							]
						},
						'liferay-ddl-form-builder-definition-serializer': {
							path: 'form_definition_serializer.js',
							requires: [
								'json',
								'liferay-ddl-form-builder-layout-visitor'
							]
						},
						'liferay-ddl-form-builder-field-support': {
							path: 'form_builder_field_support.js',
							requires: [
								'aui-form-field'
							]
						},
						'liferay-ddl-form-builder-layout-deserializer': {
							path: 'form_layout_deserializer.js',
							requires: [
								'aui-form-builder-field-list',
								'aui-layout',
								'liferay-ddl-form-builder-field-support',
								'liferay-ddm-form-field-types'
							]
						},
						'liferay-ddl-form-builder-layout-serializer': {
							path: 'form_layout_serializer.js',
							requires: [
								'json',
								'liferay-ddl-form-builder-layout-visitor'
							]
						},
						'liferay-ddl-form-builder-layout-visitor': {
							path: 'form_layout_visitor.js',
							requires: [
								'aui-form-builder-field-list',
								'aui-layout'
							]
						},
						'liferay-ddl-form-builder-util': {
							path: 'form_builder_util.js',
							requires: [
								'liferay-ddl-form-builder-field-support',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddl-portlet': {
							path: 'form_portlet.js',
							requires: [
								'liferay-ddl-form-builder',
								'liferay-ddl-form-builder-definition-serializer',
								'liferay-ddl-form-builder-layout-serializer',
								'liferay-portlet-base'
							]
						}
					},
					root: '/o/ddl-form-web/admin/js/'
				},
				ddm: {
					base: '/o/ddm-form-renderer/js/',
					modules: {
						'liferay-ddm-form-renderer': {
							path: 'form.js',
							requires: [
								'aui-component',
								'liferay-ddm-form-renderer-definition',
								'liferay-ddm-form-renderer-feedback',
								'liferay-ddm-form-renderer-nested-fields',
								'liferay-ddm-form-renderer-pagination',
								'liferay-ddm-form-renderer-tabs',
								'liferay-ddm-form-renderer-util',
								'liferay-ddm-form-renderer-validation'
							]
						},
						'liferay-ddm-form-renderer-definition': {
							path: 'form_definition_support.js',
							requires: [
								'liferay-ddm-form-renderer-types',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-expressions-evaluator': {
							path: 'expressions_evaluator.js',
							requires: [
								'aui-request'
							]
						},
						'liferay-ddm-form-renderer-feedback': {
							path: 'form_feedback_support.js',
							requires: [
								'aui-alert'
							]
						},
						'liferay-ddm-form-renderer-field': {
							path: 'field.js',
							requires: [
								'aui-datatype',
								'aui-node',
								'liferay-ddm-form-renderer',
								'liferay-ddm-form-renderer-field-events',
								'liferay-ddm-form-renderer-field-feedback',
								'liferay-ddm-form-renderer-field-repetition',
								'liferay-ddm-form-renderer-field-validation',
								'liferay-ddm-form-renderer-nested-fields',
								'liferay-ddm-form-renderer-types',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-field-events': {
							path: 'field_events_support.js',
							requires: []
						},
						'liferay-ddm-form-renderer-field-feedback': {
							path: 'field_feedback_support.js',
							requires: [
								'aui-node'
							]
						},
						'liferay-ddm-form-renderer-field-repetition': {
							path: 'field_repetition_support.js',
							requires: [
								'aui-datatype',
								'liferay-ddm-form-renderer-types',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-field-validation': {
							path: 'field_validation_support.js',
							requires: [
								'liferay-ddm-form-renderer-expressions-evaluator'
							]
						},
						'liferay-ddm-form-renderer-field-visibility': {
							path: 'field_visibility_support.js',
							requires: [
								'liferay-ddm-form-renderer-expressions-evaluator',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-nested-fields': {
							path: 'nested_fields_support.js',
							requires: [
								'liferay-ddm-form-renderer-types',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-pagination': {
							path: 'form_pagination_support.js',
							requires: [
								'aui-pagination',
								'liferay-ddm-form-renderer-wizard'
							]
						},
						'liferay-ddm-form-renderer-tabs': {
							path: 'form_tabs_support.js',
							requires: [
								'aui-tabview'
							]
						},
						'liferay-ddm-form-renderer-types': {
							path: 'types.js',
							requires: [
								'array-extras',
								'aui-form-builder-field-type',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-util': {
							path: 'util.js',
							requires: [
								'liferay-ddm-form-renderer-types',
								'queue'
							]
						},
						'liferay-ddm-form-renderer-validation': {
							path: 'form_validation_support.js',
							requires: [
								'aui-alert',
								'liferay-ddm-form-renderer-expressions-evaluator'
							]
						},
						'liferay-ddm-form-renderer-wizard': {
							path: 'wizard.js',
							requires: [
								'aui-component',
								'aui-node',
								'widget'
							]
						}
					},
					root: '/o/ddm-form-renderer/js/'
				},
				'field-checkbox': {
					base: '/o/ddm-type-checkbox/',
					modules: {
						'liferay-ddm-form-field-checkbox': {
							path: 'checkbox_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-checkbox-template': {
							path: 'checkbox.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-checkbox/'
				},
				'field-options': {
					base: '/o/ddm-type-options/',
					modules: {
						'liferay-ddm-form-field-options': {
							path: 'options_field.js',
							requires: [
								'liferay-auto-fields',
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-options-template': {
							path: 'options.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-options/'
				},
				'field-radio': {
					base: '/o/ddm-type-radio/',
					modules: {
						'liferay-ddm-form-field-radio': {
							path: 'radio_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-radio-template': {
							path: 'radio.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-radio/'
				},
				'field-select': {
					base: '/o/ddm-type-select/',
					modules: {
						'liferay-ddm-form-field-select': {
							path: 'select_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-select-template': {
							path: 'select.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-select/'
				},
				'field-text': {
					base: '/o/ddm-type-text/',
					modules: {
						'liferay-ddm-form-field-text-template': {
							path: 'text.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-text/'
				},
				'field-validation': {
					base: '/o/ddm-type-validation/',
					modules: {
						'liferay-ddm-form-field-validation': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'validation_field.js',
							requires: [
								'liferay-ddm-form-renderer-field'
							]
						},
						'liferay-ddm-form-field-validation-template': {
							condition: {
								trigger: 'liferay-ddm-form-renderer'
							},
							path: 'validation.soy.js',
							requires: [
								'soyutils'
							]
						}
					},
					root: '/o/ddm-type-validation/'
				}
			}
		}
	);
})();