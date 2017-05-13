<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-角色管理</title>
    <%@ include file="../common/header.jsp" %>
    <link href="${staticServer}/assets/css/treeTable.min.css?version=${versionNo}" rel="stylesheet" type="text/css"/>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp" %>
<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp" %>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
                    <li class="active">系统管理</li>
                    <li class="active">角色管理</li>
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
                                                </button>
                                                <c:if test="${bln:isP('SysRoleAdd')}">
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
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=60>序号</th>
                                <th width=150>角色名称</th>
                                <th>描述</th>
                                <th width=100>创建人</th>
                                <th width=140>创建时间</th>
                                <th width="140">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list }" var="sysRole" varStatus="st">
                                <tr>
                                    <td>${st.index+1 }</td>
                                    <td>${sysRole.name }</td>
                                    <td>${sysRole.description }</td>
                                    <td>${sysRole.create_person }</td>
                                    <th width=120><fmt:formatDate value="${sysRole.create_date}"
                                                                  pattern="yyyy-MM-dd HH:mm"/></th>
                                    <td><c:if test="${bln:isP('SysRoleUpdate')}">
                                        <a href="toUpdate.htm?id=${sysRole.id }&backUrl=${backUrl}"> 修改 </a>
                                    </c:if> <c:if test="${bln:isP('SysRoleDelete')}">
                                        <a href="javascript:delRole(${sysRole.id });"> 删除 </a>
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
        <%@ include file="../common/js.jsp" %>
        <script src="${staticServer }/assets/js/jquery.treeTable.min.js" type="text/javascript"></script>

        <script type="text/javascript">
				$(function() {
					$("#btnSearch").bind('click', searchModule);
					$("#btnAdd").bind('click', addRole);

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
				var delRole = function(id) {
					bootbox.confirm("你确定要删除该角色吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				//新增
				var addRole = function(id) {
					window.location = 'toAdd.htm?backUrl=${backUrl }';
				}

        </script>
</body>
</html>
