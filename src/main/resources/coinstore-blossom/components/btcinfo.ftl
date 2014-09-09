[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h2>BTC Address</h2>
<div>${btc.bitcoinAddress.address}</div>

<table>
<tr><td>Update</td><td>${btc.updated?time}</td></tr>
<tr><td>Balance</td><td>${btc.bitcoinAddress.finalBalance!"n/a"}</td></tr>
<tr><td>NumTrans</td><td>${btc.bitcoinAddress.numTransactions!"n/a"}</td></tr>
<tr><td>Received</td> <td>${btc.bitcoinAddress.totalReceived!"n/a"}</td></tr>
<tr><td>QR</td> <td>
<img width=70 height=70 src="http://www.btcfrog.com/qr/bitcoinPNG.php?address=${btc.bitcoinAddress.address}">


 </td></tr>
</table>
          
Recent transactions