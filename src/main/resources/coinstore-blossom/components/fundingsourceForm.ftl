[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 
<h1>Fundingsource Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    [@spring.formHiddenInput  path="fundingsourceForm.id"  /]
    
    <table>
    <tr>
    <td>Name</td><td>[@spring.formInput path="fundingsourceForm.name" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    <tr>
    <td>Type</td><td>
    
     [@spring.formSingleSelect  path="fundingsourceForm.type" options=fundingsourceTypesHash /]
&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
    
    <tr>
    <td>Account</td><td>[@spring.formInput path="fundingsourceForm.account" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    <tr>
    <td>Routing 082901473</td><td>[@spring.formInput path="fundingsourceForm.routing" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
    
 
	<tr><td colspan=2><input type="submit" value="submit" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>

 
[@spring.bind "fundingsourceForm" /] 

[#if spring.status.error]
    <div class="errors">
        There were problems with the data you entered:
        <ul>
            [#list spring.status.errorMessages as error]
                <li>${error}</li>
            [/#list]
        </ul>
    </div>
[/#if]

 