<#if (actionErrors?? && actionErrors?size > 0)>
	<div<#rt/>
<#if parameters.id?if_exists != "">
 id="${parameters.id?html}"<#rt/>
<#else>
 id="message-red"<#rt/>
</#if>            
<#if parameters.cssClass??>
 class="message-red message-div ${parameters.cssClass?html}"<#rt/>
<#else>
 class="message-red message-div errorMessage"<#rt/>
</#if>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
>
	<#list actionErrors as error>
		<#if error?if_exists != "">
            <table width="100%" cellspacing="0" cellpadding="0" border="0">
						<tbody><tr>
							<td class="red-left"><#if parameters.escape>${error!?html}<#else>${error!}</#if></td>
							<td class="red-right"><a class="close-red message-close" title="<@s.text name="table.form.messages.close" />"></a></td>
						</tr>
						</tbody></table>
        </#if>
	</#list>
	</div>
</#if>