<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/ztree/zTreeStyle.css">
	<style>
		.tree li {
			list-style-type: none;
			cursor:pointer;
		}
		table tbody tr:nth-child(odd){background:#F4F4F4;}
		table tbody td:nth-child(even){color:#C00;}
	</style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<%@include file="../common/top.jsp"%>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
</nav>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
			</div>
		</div>

		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
				</div>
				<div class="panel-body">
					<%--主体内容 树--%>
					<ul id="treeDemo" class="ztree"></ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH }/script/docs.min.js"></script>
<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
<script type="text/javascript" src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript">
	$(function () {
		$(".list-group-item").click(function(){
			if ( $(this).find("ul") ) {
				$(this).toggleClass("tree-closed");
				if ( $(this).hasClass("tree-closed") ) {
					$("ul", this).hide("fast");
				} else {
					$("ul", this).show("fast");
				}
			}
		});
		showMenu();
	});

	/**
	 *  显示本页菜单
	 */
	<%--function showMenu () {--%>
	<%--&lt;%&ndash;var remoteaddr = '${pageContext.request.requestURL}';&ndash;%&gt;--%>
	<%--var remoteaddr = '<%=request.getServletPath()%>';--%>
	<%--alert(remoteaddr);--%>
	<%--}--%>

	function showMenu(){
		var href = window.location.href ;
		var host = window.location.host ;
		var index = href.indexOf(host);
		var path = href.substring(index + host.length);
		var contextPath = "${APP_PATH}";
		var pathAddress = path.substring(contextPath.length);
		var alink = $(".list-group a[href*='"+pathAddress+"']");
		alink.css("color","red");
		alink.parent().parent().parent().removeClass("tree-closed");
		alink.parent().parent().show();

	}
	// var zNodes =[
	// 	{ name:"父节点1 - 展开", open:true,
	// 		children: [
	// 			{ name:"父节点11 - 折叠",
	// 				children: [
	// 					{ name:"叶子节点111"},
	// 					{ name:"叶子节点112"},
	// 					{ name:"叶子节点113"},
	// 					{ name:"叶子节点114"}
	// 				]},
	// 			{ name:"父节点12 - 折叠",
	// 				children: [
	// 					{ name:"叶子节点121"},
	// 					{ name:"叶子节点122"},
	// 					{ name:"叶子节点123"},
	// 					{ name:"叶子节点124"}
	// 				]},
	// 			{ name:"父节点13 - 没有子节点", isParent:true}
	// 		]},
	// 	{ name:"父节点2 - 折叠",
	// 		children: [
	// 			{ name:"父节点21 - 展开", open:true,
	// 				children: [
	// 					{ name:"叶子节点211"},
	// 					{ name:"叶子节点212"},
	// 					{ name:"叶子节点213"},
	// 					{ name:"叶子节点214"}
	// 				]},
	var setting = {
		view: {
			addDiyDom:function addDiyDom(treeId, treeNode) { //更换树的图标
				//得到显示的地方
				var aObj = $("#" + treeNode.tId + "_ico");
				//将树结点的信息 赋给 显示的span
				if( treeNode.icon  ){
					//有图标的话11
					aObj.removeClass("button ico_docu").addClass(treeNode.icon).css("background","");
				}
			},
            addHoverDom: function(treeId, treeNode){   //设置自定义按钮组,在节点后面悬停显示增删改按钮组.
                var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
                aObj.attr("href", "javascript:;"); // 取消当前链接事件.
                if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
                var s = '<span id="btnGroup'+treeNode.tId+'">';//无视报错
                if ( treeNode.level == 0 ) { //根节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 1 ) { //分支节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#" onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    if (treeNode.children == undefined || treeNode.children.length == 0) {
                        s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                    }
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="window.location.href=\'${APP_PATH}/permission/toAdd.htm?id='+treeNode.id+'\'">&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
                } else if ( treeNode.level == 2 ) { //叶子节点
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="#"  onclick="window.location.href=\'${APP_PATH}/permission/toUpdate.htm?id='+treeNode.id+'\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
                    s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="#" onclick="deletePermission('+treeNode.id+','+treeNode.name+')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
                }

                s += '</span>';
                aObj.after(s);
            },
            removeHoverDom: function(treeId, treeNode){
                $("#btnGroup"+treeNode.tId).remove();
            }

        }

	};

	$(document).ready(function(){
		var loadingIndex = layer.msg('加载数据中', {icon: 16});
		$.ajax({
			data:{},
			type:"POST",
			url:"${APP_PATH}/permission/loadData.do",
			success:function (result) {
				layer.close(loadingIndex);
				$.fn.zTree.init($("#treeDemo"), setting, result.data);
			},
			error:function (result) {
				layer.close(loadingIndex);
				layer.msg(result.message, {time:1000, icon:5, shift:6});
			}
		});

	});

</script>
</body>
</html>
