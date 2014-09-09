[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 [@spring.bind "orderForm" /]
<h1>Buy Coin Confirm</h1>
  
    <table>
    <tr><td>order id</td><td>${order.id}</td></tr>
    <tr><td>Status</td><td>${order.status}</td></tr>
    <tr><td>Type</td><td>${order.type}</td></tr>
    <tr><td>amount</td><td>${order.amount}</td></tr>
    <tr><td>wallet</td><td>${order.walletId}</td></tr>
    <tr><td>fundingsource</td><td>${order.fundingsourceId}</td></tr>

    <tr><td>fees</td><td>base: ${order.feebase} pct: ${order.feepct} total: ${order.feetotal} </td></tr>
    <tr><td>exchange</td><td>fxrate: ${order.fxrate} coinvalue: ${order.coinvalue}  currency value: ${order.totalcost}  </td></tr>
 
<form action="?" method="POST">
 
    [@spring.formHiddenInput  path="orderForm.mode"  /]
    [@spring.formHiddenInput  path="orderForm.id"  /]

    [@blossom.pecidInput /]
 
 
 
	<tr><td colspan=2>
	<input type="submit" name="submitaction" id="submitaction" value="Confirm" /> 
	<input type="submit" id="submitaction" name="submitaction" value="Change" />
	<input type="submit" id="submitaction" name="submitaction" value="Cancel" />
	 </td></tr>
	 
</form>

    </table>


 


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

 