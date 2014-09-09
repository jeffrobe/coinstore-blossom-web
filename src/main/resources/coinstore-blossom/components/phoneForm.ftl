[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h1>Register Add Phone Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    <table>
    
    <tr>
    <td>Phone (used for authenitcation)</td><td>[@spring.formInput path="profileForm.phone" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    
	<tr><td colspan=2><input type="submit" value="Update" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>


[@spring.bind "profileForm" /]
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


 