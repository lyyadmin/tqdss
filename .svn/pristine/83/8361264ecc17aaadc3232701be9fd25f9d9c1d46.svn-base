;;(function(){
	
	'use strict'
	/**
	 * example:04450152
	 $tenly.init('canvas','graphDivId',path+'/images/checkout_img.png',data);
	 $tenly.setItemWarn([0,2,4,6,8,10,12,14]);
	 $tenly.isShowAll(true);
	 $tenly.setItemClick(function(item){
		 console.log(item);
	 });
	 */
	var img = new Image();
	var number = 1;
	var can = undefined;
	var ctx = undefined;
	var cs = 20;
	var ls = [130,8];
	var mchat = [48,72];
	var mbtn = [31,32];
	var mcbtn = [26,45];
	var imgPath = '';
	var orangeLine = new item(0,26,131,11,ls[0],ls[1],"#FD8603");
	var red = new item(0,0,25,26,cs,cs,"#d73300");
	var redLine = new item(0,26,131,11,ls[0],ls[1],"#d73300");
	var blue = new item(25,0,26,26,cs,cs,"#2aa6e5");
	var blueLine = new item(0,48,114,11,ls[0],ls[1],"#2aa6e5");
	var yellow = new item(51,0,26,26,cs,cs,"#edf317");
	var yellowLine = new item(0,140,114,11,ls[0],ls[1],"#edf317");
    var green = new item(51,0,26,26,cs,cs,"#24c12e");
    var greenLine = new item(0,140,114,11,ls[0],ls[1],"#24c12e");
	var gray = new item(77,0,25,25,cs,cs,"#6b6b6b");
	var grayLine = new item(0,151,125,11,ls[0],ls[1],"#6b6b6b");
	var dashLine = new item(0,59,82,12,ls[0],ls[1],"#c5d8f5",ls[0]);
	var dashLineV = new item(168,0,12,82,ls[0],ls[1],"#c5d8f5");
	var chatbg = new item(0,72,48,60,mchat[0],mchat[1],"#FFFFFF");
	var btnNoml = new item(48,72,31,32,mbtn[0],mbtn[1],"#FFFFFF");
	var btnFocs = new item(48,104,31,32,mbtn[0],mbtn[1],"#FFFFFF");
	var cbtnNoml = new item(79,72,26,45,mcbtn[0],mcbtn[1],"#FFFFFF");
	var cbtnFocs = new item(105,72,26,45,mcbtn[0],mcbtn[1,"#FFFFFF"]);
	var w = 0;
	var sumW = 0;
	var cols = 0;
	var rows = 1;
	var padding = 20;
	var padtop = 80;
	var padbottom = 30;
	var rowHeight = 150;
	var da = [];
	var datas = [];
	var rect = [];
	var rectL = [];
	var msgs = [];
	var selectId = -1;
	var selectMsg = -1;
	var selectItem = [];
	var lineSelect = -1;
	var timer = undefined;
	var delay = 2000;
	var domId = '';
	var pId = '';
	var showAll = true;
	var left = 0,top = 0;
	var pLeft = 0;
	var mLeft = 0,mTop = 0;
	var ptop = 150;
	var paddingLR = 88;
	var nextX = paddingLR;
	var nextY = ptop;
	var currentRow = 1;
	var dir = 0;
	var objs = [];
	var dis = 0;
	var last = 0;
	var lefttop = 0;
	var dashleftdis = 0;
	var loss = 2;
	var lossD = ls[0]/loss;
	var lossW = lossD/2;
	var circlelist = [];
	var divstr = "";
	var updateDatas = [];
	var msgText = "点击选择数据缺失原因";

	function clear(){;
		sumW = 0;
		cols = 0;
		rows = 1;
		da = [];
		datas = [];
		rect = [];
		rectL = [];
		msgs = [];
		selectId = -1;
		selectMsg = -1;
		selectItem = [];
		lineSelect = -1;
		ptop = 150;
		nextX = paddingLR;
		nextY = ptop;
		currentRow = 1;
		dir = 0;
		objs = [];
		dis = 0;
		last = 0;
		lefttop = 0;
		dashleftdis = 0;
		circlelist = [];
		var ld = document.getElementById("lossdiv");
		if(ld){
			$(ld).html("");
		}
	}
	var obj = function(){
	}
	obj.prototype.init = function(canvasId,parentId,texturePath,data){
		imgPath = texturePath+'/images/';
		img.src = texturePath+'/images/checkout_texture.png';
		domId = canvasId;
		pId = parentId;
		can = document.getElementById(canvasId);
		$("#"+parentId).css({"margin":"0"});
		w = $("#"+parentId).width();
		can.width = w;
		ctx = can.getContext('2d');
		left = can.getBoundingClientRect().left;
		top = can.getBoundingClientRect().top;
		pLeft = getInt($('#'+pId).css('padding-left'));
		divstr = '<div id="trainInfo" style="display:none;position:absolute;left:0px;top:0px;background:rgba(0,0,0,0.7);color:#FFF;text-align:left;padding:5px;border-radius:4px;font-size:14px;font-weight:normal;z-index:100;">'
			+'<div style="display:table-row;"><span style="display:table-cell;width:60px;">车次</span><span style="display:table-cell;">：</span><span id="checi" style="display:table-cell;text-align:left;width:150px;"></span></div>'
			+'<div style="display:table-row;"><span style="display:table-cell;">始发站</span><span style="display:table-cell;">：</span><span id="sfz" style="display:table-cell;text-align:left;"></span></div>'
			+'<div style="display:table-row;"><span style="display:table-cell;">终到站</span><span style="display:table-cell;">：</span><span id="zdz" style="display:table-cell;text-align:left;"></span></div>'
			+'<div style="display:table-row;"><span style="display:table-cell;">文件开始时间</span><span style="display:table-cell;">：</span><span id="sfsj" style="display:table-cell;text-align:left;"></span></div>'
			+'<div style="display:table-row;"><span style="display:table-cell;">文件结束时间</span><span style="display:table-cell;">：</span><span id="zdsj" style="display:table-cell;text-align:left;"></span></div>'
//			+'<div style="display:none;"><span style="display:table-cell;">详细描述</span><span style="display:table-cell;">：</span><span id="info" style="display:table-cell;text-align:left;"></span></div>'
			+'</div>';
		$("#"+parentId).append('<div style="display:block;position:absolute;width:100%;height:100%;left:0px;top:0px;" id="lossdiv"></div>');
		$("#"+parentId).append(divstr);
			mLeft = $('#lossdiv').offset().top; 
			mTop = $('#lossdiv').offset().left; 
		if(data){
			addDatas(data);
			number = da.length;
			cols = parseInt((w-cs-padding*2)/(cs+ls[0]));
			padding = (w-cs-cols*(cs+ls[0]))/2;
			rows = parseInt(number/(cols+1));
			if(number%(cols+1)!==0){
				rows++;
			}
			sumW = getSise(number)[0];
			can.height = rows * rowHeight+padtop+padbottom;
			cols++;
			if(number<cols){
				padding = (w-number*(cs+ls[0])+ls[0])/2;
			}
//			timer = setInterval(mAnimal,delay);
			cols = 7;
		}
		$("#lossdiv").on('mousemove',function(e){
			mMove(e);
		});
//		$("#"+canvasId).on('mousedown',function(e){
//			mDown(e);
//		});
	}
    obj.prototype.resize = function(){
        w = $("#"+pId).width();
        can.width = w;
        da = [];
        if(updateDatas){
            addDatas(updateDatas);
        }
        number = da.length;
        cols = parseInt((w-cs)/(cs+ls[0]));
        padding = (w-cs-cols*(cs+ls[0]))/2;
        rows = parseInt(number/(cols+1));
        if(number%(cols+1)!==0){
            rows++;
        }
        sumW = getSise(number)[0];
        can.height = rows * rowHeight+10+padbottom;
        cols++;
        if(number<cols){
            padding = (w-number*(cs+ls[0])+ls[0])/2;
        }
        w = $("#"+pId).width();
        can.width = w;
        refresh();
	}
	obj.prototype.render = function(){
		drawBg("#fefefe");
//		debug(100);
		drawLabel('center',50);
		drawContent();
	}

	obj.prototype.update = function(data){
        updateDatas = data;
		da = [];
		if(data){
			addDatas(data);
		}
		number = da.length;
		cols = parseInt((w-cs)/(cs+ls[0]));
		padding = (w-cs-cols*(cs+ls[0]))/2;
		rows = parseInt(number/(cols+1));
		if(number%(cols+1)!==0){
			rows++;
		}
		sumW = getSise(number)[0];
		can.height = rows * rowHeight+10+padbottom;
		cols++;
		if(number<cols){
			padding = (w-number*(cs+ls[0])+ls[0])/2;
		}
        w = $("#"+pId).width();
        can.width = w;
		refresh();
	}
	obj.prototype.setItemWarn = function(index){
		selectItem = [];
		lineSelect = [];
		if(typeof index === 'object'){
			selectItem = index;
		}else if(typeof index === 'number'){
			selectItem.push(index);
		}
		refresh();
	}
	obj.prototype.setItemClick = function(callback){
		way.callback = callback;
		$("#lossdiv").on('click',function(e){
			mClick(e,callback);
		});
	}
	obj.prototype.isShowAll = function(b){
		showAll = b;
	}
	/**
	 * 处理当前司机的始发终到
	 * 约定：
	 * 运安和lkj数据匹配    判断缺失显示颜色归类
	 * 1、运安存在  但是lkj不存在  代表数据缺失
	 * 2、如果运安不存在  lkj存在  也是数据完整
	 * 3、如果都存在             代表数据完整
	 * yun_an_isnotexits 、lkj_isnotexits
	 * 0  缺失
	 * 1  存在
	 * 
	 * 
	 * 疑似缺失数据
	 * 如果缺失 用橙色标识   后端判断逻辑为，从乘务员出勤到退勤的地点不一致  说明疑似缺失数据
	 * is_exists_suspicious
	 * 0缺失
	 * 1不缺失
	 * 
	 * 
	 * 前端交路图颜色规约
	 * 1  红色
	 * 2  黄色
	 * 3  灰色
	 * 4  橙色
	 * 0  绿色
	 */
	function getDatas(odata){
		var usedata = [];
		for(var i=0;i<odata.length;i++){
			var it = odata[i];
			it.index = i;
			if(it.yun_an_isnotexits=="1"&&it.lkj_isnotexits=="1"){
                //不缺失
                it.tag = 0;
			}else if(it.yun_an_isnotexits=="0"&&it.lkj_isnotexits=="1"){
                it.tag = 0;
			}else{
                it.tag = 1;
			}
			usedata.push(it);
		}
		return usedata;
	}

	function addDatas(odata){
		clear();
		var usedata = getDatas(odata);
		var n = 0;
		for(var i=0;i<usedata.length;i++){
			var it = usedata[i];
			var io = saveDatas(it,i);
			if(io){
				objs.push(io);
			}
			if(i!=odata.length-1){
				saveDashLine(usedata[i+1],i+1);
			}
			
			da.push({
				index:n,
				type:0,
				time:it.shifashijian,
				address:it.shifazhan,
				datatype:it.tag,
				sjh:it.sjh,
				drivertime:it.drivertime,
				traincheci:'('+it.traincheci+')'
			});
			da.push({
				index:n,
				type:1,
				time:it.zhongdaoshijian,
				address:it.zhongdaozhan,
				datatype:it.tag
			});
			datas.push(it);
			n++;
		}
	}
	function getSise(num){
		var ret = [0,0];
		if(num===1){
			ret[0] = cs;
			ret[1] = rowHeight;
		}else{
			ret[0] = cs+(cs+ls[0])*(num%cols);
			ret[1] = rowHeight*(num/cols);
		}
		return ret;
	}
	function item(x,y,w,h,pw,ph,color,zw){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.pw = pw;
		this.ph = ph;
		this.color = color;
		if(zw){
			this.w = zw;
		}
		this.setLineWidth = function(lw){
			this.pw = lw;
		}
	}
	function itemObj(x,y,type,info){
		this.x = x;
		this.y = y;
		this.w = 0;
		this.h = 0;
		this.type = type;
		this.info = info;
		this.circle = undefined;
		this.line = undefined;
		this.lineWidth = 0;
		this.path = [];
		this.direction = 0;
		this.tag = "";
		this.isBule = true;
		this.init = function(){
			if(type===-1){
				this.line = dashLine;
			}else if(type===1){//运安中有，而我们没有为文件丢失@红色
				this.circle = red;
				this.line = redLine;
			}else if(type===11){//数据丢失@蓝色(红色)
				this.circle = blue;
				this.line = blueLine;
			}else if(type===2){//中途换乘@黄色
				this.circle = yellow;
				this.line = yellowLine;
			}
			else if(type===3){//始发终到运安数据库中没有，我们有@灰色
				this.circle = gray;
				this.line = grayLine;
			}else if(type===4){//疑似数据丢失@橙色
				this.circle = blue;
				this.line = orangeLine;
			}else if(type===6){//运安存在@绿色
                this.circle = green;
                this.line = greenLine;
            }else{//正常@蓝色
				this.circle = blue;
				this.line = blueLine;
			}
			if(this.isBule && type!==-1){
				this.circle = blue;
			}
			if(type===-1){
				this.w = this.line.pw;
				this.h = this.line.ph;
			}else{
				this.w = this.circle.pw*2+this.line.pw;
				this.h = this.circle.ph;
			}
		}
		this.getRect = function(){
			return {x:this.x,y:this.y,w:this.w,h:this.h};
		}
		this.contains = function(x,y){
			return x>=this.x && x<=this.x+this.w && y>=this.y && y<=this.y+this.h;
		}
		this.getInfo = function(){
			return this.info;
		}
		this.getLine = function(){
			return this.line;
		}
		this.setLineWidth = function(lw){
			this.lineWidth = lw;
			if(this.circle){
				this.w = this.circle.pw*2+this.lineWidth;
			}else{
				this.w = this.lineWidth;
			}
		}
		this.draw = function(){
			var circle = this.circle;
			var line = this.line;
			var lineY = (cs-line.ph)/2;
			if(circle){
				circle.draw(this.x,this.y,circle.pw,circle.ph,"circle",info,info);
				line.drawColor(this.x+circle.pw,this.y+lineY,this.lineWidth,line.ph)
				circle.draw(this.x+circle.pw+this.lineWidth,this.y,circle.pw,circle.ph,"circle",info);
			}else{
				line.draw(this.x,this.y+lineY,line.pw,line.ph)
			}
		}
		this.drawSelf = function(){
			var circle = this.circle;
			var line = this.line;
			var lineY = (cs-line.ph)/2;
			var path = this.path;
			var startTimeXY = [0,0];
			var endTimeXY = [0,0];
			if(circle){
				if(this.tag==="clleft"){
					this.direction = 0;
					if(path.length==1){
						line.drawColor(path[0][2],path[0][0],line.ph,path[0][1]-path[0][0]-cs);
						circle.draw(path[0][2]-lineY,path[0][0],circle.pw,circle.ph,"circle",info);
						circle.draw(path[0][2]-lineY,path[0][1]-cs,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[0][2]-lineY,path[0][0]];
						endTimeXY = [path[0][2]-lineY,path[0][1]-cs];
						if(this.type===11){
							var jv = (lossD-lossW)/2;
							var lleft = path[0][1]-path[0][0]-cs*2;
							var lnum = parseInt(lleft/lossD);
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n = 0;
							for(var i=0;i<lnum;i++){
								lx = path[0][2];
								ly = path[0][0]+circle.pw+jv+(jv*2+lossW)*i;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
						}
					}else if(path.length==2){
						line.drawColor(path[1][0],path[0][0],line.ph,path[0][1]-path[0][0]);
						line.drawColor(path[1][0],path[0][1],path[1][1]-path[1][0]-cs,line.ph);
						circle.draw(path[1][0]-lineY,path[0][0]-cs,circle.pw,circle.ph,"circle",info);
						circle.draw(path[1][1]-cs,path[0][1]-lineY,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[1][0]-lineY,path[0][0]-cs];
						endTimeXY = [path[1][1]-cs,path[0][1]-lineY];
						if(this.type==11){//left-bottom
							var lleft = path[0][1]-path[0][0];
							var lbottom = path[1][1]-path[1][0]-cs;
							var jv = (lossD-lossW)/2;
							var mod = lleft%lossD;
							var lnum = parseInt(lleft/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n = 0;
							var add = false;
							var hide = false;
							//*******************left*******************
							for(var i=0;i<lnum;i++){
								lx = path[1][0];
								ly = path[0][0]+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							if(mod>jv){
								lx = path[1][0];
								if(mod>=jv+lossW){
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								if(ly==(jv*2+lossW)){
									ly = path[0][0]+jv;
								}
								add = true;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							//*******************bottom*******************
							if(ltou>=jv+lossW){
								lx = path[1][0]+(jv-mod);
								ly = path[0][1];
								lw = lossW;
								if(!add){
									n++
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[1][0];
									ly = path[0][1];
									lw = ltou-jv;
									if(!add){
										n++
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								ly = path[0][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
						}
					}
				}else if(this.tag==="leftbottomright"){
					this.direction = 0;
					if(path.length==3){
						line.drawColor(path[1][0],path[0][0],line.ph,path[0][1]-path[0][0]);
						line.drawColor(path[1][0],path[0][1],path[1][1]-path[1][0],line.ph);
						line.drawColor(path[1][1]-ls[1],path[2][0],line.ph,path[2][1]-path[2][0]-cs);
						circle.draw(path[1][0]-lineY,path[0][0]-cs,circle.pw,circle.ph,"circle",info);
						circle.draw(path[1][1]-ls[1]-lineY,path[2][1]-cs,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[1][0]-lineY,path[0][0]-cs];
						endTimeXY = [path[1][1]-ls[1]-lineY,path[2][1]-cs];
						if(this.type==11){//left-bottom
							var lleft = path[0][1]-path[0][0];
							var lbottom = path[1][1]-path[1][0];
							var lright = path[2][1]-path[2][0];
							var jv = (lossD-lossW)/2;
							var mod = lleft%lossD;
							var lnum = parseInt(lleft/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n = 0;
							var add = false;
							//*******************left*******************
							for(var i=0;i<lnum;i++){
								lx = path[1][0];
								ly = path[0][0]+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							if(mod>jv){
								lx = path[1][0];
								if(mod>=jv+lossW){
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								if(ly==(jv*2+lossW)){
									ly = path[0][0]+jv;
								}
								n++;
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								add = true;
							}
							//*******************bottom*******************
							if(ltou>=jv+lossW){
								lx = path[1][0]+(jv-mod);
								ly = path[0][1];
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[1][0];
									ly = path[0][1];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							mod = lbottom%lossD;
							for(var i=0;i<lnum;i++){
								lx = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								ly = path[0][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = lx+(jv*2+lossW);
								}else{
									lx = lx+(jv*2+lossW);
									lw = mod-jv;
								}
								if(lx===(jv*2+lossW)){
									lx = path[0][0]+jv;
								}
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								add = true;
							}
							//*******************right*******************
							ltou = lossD-mod;
							if(ltou>jv+lossW){
								lx = path[1][1]-ls[1];
								ly = path[2][0]+(jv-mod);
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[1][1]-ls[1];
									ly = path[2][0];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								}
							}
							lright = lright-ltou;
							lnum = parseInt(lright/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[1][1]-ls[1];
								ly = path[2][0]+ltou+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
						}
					}
				}else if(this.tag==="cllefttop"){
					this.direction = 0;
					if(path.length==2){
						line.drawColor(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0]-cs,line.ph);
						line.drawColor(path[0][0],path[1][0],line.ph,path[1][1]-path[1][0]-cs);
						circle.draw(path[0][1]-cs,path[1][0]-ls[1]-lineY,circle.pw,circle.ph,"circle",info);
						circle.draw(path[0][0]-lineY,path[1][1]-cs,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[0][1]-cs,path[1][0]-ls[1]-lineY];
						endTimeXY = [path[0][0]-lineY,path[1][1]-cs];
						if(this.type===11){
							var ltop = path[0][1]-path[0][0]-cs;
							var lleft = path[1][1]-path[1][0];
							var jv = (lossD-lossW)/2;
							var mod = ltop%lossD;
							var lnum = parseInt(ltop/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n = 0;
							var add = false;
							//*******************top*******************
							for(var i=0;i<lnum;i++){
								lx = path[0][1]-cs-(jv*2+lossW)*(i+1)+jv;
								ly = path[1][0]-ls[1];
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = lx-(jv*2+lossW);
									ly = path[1][0]-ls[1]
									if(lx==-(jv*2+lossW)){
										lx = path[0][0];
									}
								}else{
									lx = path[0][0];
									ly = path[1][0]-ls[1]
									lw = mod-jv;
								}
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								add = true;
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							//*******************left*******************
							if(ltou>=jv+lossW){
								lx = path[0][0];
								ly = path[1][0]+(jv-mod);
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[0][0];
									ly = path[1][0];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								}
							}
							lleft = lleft-ltou;
							mod = lleft%lossD;
							lnum = parseInt(lleft/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[0][0];
								ly = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
						}
					}
				}else if(this.tag==="clright"){
					this.direction = 0;
					if(path.length===2){
						line.drawColor(path[1][1]-ls[1],path[0][0],line.ph,path[0][1]-path[0][0]);
						line.drawColor(path[1][0]+cs,path[0][1],path[1][1]-path[1][0]-cs,line.ph);
						circle.draw(path[1][1]-ls[1]-lineY,path[0][0]-cs,circle.pw,circle.ph,"circle",info);
						circle.draw(path[1][0],path[0][1]-lineY,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[1][1]-ls[1]-lineY,path[0][0]-cs];
						endTimeXY = [path[1][0],path[0][1]-lineY];
						if(this.type===11){
							var jv = (lossD-lossW)/2;
							var lright = path[0][1]-path[0][0];
							var lbottom = path[1][1]-path[1][0]-cs;
							var jv = (lossD-lossW)/2;
							var mod = lright%lossD;
							var lnum = parseInt(lright/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var add = false;
							var n = 0;
							//*******************right*******************
							for(var i=0;i<lnum;i++){
								lx = path[1][1]-ls[1];
								ly = path[0][0]+jv+(jv*2+lossW)*i;
								lw = lossW;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								n++;
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								if(ly==(jv*2+lossW)){
									ly = path[0][0]+jv;
								}
								lx = path[1][1]-ls[1];
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								add = true;
							}
//							//*******************bottom*******************
							if(ltou>=jv+lossW){
								lx = path[1][1]-(jv-mod)-lossW;
								ly = path[0][1];
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[1][1]-(ltou-jv);
									ly = path[0][1];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[1][1]-ltou+jv-(jv*2+lossW)*(i+1);
								ly = path[0][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
						}
					}
				}else if(this.tag==="rightbottomleft"){
					this.direction = 0;
					if(path.length==3){
						line.drawColor(path[1][1]-ls[1],path[0][0],line.ph,path[0][1]-path[0][0]);
						line.drawColor(path[1][0],path[0][1],path[1][1]-path[1][0],line.ph);
						line.drawColor(path[1][0],path[2][0],line.ph,path[2][1]-path[2][0]-cs);
						circle.draw(path[1][1]-ls[1]-lineY,path[0][0]-cs,circle.pw,circle.ph,"circle",info);
						circle.draw(path[1][0]-lineY,path[2][1]-cs,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[1][1]-ls[1]-lineY,path[0][0]-cs];
						endTimeXY = [path[1][0]-lineY,path[2][1]-cs];
						if(this.type===11){
							var jv = (lossD-lossW)/2;
							var lright = path[0][1]-path[0][0];
							var lbottom = path[1][1]-path[1][0];
							var lleft = path[2][1]-path[2][0];
							var jv = (lossD-lossW)/2;
							var mod = lright%lossD;
							var lnum = parseInt(lright/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n=0;
							var add = false;
							//*******************right*******************
							for(var i=0;i<lnum;i++){
								lx = path[1][1]-ls[1];
								ly = path[0][0]+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = path[1][1]-ls[1];
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									lx = path[1][1]-ls[1];
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								if(ly==(jv*2+lossW)){
									ly = path[0][0]+jv;
								}
								n++;
								add = true;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							//*******************bottom*******************
							if(ltou>=jv+lossW){
								lx = path[1][1]-(jv-mod)-lossW;
								ly = path[0][1];
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[1][1]-(ltou-jv);
									ly = path[0][1];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							mod = lbottom%lossD;
							lx = 0;
							for(var i=0;i<lnum;i++){
								lx = path[1][1]-ltou+jv-(jv*2+lossW)*(i+1);
								ly = path[0][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = path[1][0];
									ly = path[0][1];
									lw = lossW;
								}else{
									lx = path[1][0];
									ly = path[0][1];
									lw = mod-jv;
								}
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								add = true;
							}
							//*******************right*******************
							ltou = lossD-mod;
							if(ltou>jv){
								if(ltou>=jv+lossW){
									lx = path[1][0];
									ly = path[2][0]+(jv-mod);
									lw = lossW;
								}else{
									lx = path[1][0];
									ly = path[2][0];
									lw = ltou-jv;
								}
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							lleft = lleft-ltou;
							lnum = parseInt(lleft/lossD);
							mod = lleft%lossD;
							ltou = lossD-mod;
							for(var i=0;i<lnum;i++){
								lx = path[1][0];
								ly = path[2][0]+ltou-jv+(jv*2+lossW)*i;;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
						}
					}
				}else if(this.tag==="topleftbottom"){
					this.direction = 0;
					if(path.length==3){
						line.drawColor(path[0][0]-ls[1],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph);
						line.drawColor(path[0][0]-ls[1],path[1][0],line.ph,path[1][1]-path[1][0]);
						line.drawColor(path[0][0]-ls[1],path[1][1],path[2][1]-path[2][0],line.ph);
						circle.draw(path[0][1]-cs,path[1][0]-ls[1]-lineY,circle.pw,circle.ph,"circle",info);
						circle.draw(path[2][1]-cs,path[1][1]-lineY,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[0][1]-cs,path[1][0]-ls[1]-lineY];
						endTimeXY = [path[2][1]-cs,path[1][1]-lineY];
						if(this.type===11){
							var ltop = path[0][1]-path[0][0]-cs;
							var lleft = path[1][1]-path[1][0];
							var lbottom = path[2][1]-path[2][0]-cs;
							var jv = (lossD-lossW)/2;
							var mod = ltop%lossD;
							var lnum = parseInt(ltop/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = 0;
							var lw = lossW;
							var n = 0;
							var add = false;
							//*******************top*******************
							for(var i=0;i<lnum;i++){
								lx = path[0][1]-cs-(jv*2+lossW)*(i+1)+jv;
								ly = path[1][0]-ls[1];
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								n++;
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = lx-(jv*2+lossW);
									ly = path[1][0]-ls[1]
									if(lx==-(jv*2+lossW)){
										lx = path[0][0]-ls[1];
									}
								}else{
									lx = path[0][0]-ls[1];
									ly = path[1][0]-ls[1]
									lw = mod-jv;
								}
								n++;
								add = true;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							//*******************left*******************
							if(ltou>jv+lossW){
								lx = path[0][0]-ls[1];
								ly = path[1][0]+(jv-mod);
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[0][0]-ls[1];
									ly = path[1][0];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								}
							}
							lleft = lleft-ltou;
							mod = lleft%lossD;
							lnum = parseInt(lleft/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[0][0]-ls[1];
								ly = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								n++;
								add = true;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							//*******************bottom*******************
							ltou = lossD-mod;
							if(ltou>=jv+lossW){
								lx = path[0][0]-ls[1]+(jv-mod);
								ly = path[1][1];
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[0][0]-ls[1];
									ly = path[1][1];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[0][0]-ls[1]+ltou+jv+(jv*2+lossW)*i;
								ly = path[1][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
						}
					}
				}else if(path.length==0){
					line.drawColor(this.x+circle.pw,this.y+lineY,this.lineWidth,line.ph)
					circle.draw(this.x,this.y,circle.pw,circle.ph,"circle",info);
					circle.draw(this.x+circle.pw+this.lineWidth,this.y,circle.pw,circle.ph,"circle",info);
					startTimeXY = [this.x,this.y];
					endTimeXY = [this.x+circle.pw+this.lineWidth,this.y];
					if(this.type===11){
						var jv = (lossD-lossW)/2;
						var ltop = this.lineWidth;
						var lnum = parseInt(ltop/lossD);
						var lx = 0;
						var ly = 0;
						var lw = lossW;
						var n = 0;
						for(var i=0;i<lnum;i++){
							lx = this.x+circle.pw+jv+(jv*2+lossW)*i;
							ly = this.y+lineY;
							n++;
							if(n>this.info.dataloss){
								hide = true;
							}
							lossRect(lx,ly,lw,line.ph,this.info,false,hide);
						}
					}
				}else if(this.tag==="clrighttop"){
					this.direction = 0;
					if(path.length===2){
						line.drawColor(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph);
						line.drawColor(path[0][1]-ls[1],path[1][0]-ls[1],line.ph,path[1][1]-path[1][0]);
						circle.draw(path[0][0]-cs,path[1][0]-ls[1]-lineY,circle.pw,circle.ph,"circle",info);
						circle.draw(path[0][1]-ls[1]-lineY,path[1][1]-cs,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[0][0]-cs,path[1][0]-ls[1]-lineY];
						endTimeXY = [path[0][1]-ls[1]-lineY,path[1][1]-cs];
						if(this.type==11){
							var jv = (lossD-lossW)/2;
							var ltop = path[0][1]-path[0][0];
							var lright = path[1][1]-path[1][0]-cs;
							var jv = (lossD-lossW)/2;
							var mod = ltop%lossD;
							var lnum = parseInt(ltop/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = path[1][0]-ls[1];
							var lw = lossW;
							var n = 0;
							var add = false;
							//*******************top*******************
							for(var i=0;i<lnum;i++){
								lx = path[0][0]+jv+(jv*2+lossW)*i;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = lx+(jv*2+lossW);
								}else{
									lx = lx+(jv*2+lossW);
									lw = mod-jv;
								}
								if(lx===(jv*2+lossW)){
									lx = path[0][0]+jv;
								}
								n++;
								add = true;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							//*******************right*******************
							if(ltou>jv+lossW){
								lx = path[0][1]-ls[1];
								ly = path[1][0]+(jv-mod);
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[0][1]-ls[1];
									ly = path[1][0];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								}
							}
							lright = lright-ltou;
							lnum = parseInt(lright/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[0][1]-ls[1];
								ly = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
						}
					}else if(path.length===3){
						line.drawColor(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph);
						line.drawColor(path[0][1]-ls[1],path[1][0],line.ph,path[1][1]-path[1][0]);
						line.drawColor(path[2][0]+cs,path[1][1],path[2][1]-path[2][0]-cs,line.ph);
						circle.draw(path[0][0]-cs,path[1][0]-ls[1]-lineY,circle.pw,circle.ph,"circle",info);
						circle.draw(path[2][0],path[1][1]-lineY,circle.pw,circle.ph,"circle",info);
						startTimeXY = [path[0][0]-cs,path[1][0]-ls[1]-lineY];
						endTimeXY = [path[2][0],path[1][1]-lineY];
						if(this.type===11){
							var jv = (lossD-lossW)/2;
							var ltop = path[0][1]-path[0][0];
							var lright = path[1][1]-path[1][0];
							var lbottom = path[2][1]-path[2][0]-cs;
							var jv = (lossD-lossW)/2;
							var mod = ltop%lossD;
							var lnum = parseInt(ltop/lossD);
							var ltou = lossD-mod;
							var lx = 0;
							var ly = path[1][0]-ls[1];
							var lw = lossW;
							var n = 0;
							var add = false;
							//*******************top*******************
							for(var i=0;i<lnum;i++){
								lx = path[0][0]+jv+(jv*2+lossW)*i;
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								n++;
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									lx = lx+(jv*2+lossW);
								}else{
									lx = lx+(jv*2+lossW);
									lw = mod-jv;
								}
								if(lx===(jv*2+lossW)){
									lx = path[0][0]+jv;
								}
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								add = true;
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
							//*******************right*******************
							if(ltou>jv+lossW){
								lx = path[0][1]-ls[1];
								ly = path[1][0]+(jv-mod);
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[0][1]-ls[1];
									ly = path[1][0];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,line.ph,lw,this.info,false,hide);
								}
							}
							lright = lright-ltou;
							mod = lright%lossD;
							lnum = parseInt(lright/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[0][1]-ls[1];
								ly = path[1][0]+ltou+jv+(jv*2+lossW)*i;
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							if(mod>jv){
								if(mod>=jv+lossW){
									ly = ly+(jv*2+lossW);
									lw = lossW;
								}else{
									ly = ly+(jv*2+lossW);
									lw = mod-jv;
								}
								n++;
								add = true;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,line.ph,lw,this.info,false,hide);
							}
							//*******************bottom*******************
							ltou = lossD-mod;
							if(ltou>=jv+lossW){
								lx = path[0][1]-ls[1]-(jv-mod)-lossW;
								ly = path[1][1];
								lw = lossW;
								if(!add){
									n++;
								}else{
									add = false;
								}
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}else{
								if(ltou>jv){
									lx = path[2][1]-(ltou-jv);
									ly = path[1][1];
									lw = ltou-jv;
									if(!add){
										n++;
									}else{
										add = false;
									}
									if(n>this.info.dataloss){
										hide = true;
									}
									lossRect(lx,ly,lw,line.ph,this.info,false,hide);
								}
							}
							lbottom = lbottom-ltou;
							lnum = parseInt(lbottom/lossD);
							for(var i=0;i<lnum;i++){
								lx = path[2][1]-ltou+jv-(jv*2+lossW)*(i+1);
								ly = path[1][1];
								lw = lossW;
								n++;
								if(n>this.info.dataloss){
									hide = true;
								}
								lossRect(lx,ly,lw,line.ph,this.info,false,hide);
							}
						}
					}
				}else if(path.length==3){
					this.direction = 0;
					line.drawColor(this.x+circle.pw,this.y+lineY,path[0][1]-path[0][0],line.ph);
					line.drawColor(path[0][1]-ls[1],this.y+lineY,line.ph,path[1][1]-path[1][0]);
					line.drawColor(path[2][0],path[1][1]-lineY,path[2][1]-path[2][0],line.ph);
					circle.draw(this.x,this.y,circle.pw,circle.ph,"circle",info);
					circle.draw(path[2][0],path[1][1]-cs,circle.pw,circle.ph,"circle",info);
					startTimeXY = [this.x,this.y];
					endTimeXY = [path[2][0],path[1][1]-cs];
				}
				circlelist.push({
					s:startTimeXY,
					e:endTimeXY,
					info:this.info
				});
			}else{
				if(this.tag==="clleft"){
					if(path.length==2){
						line.draw(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph,"dashline");
						dashLineV.draw(path[0][1]-ls[1],path[1][0],line.ph,path[1][1]-path[1][0],"dashline");
					}
				}else if(this.tag==="topleft"){
					if(path.length==2){
						line.draw(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph,"dashline");
						dashLineV.draw(path[0][0],path[1][0],line.ph,path[1][1]-path[1][0],"dashline");
					}
				}else if(this.tag==="clleftbottom"){
					if(path.length==2){
						dashLineV.draw(path[1][0],path[0][0],line.ph,path[0][1]-path[0][0],"dashline");
						line.draw(path[1][0],path[0][1],path[1][1]-path[1][0],line.ph,"dashline");
					}
				}else if(this.tag==="clright"){
					if(path.length==2){
						dashLineV.draw(path[1][1],path[0][0]+lineY+ls[1],line.ph,path[0][1]-path[0][0],"dashline");
						line.draw(path[1][0],path[0][1]+lineY,path[1][1]-path[1][0],line.ph,"dashline");
					}
				}else if(this.tag==="right"){
					if(path.length==1){
						dashLineV.draw(can.width-paddingLR-lineY-ls[1],path[0][0],line.ph,path[0][1]-path[0][0],"dashline");
					}
				}else if(path.length==0){
					line.draw(this.x,this.y+lineY,line.pw,line.ph,"dashline")
				}else{
					if(this.direction==1){
						dashLineV.draw(path[1][1],path[0][0]+lineY+ls[1],line.ph,path[0][1]-path[0][0],"dashline");
						line.draw(path[1][0],path[0][1]+lineY,path[1][1]-path[1][0],line.ph,"dashline");
					}else{
						line.draw(path[0][0],path[1][0]-ls[1],path[0][1]-path[0][0],line.ph,"dashline");
						dashLineV.draw(path[0][0],path[1][0],line.ph,path[1][1]-path[1][0],"dashline");
					}
				}
			}
			if(this.info){
				var it = this.info;
				var se = this.direction==0;
				if(se){
					if(it.shifashijian && it.shifashijian!=""){
						var times = it.shifashijian.split(" ");
						mFillText(times[0],startTimeXY[0]+cs/2,startTimeXY[1]-cs,"#666",'normal','center',14);
						mFillText(times[1],startTimeXY[0]+cs/2,startTimeXY[1]-cs+14,"#666",'normal','center',12);
					}
					if(it.shifazhan && it.shifazhan!=""){
						mFillText(it.shifazhan,startTimeXY[0]+cs/2,startTimeXY[1]+cs*2,"#3f5fea",'bold');
					}
					if(it.zhongdaoshijian && it.zhongdaoshijian!=""){
						var times = it.zhongdaoshijian.split(" ");
						mFillText(times[0],endTimeXY[0]+cs/2,endTimeXY[1]-cs,"#666",'normal','center',14);
						mFillText(times[1],endTimeXY[0]+cs/2,endTimeXY[1]-cs+14,"#666",'normal','center',12);
					}
					if(it.zhongdaozhan && it.zhongdaozhan!=""){
						mFillText(it.zhongdaozhan,endTimeXY[0]+cs/2,endTimeXY[1]+cs*2,"#3f5fea",'bold');
					}
				}else{
					if(it.shifashijian && it.shifashijian!=""){
						var times = it.shifashijian.split(" ");
						mFillText(times[0],endTimeXY[0]+cs/2,endTimeXY[1]-cs,"#666",'normal','center',14);
						mFillText(times[1],endTimeXY[0]+cs/2,endTimeXY[1]-cs+14,"#666",'normal','center',12);
					}
					if(it.shifazhan && it.shifazhan!=""){
						mFillText(it.shifazhan,endTimeXY[0]+cs/2,endTimeXY[1]+cs*2,"#3f5fea",'bold');
					}
					if(it.zhongdaoshijian && it.zhongdaoshijian!=""){
						var times = it.zhongdaoshijian.split(" ");
						mFillText(times[0],startTimeXY[0]+cs/2,startTimeXY[1]-cs,"#666",'normal','center',14);
						mFillText(times[1],startTimeXY[0]+cs/2,startTimeXY[1]-cs+14,"#666",'normal','center',12);
					}
					if(it.zhongdaozhan && it.zhongdaozhan!=""){
						mFillText(it.zhongdaozhan,startTimeXY[0]+cs/2,startTimeXY[1]+cs*2,"#3f5fea",'bold');
					}
				}
				var x = 10;
				var y = 10;
				var textalign = 'top';
				if(startTimeXY[0]<endTimeXY[0]){//𠃍𠃊
					if(startTimeXY[1]<endTimeXY[1]){
						if(startTimeXY[0]>can.width/2){
							if(endTimeXY[0]-startTimeXY[0]>this.w/2){
								x = startTimeXY[0]+this.w/2;
								y = startTimeXY[1]+cs*3/2;
							}else{
								x = endTimeXY[0]-cs*2;
								y = startTimeXY[1]+this.w/2;
								textalign = 'left';
							}
						}else{
							if(endTimeXY[0]-startTimeXY[0]>this.w/2){
								x = endTimeXY[0]-this.w/2;
								y = endTimeXY[1]+cs*3/2;
								textalign = 'bottom';
							}else{
								x = startTimeXY[0]+cs*2;
								y = endTimeXY[1]-rowHeight/3;
								textalign = 'right';
							}
						}
					}else if(startTimeXY[1]>endTimeXY[1]){
					}else{
						x = startTimeXY[0]+this.w/2;
						y = startTimeXY[1]+cs*3/2;
					}
				}else if(startTimeXY[0]==endTimeXY[0]){
					if(startTimeXY[0]>can.width/2){
						x = endTimeXY[0]-cs*2;
						y = startTimeXY[1]+this.w/2;
						textalign = 'left';
					}else{
						x = endTimeXY[0]+cs*2;
						y = startTimeXY[1]+rowHeight/2;
						textalign = 'right';
					}
				}else{//𠃎⺁
					if(startTimeXY[0]>endTimeXY[0]){
						if(startTimeXY[0]>can.width/2){
							if(startTimeXY[0]-endTimeXY[0]>this.w/2){
								x = endTimeXY[0]+this.w/2;
								y = endTimeXY[1]+cs*3/2;
								textalign = 'bottom';
							}else{
								x = startTimeXY[0]-cs*2;
								y = endTimeXY[1]-rowHeight/3;
								textalign = 'left';
							}
						}else{
							if(startTimeXY[0]-endTimeXY[0]>this.w/2){
								x = startTimeXY[0]-this.w/2+cs;
								y = startTimeXY[1]+cs*3/2;
							}else{
								x = endTimeXY[0]+cs*2;
								y = startTimeXY[1]+rowHeight/2;
								textalign = 'right';
							}
						}
					}else if(startTimeXY[1]>endTimeXY[1]){
					}else{
						x = endTimeXY[0]+this.w/2;
						y = startTimeXY[1]+cs*3/2;
					}
				}
				if(this.type==1 || this.type==4){
					if(textalign==='right'){
						msgBox(it,x-cs*2,y-cs/2,'#f39800','',0,textalign);
					}else if(textalign==='left'){
						msgBox(it,can.width-paddingLR-ls[1],y-cs/2,'#f39800','',0,textalign);
					}else if(textalign==='bottom'){
						msgBox(it,x,y-cs/2-6,'#f39800','',0,textalign);
					}else{
						msgBox(it,x,y-cs*3/2,'#f39800','',0,textalign);
					}
				}
				if(it.traincheci && it.traincheci!=""){
					mFillText("("+it.traincheci+")",x,y,"#3f5fea",'bold','center');
				}
			}
		}
		this.init();
	}
	function saveDatas(it,i){
		var tag = it.tag;
		var x = nextX;
		var y = nextY;
		var itobj = new itemObj(x,y,tag,it);
		itobj.direction = dir;
		if(it.dataloss){
			itobj.setLineWidth((ls[0]/loss)*it.dataloss);
		}else{
			itobj.setLineWidth(ls[0]);
		}
		if(last===1){
			itobj.x = itobj.x-itobj.w;
			last = 0;
		}
		var clH = (cs-ls[1])/2;
		if(dir==0){
			if(lefttop===1){
				var cha = paddingLR-x+clH;
				if(cha<rowHeight){
					var x0 = paddingLR+clH;
					var y0 = paddingLR+clH+(itobj.w-cha);
					if(y0-x0<cs){
						y0 = x0+cs;
					}
					itobj.path.push([x0,y0]);
					itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+cha]);
					dashleftdis = rowHeight-cha-ls[1]-clH;
					nextY += rowHeight;
					nextX = paddingLR;
					itobj.tag = "cllefttop";
					if(dashleftdis===0){
						dashleftdis = 1;
					}
				}else{
					var x0 = paddingLR+clH;
					var y0 = paddingLR+clH+(itobj.w-cha);
					if(y0-x0<cs){
						y0 = x0+cs;
					}
					itobj.path.push([x0,y0]);
					var x1 = nextY+clH+ls[1];
					var y1 = nextY+rowHeight+clH;
					itobj.path.push([x1,y1]);
					var x2 = paddingLR+clH;
					var y2 = paddingLR+clH+cha-(y1-x1);
					itobj.path.push([x2,y2]);
					itobj.tag = "topleftbottom";
					nextX = y2;
					nextY += rowHeight;
				}
				lefttop = 0;
			}else if(dis!=0){
				if(dis>itobj.w){//current
					dashleftdis = dis-itobj.w;
					itobj.path.push([nextY-dashleftdis-itobj.w,nextY-dashleftdis,paddingLR+clH]);
					itobj.tag = "clleft";
					dis = 0;
				}else{
					itobj.path.push([nextY-dis+cs,nextY+clH]);
					var x1 = paddingLR+clH;
					var x2 = paddingLR+clH+(itobj.w-dis);
					if(itobj.w-dis>can.width-2*paddingLR){
						var yu = itobj.w-dis-(can.width-2*paddingLR);
						x2 = can.width-paddingLR-clH;
						itobj.path.push([x1,x2]);
						itobj.tag = "leftbottomright";
						itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+yu]);
						dis = rowHeight-yu-cs;
						nextX = x2;
						nextY += rowHeight;
						dir = 1;
					}else{
						if(x2-x1<cs){
							x1 = paddingLR+clH;
							x2 = x1+cs
							itobj.path.push([x1,x2]);
							itobj.tag = "clleft";
							nextX = x2;
							dis = 0;
						}else{
							itobj.path.push([x1,x2]);
							itobj.tag = "clleft";
							nextX = x2;
							dis = 0;
						}
					}
				}
			}else{
				nextX = x + itobj.getRect().w;
				if(nextX>can.width-paddingLR){
					var cha = nextX-(can.width-paddingLR-clH);
					dis = rowHeight-clH-ls[1]-cha;
					var x1 = x+cs;
					var x2 = can.width-paddingLR-clH;
					if(x1>x2){
						x1 = can.width-paddingLR-clH;
					}
					itobj.path.push([x1,x2]);
					if(cha>rowHeight){
						var x11 = nextY+clH+ls[1];
						var x12 = nextY+rowHeight+clH;
						itobj.path.push([x11,x12]);
						var y1 = can.width-paddingLR-clH-(cha-(x12-x11));
						var y2 = can.width-paddingLR-clH;
						itobj.path.push([y1,y2]);
						nextX = y1-ls[0];
						if(nextX<paddingLR){
							dir = 0;
							last=1;
						}else{
							dir = 1;
						}
						dis = 0;
					}else{
						itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+cha]);
						nextX = can.width-paddingLR-clH;
						if(dis===0){
							dis=clH;
						}
						dir = 1;
					}
					itobj.tag = "clrighttop";
					nextY += rowHeight;
				}
			}
		}else{
			if(dis!=0){
				if(dis>itobj.w){
					dis = dis-itobj.w;
					itobj.path.push([nextY-dis-itobj.w,nextY-dis,can.width-paddingLR-clH-ls[1]]);
					itobj.tag = "clleft";
				}else{
					itobj.path.push([nextY-dis+cs,nextY+clH]);
					var x1 = can.width-paddingLR-clH-(itobj.w-dis);
					var x2 = can.width-paddingLR-clH;
					if(itobj.w-dis>can.width-2*paddingLR){
						var yu = itobj.w-dis-(can.width-2*paddingLR);
						x1 = paddingLR+clH;
						itobj.path.push([x1,x2]);
						itobj.tag = "rightbottomleft";
						itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+yu]);
						dashleftdis = rowHeight-yu-cs;
						nextX = x1;
						nextY += rowHeight;
						dir = 0;
					}else{
						itobj.path.push([x1,x2]);
						itobj.tag = "clright";
						nextX = x1-ls[0];
					}
					dis=0;
				}
			}else{
				if(itobj.w>can.width-paddingLR*2){
					itobj.path.push([paddingLR+clH+ls[1],nextX]);
					var yu = itobj.w-(nextX-paddingLR-clH);
					var x0 = nextY+clH+ls[1];
					var y0 = x0+yu;
					if(yu>rowHeight){
						dis = yu-rowHeight;
						y0 = nextY+rowHeight+clH;
						itobj.path.push([x0,y0]);
						var x1 = paddingLR+clH+ls[1];
						var y1 = x1+dis+cs;
						itobj.path.push([x1,y1]);
						itobj.tag = "topleftbottom";
						nextX = y1;
						dir = 0;
						dis = 0;
					}else{
						itobj.path.push([x0,y0]);
						nextX = paddingLR;
						itobj.tag = "cllefttop";
						dashleftdis = rowHeight-yu-cs-ls[1];
					}
					nextY += rowHeight;
				}else{
					if(itobj.x<paddingLR){
						itobj.path.push([paddingLR+clH+ls[1],nextX]);
						var yu = itobj.w-(nextX-paddingLR-clH);
						var x0 = nextY+clH+ls[1];
						var y0 = x0+yu;
						if(yu>rowHeight){
							dis = yu-rowHeight;
							y0 = nextY+rowHeight+clH;
							itobj.path.push([x0,y0]);
							var x1 = paddingLR+clH+ls[1];
							var y1 = x1+dis+cs;
							itobj.path.push([x1,y1]);
							itobj.tag = "topleftbottom";
							nextX = y1;
							dir = 0;
							dis = 0;
						}else{
							itobj.path.push([x0,y0]);
							nextX = paddingLR;
							itobj.tag = "cllefttop";
							dashleftdis = rowHeight-yu-cs-ls[1]-clH;
						}
						nextY += rowHeight;
					}else{
						nextX = itobj.x - ls[0];
						if(nextX<paddingLR){
							dir = 0;
							last=1;
						}
					}
				}
			}
		}
		return itobj;
	}
	function saveDashLine(it,i){
		var tag = it.tag
		var x = nextX;
		var y = nextY;
		var itobj = new itemObj(x,y,-1,undefined);
		var nextobj = new itemObj(x,y,tag,it);
		if(it.dataloss){
			nextobj.setLineWidth((ls[0]/loss)*it.dataloss);
		}else{
			nextobj.setLineWidth(ls[0]);
		}
		itobj.direction = dir;
		itobj.setLineWidth(ls[0]);
		var clH = (cs-ls[1])/2;
		if(dir==0){
			if(last===1){
				last = 0;
				var cha = paddingLR-x+clH;
				itobj.path.push([paddingLR+clH,paddingLR+clH+(ls[0]-cha)]);
				itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+cha]);
				dis = rowHeight-cha;
				nextY += rowHeight;
				nextX = paddingLR;
			}else if(dashleftdis!=0){
				itobj.path.push([nextY-dashleftdis,nextY+clH]);
				var x1 = paddingLR+clH;
				var x2 = paddingLR+clH+(ls[0]-dashleftdis);
				itobj.path.push([x1,x2]);
				itobj.tag = "clleftbottom";
				nextX = x2;
				dashleftdis = 0;
			}else{
				nextX = x + itobj.getRect().w;
				if(nextX>can.width-paddingLR-clH){
					var cha = (nextX-can.width+paddingLR+clH);
					itobj.path.push([x,x+(ls[0]-cha)]);
					var x1 = nextY+clH+ls[1];
					var x2 = nextY+clH+ls[1]+cha;
					itobj.path.push([x1,x2]);
					nextX = x+(ls[0]-cha);
					dis = rowHeight-cha;
					nextY += rowHeight;
					dir = 1;
					itobj.tag = "clleft";
				}
			}
		}else{
			if(dis!=0){
				if(dis>ls[0]){
					itobj.path.push([nextY-dis,nextY]);
					itobj.tag = "right";
					dis = dis-ls[0];
					nextX = x1;
				}else{
					var y1 = nextY-clH-ls[1]-dis;
					itobj.path.push([y1,nextY]);
					var x1 = nextX-(ls[0]-dis)-ls[1];
					var x2 = nextX-ls[1];
					itobj.path.push([x1,x2]);
					itobj.tag = "clright";
					dir = 1;
					nextX = x1;
					last = 1;
					dis = 0;
				}
			}else if(dashleftdis!=0){
				itobj.path.push([nextY-dashleftdis-cs,nextY+clH]);
				var x1 = paddingLR+clH+ls[1];
				var x2 = paddingLR+clH+(ls[0]-dashleftdis);
				itobj.path.push([x1,x2]);
				itobj.tag = "clleftbottom";
				nextX = x2;
				dashleftdis = 0;
				dir = 0;
			}else{
				if(nextX<paddingLR){
					var cha = paddingLR-x+clH;
					itobj.path.push([paddingLR+clH,paddingLR+clH+(ls[0]-cha)]);
					itobj.path.push([nextY+clH+ls[1],nextY+clH+ls[1]+cha]);
					dis = rowHeight-cha;
					nextY += rowHeight;
					nextX = paddingLR;
					dir = 0;
					itobj.tag = "topleft";
				}else{
					nextX = x - nextobj.w;
					if(nextX<paddingLR){
						dir = 0;
						lefttop = 1;
					}
				}
			}
		}
		objs.push(itobj);
	}
	item.prototype.drawColor = function(x,y,w,h){
		if(ctx){
			drawColor(this,x,y,w,h);
		}
	}
	item.prototype.draw = function(x,y,w,h,bo,info){
		if(ctx){
			drawImg(this,x,y,w,h,bo,info);
		}
	}
	function drawLabel(x,y){
		if(!datas || datas.length<1){
			return;
		}
		var labelLeft = 0;
		var textSize = 16;
		var lineW = ls[0]/2;
		var textColor = '#666';
		ctx.font = '600 '+textSize+'px 黑体';
		var texts = ['数据丢失','数据完整','疑似丢失'];
		var items = [redLine,blueLine,orangeLine];
		var paddLeft = 20;
		var gLeft = 2;
		var textW = 0;
		for(var i=0;i<texts.length;i++){
			textW+=lineW+gLeft+paddLeft+ctx.measureText(texts[i]).width;
		}
		var gW = textW-paddLeft;
		if(x==='left'){
			labelLeft = 0;
		}else if(x==='right'){
			labelLeft = can.width-gW;
		}else if(x==='center'){
			labelLeft = (can.width-gW)/2;
		}else if(typeof x === 'number'){
			labelLeft = x;
		}
		for(var j=0;j<items.length;j++){
			if(j>0){
				labelLeft+=lineW+gLeft+paddLeft+ctx.measureText(texts[j-1]).width;
			}
			if(texts[j]==='数据丢失'){
				drawLine(labelLeft,y,items[j],texts[j],gLeft,textSize,textColor,lineW,true);
			}else{
				drawLine(labelLeft,y,items[j],texts[j],gLeft,textSize,textColor,lineW);
			}
		}
	}
	function drawContent(){
		if(number===1){
			blue.draw((w-blue.pw)/2,0,blue.pw,blue.ph);
		}else{
			for(var i=0;i<objs.length;i++){
				objs[i].drawSelf();
			}
			return;
			rect = [];
			msgs = [];
			for(var i=0;i<number;i++){
				var it = da[i];
				var r = parseInt(i/cols);
				var c = parseInt(i%cols);
				var circle = blue;
				var line = blueLine;
				line.tag = 'ok';
				if(i===selectId || it.datatype===1){
					circle = red;
				}else if(it.datatype===2){
					circle = yellow;
					line = yellowLine;
				}else if(it.datatype===3){
					circle = gray;
					line = grayLine;
				}
				if(i===lineSelect || (it.datatype===1 && it.type===0)){
					line = redLine;
					line.tag = 'error';
				}
				for(var k=0;k<selectItem.length;k++){
					if(it.index === selectItem[k]){
						circle = red;
					}
					if(i === selectItem[k]*2){
						line = redLine;
						line.tag = 'error';
					}
				}
				if(it.type===1){
					line = dashLine;
				}
				if(r%2===0){
					if(it.time && it.time!=""){
						var times = it.time.split(" ");
						mFillText(times[0],padding+c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop-cs,"#666",'normal','center',14);
						mFillText(times[1],padding+c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop-cs+14,"#666",'normal','center',12);
					}
					circle.draw(padding+c*(blue.pw+blueLine.pw),r*rowHeight+padtop,blue.pw,blue.ph,"circle",info);
					if(it.address && it.address!=""){
						mFillText(it.address,padding+c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop+cs*2,"#3f5fea",'bold');
					}
					
					rect.push({
						index:i,
						po:{
							l:padding+c*(blue.pw+blueLine.pw),
							t:r*rowHeight+padtop,
							r:padding+c*(blue.pw+blueLine.pw)+blue.pw,
							b:r*rowHeight+padtop+blue.ph
						},
						info:it
					});
					if(i===number-1){
					}else if(c===cols-1){
						ctx.save();
						ctx.translate(padding+blue.pw+c*(blue.pw+blueLine.pw)-cs/2,blue.ph/2+r*rowHeight+padtop);
						ctx.rotate(90*Math.PI/180);
						line.draw(cs/2,-blueLine.ph/2,rowHeight-cs,blueLine.ph);
						ctx.restore();
						rectL.push({
							index:i,
							po:{
								l:padding+c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2,
								t:r*rowHeight+padtop+blue.ph,
								r:padding+c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2+blueLine.ph,
								b:r*rowHeight+padtop+blue.ph+blueLine.pw
							},
							info:it
						});

						if(it.traincheci && it.traincheci!=""){
							mFillText(it.traincheci,padding+c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2,r*rowHeight+padtop+blue.ph+blueLine.pw/2+24,"#3f5fea",'bold','right');
						}

						if(it.type===0 && line.tag === 'error'){
							msgBox(padding+c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2,r*rowHeight+padtop+blue.ph+blueLine.pw/4+12,'#f39800','',it.index,'right');
						}
					}else{

						if(it.traincheci && it.traincheci!=""){
							mFillText(it.traincheci,padding+c*(blue.pw+blueLine.pw)+cs+blueLine.pw/2,r*rowHeight+padtop+cs*2,"#3f5fea",'bold');
						}
						line.draw(padding+blue.pw+c*(blue.pw+blueLine.pw),(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,blueLine.pw,blueLine.ph);
						rectL.push({
							index:i,
							po:{
								l:padding+blue.pw+c*(blue.pw+blueLine.pw),
								t:(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,
								r:padding+blue.pw+c*(blue.pw+blueLine.pw)+blueLine.pw,
								b:(blue.ph-blueLine.ph)/2+r*rowHeight+padtop+blueLine.ph
							},
							info:it
						});
						
						if(it.type===0 && line.tag === 'error'){
							msgBox(padding+blue.pw+c*(blue.pw+blueLine.pw)+blueLine.pw/2,(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,'#f39800','',it.index);
						}
					}
				}else{
					if(it.time && it.time!=""){
						var times = it.time.split(" ");
						mFillText(times[0],w-padding-blue.pw-c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop-cs*0.2-14,"#666",'normal','center',14);
						mFillText(times[1],w-padding-blue.pw-c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop-cs*0.2,"#666",'normal','center',12);
					}
					circle.draw(w-padding-blue.pw-c*(blue.pw+blueLine.pw),r*rowHeight+padtop,blue.pw,blue.ph,"circle",info);
					if(it.address && it.address!=""){
						mFillText(it.address,w-padding-blue.pw-c*(blue.pw+blueLine.pw)+cs/2,r*rowHeight+padtop+cs*2,"#3f5fea",'bold');
					}
					
					rect.push({
						index:i,
						po:{
							l:w-padding-blue.pw-c*(blue.pw+blueLine.pw),
							t:r*rowHeight+padtop,
							r:w-padding-c*(blue.pw+blueLine.pw),
							b:r*rowHeight+padtop+blue.ph
						},
						info:it
					});
					if(i===number-1){
					}else if(c===cols-1){
						ctx.save();
						ctx.translate(padding+blue.pw-cs/2,blue.ph/2+r*rowHeight+padtop);
						ctx.rotate(90*Math.PI/180);
						line.draw(cs/2,-blueLine.ph/2,rowHeight-cs,blueLine.ph);
						ctx.restore();
						rectL.push({
							index:i,
							po:{
								l:w-padding-blue.pw-c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2,
								t:r*rowHeight+padtop+blue.ph,
								r:w-padding-blue.pw-c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2+blueLine.ph,
								b:r*rowHeight+padtop+blue.ph+blueLine.pw
							},
							info:it
						});
						if(it.traincheci && it.traincheci!=""){
							mFillText(it.traincheci,w-padding-blue.pw-c*(blue.pw+blueLine.pw)+(blue.pw-blueLine.ph)/2+blueLine.ph,r*rowHeight+padtop+blue.ph+blueLine.pw/2+12,"#3f5fea",'bold','left');
						}
					}else{
						if(it.traincheci && it.traincheci!=""){
							mFillText(it.traincheci,w-padding-blue.pw-c*(blue.pw+blueLine.pw)-blueLine.pw/2,r*rowHeight+padtop+cs*2,"#3f5fea",'bold');
						}
						line.draw(w-padding-blue.pw-blueLine.pw-c*(blue.pw+blueLine.pw),(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,blueLine.pw,blueLine.ph);
						rectL.push({
							index:i,
							po:{
								l:w-padding-blue.pw-blueLine.pw-c*(blue.pw+blueLine.pw),
								t:(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,
								r:w-padding-blue.pw-c*(blue.pw+blueLine.pw),
								b:(blue.ph-blueLine.ph)/2+r*rowHeight+padtop+blueLine.ph
							},
							info:it
						});
						
						if(it.type===0 && line.tag === 'error'){
							msgBox(w-padding-blue.pw-blueLine.pw-c*(blue.pw+blueLine.pw)+blueLine.pw/2,(blue.ph-blueLine.ph)/2+r*rowHeight+padtop,'#f39800','',it.index);
						}
					}
				}
			}
		}
	}
	function drawBg(color){
		can.height = nextY+rowHeight/2+padbottom;
		ctx.fillStyle = color;
		ctx.fillRect(0,0,can.width,can.height);
	}
	function drawImg(item,x,y,w,h,tag,info){
		if(w>0 && h>0){
			if(tag=="dashline"){
				ctx.drawImage(img,item.x,item.y,w,h,x,y,w,h);
			}else if(tag=="circle"){
				drawDomCircle(img,item,x,y,w,h,info);
			}else{
				ctx.drawImage(img,item.x,item.y,item.w,item.h,x,y,w,h);
			}
		}
	}
	function drawColor(item,x,y,w,h){
		ctx.fillStyle = item.color;
		ctx.fillRect(x,y,w,h);
	}
	function mFillText(text,x,y,color,bold,align,size,row){
		if(size){
			if(bold==='bold'){
				ctx.font = '600 '+size+'px 宋体';
			}else{
				ctx.font = '500 '+size+'px 宋体';
			}
		}else{
			if(bold==='bold'){
				ctx.font = '600 16px 宋体';
			}else{
				ctx.font = '500 12px 宋体';
			}
		}
		ctx.fillStyle = color;
        if(row>1){
        	var addy = 0;
        	if(row==2){
                addy=8;
			}
			var txt = [];
			for(var i=0;i<row;i++){
                txt[i] = text.substr(i*5,5);
                if(align==='left'){
                    ctx.fillText(txt[i],x,y+18*(i-1)+addy);
                }else if(align==='right'){
                    ctx.fillText(txt[i],x-ctx.measureText(txt[i]).width,y+18*(i-1)+addy);
                }else{
                    ctx.fillText(txt[i],x-ctx.measureText(txt[i]).width/2,y+18*(i-1)+addy);
                }
			}
            return [5*12,row*18];
        }else{
            if(align==='left'){
                ctx.fillText(text,x,y);
            }else if(align==='right'){
                ctx.fillText(text,x-ctx.measureText(text).width,y);
            }else{
                ctx.fillText(text,x-ctx.measureText(text).width/2,y);
            }
            return [ctx.measureText(text).width,ctx.measureText(text).height];
		}
	}
	function mStrokeText(text,x,y,color){
		ctx.strokeStyle = color;
		ctx.strokeText(text,x-ctx.measureText(text).width/2,y);
	}
	function getPosition(x,y,rt){
		for(var j=0;j<rt.length;j++){
			var po = rt[j].po;
			if(x>=po.l && x<=po.r && y>=po.t && y<=po.b){
				return rect[j].index;
			}
		}
		return -1;
	}
	function drawCircle(x,y,r,color){
		ctx.fillStyle = color;
		ctx.beginPath();
		ctx.arc(x,y,r,0,Math.PI*2,false);
		ctx.closePath();
		ctx.fill();
	}
	function drawGroup(x,y,circle,line,text,gLeft,size,color,lineW){
		circle.draw(x,y,circle.pw,circle.ph,"circle");
		line.draw(x+circle.pw,y+(circle.ph-line.ph)/2,lineW,line.ph);
		circle.draw(x+circle.pw+lineW,y,circle.pw,circle.ph,"circle");
		var ts = mFillText(text,x+circle.pw+lineW+circle.pw+gLeft,y+circle.ph-(circle.ph-size)/2-3,color,'bold','left',size);
		return [circle.pw+lineW+circle.pw+ts[0]+gLeft,circle.ph];
		
	}
	function drawLine(x,y,line,text,gLeft,size,color,lineW,isLoss){
		line.drawColor(x,y+(cs-line.ph)/2,lineW,line.ph,"dashline");
		var ts = mFillText(text,x+lineW+gLeft,y+cs-(cs-size)/2-3,color,'bold','left',size);
		if(isLoss){
			var jv = (lossD-lossW)/2;
			lossRect(x+jv,y+(cs-line.ph)/2,lossW,line.ph,undefined,true);
		}
		return [lineW+ts[0]+gLeft,cs];
	}
	function drawBtn(x,y,mybtn,text,gLeft,size,color){
		mybtn.draw(x,y-mybtn.ph/5,mybtn.pw,mybtn.ph);
		var ts = mFillText(text,x+mybtn.pw+gLeft,y+cs-(cs-size)/2-3,color,'bold','left',size);
		return [mybtn.pw+ts[0]+gLeft,cs];
	}
	function msgBox(info,x,y,color,text,id,align){
		if(!text || text===''){
			text = msgText;
		}
		if(info.defect){
            text = info.defect;
		}
		if(!align){
			align = 'top';
		}
		var msg = {index:id,info:info};
		var textpo = [0,0];
		ctx.fillStyle = color;
        ctx.font = '500 12px 宋体';
        var textW = ctx.measureText(text).width;
        var textH = 18;
		var msgW = 70,msgH = 28,msgR = 4,tW = 10,tH = 10;
		var textRow = parseInt(textW/(msgW-10));
		if(textW%(msgW-10)>0){
            textRow+=1;
		}
        msgH = textH*textRow+14;
        if(msgH<28){
            msgH = 28;
		}
		ctx.beginPath();
		if(align==='top'){
			tH = 10;
			ctx.moveTo(x,y);
			ctx.lineTo(x+tW/2,y-tH);
			ctx.lineTo(x+msgW/2-msgR,y-tH);
			ctx.arcTo(x+msgW/2,y-tH,x+msgW/2,y-tH-msgR,msgR);
			ctx.lineTo(x+msgW/2,y-tH-msgH+msgR);
			ctx.arcTo(x+msgW/2,y-tH-msgH,x+msgW/2-msgR,y-tH-msgH,msgR);
			ctx.lineTo(x-msgW/2+msgR,y-tH-msgH);
			ctx.arcTo(x-msgW/2,y-tH-msgH,x-msgW/2,y-tH-msgH+msgR,msgR);
			ctx.lineTo(x-msgW/2,y-tH-msgR);
			ctx.arcTo(x-msgW/2,y-tH,x-msgW/2+msgR,y-tH,msgR);
			ctx.lineTo(x-tW/2,y-tH);
			msg.po = {l:x-msgW/2,t:y-tH-msgH,r:x+msgW/2,b:y-tH}
			textpo[0] = x;
			textpo[1] = y-tH-msgH/2+4;
		}else if(align==='right'){
			ctx.moveTo(x,y);
			ctx.lineTo(x-tH,y-tW/2);
			ctx.lineTo(x-tH,y-msgH/2+msgR);
			ctx.arcTo(x-tH,y-msgH/2,x-tH-msgR,y-msgH/2,msgR);
			ctx.lineTo(x-tH-msgW+msgR,y-msgH/2);
			ctx.arcTo(x-tH-msgW,y-msgH/2,x-tH-msgW,y-msgH/2+msgR,msgR);
			ctx.lineTo(x-tH-msgW,y+msgH/2-msgR);
			ctx.arcTo(x-tH-msgW,y+msgH/2,x-tH-msgW+msgR,y+msgH/2,msgR);
			ctx.lineTo(x-tH-msgR,y+msgH/2);
			ctx.arcTo(x-tH,y+msgH/2,x-tH,y+msgH/2-msgR,msgR);
			ctx.lineTo(x-tH,y+tW/2);
			msg.po = {l:x-msgW-tH,t:y-msgH/2,r:x-tH,b:y+msgH/2}
			textpo[0] = x-msgW/2-tH;
			textpo[1] = y+tW/2;
		}else if(align==='left'){
			ctx.moveTo(x,y);
			ctx.lineTo(x+tH,y-tW/2);
			ctx.lineTo(x+tH,y-msgH/2+msgR);
			ctx.arcTo(x+tH,y-msgH/2,x+tH+msgR,y-msgH/2,msgR);
			ctx.lineTo(x+tH+msgW-msgR,y-msgH/2);
			ctx.arcTo(x+tH+msgW,y-msgH/2,x+tH+msgW,y-msgH/2+msgR,msgR);
			ctx.lineTo(x+tH+msgW,y+msgH/2-msgR);
			ctx.arcTo(x+tH+msgW,y+msgH/2,x+tH+msgW-msgR,y+msgH/2,msgR);
			ctx.lineTo(x+tH+msgR,y+msgH/2);
			ctx.arcTo(x+tH,y+msgH/2,x+tH,y+msgH/2-msgR,msgR);
			ctx.lineTo(x+tH,y+tW/2);
			msg.po = {r:x+msgW+tH,t:y-msgH/2,l:x+tH,b:y+msgH/2}
			textpo[0] = x+msgW/2+tH;
			textpo[1] = y+tW/2;
		}else if(align==='bottom'){
			tH = 40;
			ctx.moveTo(x,y);
			ctx.lineTo(x+tW/2,y+tH/2);
			ctx.lineTo(x+msgW/2-msgR,y+tH/2);
			ctx.arcTo(x+msgW/2,y+tH/2,x+msgW/2,y+tH/2+msgR,msgR);
			ctx.lineTo(x+msgW/2,y+tH/2+msgH-msgR);
			ctx.arcTo(x+msgW/2,y+tH/2+msgH,x+msgW/2-msgR,y+tH/2+msgH,msgR);
			ctx.lineTo(x-msgW/2+msgR,y+tH/2+msgH);
			ctx.arcTo(x-msgW/2,y+tH/2+msgH,x-msgW/2,y+tH/2+msgH-msgR,msgR);
			ctx.lineTo(x-msgW/2,y+tH/2+msgR);
			ctx.arcTo(x-msgW/2,y+tH/2,x-msgW/2+msgR,y+tH/2,msgR);
			ctx.lineTo(x-tW/2,y+tH/2);
			msg.po = {l:x-msgW/2,b:y+tH/2+msgH,r:x+msgW/2,t:y+tH/2}
			textpo[0] = x;
			textpo[1] = y+tH/2+msgH/2+4;
		}else{

		}
		ctx.closePath();
		ctx.fill();
		mFillText(text,textpo[0],textpo[1],"#ffffff",false,'center',12,textRow);

		msgs.push(msg);
	}
	function drawMsg(x,y,color,text,id,align){
		var msg = {index:id};
		var textpo = [0,0];
		ctx.fillStyle = color;
		var msgW = 70,msgH = 28,msgR = 4,tW = 10,tH = 10;
		var btn = btnNoml;
		var cbtn = cbtnNoml;
		var cor = "#bfbfbf";
		if(selectMsg===id){
			btn = btnFocs;
			cbtn = cbtnFocs;
			cor = "#333333";
		}
		if(align==='top'){
			tH = 10;
			chatbg.draw(x-chatbg.pw/2, y+ls[1]-chatbg.ph, chatbg.pw, chatbg.ph);
			btn.draw(x-chatbg.pw/2+chatbg.pw+3, y+ls[1]*2-2-chatbg.ph, btn.pw, btn.ph);
			cbtn.draw(x-cbtn.pw/2, y+ls[1]/2-12, cbtn.pw, cbtn.ph);
			msg.po = {l:x-chatbg.pw/2+chatbg.pw+3,t:y+ls[1]*2-2-chatbg.ph,r:x-chatbg.pw/2+chatbg.pw+3+btnNoml.pw,b:y+ls[1]*2-2-chatbg.ph+btnNoml.ph}
			textpo[0] = x;
			textpo[1] = y-ls[1]-chatbg.ph*3/7;
		}else if(align==='right'){
			chatbg.draw(x-chatbg.pw-cbtn.pw/4, y-chatbg.ph/4, chatbg.pw, chatbg.ph);
			btn.draw(x-chatbg.pw-cbtn.pw/4-btn.pw, y-chatbg.ph/4+5, btn.pw, btn.ph);
			cbtn.draw(x-cbtn.pw/2+ls[1]/2, y, cbtn.pw, cbtn.ph);
			msg.po = {l:x-chatbg.pw-cbtn.pw/4-btn.pw,t:y-chatbg.ph/4+5,r:x-chatbg.pw-cbtn.pw/4-btn.pw+btn.pw,b:y-chatbg.ph/4+5+btn.ph}
			textpo[0] = x-chatbg.pw/2-btn.pw/4;
			textpo[1] = y+btn.ph/4;
		}else if(align==='left'){
			msg.po = {r:x+msgW+tH,t:y-msgH/2,l:x+tH,b:y+msgH/2}
			textpo[0] = x+msgW/2+tH;
			textpo[1] = y+tW/2;
		}else if(align==='bottom'){
			msg.po = {l:x-msgW/2,b:y+tH+msgH,r:x+msgW/2,t:y+tH}
			textpo[0] = x;
			textpo[1] = y+tH+msgH/2+4;
		}else{

		}
		mFillText(text,textpo[0],textpo[1],cor,false,'center');
		msgs.push(msg);
	}
	function getMsgPosition(x,y){
		for(var k=0;k<msgs.length;k++){
			var po = msgs[k].po;
			if(x>=po.l && x<=po.r && y>=po.t && y<=po.b){
				return msgs[k].info;
			}
		}
		return -1;
	}
	function mAnimal(){
		selectId++;
		if(selectId>da.length-1){
			selectId = 0;
		}
//		lineSelect++;
//		if(lineSelect>da.length-1){
//			lineSelect = 0;
//		}
		refresh();
	}
	function mClick(e,callback){
//		var x = e.pageX-left;
//		var y = e.pageY-top;
		var x = e.pageX-$("#"+pId).offset().left; 
		var y = e.pageY-$("#"+pId).offset().top;
		var indx = getPosition(x,y,rect);
		var ls = getPosition(x,y,rectL);
		var msgsI = getMsgPosition(x,y);
		if(indx!==-1){
//			selectId = indx;
//			refresh();
//			console.log(indx);
//			callback(datas[da[indx].index]);
		}else if(ls!==-1){
//			lineSelect = ls;
//			refresh();
//			console.log(ls);
//			if(da[ls].type===0){
//				callback(datas[da[ls].index]);
//			}
		}else if(msgsI){
//			console.log(msgsI);
//			console.log(JSON.stringify(datas[msgsI]));
			callback(msgsI);
		}else{
			console.log("没有点钟！");
		}
	}
	function mDown(e){
		refresh();
	}
	function debug(n){
		var num = can.width/n;
		for(var k=0;k<num;k++){
			ctx.fillStyle = "#111111";
			ctx.fillRect(n*k,0,1,can.height);
		}
	}
	function getRects(x,y){
//		for(var j=0;j<rect.length;j++){
//			var po = rect[j].po;
//			if(x>=po.l && x<=po.r && y>=po.t && y<=po.b){
//				return true;
//			}
//		}
//		for(var j=0;j<circlelist.length;j++){
//			var po = circlelist[j];
//			if(x>=po.s[0] && x<=po.s[0]+cs && y>=po.s[1] && y<=po.s[1]+cs){
//				return po.info;
//			}else if(x>=po.e[0] && x<=po.e[0]+cs && y>=po.e[1] && y<=po.e[1]+cs){
//				return po.info;
//			}
//		}
		for(var j=0;j<msgs.length;j++){
			var po = msgs[j].po;
			if(x>=po.l && x<=po.r && y>=po.t && y<=po.b){
				return "rect";
			}
		}
		return false;
	}
	function mMove(e){
		var x = e.pageX-$("#"+pId).offset().left; 
		var y = e.pageY-$("#"+pId).offset().top;
//		if(e.which===1){
//			drawCircle(x,y,2,"#333");
//		}
		var info = getRects(x,y);
		if(info){
			if($("#lossdiv").css('cursor')!='pointer'){
				$("#lossdiv").css('cursor','pointer');
			}
//			if(info!="rect"){
//				if(x>can.width/2){
//					$("#trainInfo").css({left:(x+10-$("#trainInfo").width())+"px",top:(y-$("#trainInfo").height()-cs)+"px"});
//				}else{
//					$("#trainInfo").css({left:(x+20+cs)+"px",top:(y-$("#trainInfo").height()-cs)+"px"});
//				}
//				if($("#trainInfo").css("display")=="none"){
//					$("#trainInfo").show();
//				}
//				$("#trainInfo").show();
//				$("#sfz").html(info.shifazhan);
//				$("#zdz").html(info.zhongdaozhan);
//				$("#sfsj").html(timeFormat(info.shifashijian));
//				$("#zdsj").html(timeFormat(info.zhongdaoshijian));
//				$("#checi").html(info.traincheci);
//				$("#info").html(info.info);
//			}
		}else{
			if($("#lossdiv").css('cursor')!='default'){
				$("#lossdiv").css('cursor','default');
			}
//			if($("#trainInfo").css("display")=="block"){
//				$("#trainInfo").hide();
//			}
		}
	}
	function lossRect(x,y,w,h,obj,noevent,hide){
		if(hide){
			return;
		}
		var dom = document.createElement('span');
		$("#lossdiv").append(dom);
		$(dom).css({
			'display':'inline-block',
			'position':'absolute',
			width:w+'px',
			height:h+'px',
			left:x+pLeft+'px',
			top:y+'px',
			'background':'#d73300'
		});
		if(!noevent){
			$(dom).hover(function(){
				$(dom).css({'cursor':'pointer'});
			});
			$(dom).on('click',function(){
				if(way.callback){
					way.callback(obj);
				}
			});
			$(dom).on('mouseleave',function(){
				$(dom).css({
					'background':'#d73300'
				});
			});
		}
	}
	function drawDomCircle(img,item,x,y,w,h,info){
		var dom = document.createElement('img');
		dom.src = imgPath+'blue_circle.png';
		$("#lossdiv").append(dom);
		$(dom).css({
			'display':'inline-block',
			'position':'absolute',
			width:w+'px',
			height:h+'px',
			left:x+pLeft+'px',
			top:y+'px',
			'cursor':'pointer'
		});
		x = x+20;
		$(dom).on('mouseenter',function(){
			if(x>can.width/2){
				$("#trainInfo").css({left:(x-cs*2-$("#trainInfo").width())+"px",top:(y-$("#trainInfo").height()-cs)+"px"});
			}else{
				$("#trainInfo").css({left:(x+cs)+"px",top:(y-$("#trainInfo").height()-cs)+"px"});
			}
			if($("#trainInfo").css("display")=="none"){
				$("#trainInfo").show();
			}
			if(info){
				$("#sfz").html(info.shifazhan);
				$("#zdz").html(info.zhongdaozhan);
				$("#sfsj").html(info.shifashijian);
				$("#zdsj").html(info.zhongdaoshijian);
				$("#checi").html(info.traincheci);
				$("#info").html(info.info);
			}
		});
		$(dom).on('mouseleave',function(){
			if($("#trainInfo").css("display")=="block"){
				$("#trainInfo").hide();
			}
		});
	}
	function getInt(str){
		return parseInt(str.replace("px",""));
	}
	function timeFormat(timestr){
		var str = timestr;
		if(str){
			str = insert(timestr,4,"年");
			str = insert(str,7,"月");
			str = insert(str,10,"日");
			str.replace(" ","<br />");
		}
		return str;
	}
	function insert(soure,start,newStr){
		return soure.slice(0,start)+newStr+soure.slice(start);
	}
	function refresh(){
		way.render();
	}
	
	var way = new obj();
	way.version = '3.0.0';
	way.name = 'tenly.com';
	
	img.onload = function(){
		way.render();
	}
	
	window.$tenly = way;
})();