<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-用户管理</title>
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
						<li class="active">用户登录记录</li>
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
												<td>
													<button class="btn btn-sm" id="btnSearch" onclick="location.href='${dynamicServer}/sys/user/index.htm'">
														<i class="ace-icon fa fa-undo"></i> 返回
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
										<th width=140>登录时间</th>
										<th width="120">登录IP</th>
										<th>终端</th>
										<th>浏览器类型</th>
										<th>浏览器版本</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="sysUserLogin" varStatus="st">
										<tr>
											<td>${st.index+1 }</td>
											<td><fmt:formatDate value="${sysUserLogin.login_date }" pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${sysUserLogin.login_ip }</td>
											<td>${sysUserLogin.terminal }</td>
											<td>${sysUserLogin.explorerType }</td>
											<td>${sysUserLogin.explorerVersion}</td>
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

			<script type="text/javascript">
				
			</script>
</body>
</html>
