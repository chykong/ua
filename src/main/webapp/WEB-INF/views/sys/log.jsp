<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-操作日志</title>
<%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="../common/top.jsp"%>
	<div class="main-container" id="main-container">
		<%@ include file="../common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">系统管理</li>
						<li class="active">操作日志</li>
					</ul>
					<!-- /.breadcrumb -->
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<div class="widget-box widget-color-blue">
								<!-- #section:custom/widget-box.options -->
								<div class="widget-header">
									<h5 class="widget-title bigger lighter">
										<i class="ace-icon fa fa-table"></i> 操作面板
									</h5>
								</div>

								<!-- /section:custom/widget-box.options -->
								<div class="widget-body">
									<div class="widget-main">
										<table class="searchField" style="margin: 4px; padding: 4px;">
											<tr>
												<td>用户：</td>
												<td><form:select path="sysLogSearchVO.user_id" class="form-control input-small" id="user_id">
														<form:option value="" label="-选择-" />
														<form:options items="${listUser}" itemValue="id" itemLabel="realname" />
													</form:select></td>
												<td>起止日期：</td>
												<td><input type="text" id="s_date" class="form-control input-small" placeholder="" value="${sysLogSearchVO.s_date }">
												</td>
												<td>至</td>
												<td><input type="text" id="e_date" class="form-control input-small" placeholder="" value="${sysLogSearchVO.e_date }"></td>
												<td>
													<button class="btn btn-primary btn-sm" id="btnSearch">
														<i class="ace-icon fa fa-search"></i> 查询
													</button>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- PAGE CONTENT BEGINS -->
					<div class="row">
						<div class="col-xs-12">
							<table id="simple-table" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width=40>#</th>
										<th width="100">用户姓名</th>
										<th>操作时间</th>
										<th width="100">模块名称</th>
										<th>操作名称</th>
										<th>操作url</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${list }" var="sysLog" varStatus="st">
										<tr>
											<td>${st.index+1 }</td>
											<td>${sysLog.realname }</td>
											<td><fmt:formatDate value="${sysLog.opera_date}" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${sysLog.module_name }</td>
											<td>${sysLog.opera_name }</td>
											<td>${sysLog.opera_url }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.span -->
					</div>
					<!-- /.row -->
					<div class="row">
						<div class="col-xs-12">${ pageNavigate.pageModel}</div>
					</div>

				</div>
				<!-- /.main-content -->
			</div>
			<!-- /.main-container -->
			<%@ include file="../common/js.jsp"%>
			<link rel="stylesheet" type="text/css" href="${staticServer }/assets/datetimepicker/jquery.datetimepicker.css" />
			<script src="${staticServer }/assets/datetimepicker/jquery.datetimepicker.js" type="text/javascript"></script>
			<script type="text/javascript">
				$('#s_date').datetimepicker({
					lang : 'ch',
					timepicker : false,
					format : 'Y-m-d',
					formatDate : 'Y-m-d'
				});
				$('#e_date').datetimepicker({
					lang : 'ch',
					timepicker : false,
					format : 'Y-m-d',
					formatDate : 'Y-m-d'
				});
			</script>
			<script type="text/javascript">
				$(function() {
					$("#btnSearch").bind('click', searchUser);
				})

				// 查询方法
				var searchUser = function() {
					var url = "index.htm?";
					if ($("#user_id").val() != '')
						url += "user_id=" + $("#user_id").val();
					if ($("#s_date").val() != '')
						url += "&s_date=" + $("#s_date").val();
					if ($("#e_date").val() != '')
						url += "&e_date=" + $("#e_date").val();
					window.location = encodeURI(url);
				}
			</script>
</body>
</html>
