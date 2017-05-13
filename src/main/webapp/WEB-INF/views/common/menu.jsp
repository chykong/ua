<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="taglib.jsp"%>
<script src="${staticServer }/assets/js/jCookie.js"></script>
<!-- /section:basics/navbar.layout -->
<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar responsive ace-save-state  sidebar-fixed">
	<script type="text/javascript">
		try {
			ace.settings.loadState('sidebar')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<!-- #section:basics/sidebar.layout.shortcuts -->
			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>

			<!-- /section:basics/sidebar.layout.shortcuts -->
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span
				class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->


	<ul class="nav nav-list">
		<li class="active" id="menu-statistic"><a href="${dynamicServer}/index.htm" id="module_statistic"> <i
				class="menu-icon fa fa-tachometer"></i> <span class="menu-text"> 功能菜单 </span>
		</a> <b class="arrow"></b></li> ${bln:createMenu(pageContext.request) }
	</ul>

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
	<script type="text/javascript">
		$(".submenu li, #menu-statistic").find("a").click(function() {
			//移除a里面的样式
			//$("#footer").find("a").removeClass("active");
			//当前选择的下标
			var index = $(this).parent().attr("id");
			//alert(index);
			//记录下标
			jQuery.jCookie('current', index, 30, {
				path : '/'
			});
		});
		var current = jQuery.jCookie('current');
		if (current != null && current != '') {
			$('.nav-list .active').removeClass('active');
			var $current = $("#" + current);
			$current.addClass("active");
			$current.parent().parent().addClass("active open");
		}
	</script>
	<!-- /section:basics/sidebar.layout.minimize -->
</div>