[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h1>Forgot password Form</h1>

<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    <table>
    <tr>
    <td>E-mail</td><td>[@spring.formInput path="profileForm.email" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
	</tr>
 
 [#if requireToken && !seperateTokenAuth ]
<tr><td><label for="token">token</label></td><td>[@spring.formInput path="profileForm.token" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td></tr>
[/#if]
 
	<tr>
    <td colspan="2"><input type="submit" value="Submit" /></td>
    </tr>
    
	</table>
</form>

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
