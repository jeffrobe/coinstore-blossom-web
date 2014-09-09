[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
[#assign security=JspTaglibs["http://www.springframework.org/security/tags"] /]

<form action="?" method="POST">
    [@blossom.pecidInput /]

<table>

<tr><td><label for="email">email</label></td><td>[@spring.formInput path="profileForm.email" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td></tr>
<tr><td><label for="password">Password</label></td><td>[@spring.formPasswordInput path="profileForm.password" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td></tr>

[#if requireToken && !seperateTokenAuth ]
<tr><td><label for="token">token, use forgot password page to setup phone number</label></td><td>[@spring.formInput path="profileForm.token" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td></tr>
[/#if]

<tr><td colspan=2><input type="submit" value="Login"></td></tr>

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
 