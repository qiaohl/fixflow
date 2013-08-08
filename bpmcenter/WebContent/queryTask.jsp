<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查询</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<style>
a{text-decoration: none;}
</style>
</head>

<body>
<div>
  <a id="b1" target="_self" href="FlowCenter?action=getMyProcess">发起流程</a>
  <a id="b2" target="_self" href="FlowCenter?action=getMyTask">待办任务</a>
  <a id="b3" target="_self" href="FlowCenter?action=getInitorTask">流程查询</a>
  <a id="b4" target="_self" href="login.jsp">归档任务</a>
  <a id="b5" target="_self" href="login.jsp">返回登陆</a>
</div>
<div style="margin-top:10px;">
<!-- 左 -->
	<div style="float:left;width:20%;">
	  <a id="myTask" style="display:block;">全部流程</a>
	  <a target="_self" href="FlowCenter?action=getInitorTask" style="display:block;">我参与的流程</a>
	  <a target="_self" href="FlowCenter?action=getParticipantsTask" style="display:block;">我发起的流程</a>
	</div>
<!-- 右-->
	<div style="float:right;width:79%;">
<!-- 查 -->
	  <div id="search">
	  </div>
	    任务主题：<input type="text" value="<c:out value="${result.title}"/>"/>
	    流程变量：<input type="text"/>
	        单据号：<input type="text"/><br/>
	    到达时间：<input type="text" class="Wdate" onClick="WdatePicker()" value="<c:out value="${result.arrivalTimeS}"/>"/> 
	    	—— <input type="text" class="Wdate" onClick="WdatePicker()" value="<c:out value="${result.arrivalTimeE}"/>"/>
	        发起人：<input type="text" value="<c:out value="${result.initor}"/>"/>
	  <div>
<!-- 表 -->
		<table>
		  <thead>
		    <th>优先级</th>
		    <th>流程名称</th>
		    <th>单据号</th>
		    <th>任务主题</th>
		    <th>发起人</th>
		    <th>发起时间</th>
		    <th>当前步骤</th>
		    <th>到达时间</th>
		  </thead>
		  <tbody>
		    <c:forEach items="${result.dataList}" var="dataList" varStatus="index">
		    <tr>
		      <td>${dataList.name}</td>
		    </tr>
		    </c:forEach>
		  </tbody>
	    </table>
<!-- 分页 -->	    
	    <div id="page">
	      <a name="page" href="javascript:void(0);">1</a>
	      <a name="page" href="javascript:void(0);">2</a>
	      <a name="page" href="javascript:void(0);">3</a>
	    </div>
	  </div>
	</div>
</div>
<!-- 隐藏参数部分 -->
<input type="hidden" name="userId" value="<c:out value="${result.userId}"/>">
<input type="hidden" name="pageIndex" value="<c:out value="${result.pageIndex}"/>">
<input type="hidden" name="rowNum" value="<c:out value="${result.rowNum}"/>">
<input type="hidden" name="agentType" value="<c:out value="${result.agentType}"/>">
</body>
<script>
/*
 * "userId" 用户编号
 * "pdkey" 流程编号
 * "pageIndex" 第几页
 * "rowNum" 有几行
 * "agentUserId" 有几行
 * "agentType" 0我代理别人，1别人委托给我
 * "title" 查询主题
 * "processVeriy" 查询变量
 * "arrivalTimeS" 到达时间开始
 * "arrivalTimeE" 到达时间结束
 * "initor" 发起人
 * @param @return
 * "dataList" 数据列表
 * "pageNumber" 总行数
 * "agentUsers" 代理用户
 * "agentToUsers" 委托用户
 * "pageIndex" 第几页
 * "rowNum" 有几行
 */
$(function(){
  var agentType = $("input[name=agentType]").val();
  var userId = $("input[name=userId]").val();
  $("a[name=page]").click(function(){
    var pageNo = $(this).html();
    window.location.href = "FlowCenter?action=getMyTask&pageIndex="+pageNo+"&rowNum=15&agentType="+agentType+"&userId="+userId;
  });
  $("li[name=agentUsers]").click(function(){
    var userId = $(this).attr("userId");
    window.location.href = "FlowCenter?action=getMyTask&agentType=0&userId="+userId;
  });
  $("li[name=agentToUsers]").click(function(){
    var userId = $(this).attr("userId");
    window.location.href = "FlowCenter?action=getMyTask&agentType=1&userId="+userId;
  });
});
</script>
</html>