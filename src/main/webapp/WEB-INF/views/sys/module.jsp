<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-模块管理</title>
<%@ include file="../common/header.jsp"%>
<link href="${staticServer}/assets/css/treeTable.min.css?version=${versionNo}" rel="stylesheet" type="text/css" />
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
														<i class="ace-icon fa fa-search"></i> 刷新
													</button> <c:if test="${bln:isP('SysModuleAdd')}">
														<button type="button" class="btn btn-success btn-sm" id="btnAdd">
															<i class="ace-icon fa fa-plus bigger-110"></i>新增
														</button>
													</c:if>
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
							<table id="treeTable" class="table table-striped table-bordered table-condensed">
								<thead>
									<tr>
										<th width=150>模块名称</th>
										<th width=120>模块代码</th>
										<th>模块链接</th>
										<th width=80>链接目标</th>
										<th width=80>图标</th>
										<th style="text-align: center;" width=80>排序</th>
										<th width="241">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="sysModule" varStatus="st">
										<tr id="${sysModule.id}" pId="${sysModule.parent_id ne 1?sysModule.parent_id:'0'}">
											<td>${sysModule.name}</td>
											<td>${sysModule.code}</td>
											<td style="word-break: break-all;">${sysModule.url}</td>
											<td>${sysModule.target}</td>
											<td><div>
													<i class="fa ${sysModule.iconImg}"></i>
												</div></td>
											<td style="text-align: center;">${sysModule.display_order}</td>
											<td><c:if test="${bln:isP('SysModuleAdd')}">
													<a href="toUpdate.htm?id=${sysModule.id}&backUrl=${backUrl}"> 修改</i>
													</a>
												</c:if> <c:if test="${bln:isP('SysModuleDelete')}">
													<a href="javascript:delModule(${sysModule.id });"> 删除 </a>
												</c:if> <a href="${dynamicServer }/sys/function/index.htm?module_id=${sysModule.id }">功能设置 </a></td>
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
			<script src="${staticServer }/assets/js/jquery.treeTable.min.js" type="text/javascript"></script>

			<script type="text/javascript">
				$(function() {
					$("#btnSearch").bind('click', searchModule);
					$("#btnAdd").bind('click', addUser);

					$("#treeTable").treeTable({
						expandLevel : 3
					});
				})

				// 查询方法
				var searchModule = function() {
					var url = "index.htm?";
					window.location = encodeURI(url);
				}
				// 删除
				var delModule = function(id) {
					bootbox.confirm("你确定要删除该模块吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addUser = function(id) {
					window.location = 'toAdd.htm?backUrl=${backUrl }';
				}
			</script>
</body>
</html>
