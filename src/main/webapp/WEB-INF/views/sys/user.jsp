<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>${webTitle }-用户管理</title>
    <%@ include file="../common/header.jsp" %>
    <link rel="stylesheet" href="${staticServer }/assets/components/jquery-ui/jquery-ui.css?version=${versionNo}"/>

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
                    <li class="active">用户管理</li>
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
                                            <td>账号：</td>
                                            <td><input type="text" id="txtUsername" class="form-control input-small"
                                                       placeholder=""
                                                       value="${sysUserSearchVO.username }"></td>
                                            <td>姓名：</td>
                                            <td><input type="text" id="txtRealname" class="form-control input-small"
                                                       placeholder=""
                                                       value="${sysUserSearchVO.realname }"></td>
                                            <td>状态：</td>
                                            <td><form:select path="sysUserSearchVO.status" class="form-control"
                                                             id="cmbStatus">
                                                <form:option value="" label="--全部--"/>
                                                <form:option value="1">正常</form:option>
                                                <form:option value="2">已锁定</form:option>
                                            </form:select></td>
                                            <td>角色：</td>
                                            <td><form:select path="sysUserSearchVO.role_id" class="form-control"
                                                             id="cmbRole_id">
                                                <form:option value="" label="--全部--"/>
                                                <form:options items="${listRole}" itemValue="id" itemLabel="name"/>
                                            </form:select></td>
                                            <td>
                                                <button class="btn btn-primary btn-sm" id="btnSearch">
                                                    <i class="ace-icon fa fa-search"></i> 查询
                                                </button>
                                                <c:if test="${bln:isP('SysUserAdd')}">
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
                        <table id="simple-table" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th width=40>#</th>
                                <th width=120>账号</th>
                                <th width="120">姓名</th>
                                <th>角色</th>
                                <th>创建人</th>
                                <th>创建时间</th>
                                <th>状态</th>
                                <th>登录记录</th>
                                <th width="160">操作</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${list }" var="sysUser" varStatus="st">
                                <tr>
                                    <td>${st.index+1 }</td>
                                    <td>${sysUser.username }</td>
                                    <td>${sysUser.realname }</td>
                                    <td>${sysUser.role_name }</td>
                                    <td>${sysUser.create_person }</td>
                                    <td><fmt:formatDate value="${sysUser.create_date }"
                                                        pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td>${bln:getUserStatus(sysUser.status)}</td>
                                    <td>
                                        <a href="javascript:viewLoginHis('${sysUser.id}','${sysUser.username}')">查看 </a>
                                    </td>
                                    <td><c:if test="${bln:isP('SysUserUpdate')}">
                                        <a href="toUpdate.htm?id=${sysUser.id }&backUrl=${backUrl}"> 修改 </a>
                                    </c:if> <c:if test="${bln:isP('SysUserDelete')}">
                                        <a href="javascript:delUser(${sysUser.id });"> 删除 </a>
                                    </c:if> <c:if test="${bln:isP('SysUserLock')&&sysUser.status==1}">
                                        <a href="javascript:lock(${sysUser.id });">锁定 </a>
                                    </c:if> <c:if test="${bln:isP('SysUserUnlock')&&sysUser.status==2}">
                                        <a href="javascript:unlock(${sysUser.id });">解锁 </a>
                                    </c:if> <c:if test="${bln:isP('SysUserResetPass')}">
                                        <a href="javascript:resetPass(${sysUser.id });">重置密码 </a>
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
                <div id="dialog-viewLogin" class="hide center">
                </div>
            </div>
            <!-- /.main-content -->
        </div>
        <!-- /.main-container -->
        <%@ include file="../common/js.jsp" %>
        <script src="${staticServer }/assets/components/jquery-ui/jquery-ui.js"></script>

        <script type="text/javascript">
				$(function() {
					$("#btnSearch").bind('click', searchUser);
					$("#btnAdd").bind('click', addUser);
				})

				// 查询方法
				var searchUser = function() {
					var url = "index.htm?";
					if ($("#txtUsername").val() != '')
						url += "username=" + $("#txtUsername").val();
					if ($("#txtRealname").val() != '')
						url += "&realname=" + $("#txtRealname").val();
					if ($("#cmbStatus").val() != '')
						url += "&status=" + $("#cmbStatus").val();
					if ($("#cmbRole_id").val() != '')
						url += "&role_id=" + $("#cmbRole_id").val();
					window.location = encodeURI(url);
				}
				// 删除
				var delUser = function(id) {
					bootbox.confirm("你确定要删除该用户吗？", function(result) {
						if (result) {
							window.location = "delete.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				// 锁定
				var lock = function(id) {
					bootbox.confirm("你确定要锁定该用户吗？", function(result) {
						if (result) {
							window.location = "saveLock.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				// 解锁
				var unlock = function(id) {
					bootbox.confirm("你确定要解锁该用户吗？", function(result) {
						if (result) {
							window.location = "saveUnlock.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}
				// 重置密码
				var resetPass = function(id) {
					bootbox.confirm("你确定要给该用户重置密码吗？", function(result) {
						if (result) {
							window.location = "saveResetPass.htm?id=" + id + "&backUrl=${backUrl}";
						}
					})
				}

				//新增
				var addUser = function(id) {
					window.location = 'toAdd.htm?backUrl=${backUrl }';
				}

	var viewLoginHis = function (id, title) {
        $.post('searchUserLogin.htm', {
            id: id
        }, function (html) {
            $("#dialog-viewLogin").html(html);
            var dialog = $("#dialog-viewLogin").removeClass('hide').dialog({
                title: "【" + title + "】登录历史",
                title_html: false,
                width: 1000,
                minHeight:400,
                position: { my: "center", at: "center", of: window  },
                modal: true,
                buttons: [
                    {
                        text: "返回",
                        "class": "btn btn-minier btn-center",
                        click: function () {
                            $(this).dialog("close");
                        }
                    },
                ]
            });
        });
    }



        </script>
</body>
</html>
