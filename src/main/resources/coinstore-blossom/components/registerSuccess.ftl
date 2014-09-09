[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 

<div class="title">You are now registered you will receive an email with further instructions.</div>
  
<div class="info">Email Sent To</div>
<div class="confirm">${email}</div>

 