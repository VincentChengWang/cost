<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//获取绝对路径路径 
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户管理-用户添加</title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<!-- <link href="resource/css/main.css" rel="stylesheet" /> -->
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resource/validation/jquery.validate.js"></script>


</head>
<body>
	<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>财务管理</li>
			<li>财务审批</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert"
		style="display:${tip==null?'none':'block' };margin-bottom: 0px;">
		<h4 align="center" style="color: red">${tip }</h4>
	</div>
	<form action="finance/financeAudit" id="f1" method="post"
		class="form-horizontal">
		
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">基本信息</h5>
		<!-- 开始1 -->
		<div class="row">
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销编号</label>
					<div class="col-xs-9 ">
						<input type="hidden" name="expenseId" value="${expenseInfo.expenseId }"/>
					 <input type="hidden" name="userId" value="${userinfo.userId }"/>
					 <p class="form-control-static">${expenseInfo.expenseId }</p>
						
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销原因</label>
					<div class="col-xs-9 ">
					 <p class="form-control-static">${expenseInfo.expenseName }</p>
						
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销总金额</label>
					<div class="col-xs-5 ">
					<p class="form-control-static">${expenseInfo.expenseMoney}</p>
						
					</div>
				</div>
			</div>
			<div class="col-xs-7">
				<div class="form-group ">
					<label class="col-xs-3 control-label">报销详情</label>
					<div class="col-xs-9 ">
						<p class="form-control-static">${expenseInfo.expenseDesc}</p>
						
					</div>
				</div>
			</div>
		</div>
		<!--结束1 -->

		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">
			报销单明细
		
		</h5>
		<table class="table" id="tab1" >
			<tr>
				<th>明细编号</th>
				<th>费用名称</th>
				<th>具体金额</th>
				<th>费用说明</th>
		
			</tr>
			
			<c:forEach items="${detailList }" var="di">
				<tr>
					<td>${di.detailId }</td>
					<td>${di.costName }</td>
					<td>${di.detailMoney }</td>
					<td>${di.detailDesc }</td>
				</tr>
			
			</c:forEach>
		
		</table>
		<h5 class="page-header alert-info"
			style="margin: 0px; padding: 10px; margin-bottom: 10px;">
			审核历史记录
		</h5>
		<table class="table" id="tab1" >
			<tr>
				<th>历史记录编号</th>
				<th>审核人</th>
				<th>审核状态</th>
				<th>审核时间</th>
				<th>审核描述</th>
			</tr>
			<c:forEach items="${auditList }" var="ah">
				<tr>
					<td>${ah.auditId }</td>
					<td>${ah.userName }</td>
					<td>${ah.showAuditState }</td>
					<td>${ah.auditDate }</td>
					<td>${ah.auditSugg }</td>
				</tr>
			
			</c:forEach>
		</table>
	<h5 class="page-header alert-info" style="margin: 0px; padding: 10px; margin-bottom: 10px;">经理审核</h5>
		
		<div class="row">
			<div class="col-xs-7 ">
				 <div class="form-group">
				    <label  class="col-xs-3 control-label" >审批状态</label>
				    <div class="col-xs-9">
				     	<div class="radio">
				     	<label>
				    	<input type="radio" checked="checked" name="auditState" value="3"/>财务审核通过
				    	</label>
				    	</div>
				    	 <div class="radio">
				    	 <label>
				  	 	<input type="radio" name="auditState" value="-2"/>财务审核不通过
				  	 	</label>
				  	 	</div>
				  	 	 <div class="radio">
				  	 	 
				  	 	</div>
				  	 </div>	
				
				  </div>
			</div>
		<div class="col-xs-7">
			 <div class="form-group">
			    <label  class="col-xs-3 control-label" >审批描述</label>
			    <div class="col-xs-8">
			  		<textarea rows="8" class="form-control"  name="auditSugg" placeholder="请输入审批描述" required cols=""></textarea>
			  	  
			 	</div>
			  </div>
		</div>
		</div>
		<div class="row">
			<div class="col-sm-7" align="center">
				
				<input type="submit" value="提交审批" class="btn btn-success"/>
				<a href="finance/expenselist" class="btn btn-warning">返回上一级</a>
			</div>
		</div>
	</form>

</body>
</html>