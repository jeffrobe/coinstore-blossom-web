[#import "/org/springframework/web/servlet/view/freemarker/spring.ftl" as spring /]
[#assign htmlEscape=true in spring /]
[#assign blossom=JspTaglibs["blossom-taglib"] /]
 
<style type="text/css">
    .textinput {margin-bottom:8px;}
    .errorMessage {color:red;}
</style>

[@blossom.pecidInput /]

<h1>registration email confirmation page</h1>

[@spring.bind "profileForm" /]
  
<h2>Terms:</h2>


[#if spring.status.error]
    <div class="errors">
        There were problems with the data you entered:
        <ul>
            [#list spring.status.errorMessages as error]
                <li>${error}</li>
            [/#list]
        </ul>
    </div>
[/#if]
 
[#if showTerms]
    

<form action="?" method="POST">
    <table>
 

    <tr>
    <td>
    <textarea name="terms" rows="10" cols="50">terms read all
    asdfasdfsdfd
    fsdf
    sdf
    sd
    fs
    df
    sdf
    sd
    fsd
    f
    sdf
    sd
    f
    sdf
    sd
    fsd
    f
    sd
    fsd
    fs
    df
    sd
    fs
    df
    sd
    fsd
    f
    sd
    
    
    
     </textarea>
    </td>
    </tr>

	<tr>
	<td>
	 
[@spring.formCheckbox path="profileForm.tos"  /]&nbsp;[@spring.showErrors separator=", " classOrStyle="errorMessage" /] 
	
	
	I agree to the terms
	<input type="submit" value="submit" />
	</td></tr>
 
</form>

    </table>

[/#if]

