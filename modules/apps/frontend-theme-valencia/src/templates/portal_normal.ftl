<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<#if has_navigation && is_setup_complete>
	<#include "${full_templates_path}/navigation.ftl" />
</#if>

<div class="container-fluid" id="wrapper">
	<header id="banner" role="banner">
		<div class="header-portlet-wrapper">

			<@liferay_portlet["runtime"]
				instanceId="${the_page_title}_HEADER"
				portletProviderAction=portletProviderAction.ADD
				portletProviderClassName="com.liferay.journal.model.JournalArticle"
			/>

		</div>
	</header>

	<div class="content-wrapper">
		<section id="content">
			<h1 class="hide-accessible">${the_title}</h1>

			<#if selectable>
				<@liferay_util["include"] page=content_include />
			<#else>
				${portletDisplay.recycle()}

				${portletDisplay.setTitle(the_title)}

				<@liferay_theme["wrap-portlet"] page="portlet.ftl">
					<@liferay_util["include"] page=content_include />
				</@>
			</#if>
		</section>

		<footer id="footer" role="contentinfo">
			<p class="powered-by">
				<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
			</p>
		</footer>
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>