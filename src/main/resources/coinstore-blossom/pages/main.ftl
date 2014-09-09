[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="sv" lang="sv">
<head>
<title>coin - ${content.title!}</title>
<link rel="stylesheet" type="text/css" href="${ctx.contextPath}/.resources/css/main.css">

    [@cms.init /]
</head>
<body>

<div id="container">

        <div id="logo">&nbsp;</div>
 
        <div id="menu">
            <ul>
             
              <li><a href="${ctx.contextPath}/${contextBase}/"> Home </a></li>

            [#if !isLoggedIn]
              <li><a href="${ctx.contextPath}/${contextBase}/login.html"> login </a></li>
              <li><a href="${ctx.contextPath}/${contextBase}/forgotpassword.html">Forgot Password</a></li>
              <li><a href="${ctx.contextPath}/${contextBase}/register.html">register</a></li>
			[/#if]

            
            [#if isLoggedIn]
              <li><a href="${ctx.contextPath}/${contextBase}/${memberDirectory}/index.html"> members </a></li>
              <li><a href="${ctx.contextPath}/${contextBase}/logout.html"> logout </a></li>
              <li><a href="${ctx.contextPath}/${contextBase}/${memberDirectory}/profile.html">profile ${userDisplayName} [${userId}]</a></li>
			[/#if]
            </ul>
        </div>

<div id="content">


 [@cms.area name="main"/]
      
 [@cms.area name="promos"/]
 
</div>
</div>
</body>
</html>
