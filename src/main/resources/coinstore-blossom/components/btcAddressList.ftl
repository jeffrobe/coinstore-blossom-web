[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h2>
BTC Addresses 
<a href="btcaddress.html">[new]</a>
</h2>

<table>
<tr>
<th>Action</th>

<th>ID</th>
<th>Status</th>
<th>Wallet</th>
<th>Address</th>
<th>Update</th>
<th>Balance</th>
<th>NumTrans</th>
<th>Received</th>
</tr>

[#list btcaddresses as btcaddress]
    <tr> 
     <td>
     <a href="btcaddress.html?id=${btcaddress.btcaddress.id}">view/edit</a>
     <a href="btcinfo.html?id=${btcaddress.btcaddress.publickey}">validate</a>

    </td>
    
    <td>${btcaddress.btcaddress.id!"n/a"}</td>
    <td>${btcaddress.btcaddress.status!"n/a"}</td>
    <td>${btcaddress.btcaddress.wallet.name!"n/a"}</td>
    <td>${btcaddress.btcaddress.publickey!"n/a"}</td>
    <td>${btcaddress.updated?time}</td>
    <td>${btcaddress.bitcoinAddress.finalBalance!"n/a"}</td>
    <td>${btcaddress.bitcoinAddress.numTransactions!"n/a"}</td>
    <td>${btcaddress.bitcoinAddress.totalReceived!"n/a"}</td>
    </tr>
[/#list]
</table>
          
