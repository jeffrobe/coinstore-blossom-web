[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of order</h1>

<table>
<tr>
<th>ID</th>
<th>Status</th>
</tr>
[#list orders as order]
    <tr> 
    <td>${order.id}</td>
    <td>${order.status}</td>
    </tr>
[/#list]
</table>
            