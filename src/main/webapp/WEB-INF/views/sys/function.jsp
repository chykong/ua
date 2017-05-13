<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-模块管理</title>
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
						<li class="active">模块管理</li>
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
													<button class="btn btn-primary btn-sm" id="btnSearch">
														<i class="ace-icon fa fa-search"></i>刷新
													</button> <c:if test="${bln:isP('SysFunctionAdd')}">
														<button type="button" class="btn btn-success btn-sm" id="btnAdd">
															<i class="ace-icon fa fa-plus bigger-110"></i>新增
														</button>

													</c:if>
													<button type="button" class="btn  btn-sm" onclick="location.href='${dynamicServer}/sys/module/index.htm'">
														<i class="ace-icon fa fa-undo bigger-110"></i>返回
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
										<th width=120>操作名称</th>
										<th>操作代码</th>
										<th>模块链接</th>
										<th>操作类型</th>
										<th style="text-align: center;">排序</th>
										<th width=160>操作</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach items="${functionList}" var="sysFunction" varStatus="st">
										<tr>
											<td>${st.index+1 }</td>
											<td>${sysFunction.name}</td>
											<td>${sysFunction.code}</td>
											<td>${sysFunction.url}</td>
											<td><c:if test="${sysFunction.type eq 0}">读</c:if> <c:if test="${sysFunction.type eq 1}">写</c:if></td>
											<td>${sysFunction.display_order}</td>
											<td><c:if test="${bln:isP('SysFunctionAdd')}">
													<a href="toUpdate.htm?id=${sysFunction.id}&backUrl=${backUrl}"> 修改</i>
													</a>
												</c:if> <c:if test="${bln:isP('SysFunctionDelete')}">
													<a href="javascript:delFunction(${sysFunction.id });"> 删除 </a>
												</c:if></td>
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
				$(function() {
					$("#btnSearch").bind('click', searchFunction);
					$("#btnAdd").bind('click', addFunction);
				})

				// 查询方法
				var searchFunction = function() {
					var url = "index.htm?module_id=${sysModule.id}";
					window.location = encodeURI(url);
				}
				// 删除
				var delFunction = function(id) {
					bootbox.confirm("你确定要删除该功能吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addFunction = function(id) {
					window.location = 'toAdd.htm?module_id=${sysModule.id}&backUrl=${backUrl }';
				}
			</script>
</body>
</html>
