[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="sv" lang="sv">
<head>
<title>Admin - ${content.title!}</title>
<style type="text/css">
    .textinput {margin-bottom:8px;}
    .errorMessage {color:red;}

	body { font-family: "Lucida Sans Unicode","Lucida Grande",Verdana,Arial,Helvetica,sans-serif; font-size: 13px; background-color: #DDDDDD; }
    a {color: #4040ff}
    a:visited {color: #4040ff;}
    #container {margin-left:50px; width:875px;}
    #logo {font-family:Georgia,'Times New Roman',Times,serif;font-size:46px;padding:50px 0px 8px 10px;background-color:#ffffff;}
    #menu ul {
        background:#313131;
        background:-moz-linear-gradient(center top ,#5D5Da0, #313160);
        background:-webkit-gradient(linear, left top, left bottom, from(#5D5Da0), to(#313160));
        height:30px;
        margin: 0 0 10px 0;
        padding:0 0 0 4px;
        font-size:14px;
        list-style:none;}
    #menu ul li {margin-top:0;float:left;display:inline}
    #menu ul li a {margin-top:4px;padding:5px 8px;color:#fff;text-decoration:none;display:block;outline:0 none;}
    #menu ul li a:hover {background:#fafafa;color:#4b4b4b}
    #content {background-color:white; padding:15px;margin-bottom:20px;-moz-border-radius-bottomleft:5px;-moz-border-radius-bottomright:5px;}
	#main1 { float:left; width:100%; background-color: #ffffff;}
	#promos { float:left; width: 200px; background-color: #ffffff;margin-left:20px;}
    #copyright {padding:20px 15px 0 15px;clear:both;text-align:center;color: #737373;}

</style>
    [@cms.init /]
</head>
<body>
    <div id="container">

        <div id="logo">admin</div>
 
        <div id="menu">
            <ul>
           <li><a href="${ctx.contextPath}/${contextBase}/">Home</a></li>
            [#list navigation?keys as key]
                <li><a href="${ctx.contextPath}${key}.html"> ${navigation[key]} </a></li>
            [/#list]
            
       
            </ul>
        </div>

        <div id="content">[@cms.area name="main"/]</div>
    </div>
</body>
</html>
