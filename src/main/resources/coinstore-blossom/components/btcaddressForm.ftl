[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 
<h1>BTC address Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    
    [@spring.formHiddenInput  path="btcaddressForm.id"  /]
    
    <table>
    <tr>
    <td>Public Key</td><td>[@spring.formInput path="btcaddressForm.publickey" attributes='size="45" class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    <tr>
    <td>Private Key</td><td>[@spring.formInput path="btcaddressForm.privatekey" attributes='size="45"  class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    <tr>
    <td>Wallet</td><td>
     [@spring.formSingleSelect path="btcaddressForm.walletId" options=walletHash  /]
    &nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
      

	<tr><td colspan=2><input type="submit" value="submit" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>

 
 
[@spring.bind "btcaddressForm" /] 
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

 