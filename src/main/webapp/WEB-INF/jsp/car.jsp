<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前端名片生成</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/success.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.qrcode.min.js"></script>
</head>
<body>
<div class="top">
		<div class="header">
			<div class="logo">一明自学记录</div>
			<div class="desc">名片自动识别系统 by 一明</div>
		</div>
	</div>
	<dir class="content">
		<div class="box">
			<div class="info">
				<p>
					<span>姓名:</span>
					<input type="text" id="name" class="text" placeholder="请输入姓名">
				</p>
				<p>
					<span>手机:</span>
					<input type="text" id="phone" class="text" placeholder="请输入手机号">
				</p>
				
				<p>
					<span>公司:</span>
					<input type="text" id="company" class="text" placeholder="请输入公司名称">
				</p>
				<p>
					<span>职务:</span>
					<input type="text" id="work" class="text" placeholder="请输入职业">
				</p>
				<p>
					<span>地址:</span>
					<input type="text" id="address" class="text" placeholder="请输入地址">
				</p>
				<p>
					<span>邮箱:</span>
					<input type="text" id="email" class="text" placeholder="请输入邮箱">
				</p>
				<p>
					<span>URL:</span>
					<input type="text" id="url" class="text" placeholder="请输入网址">
				</p>
				<p>
					<span>备注:</span>
					<input type="text" id="remark" class="text" placeholder="请输入备注">
				</p>
				<p class="button">
					<a href="javascript:void(0)" id ="btn">生成名片</a>
				</p>
			</div>
			<div class="qrcode"></div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#btn").click(function(){
				var name ="",url="",phone="",company="",work="",address="",email="",remark="";
				if($("#name").val().length>1){
					name = "FN:"+$("#name").val()+"\n";
				}
				if($("#company").val().length>1){
					company = "ORG:"+$("#company").val()+"\n";
				}  
				if($("#work").val().length>1){
					work = "TITLE:"+$("#work").val()+"\n";
				}
				if($("#phone").val().length>1){
					phone = "TEL;HOME:"+$("#phone").val()+"\n";
				}
				if($("#address").val().length>1){
					address = "ADR;WORK:"+$("#address").val()+"\n";
				}
				if($("#email").val().length>1){
					email = "EMAIL:"+$("#email").val()+"\n";
				}
				if($("#url").val().length>1){
					url = "URL:"+$("#url").val()+"\n";
				}
				if($("#remark").val().length>1){
					remark = "NOTE;ENCODING=QUOTED-PRINTABLE:"+$("#remark").val()+"\n";
				}
				$(".qrcode").qrcode(utf16to8("BEGIN:VCARD\n"+name+company+work+phone+address+url+email+remark+"END:VCARD"));
			});
		})
		//中文乱码解决
		function utf16to8(str){
		var out,i,len,c;
		out = '';
		len = str.length;
		for(i = 0 ; i<len;i++){
			c = str.charCodeAt(i);
			if((c>=0x0001)&&(c<=0x007F)){
				out+=str.charAt(i);
			}else if(c>0x007FF){
				out += String.fromCharCode(0xE0|((c>>12)&0x0F));
				out += String.fromCharCode(0x80|((c>>6)&0x3F));
				out += String.fromCharCode(0x80|((c>>0)&0x3F));
			}else{
				out += String.fromCharCode(0xC0|((c>>6)&0x1F));
				out += String.fromCharCode(0x80|((c>>0)&0x3F));
			}
		}
		return out;
	}
	</script>
</body>
</html>