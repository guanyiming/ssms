
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
<link type="text/css" rel="stylesheet" href="static/css/index.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div id="login" class="panel">  
        <h1>登录界面-一明</h1>  
        <form method="post" action="login">  
            <input type="text" required="required"  placeholder="用户名" name="userName"></input>  
            <input type="password" required="required" placeholder="密码" name="password"></input>
            <input type="text" required="required" id="validatecode" name="validatecode" value="" class="codeInput" placeholder="验证码"/> <img src="code" id="codeImg" class="codeImg"/> 
            <span id = "node" style="text-align:center;color:#fff;">${msg }</span>
            <button class="but" type="submit">登录</button>
        </form>  
        <a href="javascript:void(0)" id="qrcode">扫码登录</a>
    </div> 
    <div class="panel none" id="qrcodePanel">
    	<h1>扫码登陆</h1>
    	<img alt="登陆二维码" id="codelog" style="width: 235px;height: 235px"><br>
    	<a href="javascript:void(0)" id="loginNormal">常规登陆</a>
    </div>
    <script type="text/javascript">
    	$(function(){
    		$("#codeImg").click(function(){
    			$("#codeImg").attr("src","code?"+Math.random());
    		});
    		/*  document.getElementById("codeImg").onclick=function(){
    	    	document.getElementById("codeImg").src="code?"+Math.random();
    	    }  */
    	    $("#qrcode").click(function(){
    	    	$("#codelog").attr("src","loginCode?"+Math.random());
    			$("#login").hide(500);
    			$("#qrcodePanel").show(1000);
    			var valTime = setInterval(function(){
        	    	$.ajax({
        	    		type:"post",
        	    		url:"vailQrcode",
        	    		success:function(data){
        	    			if(data=="true"){
        	    				window.location.href="toSuccess";
        	    			}
        	    		}
        	    	});
        	    },1000);
    		});
    	    $("#qrcodePanel").click(function(){
    			$("#qrcodePanel").hide(500);
    			$("#login").show(1000);
    		});
    	    $("#validatecode").blur(function(){
    	    	$.ajax({
    	    		type:"post",
    	    		url:"validate",
    	    		data:{"validatecode":$("#validatecode").val()},
    	    		success:function(data){
    	    			if(data=="true"){
    	    				$("#node").html("验证码正确！");
    	    			}else{
    	    				$("#node").html("验证码错误！");
    	    				return;
    	    			}
    	    		}
    	    	});
    	    });
    	})
    		
    	
    </script>
</body>
</html>