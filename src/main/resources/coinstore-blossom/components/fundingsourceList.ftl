[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of funding sources</h1>

<table>
<tr>
<th>Status</th>
<th>Name</th>
</tr>
[#list fundingsources as fundingsource]
    <tr> 
    <td>${fundingsource.status}</td>
    <td>${fundingsource.name}</td>
    </tr>
[/#list]
</table>
            