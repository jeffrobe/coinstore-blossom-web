[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
<script type="text/javascript">
 
 var RecaptchaOptions = {
    theme : 'clean'
 };
 </script>
 
<h1>Register Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    <table>
 

    <tr>
    <td>E-mail</td><td>[@spring.formInput path="profileForm.email" attributes='maxlength="100" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

<!--
    <tr>
    <td>Phone NUmber (used for sms/authy verification)</td><td>[@spring.formInput path="profileForm.phone" attributes='maxlength="100" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

-->
    <tr>
    <td>Password</td><td>[@spring.formPasswordInput path="profileForm.password"  attributes='maxlength="16" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

<!--
    <tr>
    <td>Confrim Password</td><td>[@spring.formPasswordInput path="profileForm.password2"  attributes='maxlength="16" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
-->

[#if requireImageAuth]

	<tr><td colspan="2">
	 <script type="text/javascript" src="http://www.google.com/recaptcha/api/challenge?k=6LdjofcSAAAAAMrotzEC25IaXxVXnFykWNyaYRYr"></script>
 
   <noscript>
       <iframe src="http://www.google.com/recaptcha/api/noscript?k=your_public_key"    height="300" width="500" frameborder="0"></iframe><br>
       <textarea name="recaptcha_challenge_field" rows="3" cols="40">
       </textarea>
       <input type="hidden" name="recaptcha_response_field" value="manual_challenge">
    </noscript>
 
[/#if]
	
	</td></tr>
 
	<tr><td colspan="2" align=center><input type="submit" value="Register" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">* Required</span>


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

 