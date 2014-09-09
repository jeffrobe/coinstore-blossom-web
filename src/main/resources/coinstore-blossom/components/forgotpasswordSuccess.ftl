[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<h1>Forgot password reset</h1>
<div>email sent to:</div> 
<div>email: ${email}</div>
