[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]
 
 <h1>Token auth Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    <table>
    
    <tr>
    <td>token</td><td>[@spring.formInput path="tokenForm.token" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    
	<tr><td colspan=2><input type="submit" value="Update" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>
