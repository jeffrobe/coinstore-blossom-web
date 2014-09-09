[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h1>Ticker</h1>

<table border="1" align="center">
<tr>
<th>Exchange</th>
<th>Updated</th>
<th>last</th>
<th>bid</th>
<th>ask</th>
<th>high</th>
<th>low</th>
<th>volume</th>
</tr>

[#list btcTickers as btcTicker]
<tr>
<td>${btcTicker.name!"n/a"}</td>
<td>${btcTicker.updated?time}</td>
<td>${btcTicker.ticker.last!"n/a"}</td>
<td>${btcTicker.ticker.bid!"n/a"}</td>
<td>${btcTicker.ticker.ask!"n/a"}</td>
<td>${btcTicker.ticker.hig!"n/a"}</td>
<td>${btcTicker.ticker.low!"n/a"}</td>
<td>${btcTicker.ticker.volume!"n/a"}</td>
</tr>
[/#list]
</table>
 