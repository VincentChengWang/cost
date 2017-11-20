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
<script type="text/javascript">
$(function(){
	
	//实现全选和全不选
	$("#all").click(function(){
		var checked = this.checked;
		$("input[name=ids]").each(function(){
			this.checked = checked;
		});
	});
	
	//普通按钮提交表单
	$("#del").click(function(){
			//获取选中的复选框
			var ids = $("input[name=ids]:checked");
		
			if(ids.length == 0){
				alert("请选择删除的费用!");
				return;
			}
		
			if(confirm("确认要删除！")){
				var f1 = $("#f1");
				f1.attr("action","system/deleteCosts");
				f1.submit();//提交表单
			}
		
	});
	
})

</script>	
</head>
<body>
<div>
		<ul class="breadcrumb" style="margin: 0px;">
			<li>系统管理</li>
			<li>费用管理</li>
			<li>费用查询</li>
		</ul>
	</div>
	<div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:${info==null?'none':'block' };margin-bottom: 0px;">
     	<h4 align="center" style="color: red">${info }</h4>
    </div>
	<form action="system/queryCosts" id="f1" method="post" class="form-inline">
		<div class="row alert alert-info" style="margin: 0px; padding: 5px;">
			<div class="form-group">
				<label>费用编号:</label> 
				<input type="text" class="form-control" name="costId" value="" placeholder="请输入费用编号" />
				<label>费用名称:</label>
				<input type="text" class="form-control" name="costName" value="${cost.costName }"  placeholder="请输入费用姓名" />
			</div>
			<input type="submit" class="btn btn-danger" value="查询"> <a
				href="view/system/cost/cost_add.jsp" class="btn btn-success">添加费用</a>
			<input type="button" class="btn btn-warning" id="del" value="删除费用">
		
		</div>
		
		<div class="row" style="padding: 15px;">
			<d:table class="table table-hover table-condensed" name="list" pagesize="10" requestURI="system/queryCosts">
				<d:column property="ck" title="<input type='checkbox'  id='all'  />"></d:column>
				<d:column property="costId" title="费用编号"></d:column>
				<d:column property="costName" title="费用姓名"></d:column>
				<d:column property="costDesc" title="费用性别"></d:column>
				<d:column value="修改" title="修改" href="system/updateCost"  paramId="costId" paramProperty="costId"></d:column>
				<%-- <d:column value="删除" title="删除" href="system/deleteCosts"  paramId="costId" paramProperty="costId"></d:column> --%>
			</d:table>
		</div>
	</form>
</body>
</html>