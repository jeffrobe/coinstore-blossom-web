[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of Notifications</h1>

<table>
<tr>
<th>Actions</th>
<th>ID</th>
<th>Entered</th>
<th>Status</th>
<th>Ident</th>
<th>Name</th>
<th>Template</th>
<th>Description</th>
</tr>
[#list notifications as notification]
    <tr> 
    <td>
 <a href="">[edit]</a>
    </td>
 
    <td>${notification.id}</td>
    <td>${notification.entered}</td>
    <td>${notification.status}</td>
    <td>${notification.ident}</td>
    <td>${notification.name!"n/a"}</td>
    <td>${notification.template!"n/a"}</td>
    <td>${notification.description!"n/a"}</td>

    </tr>
[/#list]
</table>
            