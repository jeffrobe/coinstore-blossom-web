[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>List of users</h1>

<table>
<tr>
<th>Actions</th>
<th>ID</th>
<th>Entered</th>
<th>Status</th>
<th>Email</th>
<th>Password</th>
<th>Confirmed</th>
<th>phone</th>
<th>Last Name</th>
<th>First Name</th>
</tr>
[#list users as user]
    <tr> 
    <td>
 <a href="">[email forgot password]</a>
 <a href="">[accounts]</a>
 <a href="">[wallets]</a>
 <a href="">[orders]</a>
    </td>
 
    <td>${user.id}</td>
    <td>${user.entered}</td>
    <td>${user.status}</td>
    <td>${user.email}</td>
    <td>${user.password}</td>
    <td>${user.confirmed!"n/a"}</td>
    <td>${user.phone!"n/a"}</td>
    <td>${user.firstname!"n/a"}</td>
    <td>${user.lastname!"n/a"}</td>
    </tr>
[/#list]
</table>
            