var queryPath = 'Query.do';

/*function reloadgrid() {  
    //查询参数直接添加在queryParams中      
    var queryParams = $('#zyts').datagrid('options').queryParams;  
    getQueryParams(queryParams);  
    $('#zyts').datagrid('options').queryParams = queryParams;//传递值  
    $("#zyts").datagrid('reload');//重新加载table  
}*/

//获取Datagrid Options对象属性的方式
/*function getGridProperty(){
   //先获取Options对象，然后通过Options获取其属性
   var optionsObj = $('#test').datagrid('options');
   var queryParams = optionsObj.queryParams;
}*/
//作业天数统计分页控制
function getZytsPage(orgIds, yearStart, monthStart, yearEnd, monthEnd, pageNumber, pageSize){
	$.messager.progress({
        title: '请等待',
        msg: '数据加载中...',
        text: '进行中……'
    });
	$.ajax({
		url : queryPath,
		data : {reqType: 3, orgId: orgIds, yearStart: yearStart, monthStart: monthStart, yearEnd: yearEnd, monthEnd: monthEnd, page: pageNumber, rows: pageSize},
		type : "post",
		success : function(data) {
			var msg = eval('('+data+')');
			$("#zyts").datagrid('loadData', msg);
			$.messager.progress('close');
		},
		error:function(e){
			$.messager.progress('close');
			alert("加载数据出错");
		}
	});
}
//船舶作业时长分页控制
function zyscPagerFilter(data){
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
			pager.pagination('refresh',{
				pageNumber:pageNum,
				pageSize:pageSize
			});
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

$(function() {
	//加载组织机构树
	loadOrgTree();
	//动态加载标题和数据
	$.messager.progress({
        title: '请等待',
        msg: '数据加载中...',
        text: '进行中……'
    });
    var yearStart = $('#year1').combobox('getValue');
	var monthStart = $('#month1').combobox('getValue');
	var yearEnd = $('#year2').combobox('getValue');
	var monthEnd = $('#month2').combobox('getValue');
	$.ajax({
		url : queryPath,
		data : {reqType: 3, orgId: 0, yearStart: yearStart, monthStart: monthStart, yearEnd: yearEnd, monthEnd: monthEnd, page: 1, rows: 20},
		type : "post",
		//dataType : "json",
		success : function(data) {
			var msg = eval('('+data+')');
		    var pager = $("#zyts").datagrid("getPager");
			$(pager).pagination({
			    /*onBeforeRefresh:function(pageNumber, pageSize){
		        	total: msg.total,
		            pageNumber: pageNo
		            $(this).pagination('loading');
		            alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
		            $(this).pagination('loaded'); 
	        	},*/
			    onSelectPage:function(pageNumber, pageSize){
			    	var nodes = $('#organ').tree('getChecked');
					var orgIds = '';
				    for (var i = 0; i < nodes.length; i++) {
				        if (orgIds != '') 
				            orgIds += ',';
				        orgIds += nodes[i].id;
				    }
			    	yearStart = $('#year1').combobox('getValue');
					monthStart = $('#month1').combobox('getValue');
					yearEnd = $('#year2').combobox('getValue');
					monthEnd = $('#month2').combobox('getValue');
		            $(this).pagination('loading');
		            getZytsPage(orgIds, yearStart, monthStart, yearEnd, monthEnd, pageNumber, pageSize);
		            $(this).pagination('loaded');
	        	}
	   	 	});
	   	 	$('#zysc').datagrid({loadFilter:zyscPagerFilter});  
			$("#zyts").datagrid('loadData', msg);
			$.messager.progress('close');
		},
		error:function(e){
			$.messager.progress('close');
			alert("加载数据出错");
		}
	});
	
	//船舶作业天数双击事件
	$("#zyts").datagrid({
        onDblClickRow: function (index, row) {
        	$.ajax({
				url : queryPath + "?reqType=4",
				data : {reqType: 4, year: row.year, month: row.month, shipId: row.shipId,workType: row.workType, page: 1, rows: 20},
				type : "post",
				success : function(data) {
					var msg = eval('('+data+')');
					$("#zysc").datagrid('loadData', msg);
				},
				error:function(e){
					alert("加载数据出错");
				}
			});
        	$('#dlg').dialog({
        		title: "船舶作业时长",
        		modal:true	
        	});
            $("#dlg").window("open");
        }/*,
        onLoadSuccess: function (data) {
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                $(this).datagrid('appendRow', { itemid: '<div style="text-align:center;color:red">没有相关记录！</div>' }).datagrid('mergeCells', { index: 0, field: 'itemid', colspan: 7 })
                //隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui datagrid没有提供相关方法隐藏导航条
                $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
        }*/
    });
})

//查询作业天数
function getZyts(){
	var nodes = $('#organ').tree('getChecked');
	var orgIds = '';
    for (var i = 0; i < nodes.length; i++) {
        if (orgIds != '') 
            orgIds += ',';
        orgIds += nodes[i].id;
    }
	var yearStart = $('#year1').combobox('getValue');
	var monthStart = $('#month1').combobox('getValue');
	var yearEnd = $('#year2').combobox('getValue');
	var monthEnd = $('#month2').combobox('getValue');
	$.messager.progress({
        title: '请等待',
        msg: '数据加载中...',
        text: '进行中……'
    });
	$.ajax({
		url : queryPath,
		data : {reqType: 3, orgId: orgIds, yearStart: yearStart, monthStart: monthStart, yearEnd: yearEnd, monthEnd: monthEnd, page: 1, rows: 20},
		type : "post",
		success : function(data) {
			if(data.replace(/\s/g,'')=="0"){
        		$("#zyts").datagrid('loadData', {"total":0,"rows":[]});
			}else{
				var msg = eval('('+data+')');
        		$("#zyts").datagrid('loadData', msg);
			}
        	$.messager.progress('close');
		},
		error: function(e){
			$.messager.progress('close');
		}
	});
}
//导出作业天数
function exportZyts(){
	var nodes = $('#organ').tree('getChecked');
	var orgIds = '';
    for (var i = 0; i < nodes.length; i++) {
        if (orgIds != '') 
            orgIds += ',';
        orgIds += nodes[i].id;
    }
	var yearStart = $('#year1').combobox('getValue');
	var monthStart = $('#month1').combobox('getValue');
	var yearEnd = $('#year2').combobox('getValue');
	var monthEnd = $('#month2').combobox('getValue');
	$("#sea").attr("action","Query.do?reqType=5&orgId="+orgIds+"&yearStart="+yearStart+"&monthStart="+monthStart+"&yearEnd="+yearEnd+"&monthEnd="+monthEnd);
	$("#sea").submit();
}
//导出作业时长
function exportZysc(){
	var row = $('#zyts').datagrid('getSelected');
	$("#sea").attr("action","Query.do?reqType=6&year="+row.year+"&month="+row.month+"&shipId="+row.shipId+"&workType="+encodeURI(encodeURI(row.workType)));
	$("#sea").submit();
}


//加载组织机构树
function loadOrgTree() {
	$.getJSON(queryPath + '?reqType=1', function(data) {
		var o = data.organ;
		var aa = convertTreeData(o);
		$('#organ').tree({
			data : aa
		});
	});
}

function makeEasyTree(data) {
	if (!data)
		return [];
	var _newData = []; // 最终返回结果
	var _treeArray = {}; // 记录一级节点
	var _root = 1342177280; // 最顶层fid
	var _idKey = "a"; // 主键的键名
	var _fidKey = "c"; // 父ID的键名
	_getChildren(_root);
	function _getChildren($root) {
		var $children = [];
		for (var i in data) {
			if ($root == data[i][_fidKey]) {
				data[i]["children"] = _getChildren(data[i][_idKey]);
				$children.push(data[i]);
			}
			// 只要一级节点
			if (_root == data[i][_fidKey] && !_treeArray[data[i][_idKey]]) {
				_treeArray[data[i][_idKey]] = data[i];
				_newData.push(data[i]);
			}
		}
		return $children;
	}
	return _newData;
}
//拼装父子节点关系
function convertTreeData(rows) {
	function exists(rows, parentId) {
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].a == parentId)
				return true;
		}
		return false;
	}
	
	var nodes = [];
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.c)) {
			nodes.push({
				id : row.a,
				text : row.b,
				checked : true,
				checkbox : true
			});
		}
	}
	
	var toDo = [];
	for (var i = 0; i < nodes.length; i++) {
		toDo.push(nodes[i]);
	}
	var node;
	while (toDo.length) {
		node = toDo.shift();
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i];
			if (row.c == node.id) {
				var child = {
						id : row.a,
						text : row.b,
						checked : true,
						checkbox : true
				};
				if (node.children) {
					node.children.push(child);
				} else {
					node.children = [child];
				}
				toDo.push(child);
			}
		}
	}
	return nodes;
}