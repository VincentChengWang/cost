<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	//获取绝对路径路径 
	String path = request.getContextPath();

			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
	%>   
<%@ taglib prefix="d" uri="http://displaytag.sf.net" %>	   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title></title>
<link href="resource/css/bootstrap.min.css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery.min.js"></script>
<script type="text/javascript"
	src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="resource/My97DatePicker/WdatePicker.js"></script>	
<script type="text/javascript">
$(function(){
	

	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>报销管理</li>
			<li>我的报销</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="expense/queryMyExpense" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
			
			<label>开始时间:</label> <input type="text" class="form-control " type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="startDate" value='<fmt:formatDate value="${expense.startDate }" pattern="yyyy-MM-dd" type="both"/>' placeholder="请输入开始时间" />
			<label>结束时间:</label> <input type="text" class="form-control" name="endDate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"   value='<fmt:formatDate value="${expense.endDate }" pattern="yyyy-MM-dd" type="both"/>' placeholder="请输入结束时间" />	
			<label>报销原因:</label> <input type="text" class="form-control"     name="expenseName" value="${expense.expenseName }" placeholder="请输入报销原因" />
			<label>报销状态:</label>
			<select class="form-control" name="expenseState">
				<option value="">请选择</option>
				<option value="0" ${expense.expenseState=='0'?'selected':'' } >未提交</option>
				<option value="1" ${expense.expenseState=='1'?'selected':'' }>经理未审核</option>
				<option value="2" ${expense.expenseState=='2'?'selected':'' }>经理审核通过</option>
				<option value="3" ${expense.expenseState=='3'?'selected':'' }>财务审核通过</option>
				<option value="-1" ${expense.expenseState=='-1'?'selected':''}>经理审核未通过</option>
				<option value="-2" ${expense.expenseState=='-2'?'selected':''}>财务审核未通过</option>
			</select>
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> 
		
		</div>
		
		<div class="row" style="padding: 15px;">
			<d:table class="table table-hover table-condensed" name="expenseList" pagesize="10" requestURI="expense/queryMyExpense">
				
				<d:column property="expenseId" title="报销编号"></d:column>
				<d:column property="userName" title="报销人"></d:column>
				<d:column property="expenseName" title="报销原因"></d:column>
				<d:column property="expenseDate" title="报销时间"></d:column>
				<d:column property="showExpenseState" title="报销状态"></d:column>
				<d:column property="expenseMoney" title="报销总金额"></d:column>
				<d:column value="查看详情" title="查看详情" href="expense/showExpense"  paramId="expenseId" paramProperty="expenseId"></d:column>
				 <d:column property="updateButton" title="操作"></d:column> 
				
			</d:table>
		</div>
	</form>
</body>
</html>