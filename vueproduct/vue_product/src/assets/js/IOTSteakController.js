var operate = {
	"0": {
		"isSelected": true,
		"canEdit": true,
		"upDish": 40,
		"downDish": 40,
		"time": 360
	},
	"1": {
		"isSelected": false,
		"canEdit": false,
		"upDish": 170,
		"downDish": 100,
		"time": 760
	}
};
//$('body').scroll({
//	indicators: true //是否显示滚动条
//});

var appWebviewAgent = navigator.userAgent;
//app端设置的webview agent要拼接"EJCLOUD"
if(appWebviewAgent.indexOf("EJCLOUD") > -1) {
	$('body').css('padding-bottom', '10px');
	var el = document.querySelector("header");
	el.parentNode.removeChild(el);
}
var controller = {}
controller.setNav = function() {
	if(window.location.href.indexOf("did") <= 0) {
		if(window.location.href.indexOf("?") > -1) {
			//iotDeviceController.js 里面设置的localStorage.control_urlParam  第447行
			window.location.href = window.location.href + "&" + localStorage.control_urlParam;
		} else {
			window.location.href = window.location.href + "?" + localStorage.control_urlParam;
		}
	}
	var appWebviewAgent = navigator.userAgent;
	if(appWebviewAgent.indexOf("EJCLOUD") > -1) {
		document.querySelector('header').classList.add('mui-hidden');
		document.querySelector('.mui-content').style.paddingTop = 0;
		try {
			IOTNavigationManager.showDeviceInfo('plug/IOTSteakSecond/html/IOTSteakInfo.html');
		} catch(e) {
//			mui.toast('showDeviceInfo is not defined');
		}
	}
}
controller.initNavData = function() {
	IOTUtilities.native.saveDid();  //获取URL中的明文参数存储
	IOTUtilities.native.saveToken();
}
$(document).ready(function() {
	try {
		controller.setNav();
		controller.initNavData();

		getDeviceInfo(function(data) {  //获取设备信息，设置界面的title
			var name = data.name.length > 0 ? data.name : '牛扒机';
			console.log(data.name)
			IOTUtilities.interface.setTitle(name);
		});
//IOTNativeApp.pushToDeviceDetailVC
//      //头部导航的设备信息跳转（牛扒机设置）menuType=ejmianbaoji ？？？
//		mui("body").on("tap", "#steark_set", function() {
//			var path = 'plug/IOTSteakSecond/html/IOTSteakInfo.html';
//			var param = "?" + window.location.href.split("?")[1] + "&menuType=ejmianbaoji";
//			path += param;
//			path = path.replace("#undefined", "")
//			iot.openWindow(path);
//		});
	} catch(e) {
		mui.toast(e);
		console.log(e)
	}
});
/**
 *  获取设备信息
 *  parseDeviceInfo  {function}
 */
function getDeviceInfo(parseDeviceInfo) {
	try {
		var token = IOTUserInfo.u_token();   //获取localStorage里面的loginUserInfo(token)    js/model/UserInfo
		var did = localStorage.device_did;   //localStorage.device_did是在设备列表页面进行存储的
		IOTsap.device_info("1", token, did, success, fail); //方法里面调用异步请求，成功回调的data参数是获取到的数据

		function success(data) {
			parseDeviceInfo(data);
		}

		function fail(data) {
//			isLoginFail(data);
//			IOTUtilities.native.isLoginFail(data);
		}
	} catch(e) {
		iot.log("getDeviceInfo异常", e.toString())
	}
}
//var uart_length = 94;
var steakFlag = 0;

mui("body").on("tap", '.startbtn', function() {
	if($('#childLock').hasClass('lockbg-s')) {
		// 如果开启了童锁按钮，那么就不能让用户继续下去
		confirmDialog('是否确认解除童锁？', '童锁开启中，确认取消才可继续操作', function() {
			$('#childLock').removeClass('lockbg-s').addClass('lockbg');
			$('.childlock p').css('color','#898989');
//		 	sendCommand("01000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		 	sendCommand("010302");
		}, function() {});
		return;
	}
	if($('.startbtn').text()=="开始"){
		if(steakFlag==0 || steakFlag==null){
			confirmDialog('还未设置命令参数', '设置命令参数后才可开始烹饪', function() {}, function() {});
			return;
		}
		// 发送开始烹饪的消息
		sendCommand("0004");
		$('.startbtn').text("暂停");
		
	}else{
		confirmDialog('确认停止制作吗', '', function() {
			// 发送停止烹饪的消息
			sendStopMeassage();
			$('.startbtn').text("开始");
		}, function() {
			$('.startbtn').text("暂停");
		})
	}
});

Zepto(function($) {
	IOTUtilities.draw.drawProgressBar(400, 400, 0, 0,0,'更新状态中','canvas');
	addTapEvent();
//	addInputObserver();
	addObserver();
});
function addInputObserver() {
	var r1 = $('#r1');
	var r2 = $('#r2');
	var r3 = $('#r3');
	r1.val(operate[0].upDish);  //40
	var n;
	n = operate[0].upDish / 2.5;
//	console.log(n)   //16
	r1.css({
		'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
	});
	r2.val(operate[0].downDish);
	n = operate[0].downDish / 2.5;
	r2.css({
		'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
	});
	r3.val(operate[0].time);
	n = operate[0].time / 9;
	r3.css({
		'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
	});
	$('#pop .value1').text(r1.val());
	$('#pop .value2').text(r2.val());
	$('#pop .value3').text(IOTUtilities.string.dateFormatter(r3.val()));

	//给滑块添加绑定事件
	r1.bind('input propertychange', function() {
		var value = $(this).val();
		$('#pop .value1').text(value);
		//让滑动条随着滑动改变进度
		var n = value / 2.5;
		r1.css({
			'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
		});
		$('.range_top .tips').css({
			'left': (value/2.74) + '%'
		})
//		disableStartButton();
	});
	
	$('.range_middle .tips').css({'left': '15%'});
	r2.bind('input propertychange', function() {
		var value = $(this).val();
		$('#pop .value2').text(value);
		//让滑动条随着滑动改变进度
		var n = value / 2.5;
		r2.css({
			'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
		});
		$('.range_middle .tips').css({
			'left': (value/2.74) + '%'
		})
	});
	
	$('.range_bottom .tips').css({'left': '37%'});
	r3.bind('input propertychange', function() {
		var value = $(this).val();
		$('#pop .value3').text(IOTUtilities.string.dateFormatter(value));
		//让滑动条随着滑动改变进度
		var n = value / 9;
		r3.css({
			'background-image': '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
		});
		$('.range_bottom .tips').css({
			'left': (value/9.84) + '%'
		})
	});
}

function addObserver() {
	setInterval(function() {
		try {
			var token = IOTUserInfo.u_token();
			var did = localStorage.device_did;
			IOTsap.device_state3(token, did, success, fail);

			function success(data) {
//				console.log(data) //AA02002E00 210F210001 0000020000 0000000200 000000F000 F700000000 0000000000 0000000000 0000000000 0093
				if(data == null||data==""){
					IOTUtilities.draw.drawProgressBar(400, 400, 0, 0,0,'设备离线','canvas');
					$('#childLock').removeClass('lockbg-s').addClass('lockbg');
					$('.childlock p').css('color','#898989');
					steakFlag = data;  //用户判断设备是否在线，童锁能否控制
					return;
				}else if(data=="1"){
					IOTUtilities.draw.drawProgressBar(400,400,0,0,0,"待机","canvas");
				}else{
					//判断设备的童锁状态
					if(data.childLock==1){
						$('#childLock').removeClass('lockbg').addClass('lockbg-s');
						$('.childlock p').css('color','#3CADFF');
//						IOTUtilities.draw.drawProgressBar(400,400,0,0,0,"待机","canvas");
//						return
					}else{
						$('#childLock').removeClass('lockbg-s').addClass('lockbg');
						$('.childlock p').css('color','#898989');
					}
					
					var upRemainTime = data.upRemainTimeMinute*60 + data.upRemainTimeSecond;
					var downRemainTime = data.downRemainTimeMinute*60 + data.downRemianTimeSecond;
					var remainTime = upRemainTime > downRemainTime ? upRemainTime : downRemainTime;
					var allTime = data.haveStartMinute*60 + data.haveStartSecond + remainTime;
					var percent=(allTime-remainTime)/allTime*100; //完成百分比
					
					var remainMinute = Math.floor(remainTime/60);
					var remainSecond = remainTime%60;
					steakFlag = remainTime;  //用于判断是否设置了温度时间等参数
					if(remainTime == 0){
						$('.startbtn').text("开始");
					}
					if(data.menuType==5){
						$('.startbtn').text("暂停");
					}
					var status = "";
					if(data.status===0){
						status = "待机";
					}else if(data.status===1){
						status = "预热";
					}else if(data.status===2){
						status = "预热完成";
					}else if(data.status===3){
						status = "烹饪中";
						$('.startbtn').text("暂停");
					}
					IOTUtilities.draw.drawProgressBar(400,400,percent,remainMinute,remainSecond,status,"canvas");
					$('#upDiskTemperature').text(data.upTemperature);  //上盘温度
					$('#downDiskTemperature').text(data.downTemperature); //下盘温度
					
				}
				
//				if(data.length != uart_length && data != "") {  //uart_length=94
//					return;
//				}
//				var deviceData = data.substr(18, data.length - 18);
//				console.log(deviceData); //0100000200000000000200000000F000F7000000000000000000000000000000000000000093
//				var model = parserSteakDeviceMessage(deviceData); //解析16进制的字符串，返回一个设备信息model
//				console.log(model)
//				updateState(model);
			}

			function fail(data) {
//				console.log(data + '\n isLoginFail(data)\n\t\t跳转到登陆页面未处理!!!!!!!!');
				IOTUtilities.native.isLoginFail(data);
//				var model = getOffLineModel();
//				updateState(model);
				IOTUtilities.draw.drawProgressBar(400, 400, 0, 0,0,'设备离线','canvas');
			}
		} catch(e) {
			mui.toast(e);
			iot.log("addObserver异常", e.toString());
//			var model = getOffLineModel();
//			updateState(model);
		}
	}, 1000);

}
/**
 * 获取一个离线model，该model可以让展示页面显示设备离线
 * @returns {{}} 设备离线model
 */
function getOffLineModel() {
	var model = {};
	model.isEffective = true;
	model.state = '设备离线';
	model.currentUpDiskTemperature = 0;      //当前上盘温度
	model.currentDownDiskTemperature = 0;    //当前下盘温度
	model.upDiskRemainingMinutes = 0;        //上盘剩余分钟
	model.upDiskRemainingSeconds = 0;        //上盘剩余秒钟
	model.downDiskRemainingMinutes = 0;      //下盘剩余分钟
	model.downDiskRemainingSeconds = 0;      //下盘剩余秒钟
	return model;
}
/**更新设备状态，http轮询时调用
 * @param {Object} deviceMessageModel 设备信息模型，解析过后的字符串或者自己定义的离线model
 */
function updateState(deviceMessageModel) {

	var model = deviceMessageModel;
//	console.log(model)
//	if(model.isEffective) {m
	var lastDM = JSON.stringify(deviceMessageModel);
	sessionStorage.lastDM = lastDM;  //设备返回字符串时存储
	var state = model.state;
//	var state = "预热";  //显示停止烹饪
//	var state = "预热完成"; //显示停止烹饪，再次启动
//	var state = "烹饪中";  //显示停止烹饪
//	$('#deviceState').text(state);
//	IOTUtilities.draw.drawProgressBar(400, 400, 0, "00","00",state,'canvas');
	//		mui.toast(state)
	if(state === "待机") {  //待机状态不显示控制按钮
		/*设备状态*/
//		shouldDisplayControlButton(state); //根据设备返回的状态，判断是否展示控制按钮
		var temperature = shouldDisplaySteakTemperature(model);  //当前状态应该显示的温度，都显示0
		$('#upDiskTemperature').text(temperature.upper);  //上盘温度
		$('#downDiskTemperature').text(temperature.lower); //下盘温度
		var remainingTime = getRemainingTime(model);  //待机状态下剩余时间为00：00，这句代码有没有都不影响
//		$('#remainingTime').text("00:00");   //剩余时间
		IOTUtilities.draw.drawProgressBar(400, 400, 20, "00","00",state,'canvas');
	} else {
		/*设备状态*/
//		shouldDisplayControlButton(state);//根据设备返回的状态，判断是否展示控制按钮
		var temperature = shouldDisplaySteakTemperature(model);  //当前状态应该显示的温度
		$('#upDiskTemperature').text(temperature.upper);
		$('#downDiskTemperature').text(temperature.lower);
		var remainingTime = getRemainingTime(model);
//		$('#remainingTime').text(remainingTime);
		var timeArr = remainingTime.split(':');
		var totalTime = $('#vals3').text();
		var secArr = totalTime.split(':');
		var totalSec = secArr[0]*60 + secArr[1];
		var remainingSec = timeArr[0]*60 + timeArr[1];
		var bili = remainingSec/totalSec*100;
		
		IOTUtilities.draw.drawProgressBar(400, 400, bili, timeArr[0],timeArr[1],state,'canvas');
	}
}
/**
 * 通过模型判断当前状态应该显示哪一种温度
 * @param model
 * @returns {{}} 温度模型，有上下盘温度
 */
function shouldDisplaySteakTemperature(model) {
	var temperatureModel = {};
	var state = model.state;
	if(state === '预热') {
		temperatureModel.upper = model.currentUpDiskTemperature;
		temperatureModel.lower = model.currentDownDiskTemperature;
	} else {
		temperatureModel.upper = model.currentUpDiskTemperature;
		temperatureModel.lower = model.currentDownDiskTemperature;
	}
	return temperatureModel;
}
/** 获取剩下的时间，更新设备状态时调用
 *
 * @param {Object} model 设备信息模型
 */
function getRemainingTime(model) {
	var urm = model.upDiskRemainingMinutes;   //分
	var urs = model.upDiskRemainingSeconds;   //秒

	var drm = model.downDiskRemainingMinutes;
	var drs = model.downDiskRemainingSeconds;
	//	mui.toast(urm+":"+urs+"==="+drm+":"+drs);
	if(!isNaN(urm) && !isNaN(urs) && !isNaN(drm) && !isNaN(drs)) {
		var sec = (urs) % 60;
		var min = urm + Math.floor((urs) / 60);
		return IOTUtilities.string.timeToString(min) + ':' + IOTUtilities.string.timeToString(sec);
	} else {
		return '00:00'
	}
}
function confirmDialog(title, describe, confirm, cancel) {
	var btnArray = ['取消', '确定'];
	mui.confirm(describe, title, btnArray, function(e) {
		if(e.index === 1) {
			confirm();
		} else {
			cancel();
		}
	})
}
var currentMenuJSON2Number = {
	"无效": "0",
	"本地操作": "1",
	"云食谱菜单": "2",
	"DIY": "3",
	"场景模式": "4",
	"解冻": "5"
};

//点击弹出层里的启动按钮
	mui('#pop').on('tap', '#startButton', function() {
//		if(!$('.control').hasClass('mui-hidden')) {
//			//已经显示启动按钮，再次点击openPopover就会弹出提示框
//			confirmDialog('是否停止烹饪', '确认停止烹饪才可进行启动操作', function() {
//				//发送停止烹饪消息！
//				sendStopMeassage();
//				//				$('.control').addClass('mui-hidden');
//			}, function() {});
//			pop.close();
//			return;
//		}
		var r1 = Number($('#vals1').text());
		var r2 = Number($('#vals2').text());
		var r3 = $('#vals3').text();
		var timeArr = r3.split(':');
		r3 = Number(timeArr[0]*60) + Number(timeArr[1]);
		console.log(r1,r2,r3)
//		if(operate[0].isSelected && ((r1 > 0 && r1 < 30) || (r2 > 0 && r2 < 30))) {
//			mui.alert('', '温度输入非法', function() {});
//			return;
//		}
		mui('#pop').popover('hide');
		confirmDialog('确认开始制作吗？', '', function() {
//			$('.control').removeClass('mui-hidden');
			//开始发送烹饪消息！
			sendStartMessage();
		}, function() {});
	});

/**
 *    发送开始烹饪消息，点击对话框“确认开始制作吗？”的确定按钮时调用
 */
function sendStartMessage() {
	var type = getCurrentFunction(); //获取当前指令类型，是煎烤还是解冻
//	var ut = $('#vals1').text();
//	var dt = $('#vals2').text();
//	var time = $('#vals3').text();
	var ut = Number($('#vals1').text());
	var dt = Number($('#vals2').text());
	var time = $('#vals3').text();
	var timeArr = time.split(':');
		time = Number(timeArr[0]*60) + Number(timeArr[1]);
	var min = Math.floor(time / 60);
	var sec = time % 60;
	console.log(ut)
	ut = IOTUtilities.string.decimal2Hex(ut);
	dt = IOTUtilities.string.decimal2Hex(dt);
//	if(ut < 30) {
//		ut = "FF"; //IOTUtilities.string.decimal2Hex(ut);
//	} else {
//		ut = IOTUtilities.string.decimal2Hex(ut); //十进制转十六进制
//	}
//	if(dt < 30) {
//		dt = "FF"; //IOTUtilities.string.decimal2Hex(dt);
//	} else {
//		dt = IOTUtilities.string.decimal2Hex(dt);
//	}

	min = IOTUtilities.string.decimal2Hex(min);
	sec = IOTUtilities.string.decimal2Hex(sec);
	var p1 = getSteakCookingPhaseMessage(false, ut, min, sec, dt, min, sec);
	var flag = 0;
	while(true) {
		if(flag++ === 5) break;
		p1 += getSteakCookingPhaseMessage(true);
	}
	var lastDM; //用来记录上一次发送的指令
	try {
		lastDM = sessionStorage.lastDM;
		lastDM = JSON.parse(lastDM);
	} catch(e) {
		lastDM = {};
	}
	var childLock = "00"; //默认不操作
	var menuType;

	if(currentMenuJSON2Number[lastDM.currentMenu] > 0 && currentMenuJSON2Number[lastDM.currentMenu] < 6) {
		menuType = '0' + currentMenuJSON2Number[lastDM.currentMenu];
	} else {
		var d1 = document.body.querySelectorAll(".icon1-s");
		if(0 === d1.length) {
			menuType = '05';
		} else {
			menuType = '03';
		}
	}
	var sceneMode = "00"; //目前没有
	var menuIDUpper = "00";
	// if (!isNaN(lastDM.menuIDUpper)) {
	//     menuIDUpper = IOTUtilities.string.decimal2Hex(lastDM.menuIDUpper);
	// }
	var menuIDLower = (type === 1) ? "01" : "02";
	// if (!isNaN(lastDM.menuIDLower)) {
	//     menuIDLower = IOTUtilities.string.decimal2Hex(lastDM.menuIDLower);
	// }

	var upDiskWarmUp = ut; //'00';
	var downDiskWarmUp = dt; //'00';
	if(menuType === "05") {
		upDiskWarmUp = '00';
		downDiskWarmUp = '00';
	}
	var totalPhases = '01';
	var command = getSteakCommandMessage(false, childLock, menuType, sceneMode, menuIDUpper, menuIDLower, upDiskWarmUp, downDiskWarmUp, totalPhases, p1);
	sendCommand(command);
}

/**
 *    停止烹饪消息
 */
function sendStopMeassage() {
	var command = getCancelSteakMessage();  //getCancelSteakMessage定义在UART.js
	sendCommand(command);  //发送设备命令
}

/** 发送设备命令
 * @param {Object} command
 */
function sendCommand(command) {
	console.log(command);
	var token = IOTUserInfo.u_token();
	var did = localStorage.device_did;
	IOTsap.device_control(token, did, command, function(data) {
		mui.toast("指令发送成功!")
		console.log('发送控制指令成功\n' + data);
	}, function(data) {
		console.log('发送控制指令失败\n' + data);
	});
}
function getCurrentFunction() {
	var arr = $('#functions li div');   //煎烤和解冻
	for(var i = 0; i < arr.length; i++) {
		if($(arr[i]).attr('class').length === 7) {
			return i;
		}
	}
	return NaN;
}


function addTapEvent() {
	// 监听功能按钮（弹出层的煎烤、解冻按钮）是否被点击
	var arr = $('.functions li div');
	arr[0].addEventListener("tap", function() {
		deselectOthers(0); //点击选中按钮，取消其他按钮的选中状态
		$('.middle').text('30℃').css({paddingLeft: '3%'});
		$('.repeater').css({left:'15%'});
		$('.suggest').css({display:'inline-block'});
	});

	arr[1].addEventListener("tap", function() {
		deselectOthers(1);
		$('.middle').text('40℃').css({paddingLeft: '5%'});
		$('.repeater').css({left:'18%'});
		$('.suggest').css({display:'none'});
	});
	
	//云食谱添加点击事件
	mui('.menu_div').on('tap', '.menu_top_l', function() {
		var path = 'plug/IOTRecipe/html/IOTRecipe.html';
		var param = "?" + window.location.href.split("?")[1] + "&menuType=steak";
		path += param;
		iot.openWindow(path);
	});
	
	//DIY食谱添加点击事件，页面跳转
	mui('.menu_div').on('tap', '.menu_top_r', function() {
		var path = 'plug/IOTDIYRecipe/html/IOTDIYRecipe.html';
		var param = "?" + window.location.href.split("?")[1] + "&menuType=steak";
		path += param;
		iot.openWindow(path);
//		window.location.href =path;
	});
	
	//选购牛扒添加点击事件
	mui('.menu_div').on('tap', '.menu_bottom_l', function() {
		//var path = 'EJShopWebApp/ejmall/search_result.html';
		var path='https://cert.eg-earth.com/m-wap';
		var param = "?" + window.location.href.split("?")[1] + "&words=牛排";
		path += param;
//		iot.openWindow(path);
		window.location.href =path;
	});
	
	//你懂牛扒吗添加点击事件
	mui('.menu_div').on('tap', '.menu_bottom_r', function() {
		window.location.href = 'https://mp.weixin.qq.com/s?__biz=MzI0MjA5MzA3OQ==&mid=2649041232&idx=1&sn=f1826ddfa2b3c01f9008cd0fb9890811&chksm=f1101428c6679d3e0804c9927c04e67db2a91f250d9547a94e16d782a2c7424c3d8dd05717d4&mpshare=1&scene=1&srcid=0614zal0RzjWadX2WG5Mo3Ck&key=2b4a905e8c07cb215dab7ec071f7f809ba40025a91964723f3cdccbe0a1d1342e47a24b313fd9264c2e82c781e8fb730a1e22f513d497b75ccda7b297a9919dd06fe5477b5c8379748cafb288570a0e3&ascene=0&uin=MjM4Mjg3MDk2MA%3D%3D&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.12.5+build(16F73)&version=12020810&nettype=WIFI&fontScale=100&pass_ticket=u0sllINB1oE%2BGZCTgzUx33DDExLr2aGMJtEQQV77PUTjNZjxHrGTSCoez4oMKHFX';
	});
	
	//头部导航的右侧弹出层里点击设备信息跳转到设备信息页面
	mui(".mui-popover").on("tap", ".table-view #steak_info", function() {
		var path = 'plug/IOTSteakSecond/html/IOTSteakInfo.html';
		var param = "?" + window.location.href.split("?")[1] + "&menuType=ejmianbaoji";
		path += param;
		path = path.replace("#undefined", "");
		iot.openWindow(path);
	});
	
	//头部导航的右侧弹出层里点击烹饪记录跳转到烹饪记录页面
	mui(".mui-popover").on("tap", ".table-view #cooking_records", function() {
		var path = 'plug/IOTSteakSecond/html/IOTSteakCookingRecords.html';
		var param = "?" + window.location.href.split("?")[1] + "&menuType=ejniupaji";
		path += param;
		path = path.replace("#undefined", "");
		iot.openWindow(path);
	});
	
	//头部导航的右侧弹出层里点击分享设备跳转到分享设备页面
	mui(".mui-popover").on("tap", ".table-view #share_device", function() {
		var path = 'plug/IOTSteakSecond/html/IOTSteakShare.html';
		var param = "?" + window.location.href.split("?")[1] + "&menuType=ejniupaji";
		path += param;
		path = path.replace("#undefined", "");
		iot.openWindow(path);
	});
	
	//童锁点击事件
	mui('.mui-content').on('tap', '.childlock', function() {
		if(steakFlag===""){   //steakFlag是接口返回的data，为空时是设备离线，无法控制童锁
			mui.toast("设备离线，无法操作！");
			return;
		}
		if($('#childLock').hasClass('lockbg')){
			//开启童锁
//			sendCommand("0100010000 0000000000 0000000000 0000000000 0000000000 0000000000 0000000000 0000000000 0000000000 00");
//			sendCommand("01000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
			sendCommand("010301");
			mui.toast("已开启童锁！");
			$('#childLock').removeClass('lockbg').addClass('lockbg-s');
			$('.childlock p').css('color','#3CADFF');
		}else{
			//关闭童锁
			confirmDialog('是否确认解除童锁？', '', function() {
//		 		sendCommand("01000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		 		sendCommand("010302");
		 		$('#childLock').removeClass('lockbg-s').addClass('lockbg');
				$('.childlock p').css('color','#898989');
			}, function() {});
		}
	});
	
	//扫一扫点击事件
	mui('.mui-content').on('tap', '.scan', function() {
		try {
			IOTNativeApp.nativeQRCoderScan();
		} catch(e) {
			console.log("原生端并未成功注入IOTNativeApp对象\n" + e);
		}
	})
}
/**选中按钮，取消其他按钮的选中状态
 * @param {Object} index 按钮的index
 */
function deselectOthers(index) {
	var arr = $('.functions li div');
	for(var i = 0; i < arr.length; i++) {
		$(arr[i]).removeClass('icon' + (i + 1) + '-s');
		$(arr[i]).removeClass('icon' + (i + 1));
		if(i === index) {
			operate[i].isSelected = true;
			$(arr[i]).addClass('icon' + (i + 1) + '-s');
		} else {
			operate[i].isSelected = false;
			$(arr[i]).addClass('icon' + (i + 1));
		}
	}
}

document.querySelector('#dismiss').addEventListener('tap',function(){
	document.querySelector('#pop').classList.remove('mui-active');
	document.querySelector('.mui-backdrop').classList.remove('mui-active');
	document.querySelector('#pop').style.display='none';
	document.querySelector('.mui-backdrop').style.display='none';
})

function webViewDidFinishLoad() {
	IOTNavigationManager.showDeviceInfo('plug/IOTSteakSecond/html/IOTSteakInfo.html');
}