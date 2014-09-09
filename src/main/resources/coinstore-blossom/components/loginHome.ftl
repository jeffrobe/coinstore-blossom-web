[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
 
<table>

 

<tr>
<td>
<h2>List of funding sources <a href="fundingsource.html">new</a></h2>

<table>
<tr>
<th>Action</th>
<th>Status</th>
<th>Name</th>
</tr>
[#list fundingsources as fundingsource]
    <tr> 
    <td>
    <a href="fundingsource.html?action=view&id=${fundingsource.id}">view/edit</a>
    <a href="fundingsourceAction.html?action=resend&fundingsourceId=${fundingsource.id}">resend trial deposits</a>
    <a href="fundingsourceAction.html?action=view_deposits&fundingsourceId=${fundingsource.id}">view trial deposits</a>
    </td>
    <td>${fundingsource.status}</td>
    <td>${fundingsource.name}</td>
    </tr>
[/#list]
</table>
          
          
</td>
</tr>
 

<tr>
<td>
<h2>Wallets  <a href="wallet.html">new</a></h2>

<table>
<tr>
<th>Action</th>
<th>Status</th>
<th>Type</th>
<th>Name</th>
</tr>

[#list wallets as wallet]
    <tr> 
    <td>
    <a href="wallet.html?id=${wallet.id}">view/edit</a>
    </td>
    <td>${wallet.status}</td>
    <td>${wallet.wallettype.name}</td>
    <td>${wallet.name}</td>
    </tr>
[/#list]
</table>
          
          
</td>
</tr>


<tr>
<td>
<h2>
Orders 
<a href="buycoin.html">[buy coins]</a>
<a href="sellcoin.html">[sell coins]</a>
<a href="transfercoin.html">[transfer coins]</a>
</h2>

<table>
<tr>
<th>Action</th>
<th>Type</th>
<th>ID</th>
<th>Entered</th>
<th>Status</th>
<th>Amount</th>
</tr>

[#list orders as order]
    <tr> 
     <td>
     <a href=
     [#if order.type == 'buy']
    "buycoin.html?id=${order.id}"
      [#else]
    "sellcoin.html?id=${order.id}"
    
    [/#if]
   
    
    >view/edit</a>
 
    </td>
    <td>${order.type}</td>
    <td>${order.id}</td>
    <td>${order.entered}</td>
    <td>${order.status}</td>
    <td>${order.amount}</td>
    </tr>
[/#list]
</table>
          
          
</td>
</tr>

 
</table>