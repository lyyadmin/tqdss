$(
	function(){
		$(".modal-dialog").draggable();
		var endDate = getCurrentSystemDate();
		var startDate = getLeftDate(endDate, 180);
		$("#startDate").val(startDate);
		$("#endDate").val(endDate);
		
		
		bootstrapDateTime("startTimePicker,endTimePicker");
		
		$("#startTime").val("00:00:00");
		$("#endTime").val("23:59:59");
		
		setJwdAll();
		setWorkShopAll();
		setWorkTeamAll();
		setGroupAll();
		setDriverInfoAll();
		
		setWorkScene();
		setWorkProject();
		setWorkProblem();

		search(1);
	}	
	
);

function search(pictureType) {
	$(".selected").removeClass('selected');
	if(pictureType==1 || pictureType==4){//扣分
		$("#deduct_score").addClass('selected');
	}else if(pictureType==2){//扣分次数
		$("#deduct_score_num").addClass('selected');
	}else{//平均扣分
		$("#deduct_score_avg").addClass('selected');
	}
	$.ajax({ 
        url: path + "/admin/specialItemSearch/getSearchData.do?pictureType="+pictureType,
        type: 'post',
        data: {
        	startDate : $("#startDate").val(),
        	endDate : $("#endDate").val(),
        	startTime : $("#startTime").val(),
        	endTime : $("#endTime").val(),
        	
        	jwd : $("#jwd option:selected").text(),
        	workShop : $("#workShop option:selected").text(),
        	workTeam : $("#workTeam option:selected").text(),
        	group : $("#group option:selected").text(),
        	driverInfo : $("#driverInfo option:selected").text(),
        	workScene : $("#workScene option:selected").text(),
        	workProject : $("#workProject option:selected").text(),
        	workProblem : $("#workProblem option:selected").text(),
        	
        	kehuoche : $("#kehuoche").val(),
        	checi : $("#checi").val(),
        	shifazhan : $("#shifazhan").val(),
        	zhongdianzhan : $("#zhongdianzhan").val(),
        	quduan : $("#quduan").val(),
        	chexing : $("#chexing").val()
        },
        dataType:'json',
        cache: false,
        async: false,
        success: function (data) {
        	setChart(data,pictureType);
        	
        }
    });
}
function setChart(data,pictureType) {
	var legendList = data["legendList"];
	
	var scoreDiv = echarts.init(document.getElementById("scoreDiv"));
	scoreDiv.clear();
	
	var scoreOfWorkShop = data["searchData"];
	if(scoreOfWorkShop.length == 0) {
		alert("数据为空，请重新选择查询条件！");
	}
	var xAxis1 = new Array();
	for (var i = 0; i < scoreOfWorkShop.length; i++) {
		xAxis1.push(scoreOfWorkShop[i]["data_0"]);
	}
	var yAxisName = "";
	if(pictureType==1){
		yAxisName = "扣分";
	}else if(pictureType==2){
		yAxisName = "扣分次数";
	}else{
		yAxisName = "平均扣分";
	}

	var colors=["#b19cdc","#49ced0"];
	var ca=["rgba(177,156,220,0.5)","rgba(73,206,208,0.5)"];
	var series1 = new Array();
//	for (var i = 1; i <= legendList.length; i++) {
		var series_ = new Array();
		for (var j = 0; j < scoreOfWorkShop.length; j++) {
			series_.push(scoreOfWorkShop[j]["data_1" ]);
		}
		series1.push({
			name: pictureType,
            type: 'line',
            smooth:true,
           /* areaStyle:{
            	color:ca[i-1]
            },
            color:colors[i-1],*/
            data: series_
		});
//	}
	var option1 = {
		    title : {
		        text: '',
		        left: 'center'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        y: 'top',
		    	/* orient : 'vertical',
		         x : 'right',*/
		        data: legendList
		    },
		    toolbox: {
		        show : false
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : xAxis1
		        }
		    ],
		    yAxis : [
		        {
		        	name : yAxisName,
		            type : 'value'
		        }
		    ],
		    series : series1
		};
	scoreDiv.setOption(option1);
	
	setPieChart(data,pictureType);
}
function setPieChart(data,pictureType){
	var top5Chart1 = echarts.init(document.getElementById("echart1"));
	top5Chart1.clear();
	var personalWeaknessTop3 = data["workQuestion"];
	var xAxis2 = new Array();
	var series2 = new Array();
	var colors = ["#B19CDC","#CCA711","#d73332","#1cbab4","#006ae6"];
	for (var i = 0; i < personalWeaknessTop3.length; i++) {
		xAxis2.push(personalWeaknessTop3[i]["index_name"]);
		series2.push({value:personalWeaknessTop3[i]["data_1"], name:personalWeaknessTop3[i]["index_name"],
			itemStyle:{
				normal:{
					color:colors[i%colors.length]
				}
			}
		});
		
	}
	
	var option2 = {
			title : {
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        y : 'bottom',
		        data: xAxis2
		    },
		    toolbox: {
		        show : true
		    },
		    series : [
		        {
		            name: '项点',
		            type: 'pie',
		            radius : '55%',//['40%', '60%'],
		            /*itemStyle : {
		                normal : {
		                	color:function(params){
		                		return colors[params.dataIndex%colors.length];
		                	},
		                    label : {
		                        show : true
		                    },
		                    labelLine : {
		                        show : true
		                    }
		                },
		                emphasis : {
		                    label : {
		                        show : true,
		                        position : 'center',
		                        textStyle : {
		                            fontSize : '30',
		                            fontWeight : 'bold'
		                        }
		                    }
		                }
		            },*/
		            data: series2,
		            radius:[10,110],
		            roseType:'area'
		        }
		    ]
		};
	top5Chart1.setOption(option2);
	
	top5Chart1.on('click',function(param){
		
		$.ajax({ 
	        url: path + "/admin/specialItemSearch/getSearchQuestionDetails.do?pictureType="+pictureType,
	        type: 'post',
	        data: {
	        	startDate : $("#startDate").val(),
	        	endDate : $("#endDate").val(),
	        	startTime : $("#startTime").val(),
	        	endTime : $("#endTime").val(),
	        	
	        	jwd : $("#jwd option:selected").text(),
	        	workShop : $("#workShop option:selected").text(),
	        	workTeam : $("#workTeam option:selected").text(),
	        	group : $("#group option:selected").text(),
	        	driverInfo : $("#driverInfo option:selected").text(),
	        	workScene : $("#workScene option:selected").text(),
	        	workProject : $("#workProject option:selected").text(),
	        	workProblem : $("#workProblem option:selected").text(),
	        	questionName : param.name,
	        	
	        	kehuoche : $("#kehuoche").val(),
	        	checi : $("#checi").val(),
	        	shifazhan : $("#shifazhan").val(),
	        	zhongdianzhan : $("#zhongdianzhan").val(),
	        	quduan : $("#quduan").val(),
	        	chexing : $("#chexing").val()
	        },
	        dataType:'json',
	        cache: false,
	        async: false,
	        success: function (data) {
	        	addTr(data["searchData"]);	        	
	        }
	    });
		
		
	});	
	
	function addTr(data){
		$("#conditionTable tr:gt(0)").remove();
		if(data.length>0){
			var tabTr="<tr style='background:#F9F9F9;font: bold 11pt/14pt Times, serif ; color:#7C7570;'>";
			tabTr+="<td rowspan='"+data.length+"' style='vertical-align: middle;'>"+data[0].index_name+"</td>";
			for(var i=0;i<data.length;i++){
				if(i!=0) tabTr="<tr style='background:#F9F9F9;font: bold 11pt/14pt Times, serif ; color:#7C7570;'>";
				
				//tabTr+="<td>"+data[i].index_name+"</td>";
				tabTr+="<td>"+data[i].driver_id+"</td>";
				tabTr+="<td>"+data[i].data_1+"</td>";
				tabTr+="<td>"+data[i].data_2+"</td>";
				tabTr+="<td>"+data[i].data_3+"</td>";			
				tabTr+="</tr>";
				$("#conditionTable").append(tabTr);
			}
		}
		
		$("#updataConditionModal").modal();
		
	}
	
}