[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of roles</h1>

<table>
<tr>
<th>Actions</th>
<th>ID</th>
<th>Entered</th>
<th>Status</th>
<th>Ident</th>
<th>Name</th>
<th>Description</th>
</tr>
[#list roles as role]
    <tr> 
    <td>
 <a href="">[edit]</a>
    </td>
 
    <td>${role.id}</td>
    <td>${role.entered}</td>
    <td>${role.status}</td>
    <td>${role.ident}</td>
    <td>${role.name!"n/a"}</td>
    <td>${role.description!"n/a"}</td>

    </tr>
[/#list]
</table>
            