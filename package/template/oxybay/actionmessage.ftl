<#if (actionMessages?? && actionMessages?size > 0 && !parameters.isEmptyList)>
	<div<#rt/>
<#if parameters.id?if_exists != "">
 id="${parameters.id?html}"<#rt/>
<#else>
 id="message-green"<#rt/>
</#if>
<#if parameters.cssClass??>
 class="message-green message-div ${parameters.cssClass?html}"<#rt/>
<#else>
 class="message-green message-div"<#rt/>
</#if>
<#if parameters.cssStyle??>
 style="${parameters.cssStyle?html}"<#rt/>
</#if>
>
		<#list actionMessages as message>
            <#if message?if_exists != "">
                <table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody><tr>
									<td class="green-left"><#if parameters.escape>${message!?html}<#else>${message!}</#if></td>
									<td class="green-right"><a class="close-green message-close" title="<@s.text name="table.form.messages.close" />"></a></td>
								</tr>
								</tbody></table>
            </#if>
		</#list>
	</div>
</#if>