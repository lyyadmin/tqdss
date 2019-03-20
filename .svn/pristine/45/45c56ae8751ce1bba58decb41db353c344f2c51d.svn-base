$(function(){
	var fileHeight = $(document).height()+200;
	//动态设置div 显示图形的高度
    $(".average-cls").css("height", (fileHeight/3)+"px");
	// 初始化日期
	var currentDate = getCurrentSystemDate();
	var startDate = getLeftDate(currentDate, 270);
	$("#anayDate").val(startDate);
	$("#contrastDate").val(currentDate);
	findAllTotalAnay();
	$("#search").click(function() {
		//$("#myModal").modal();
		findAllTotalAnay();
	});
});
//总体分析
function findAllTotalAnay(){
	$.ajax({
        async      :false,
        type       :'post',
//	        contentType:"application/x-www-form-urlencoded;charset=UTF-8",
        url        :path+"/admin/project/totalAnalysis.do",
        data       :$("#driverAnayForm").serialize(),
        dataType   :"text",
        success    :function(data){
        	//清空div
        	var obj = JSON.parse(data);
        	initGrah(obj);
        }
    });
}

function initGrah(data){
	var hgl = data.hgl;
	//第一个pie图形   特殊类型展示
	if(hgl!=undefined){
		getPie(hgl[0].shanghai_buhege,hgl[0].shanghai_hege,"pie","hg");
	}
	//存在的典型问题
	if(data.existProFraction!=undefined){
		getBarAndLine("",["扣分","占比"],getXdata(data.existProFraction),getYdata(data.existProFraction),"bom-left",["bar","bar"]);
	}
	//存在的典型问题
	if(data.existProPerson!=undefined){
		getBarAndLine_1("",["扣分人次","占比"],getXdata(data.existProPerson),getYdata(data.existProPerson),"bom-right",["bar","bar"]);
	}
	//关注的重点问题
	if(data.emphasesProFraction!=undefined){
		getBarAndLines("",["扣分人次","占比"],getXdata(data.emphasesProFraction),getYdata(data.emphasesProFraction),"left",["line","line"]);
		
		
	}
	//关注的重点问题
	if(data.emphasesProPerson!=undefined){
		getBarAndLines_1("",["扣分人次","占比"],getXdata(data.emphasesProPerson),getYdata(data.emphasesProPerson),"right",["line","line"]);
	}
	
}

function getYdata(obj){
	var ydataArr = [];
	var yData_1 = [];
	var yData_2 = [];
	for(var j = 0;j<obj.length;j++){
		yData_1.push(obj[j].data_1);
		yData_2.push(obj[j].data_2);
	}
	ydataArr.push(yData_1);
	ydataArr.push(yData_2);
	return ydataArr;
};
function getXdata(obj){
	var xData = [];
	for(var j = 0;j<obj.length;j++){
		xData.push(obj[j].data_0);
	}
	return xData;
}

/**
 * 獲取饼图
 */
function getPie(shanghai_buhege,shanghai_hege,grahType,divID){
	var colorL=['rgb(30,144,255)','rgb(233,105,22)','rgb(0,105,8)'];
	var myChart = echarts.init(document.getElementById(divID));
	var labelTop = {
			normal : {
				label : {
					 show : true, 
					show : true,
					position : 'center',
					formatter : '{b}',
					textStyle: {
						baseline : 'bottom'
					}
				},
				labelLine : {
					show : false
				}
			}
		};
		var labelFromatter = {
			normal : {
				label : {
					formatter : function (params){
						return 100 - params.value + '%'
					},
					textStyle: {
						baseline : 'top'
					}
				},
				color:new echarts.graphic.LinearGradient(
                		0,0,0,1,[{
                			offset:0,
                			color:'#f00'
                		},{
                			offset:1,
                			color:'#0f0'
                		}])
			},
		}
		var labelBottom = {
			normal : {
				color: '#ccc',
				label : {
					show : true,
					position : 'center'
				},
				labelLine : {
					show : false
				}
			},
			emphasis: {
				color: 'rgba(0,0,0,0)'
			}
		};
		var radius = [35, 50];
		var option = {
			legend: {
				orient:'horizontal',
				x : 'center',
				inactiveColor:'#999',
				borderColor:'blue',
				textStyle:{
					color:"#999"
				},
				data:[
					'上海机务段','南京东机务段','杭州机务段','合肥机务段'
				]
			},
			title : {
				text: '',
				x: 'center',
				padding:0
			},
			toolbox: {
				show : false,
				feature : {
					dataView : {show: true, readOnly: false},
					magicType : {
						show: true, 
						type: ['pie', 'funnel'],
						option: {
							funnel: {
								width: '20%',
								height: '30%',
								itemStyle : {
									normal : {
										label : {
											formatter : function (params){
												return 'other\n' + params.value + '%\n'
											},
											textStyle: {
												baseline : 'middle'
											}
										}
									},
								} 
							}
						}
					},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			series : [
				{
					type : grahType,
					center : ['15%', '40%'],
					radius : radius,
					x: '0%', // for funnel
					itemStyle : {
						normal : {
							label : {
								formatter : function (params){
									return 100 - params.value + '%'
								},
								textStyle: {
									baseline : 'top'
								}
							},
							color:new echarts.graphic.LinearGradient(
			                		0,0,0,1,[{
			                			offset:0,
			                			color:'#6afcc8'
			                		},{
			                			offset:1,
			                			color:'#46cfcb'
			                		}])
						},
					},
					data : [
						{name:'other', value:shanghai_buhege*100, itemStyle : labelBottom},
						{name:'上海机务段',value:shanghai_hege*100,itemStyle : labelTop}
					]
				},
				{
					type : grahType,
					center : ['35%', '40%'],
					radius : radius,
					x:'10%', // for funnel
					itemStyle : {
						normal : {
							label : {
								formatter : function (params){
									return (100 - params.value)/2 + '%'
								},
								textStyle: {
									baseline : 'top'
								}
							},
							color:new echarts.graphic.LinearGradient(
			                		0,0,0,1,[{
			                			offset:0,
			                			color:'#01d0c1'
			                		},{
			                			offset:1,
			                			color:'#3e75c8'
			                		}])
						},
					},
					data : [
						{name:'other', value:shanghai_buhege*100, itemStyle : labelBottom},
						{name:'南京东机务段', value:shanghai_buhege*100,itemStyle : labelTop}
					]
				},
				{
					type : grahType,
					center : ['60%', '40%'],
					radius : radius,
					x:'40%', // for funnel
					itemStyle : {
						normal : {
							label : {
								formatter : function (params){
									return (100 - params.value)/2 + '%'
								},
								textStyle: {
									baseline : 'top'
								}
							},
							color:new echarts.graphic.LinearGradient(
			                		0,0,0,1,[{
			                			offset:0,
			                			color:'#ffee58'
			                		},{
			                			offset:1,
			                			color:'#19ce1d'
			                		}])
						},
					},
					data : [
						{name:'other', value:shanghai_buhege*100, itemStyle : labelBottom},
						{name:'杭州机务段', value:shanghai_buhege*100,itemStyle : labelTop}
					]
				},
				{
					type : grahType,
					center : ['85%', '40%'],
					radius : radius,
					x:'60%', // for funnel
					itemStyle : {
						normal : {
							label : {
								formatter : function (params){
									return (100 - params.value)/2 + '%'
								},
								textStyle: {
									baseline : 'top'
								}
							},
							color:new echarts.graphic.LinearGradient(
			                		0,0,0,1,[{
			                			offset:0,
			                			color:'#faa88c'
			                		},{
			                			offset:1,
			                			color:'#e56590'
			                		}])
						},
					},
					data : [
						{name:'other', value:shanghai_buhege*100, itemStyle : labelBottom},
						{name:'合肥机务段', value:shanghai_buhege*100,itemStyle : labelTop}
					]
				}
//				{
//					type : grahType,
//					center : ['80%', '40%'],
//					radius : radius,
//					x:'80%', // for funnel
//					itemStyle : {
//						normal : {
//							label : {
//								formatter : function (params){
//									return 100 - params.value + '%'
//								},
//								textStyle: {
//									baseline : 'top'
//								}
//							},
//							color:new echarts.graphic.LinearGradient(
//			                		0,0,0,1,[{
//			                			offset:0,
//			                			color:'#e56590'
//			                		},{
//			                			offset:1,
//			                			color:'#673ab7'
//			                		}])
//						},
//					},
//					data : [
//						{name:'other', value:shanghai_buhege*100, itemStyle : labelBottom},
//						{name:'徐州机务段', value:shanghai_buhege*100,itemStyle : labelTop}
//					]
//				}
			]
		};
		myChart.setOption(option);
}

/**
 * 折线图和柱状图
 * @param title
 * @param legendArr
 * @param xDataArr
 * @param yDataArr
 * @param divId
 * @param grahTypeArr
 */
function getBarAndLine(title,legendArr,xDataArr,yDataArr,divId,grahTypeArr){
	var myChart = echarts.init(document.getElementById(divId));
	var option = {
		    title : {
		        text: title
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:legendArr
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: grahTypeArr},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : xDataArr
		        }
		    ],
		    yAxis : [
		        {
		        	name : legendArr[0],
		            type : 'value'
		        },{
		            name : legendArr[1],
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ]
		};
	
	var series = [];
/*	var start = ["#e8771d","#3385bf","#3bbd50","#a7379b","#1cbab4"];
    var end = ["#46cfcb","#3e75c8","#19ce1d","#e56590","#673ab7"];*/
    var end = ["#a7379b","#d73332","#1cbab4","#ce3f89","#006ae6"];
	for(var i = 0;i<yDataArr.length;i++){
			var obj = {};
			obj.name = legendArr[i];
			obj.type = grahTypeArr[i];
			obj.data = yDataArr[i];
			if(i==1){
				obj.yAxisIndex=1;
			}
			obj.itemStyle={
				width:10,
                normal: {
                	color:new echarts.graphic.LinearGradient(
	                		0,0,0,1,[{
	                			offset:0,//"#003366","#006699"  #a7379b
	                			color:'#d73332'
	                		},{
	                			offset:1,
	                			color:'#a7379b'
	                		}]),
                    label: {
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            };
			series.push(obj);
      };
        option.series=series;
    	myChart.setOption(option);   
	}
	 
function getBarAndLine_1(title,legendArr,xDataArr,yDataArr,divId,grahTypeArr){
	var myChart = echarts.init(document.getElementById(divId));
	var option = {
		    title : {
		        text: title
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:legendArr
		    },
		    color:["#003366","#006699"],
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: grahTypeArr},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : xDataArr
		        }
		    ],
		    yAxis : [
		        {
		        	name : legendArr[0],
		            type : 'value'
		        },{
		            name : legendArr[1],
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ]
		};
	
	var series = [];
/*	var start = ["#e8771d","#3385bf","#3bbd50","#a7379b","#1cbab4"];
    var end = ["#46cfcb","#3e75c8","#19ce1d","#e56590","#673ab7"];*/
    var end = ["#a7379b","#d73332","#1cbab4","#ce3f89","#006ae6"];
	for(var i = 0;i<yDataArr.length;i++){
			var obj = {};
			obj.name = legendArr[i];
			obj.type = grahTypeArr[i];
			obj.data = yDataArr[i];
			if(i==1){
				obj.yAxisIndex=1;
			}
			obj.itemStyle={
				width:10,
				normal: {
                	color:new echarts.graphic.LinearGradient(
	                		0,0,0,1,[{
	                			offset:0,//"#003366","#006699"  #a7379b
	                			color:'#a7379b'
	                		},{
	                			offset:1,
	                			color:'#003366'
	                		}]),
                    label: {
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            };
			series.push(obj);
      };
        option.series=series;
    	myChart.setOption(option);   
	}

/**
 * 面积图
 * @param title
 * @param legendArr
 * @param xDataArr
 * @param yDataArr
 * @param divId
 * @param grahTypeArr
 */
function getBarAndLines(title,legendArr,xDataArr,yDataArr,divId,grahTypeArr){
	var myChart = echarts.init(document.getElementById(divId));
	var option = {
		    title : {
		        text: title
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:legendArr
		    },
		    color:["#003366","#006699","#4cabce","#e5323e"],
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: grahTypeArr},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : xDataArr
		        }
		    ],
		    yAxis : [
		        {
		        	name : legendArr[0],
		            type : 'value'
		        },{
		            name : legendArr[1],
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ]
		};
	
	var series = [];
    var end = ["#1cbab4","#ce3f89","#006ae6"];
	for(var i = 0;i<yDataArr.length;i++){
			var obj = {};
			obj.name = legendArr[i];
			obj.type = grahTypeArr[i];
			obj.data = yDataArr[i];
			obj.smooth=true;
			if(i==1){
				obj.yAxisIndex=1;
			}
			obj.itemStyle={
				width:10,
                normal: {
                	areaStyle: {type: 'default'}
                }
            };
			series.push(obj);
      };
        option.series=series;
    	myChart.setOption(option);   
	}
///

function getBarAndLines_1(title,legendArr,xDataArr,yDataArr,divId,grahTypeArr){
	var myChart = echarts.init(document.getElementById(divId));
	var option = {
		    title : {
		        text: title
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:legendArr
		    },
		    color:["#4cabce","#e5323e"],
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: grahTypeArr},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : xDataArr
		        }
		    ],
		    yAxis : [
		        {
		        	name : legendArr[0],
		            type : 'value'
		        },{
		            name : legendArr[1],
		            type : 'value',
		            axisLabel : {
		                formatter: function(v){
		                    return - v;
		                }
		            }
		        }
		    ]
		};
	
	var series = [];
    var end = ["#1cbab4","#ce3f89","#006ae6"];
	for(var i = 0;i<yDataArr.length;i++){
			var obj = {};
			obj.name = legendArr[i];
			obj.type = grahTypeArr[i];
			obj.data = yDataArr[i];
			obj.smooth=true;
			if(i==1){
				obj.yAxisIndex=1;
			}
			obj.itemStyle={
				width:10,
                normal: {
                	areaStyle: {type: 'default'}
                }
            };
			series.push(obj);
      };
        option.series=series;
    	myChart.setOption(option);   
	}
/**
 * 
 * var myChart = echarts.init(document.getElementById('left'));
 * 图表的点击事件
 * myChart.setOption(option);
   myChart.on("click", eConsole2);
   点击事件触发的方法
   function eConsole2(params){
		alert(params.name)
	}
 * @param params
 * 
 * 属性包括
 * seriesType:string     系列类型
 * seriesIndex:number    索引
 * name：string           数据名称
 * dataIndex:number		 传入data数据   data数组中数据的索引
 * data:Objec            原始值
 * dataType：string		 数据类型
 * value ：number         传入的数据值
 * color：string         图形颜色
 */


function eConsole2(params){
	alert(params.name)
}