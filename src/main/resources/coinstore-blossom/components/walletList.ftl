[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of wallets</h1>

<table>
<tr>
<th>Status</th>
<th>Name</th>
</tr>
[#list wallets as wallet]
    <tr> 
    <td>${wallet.status}</td>
    <td>${wallet.name}</td>
    </tr>
[/#list]
</table>
            