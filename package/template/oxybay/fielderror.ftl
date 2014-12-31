<script type="text/javascript">
 $(document).ready(function(){
	 <#if fieldErrors??><#t/>
    <#assign eKeys = fieldErrors.keySet()><#t/>
    <#assign eKeysSize = eKeys.size()><#t/>
		<#if (eKeysSize > 0)><#t/>
		 <#list eKeys as eKey><#t/>
		  <#assign eValue = fieldErrors[eKey]><#t/>
		  <#list eValue as eEachValue><#t/>
		   $.oxyFormHelpError('${eKey?html}','<#if parameters.escape>${eEachValue!?html}<#else>${eEachValue!}</#if>')
		  </#list><#t/>
		 </#list><#t/>
		</#if><#t/>
	 </#if><#t/>
	});
</script>