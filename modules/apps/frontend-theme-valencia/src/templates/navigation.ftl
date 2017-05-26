<nav class="${nav_css_class} navbar navbar-color-on-scroll navbar-default navbar-fixed-top navbar-transparent" id="navigation" role="navigation">
	<div class="container">
		<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>
		<div class="navbar-header">
			<button aria-expanded="false" class="navbar-toggle collapsed" data-target="#main-site-navigation" data-toggle="collapse" type="button">
				<span class="sr-only"><@liferay.language key="menu" /></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>

			<#if show_site_name?? || show_site_logo??>
				<div class="title-logo-wrapper">
					<a class="navbar-brand" href=${site_default_url} title=${site_name}>

					<#if show_site_logo>
						<img alt="${site_name}" class="${logo_css_class}" src="${site_logo}"/>
					</#if>

					<#if show_site_name>
						<h1 class="site-name">${site_name}</h1>
					</#if>

					</a>
				</div>
			</#if>

		</div>
		<div class="collapse navbar-collapse" id="main-site-navigation">
			<ul aria-label="<@liferay.language key="site-pages" />" class="nav navbar-nav navbar-right" role="menubar">
				<#list nav_items as nav_item>
					<#assign
						nav_item_attr_data_toggle = ""
						nav_item_attr_has_popup = ""
						nav_item_attr_selected = ""
						nav_item_css_class = ""
						nav_item_layout = nav_item.getLayout()
						nav_item_link_css_class = ""
					/>

					<#if nav_item.hasChildren()>
						<#assign
							nav_item_attr_data_toggle = "data-toggle='dropdown'"
							nav_item_attr_has_popup = "aria-haspopup='true'"
							nav_item_css_class = "dropdown"
							nav_item_link_css_class = "dropdown-toggle"
						/>
					</#if>

					<#if nav_item.isSelected()>
						<#assign
							nav_item_attr_has_popup = "aria-haspopup='true'"
							nav_item_attr_selected = "aria-selected='true'"
							nav_item_css_class = nav_item_css_class + " selected"
						/>
					</#if>

					<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
						<a aria-labelledby="layout_${nav_item.getLayoutId()}" class="${nav_item_link_css_class}" ${nav_item_attr_data_toggle} ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

						<#if nav_item.hasChildren()>
							<ul class="child-menu dropdown-menu" role="menu">
								<#list nav_item.getChildren() as nav_child>
									<#assign
										nav_child_attr_selected = ""
										nav_child_css_class = ""
									/>

									<#if nav_item.isSelected()>
										<#assign
											nav_child_attr_selected = "aria-selected='true'"
											nav_child_css_class = "selected"
										/>
									</#if>

									<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
										<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
									</li>
								</#list>
							</ul>
						</#if>
					</li>
				</#list>

				<#if !is_signed_in>
					<li class="btn btn-round btn-theme">
						<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow" >${sign_in_text}</a>
					</li>
				</#if>

			</ul>
		</div>
	</div>
</nav>