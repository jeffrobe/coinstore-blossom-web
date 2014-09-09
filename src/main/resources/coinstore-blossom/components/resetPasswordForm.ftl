[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 
<h1>Reset Password</h1>
[@spring.bind "profileForm" /]

<form action="?" method="POST">


    [@blossom.pecidInput /]
<table>
    <tr>
    <td>Password</td><td>[@spring.formPasswordInput path="profileForm.password"  attributes='maxlength="16" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

	<tr><td colspan="2" align=center><input type="submit" value="reset password" /></td></tr>

</table>



</form>

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
