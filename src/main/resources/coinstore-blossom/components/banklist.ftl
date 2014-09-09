[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of banks</h1>

<table>
<tr>
<th>Actions</th>
<th>ID</th>
<th>Entered</th>
<th>Status</th>
<th>Name</th>
<th>Routing</th>
<th>Institution</th>
 
</tr>
[#list banks as bank]
    <tr> 
    <td>
 <a href="">[edit]</a>
 
    </td>
 
    <td>${bank.id}</td>
    <td>${bank.entered}</td>
    <td>${bank.status}</td>
    <td>${bank.name}</td>
    <td>${bank.routing}</td>
    <td>${bank.institution}</td>
 
    </tr>
[/#list]
</table>
            