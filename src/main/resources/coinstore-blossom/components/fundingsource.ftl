[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<h1>Funding source</h1>
<li>name: ${fundingsource.name}</li>
<li>status: ${fundingsource.status}</li>
<li>account: ${fundingsource.account}</li>
<li>routing: ${fundingsource.routing}</li>

<h1>List of trialdeposits</h1>

<table>
<tr>
<th>Status</th>
<th>entered</th>
<th>confirmed</th>
<th>deposits</th>
<th>verify deposits</th>
</tr>
[#list trialdepositList as trialdeposit]
    <tr> 
    <td>${trialdeposit.status}</td>
    <td>${trialdeposit.entered}</td>
    <td>${trialdeposit.confirmed!"n/a"}</td>
    <td>${trialdeposit.deposit1} ${trialdeposit.deposit2}</td>
    <td>
    <form action="?" method="GET">
    <input type="hidden" name="action" value="verify"> 
    <input type="hidden" name="userId" value="${userId}"> 
    <input type="hidden" name="fundingsourceId" value="${fundingsource.id}"> 
    <input type="hidden" name="trialdepositId" value="${trialdeposit.id}"> 

    1 <input type=text name="deposit1" size="4"> 2 <input type=text name="deposit2" size="4"> <input type=submit value=verify>
    </form>
    </td>
    </tr>
[/#list]
</table>
            
[#if hasError]
    <div class="errors">
        There were problems with the data you entered:
        <ul>
            [#list errors as error]
                <li>${error}</li>
            [/#list]
        </ul>
    </div>
[/#if]
            