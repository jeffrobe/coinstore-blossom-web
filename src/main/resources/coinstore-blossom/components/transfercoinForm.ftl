[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 
<h1>Transfer Coin Form</h1>
 
<form action="?" method="POST">
    [@blossom.pecidInput /]
    [@spring.formHiddenInput  path="orderForm.mode"  /]
    [@spring.formHiddenInput  path="orderForm.id"  /]
    
    
    <table>
    <tr>
    <td>Type</td><td>
     [@spring.formSingleSelect  path="orderForm.type" options=orderTypesHash /]
     &nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]
     </td>
 
    </tr>

    <tr>
    <td>Amount $ </td><td>[@spring.formInput path="orderForm.amount" attributes='class="textinput"' /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
    
    <tr>
    <td>fees $ </td><td>base: ${transactionBaseFee} pct: ${transactionPctFee} total: feeTotal</td>
    </tr>
      
 

    <tr>
    <td>funding source</td><td>
    [@spring.formSingleSelect  path="orderForm.fundingsourceId" options=fundingSourcesHash /]
     &nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>

    <tr>
    <td>wallet</td><td>
    [@spring.formSingleSelect path="orderForm.walletId" options=walletsHash  /]
     &nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /]</td>
    </tr>
    
 

	<tr><td colspan=2><input type="submit" value="submit" /></td></tr>

    </table>

</form>
<span style="color:#A73030;margin-left:30px;font-weight: bold;">*<spring:message code="required" text="required"/></span>

 
[@spring.bind "orderForm" /]

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

 