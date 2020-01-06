<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎页面</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
    	<!-- Begin of toolbar -->
        <div id="wu-toolbar">
            <div class="wu-toolbar-button">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
            </div>
            <div class="wu-toolbar-search">
                <label>菜单名称：</label><input class="wu-text s" style="width:100px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
            </div>
        </div>
        <!-- End of toolbar -->
        <table id="data-datagrid" toolbar="#wu-toolbar"></table>
    </div>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form"  method="post">
        <table>
            <tr>
                <td width="60" align="right">菜单名称:</td>
                <td><input type="text" id="name"name="name" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单名称'" /></td>
            </tr>
            <tr>
                <td align="right">上级菜单:</td>
                <td>
                	<select name="parentId" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">顶级分类</option>
		                <c:forEach items="${topList }" var="menu">
		                <option value="${menu.id }">${menu.name }</option>
		                </c:forEach>
		            </select>
                </td>
            </tr>
            <tr>
                <td align="right">菜单URL:</td>
                <td><input type="text" id="url"name="url" class="wu-text" /></td>
            </tr>
            <tr>
                <td valign="top" align="right">菜单图标:</td>
                <td>
                <input type="text" id="icon" name="icon" class="wu-text easyui-validatebox" data-options="required:true,missingMessage:'请填写菜单图标名称'" />
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="selectIcon()" plain="true">选择</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->

<!-- 选择图标弹窗 -->
<div id="select-icon-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:480px;height:450px; padding:10px;">
	<table id="icons-table" cellspacing="8">
		<!-- 图标列表动态加入 -->
	</table>
</div>
<!-- 选择图标弹窗结束 -->


<script type="text/javascript">
	/**
	* Name 载入菜单树
	*/
	$('#wu-category-tree').tree({
		url:'temp/menu.php',
		onClick:function(node){
			alert(node.text);
		}
	});

	/**
	* Name 添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		var name = $("#name").val();
		var url = $("#url").val();
		var icon = $("#icon").val();
		var parentId = $("#parentId").val(); 
		if(!validate){
			$.messager.alert("消息提醒","请检查你输入的数据:", "warning");
			return;
		}
		//debugger  用于打断点
		//var data = $("add-form").serialize();//表单序列化，表单数据转换为json
		//console.log(data);
		$.ajax( {
			url:'../admin/menu/add',
			dataType:'json',
			type:'post',
			//从页面传入的参数要和实体类的类型对应不然会报 400 错误，重点！！！！！！
			data:  {name:name,url:url,parentId:parentId,icon:icon},
			success:function(data){
				if(data.type == 'success'){
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
				}else
				{
					$.messager.alert('信息提示',data.msg,'warining');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#add-form').form('submit', {
			url:'',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#add-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#data-datagrid').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.productid);	
				});
				//alert(ids);return;
				$.ajax({
					url:'',
					data:'',
					success:function(data){
						if(data){
							$.messager.alert('信息提示','删除成功！','info');		
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加菜单信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                //点击‘确定’之后就去调用'add'方法（第73行）
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		//alert(item.productid);return;
		$.ajax({
			url:'',
			data:'',
			success:function(data){
				if(data){
					$('#add-dialog').dialog('close');	
				}
				else{
					//绑定值
					$('#add-form').form('load', data)
				}
			}	
		});
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
	* Name 分页过滤器
	*/
	function pagerFilter(data){            
		if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
			data = {                   
				total: data.length,                   
				rows: data               
			}            
		}        
		var dg = $(this);         
		var opts = dg.datagrid('options');          
		var pager = dg.datagrid('getPager');          
		pager.pagination({                
			onSelectPage:function(pageNum, pageSize){                 
				opts.pageNumber = pageNum;                   
				opts.pageSize = pageSize;                
				pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
				dg.datagrid('loadData',data);                
			}          
		});           
		if (!data.originalRows){               
			data.originalRows = (data.rows);       
		}         
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
		var end = start + parseInt(opts.pageSize);        
		data.rows = (data.originalRows.slice(start, end));         
		return data;       
	}
	
	/**
	* Name 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'../admin/menu/list',
		loadFilter:pagerFilter,		
		rownumbers:true, // 设置显示行号
		singleSelect:true,  // false设置可以多选，true设置只能单选
		pageSize:20,  // 设置每页显示20条         
		pagination:true,    //是否分页，true为分页
		multiSort:true,    //
		fitColumns:true,   //填充
		fit:true,	//
		columns:[[
			{ checkbox:true},
			//真正从数据库拿出的要显示的字段
			{ field:'name',title:'菜单名称',width:100,sortable:true},
			{ field:'url',title:'菜单URL',width:180,sortable:true},
			{ field:'icon',title:'图标icon',width:100}
		]]
	});
	
	/**
	* 图标上传路径选择框
	*/
	function selectIcon(){
		if($("#icons-table").children().length <= 0){
			$.ajax({
				url:'../admin/menu/get_icons',
				dataType:'json',
				type:'post',
				success:function(data){
					if(data.type == 'success'){
						var icons = data.content;//获取controller类返回的content
						var table = '';
						for(var i = 0;i<icons.length;i++){
							var tbody = '<td class="icon-td"><a onclick="selected(this)" href="javascript:void(0)" class="'+ icons[i] +'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>';
							if(i == 0){//第一个图标位置
								table  += '<tr>' + tbody;
							}
							if(i != 0 && i%10 == 	0){//每行最后一个图标位置
								table += tbody + '</tr><tr>' ;
							}
							table += tbody;
						}
						table += '</tr>';
						$("#icons-table").append(table);
					}else{
						$.messager.alert('信息提示', data.msg, 'warning');
					}
				}
			});
		}
		
		/**
		*点击图标后的动作
		*/
		function selected(e){
			alert(1111);
			console.log("123132");
		}
		
		
		$('#select-icon-dialog').dialog({
			closed: false,
			modal:true,
            title: "选择icon信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                //点击‘确定’之后就去调用'add'方法（第73行）
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}
	
</script>
</body>
</html>