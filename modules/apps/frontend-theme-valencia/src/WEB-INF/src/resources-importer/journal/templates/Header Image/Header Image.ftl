<#if Imagezngk.getData()?? && Imagezngk.getData() != "">
	<div class="header-filter header-image" style="background-image: url('${Imagezngk.getData()}');">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">

    				<#if (PageHeadline.getData())??>
    				    <h1 class="page-headline">${PageHeadline.getData()}</h1>
    				</#if>

    				<#if (PageTagline.getData())??>
    				    <h2 class="page-tagline">${PageTagline.getData()}</h2>
    				</#if>

				</div>
			</div>
		</div>
	</div>
</#if>