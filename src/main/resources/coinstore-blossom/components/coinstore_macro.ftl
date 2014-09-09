<#--
 * formSingleSelect
 *
 * Show a selectbox (dropdown) input element allowing a single value to be chosen
 * from a list of options.
 *
 * @param path the name of the field to bind to
 * @param options a map (value=label) or a list of all the available options
 * @param attributes any additional attributes for the element (such as class
 *    or CSS styles or size
-->
<#macro formSingleSelectLongId path options attributes="">
	<@bind path/>
 
   <select id="${status.expression}" name="${status.expression}" ${attributes}>

        <#list options as o>
    	 
       <option value="${o.key!}" 
       
      <@checkSelectedNum o.key!0/> 
  
      >${o.value} </option>
    </#list> 
             
    </select>
</#macro>



<#--
 * checkSelectedNum
 *
 * Check a value in a list to see if it is the currently selected value.
 * If so, add the 'selected="selected"' text to the output.
 * Handles values of numeric and string types.
 * This function is used internally but can be accessed by user code if required.
 *
 * @param value the current value in a list iteration
-->
<#macro checkSelectedNum value>
	<#if stringStatusValue?is_number && stringStatusValue == value?number>selected="selected"</#if>
	<#if stringStatusValue?is_string && stringStatusValue == value>selected="selected"</#if>

</#macro>
 


<#--
 * bind
 *
 * Exposes a BindStatus object for the given bind path, which can be
 * a bean (e.g. "person") to get global errors, or a bean property
 * (e.g. "person.name") to get field errors. Can be called multiple times
 * within a form to bind to multiple command objects and/or field names.
 *
 * This macro will participate in the default HTML escape setting for the given
 * RequestContext. This can be customized by calling "setDefaultHtmlEscape"
 * on the "springMacroRequestContext" context variable, or via the
 * "defaultHtmlEscape" context-param in web.xml (same as for the JSP bind tag).
 * Also regards a "htmlEscape" variable in the namespace of this library.
 *
 * Producing no output, the following context variable will be available
 * each time this macro is referenced (assuming you import this library in
 * your templates with the namespace 'spring'):
 *
 *   spring.status : a BindStatus instance holding the command object name,
 *   expression, value, and error messages and codes for the path supplied
 *
 * @param path : the path (string value) of the value required to bind to.
 *   Spring defaults to a command name of "command" but this can be overridden
 *   by user config.
 -->
<#macro bind path>
	<#if htmlEscape?exists>
		<#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
	<#else>
		<#assign status = springMacroRequestContext.getBindStatus(path)>
	</#if>
	<#-- assign a temporary value, forcing a string representation for any
	kind of variable.  This temp value is only used in this macro lib -->
	<#if status.value?exists && status.value?is_boolean>
		<#assign stringStatusValue=status.value?string>
	<#else>
		<#assign stringStatusValue=status.value?default("")>
	</#if>
</#macro>
