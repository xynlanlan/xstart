//测试部门cert.eg-earth.com
//开发:cert.eg-earth.com
//iot正式:cert.eg-earth.com
//--120.--25.--123.-3

//var URLSever = "https://apis.ej-cloud.com/iotlife/"; //app访问服务器
//var historySever = "https://apis.ej-cloud.com"; //不知道是什么鬼
//var menuSever = "https://apis.ej-cloud.com/menu"; //云食谱
//var historySeverManagement = "https://apis.ej-cloud.com"; //云食谱管理
//var mqttSever = "cert.eg-earth.com"; //mqtt
//var mqttPort = 8083;
//var appUrl = "https://apis.ej-cloud.com"; //登录的app
////设备系统
//var sapURL = "https://apis.ej-cloud.com/sap/";
////var sapURL = "//192.168.1.74/sap/";
////插件系统
//var pluginServer = "https://apis.ej-cloud.com/iotppm";
//var pluginServer = "//192.168.1.74/iotppm"; 

//var URLSever = "http://baiqi.ej-cloud.com/iotlife/"; //app访问服务器
//var historySever = "http://baiqi.ej-cloud.com"; //不知道是什么鬼
//var menuSever = "http://baiqi.ej-cloud.com/menu"; //云食谱
//var historySeverManagement = "http://baiqi.ej-cloud.com"; //云食谱管理
//var mqttSever = "baiqi.ej-cloud.com"; //mqtt
//var mqttPort = 8083;
//var appUrl = "http://baiqi.ej-cloud.com"; //登录的app
/////设备系统
//var sapURL = "http://baiqi.ej-cloud.com/sap/";


var URLSever = "//192.168.1.52/iotlife/"; //app访问服务器
var historySever = "//192.168.1.52"; //不知道是什么鬼
var menuSever = "//192.168.1.52"; //云食谱
var historySeverManagement = "//192.168.1.52"; //云食谱管理
var mqttSever = "//192.168.1.52"; //mqtt
var mqttPort = 8083;
var appUrl = "//192.168.1.52"; //登录的app
///设备系统
var sapURL = "//192.168.1.52/sap/";
//内网测试地址
//var URLSever = "https://apis.ej-cloud.com/iotlife/"; //app访问服务器
//var historySever = "https://apis.ej-cloud.com"; //不知道是什么鬼
//var menuSever = "https://apis.ej-cloud.com/menu"; //云食谱
//var historySeverManagement = "https://apis.ej-cloud.com"; //云食谱管理
//var mqttSever = "https://apis.ej-cloud.com"; //mqtt
//var mqttPort = 8083;
//var appUrl = "https://apis.ej-cloud.com"; //登录的app
/////设备系统
//var sapURL = "https://apis.ej-cloud.com/sap/";

var mqtt = null;
/***
 * 
 * @param {Object} mqttJson {topic:"",success:fun,fail:fun,message:fun,disconn:fun}
 */
function ej_mqtt(mqttJson) {
	var timestamp = new Date().getTime();

	mqtt = IOTMQTT.init(timestamp + "")
	console.log(mqttJson.topic)
	IOTMQTT.clientConnect(mqtt, mqttJson.topic, function() {
		console.log("IOTMQTT.clientConnect")
		mqttJson.success();
	});
	IOTMQTT.clientConnectionLost(mqtt, function() {
		console.log("IOTMQTT.clientConnectionLost")
		mqttJson.disconn();
	})
	IOTMQTT.clientMessageArrived(mqtt, false, function(message) {
		try {
			mqttJson.message(message.payloadString);
		} catch(e) {
			console.log(e);
		}
	})
}
/**
 * 本全局变量用来创建mqtt客户端、并可以设置客户端来建立mqtt通信
 * @type {{createMQTTClient: IOTMQTT.createMQTTClient, clientConnect: IOTMQTT.clientConnect, clientSendMessage: IOTMQTT.clientSendMessage, clientConnectionLost: IOTMQTT.clientConnectionLost, clientMessageArrived: IOTMQTT.clientMessageArrived}}
 */
var IOTMQTT = {

	init: function(clientId) {
		return IOTMQTT.createMQTTClient(historySever, "8083", clientId);
	},
	/**
	 * 快速创建一个mqtt客户端
	 * @param {String} hostname 域名
	 * @param {String} port 端口号
	 * @param {String} clientId 客户端ID，唯一
	 * @returns {Object} mqtt客户端
	 */
	createMQTTClient: function(hostname, port, clientId) {
		return new Paho.MQTT.Client(hostname, Number(port), clientId);
	},

	/**
	 * 使传入的mqtt客户端订阅指定的topic，onConnect函数在链接建立时调用
	 * @param {Object} client
	 * @param {String} subscribe
	 * @param {function} onConnect
	 */
	clientConnect: function(client, subscribe, onConnect) {

		client.connect({
			onSuccess: function() {
				client.subscribe(subscribe);
				onConnect();
			}
		});
	},

	/**
	 *  向指定远程客户端发送消息
	 * @param {Object} client
	 * @param {String} newMessage
	 * @param {String} destinationName
	 */
	clientSendMessage: function(client, newMessage, destinationName) {
		var message = new Paho.MQTT.Message(newMessage);
		message.destinationName = destinationName;
		client.send(message);
	},

	/**
	 *  当客户端失去链接时调用传入函数，返回值为默认返回值
	 * @param {Object}  client
	 * @param {function}  callBack
	 */
	clientConnectionLost: function(client, callBack) {
		client.onConnectionLost = function onConnectionLost(responseObject) {
			if(responseObject.errorCode !== 0) {
				console.log(responseObject)
				callBack(responseObject);
			}
		}
	},

	/**
	 *  当客户端收到消息时调用传入函数，通过shouldDisconnect来指定是否断开链接
	 * @param {Object} client
	 * @param {boolean} shouldDisconnect -是否在接收到消息之后就断开mqtt客户端的链接
	 * @param {function} callBack
	 */
	clientMessageArrived: function(client, shouldDisconnect, callBack) {
		client.onMessageArrived = function onMessageArrived(message) {
			//			if(shouldDisconnect)
			//				client.disconnect();
			callBack(message);
		}
	}

};

var HttpRequest = {
	doAsyncRequest: function(type, data, httpURL, success, fail) {
		$.ajax({
			type: type,
			url: httpURL,
			data: data,
			dataType: 'json',
			timeout: 10000,
			async: true,
			success: function(responseData) {
				if(responseData === undefined) {
					fail("请求失败");
					return;
				}
				var code = parseInt(responseData["resultCode"]);

				if(code === 1) {
					success(responseData["data"]);
				} else {
					if(code === 20010|| code === 20005 || code === 20001 || code === 20009 || code === 20002 ||
						code === 20008 || code === 10002 || code === 30003 || code === 40005 || code === 10005)
						console.log(HttpRequest.codeString(code));
						fail(HttpRequest.codeString(code));
					//					fail(code);
				}
			},
			error: function(xhr, type) {
				xhr = JSON.stringify(xhr);
				fail(type);
				console.log('\n\ttype:\t' + type + '\n\txhr:\t' + xhr);
			}
		}, 0)
	},
	doRequest: function(type, data, httpURL, success, fail, msg) {
		$.ajax({
			type: type,
			url: httpURL,
			data: data,
			dataType: 'json',
			timeout: 10000,
			async: false,
			success: function(responsedata) {
				if(responsedata === undefined) {
					fail("请求失败");
					return;
				}
				var code = parseInt(responsedata["resultCode"]);

				if(code === 1) {
					success(responsedata["data"]);
				} else {
					if(code === 20010 || code === 20001 || code === 20009 || code === 20002 ||
						code === 20008 || code === 10002 || code === 30003 || code === 40005 || code === 10005)
						fail(HttpRequest.codeString(code));
				}

			},
			error: function(xhr, type) {

				xhr = JSON.stringify(xhr);
				console.log('\n\ttype:\t' + type + '\n\txhr:\t' + xhr);
				fail(xhr);

			}
		}, 0)
	},

	doRequestsyn: function(type, data, httpURL, success, fail, msg) {

		//					iotLog.log("ajax请求" + data, httpURL);
		$.ajax({
			type: type,
			url: httpURL,
			data: data,
			dataType: 'json',
			timeout: 10000,
			async: false,
			success: function(responsedata) {

				if(responsedata === undefined) {
					fail("请求失败");
					return;
				}
				var code = parseInt(responsedata["resultCode"]);
				//				IOTLog.log(data, httpURL);

				if(code == 1) {
					success(responsedata["data"]);
					//					iot.log("requestDat&&&", JSON.stringify(responsedata["data"]));
				} else {
					if(code == 20010 || code == 20001 || code == 20009 || code == 20002 ||
						code == 20008 || code == 10002 || code == 30003 || code == 40005 || code == 10005)
						fail(HttpRequest.codeString(code));
				}

			},
			error: function(xhr, type) {
				//				fail("操作超时,请重试!")
				//				fail(+xhr.toString());
			}
		}, 0)
	},

	codeString: function(code) {
//		console.log(code);
		switch(code) {
			//			case 10002:
			//				{
			//					return "账号或密码错误";
			//				}
			case 20010:
				{
					return "登录已过时,请重新登录!";
				}
			case 20009:
				{
					return "密码错误!";
				}
			case 20001:
				{
					return "用户已经存在";
				}
			case 20002:
				{
					return "用户不存在";
				}
			case 20008:
				{
					return "验证码错误";
				}
			case 10002:
				{
					return "输入错误";
				}
			case 30003:
				{
					return "家电不在线";
				}
			case 40005:
				{
					return "该菜谱已暂停发布";
				}
			case 10005:
				{
					return "图片校验码错误";
				}
			case 20005:
			{
				return "用户未登录";
			}
		}
	}

};

var user = {
	login: function(key, account, password, type, channelId, version, success, fail) {
		var channelIdTrue;
		var version = localStorage.getItem("version_state");
		if(version == "phone") {
			try {
				channelIdTrue = iotChannelId.sendChanelId();
			} catch(e) {
				channelIdTrue = channelId;
			}
		} else {
			channelIdTrue = channelId;
		}
		var data = {
			key: key,
			account: account,
			password: password,
			type: type,
			channelId: channelIdTrue,
			mqttTopic: channelId,
			version: version
		};
		var u = URLSever + "user/login";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在登录");

		function fSuccess(data) {

			iot.log("login" + data.toString())
			success(data);
			//iot.log("cccdarasu",data)
		}

		function fFail(data) {
			fail(data);
			//iot.log("cdfsf",data)
		}
	},
	updatePwd: function(key, token, password, newpassword, type, success, fail) {
		var data = {
			key: key,
			token: token,
			password: password,
			newpassword: newpassword,
			type: type
		};
		var u = URLSever + "user/changepassword";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在修改");

		function fSuccess(data) {

			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//获取忘记密码的验证码
	getForgetPassWordCode: function(key, phonenumber, pictureCode, success, fail) {
		var data = {
			key: key,
			phone: phonenumber,
			pictureCode: pictureCode
		};
		var u = URLSever + "user/resetpassword/checkcode";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在获取验证码");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//提交新密码
	commitNewPassword: function(key, phonenumber, password, checkcode, success, fail) {
		var data = {
			key: key,
			phone: phonenumber,
			password: password,
			checkcode: checkcode
		};
		var u = URLSever + "user/resetpassword";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在提交");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//获取注册的验证码
	getRegisCode: function(key, phonenumber, pictureCode, success, fail) {
		var data = {
			key: key,
			phone: phonenumber,
			pictureCode: pictureCode
		};
		var u = URLSever + "user/register/checkcode";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在获取验证码");

		function fSuccess(data) {

			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//注册
	regis: function(key, phonenumber, password, code, type, success, fail) {
		var data = {
			key: key,
			account: phonenumber,
			password: password,
			checkcode: code,
			type: type
		};
		var u = URLSever + "user/register";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在注册");

		function fSuccess(data) {

			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//用户注销
	signout: function(key, token, type, success, fail) {
		var data = {
			key: key,
			token: token,
			type: type
		};
		var u = URLSever + "user/loginout";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在注销");
		iot.log("u");

		function fSuccess(data) {

			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//保存个人信息
	saveUserInfo: function(key, token, name, sex, age, birthday, address, success, fail) {
		var data = {
			key: key,
			token: token,
			name: name,
			sex: sex,
			age: age,
			birthday: birthday,
			address: address
		};
		var u = URLSever + "user/perfectinfo";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在保存");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//反馈问题
	userOpinion: function(key, token, opinion, phone, imageurl, success, fail) {
		var data = {
			key: key,
			token: token,
			opinion: opinion,
			phone: phone,
			image: imageurl,
		};
		var u = URLSever + "user/opinion";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在提交");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//绑定分享二维码的家电
	bindShareDevice: function(key, token, comment, userId, deviceId, success, fail) {
		var data = {
			key: key,
			token: token,
			comment: comment,
			userId: userId,
			deviceId: deviceId

		};
		var u = URLSever + "user/device/bindr";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在提交");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//	getMyselectImage: function(key, token, imageurl, success, fail) {
	//		var data = {
	//			key: key,
	//			token: token,
	//			image: imageurl
	//
	//		};
	//		var u = URLSever + "/menu/menus/upload";
	//				uploadImage(u,imageurl,data);
	//		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在提交");
	//
	//		function fSuccess(data) {
	//			success(data);
	//		}
	//
	//		function fFail(data) {
	//			fail(data);
	//		}
	//	},
	//首页用户反馈问题，没有图片
	sendOpinionNoImg: function(key, token, opinion, phone, imageURL, success, fail) {
		var data = {
			key: key,
			token: token,
			opinion: opinion,
			phone: phone,
			image: imageURL
		};
		console.log(JSON.stringify(data));
		var u = URLSever + "user/opinion";
		//		var u = "http://192.168.31.212:8080/iot1.5/user/opinion";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail, "正在提交");

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
}

var device = {
	list: function(key, token, success, fail) {
		var data = {
			key: key,
			token: token
		};
		iot.log("device=list", JSON.stringify(data))
		var u = URLSever + "device/list";
		console.log("getgetget")
		//		HttpRequest.doRequest("GET", data, u, fSuccess, fFail);
		HttpRequest.doAsyncRequest("GET", data, u, fSuccess, fFail);

		function fSuccess(data) {
			console.log("ssssssssss"+data)
			success(data);
		}

		function fFail(data) {
			console.log("fffffffff")
			fail(data);
		}
	},
	//解绑设备
	remove_device: function(key, token, did, success, fail) {
		var data = {
			key: key,
			token: token,
			did: did
		};
		var u = URLSever + "user/device/unbind";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	remove_sharedevice: function(key, token, did, uuid, success, fail) {
		var data = {
			key: key,
			token: token,
			did: did,
			userId: uuid
		};
		console.log(JSON.stringify(data))
		var u = URLSever + "user/device/unbind";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//获取设备用户列表接口
	get_device_share_list: function(key, token, deviceId, success, fail) {
		var data = {
			key: key,
			token: token,
			deviceId: deviceId
		};
		var u = URLSever + "device/user/list/query";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//修改设备名字
	changeDeviceName: function(key, token, did, comment, success, fail) {
		var data = {
			key: key,
			token: token,
			did: did,
			comment: comment
		};
		var u = URLSever + "user/device/comment/update";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//查询菜谱指令
	queryCookList: function(key, menuId, menuType, success, fail) {
		var data = {
			key: key,
			menuId: menuId,
			menuType: menuType
		};
		var u = historySever + "/menu/menus/cook/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	cloudMenuCook: function(key, token, menuId, menuType, cmd, did, delay, success, fail) {
		var data = {
			key: key,
			token: token,
			menuId: menuId,
			menuType: menuType,
			cmd: cmd,
			did: did,
			delay: delay
		};
		var u = historySever + "/menu/menus/cook";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

var sendMsg = {
	unpacketData: function(key, token, did, data) {
		var param = {
			key: key,
			token: token,
			did: did,
			data: data
		};

		var u = URLSever + "app/push/device";
		HttpRequest.doRequest("GET", param, u, fSuccess, fFail);

		function fSuccess(data) {
			iot.log("unpacketData===fSuccess", data);
		}

		function fFail(data) {
			iot.log("unpacketData===fFail", data);
		}
	}
};
//

var system = {
	init: function(success, fail) {
		var param = {
			"key": "1",
			"type": "4"
		};

		var u = URLSever + "user/token/init";
		HttpRequest.doRequest("GET", param, u, fSuccess, fFail, "正在設置系統");

		function fSuccess(data) {
			//alert(data)
			success(data);
			var loginID = data["id"];
			localStorage.setItem("topicID", loginID);

		}

		function fFail(data) {
			fail(data);
		}

	}
};

//牛扒机接口
var steak = {
	//历史记录
	historyMenu: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token
		};
		var u = historySever + "/menu/user/menu/history/list";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//牛扒日记列表接口
	steak_steakNote: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token

		};
		var u = historySever + "/steakMachine/device/steak/note";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//写牛扒日记接口
	steak_writeSteakNote: function(key, token, type, taste, star, comment, success, fail) {
		var param = {
			key: key,
			token: token,
			type: type,
			taste: taste,
			star: star,
			comment: comment
		};
		var u = historySever + "/steakMachine/device/steak/note/add";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//删除牛扒日记
	steak_deleteNote: function(key, token, id, success, fail) {
		var param = {
			key: key,
			token: token,
			id: id
		};
		var u = historySever + "/steakMachine/device/steak/note/delete";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}

};

//二维码
var ScanQR = {
	QRBarcode: function(barcode, success, fail) {
		var param = {
			barCode: barcode
		};
		var u = historySever + "/menu/query/menu/ingredients/url";

		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

//发送控制指令
var controlDeviceData = {
	sendControlMsg: function(key, token, did, cmd, menuType, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did,
			cmd: cmd,
			menuType: menuType
		};
		var u = URLSever + "app/push/device/cmd";

		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}

	}
};
function isJSON(str) {
//	console.log(str)
    if (typeof str == 'string') {
        try {
          //  var obj=JSON.parse(str);
            if(str.indexOf('{')>-1){ 	
                return true;
            }else{
                return false;
            }

        } catch(e) {
            console.log(e);
            return false;
        }
    }
    return false;
}
//查询设备信息
var enquiryDeviceData = {
	getDeviceMsg: function(key, token, did, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did
		};
		var u = sapURL + "device/status";
		HttpRequest.doRequestsyn("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			//success(data);
			console.log("data.uart"+data.uart);
			
			console.log("uartObject"+uartObject);
			if(data.uart=="9"||data.uart==""){
				data.uart="";
				success(data);
			}else if(!isJSON(data.uart)){
				success(data);
			}else{
				var uartObject=JSON.parse(data.uart);
				console.log("uartBody"+data.uart.uartBody);
				data.uart=uartObject.uartBody;
				success(data);
			}
		}

		function fFail(data) {
			fail(data);
		}

	}
};

//菜单
var menu = {
	//得到菜谱
	getMenu: function(key, curPage, success, fail) {
		var data = {
			key: key,
			curPage: curPage
		};
		console.log(JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//搜索菜谱或者按类型查找菜谱
	searchMenu: function(key, curPage, menuName, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			menuName: menuName
		};
		console.log(JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	searchMenuUuid: function(key, curPage, menuName, uuid, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			menuName: menuName,
			uuid: uuid
		};
		console.log(JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//根据类型面包、酸奶等选择菜谱
	searchMenuType: function(key, curPage, menuType, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			menuType: menuType
		};
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	//根据类型户外，野餐等选择菜谱
	searchType: function(key, curPage, type, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			type: type
		};
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//根据人气等选择菜谱
	searchTypePopu: function(key, curPage, sortRow, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			sortRow: sortRow
		};
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//根据类型面包、酸奶等选择菜谱
	searchMenuTypeUuid: function(key, curPage, menuType, uuid, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			menuType: menuType,
			uuid: uuid
		};
		console.log("全部===" + JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//根据类型户外，野餐等选择菜谱
	searchTypeUuid: function(key, curPage, type, uuid, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			type: type,
			uuid: uuid
		};
		console.log("精选===" + JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//根据人气等选择菜谱
	searchTypePopuUuid: function(key, curPage, sortRow, uuid, success, fail) {
		var data = {
			key: key,
			curPage: curPage,
			sortRow: sortRow,
			uuid: uuid
		};
		console.log("人气===" + JSON.stringify(data));
		var u = historySever + "/menu/menus/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//得到登录菜谱详情
	getMenuDetail: function(key, token, menuId, menuType, success, fail) {
		var data = {
			key: key,
			token: token,
			menuId: menuId,
			menuType: menuType
		};
		console.log("菜谱详情===" + JSON.stringify(data));
		var u = historySever + "/menu/menus/info";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//得到未登录菜谱详情
	getMenuDetailNoLogin: function(key, menuId, menuType, success, fail) {
		var data = {
			key: key,
			menuId: menuId,
			menuType: menuType
		};
		var u = historySever + "/menu/menus/info";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//收藏或分享菜谱
	collectOrShareMenu: function(key, token, menuId, operateType, menuType, success, fail) {
		var data = {
			key: key,
			token: token,
			menuId: menuId,
			operateType: operateType,
			menuType: menuType
		};
		var u = historySever + "/menu/menus/operate";
		console.log(data)
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//收藏菜谱列表
	collectMenuList: function(key, token, success, fail) {
		var data = {
			key: key,
			token: token
		};
		var u = historySever + "/menu/menus/collect/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//历史菜谱列表
	histroyMenuList: function(key, token, success, fail) {
		var data = {
			key: key,
			token: token
		};
		var u = historySever + "/menu/menus/history";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//取消收藏菜谱
	cancleMenu: function(key, token, menuId, menuType, success, fail) {
		var data = {
			key: key,
			token: token,
			menuId: menuId,
			menuType: menuType
		};

		var u = historySever + "/menu/menus/collect/cancel";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//获取banner
	queryBanner: function(key, bannertype, success, fail) {
		var data = {
			key: key,
			bannertype: bannertype
		};
		var u = historySever + "/menu/query/banner/list";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

//ejCloud 新接口
var ejMenu = {
	getList: function(curPage, classifyid, uuid, success, fail) {
		var data = {};

		data.currentPage = curPage;
		if(null === uuid || undefined === uuid) {

		} else {
			data.uuid = uuid;
		}
		if(classifyid === "null" || classifyid === "" || classifyid === null) {

		} else {

			var order;
			switch(classifyid) {
				case -1:
					order = 'collection_num';
					data.order = order;
					break;
				case -2:
					order = 'cook_nums';
					data.order = order;
					break;
				case -3:
					order = 'publish_date';
					data.order = order;
					break;
				default:
					data.classifyid = classifyid;
					break;
			}
		}
		//		if(classifyid > 0) {
		//			if(!uuid || uuid === null || uuid === undefined) {
		//				data = {
		//					currentPage: curPage,
		//
		//				};
		//			} else {
		//				data = {
		//					currentPage: curPage,
		//					classifyid: classifyid,
		//					uuid: uuid
		//				};
		//			}
		//		} else {
		//			var order;
		//			switch(classifyid) {
		//				case -1:
		//					order = 'collection_num';
		//					break;
		//				case -2:
		//					order = 'cook_nums';
		//					break;
		//				case -3:
		//					order = 'publish_date';
		//					break;
		//				default:
		//					break;
		//			}
		//			data = {
		//				order: order,
		//				currentPage: curPage
		//			}
		//		}

		var u = menuSever + "/menu/16/list";
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//用分类id查找食谱列表
	getMenu: function(curPage, classifyid, order, pageSize, success, fail) {
		var data = {
			currentPage: curPage,
			classifyid: classifyid,
			order: order,
			pageSize: pageSize
		};
		var u = menuSever + "/menu/16/list";
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	//类型索引(面包\蛋糕) menu_classify_tag=class
	getCategoryList_class: function(menu_classify_tag, success, fail) {
		var data = {
			menu_classify_tag: menu_classify_tag
		};
		var u = menuSever + "/classify";
		HttpRequest.doAsyncRequest("GET", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	//类型索引(养生\下午茶) menu_classify_tag=effect
	getCategoryList_effect: function(menu_classify_tag, success, fail) {
		var data = {
			menu_classify_tag: menu_classify_tag
		};
		var u = menuSever + "/classify";
		HttpRequest.doAsyncRequest("GET", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	/**
	 * 根据menuID查询该食谱支持的设备的UUID
	 * @param menuID
	 * @param success
	 * @param fail
	 */
	getRecipeUUID: function(menuID, success, fail) {
		var u = menuSever + "/menu/16/menu/selectuuid";
		// var u = testHost + '/scloudmenu/menu/16/menu/selectuuid';
		var data = {
			'menuid': menuID
		};
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	getDeviceList: function(token, success, fail) {
		var data = {
			token: token
		};
		var u = URLSever + "device/list";
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}

};

//面包机我的收藏
var collect = {
	//新建分组
	collect_newGroup: function(key, token, name, success, fail) {
		var data = {
			key: key,
			token: token,
			name: name
		};
		var u = historySever + "/menu/user/menu/group";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//得到分组列表
	collect_getGroups: function(key, token, success, fail) {
		var data = {
			key: key,
			token: token
		};
		var u = historySever + "/menu/user/menu/groups";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//修改分组名称
	collect_editGroupName: function(key, token, name, groupId, success, fail) {
		var data = {
			key: key,
			token: token,
			name: name,
			groupId: groupId
		};
		var u = historySever + "/menu/user/menu/group/update";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//删除分组
	collect_delGroup: function(key, token, groupId, success, fail) {
		var data = {
			key: key,
			token: token,
			groupId: groupId
		};
		var u = historySever + "/menu/user/menu/group/delete";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//收藏菜谱
	collect_collection: function(key, token, groupId, menuId, success, fail) {
		var data = {
			key: key,
			token: token,
			groupId: groupId,
			menuId: menuId
		};
		var u = historySever + "/menu/user/menu/group/collection";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//取消收藏菜谱
	collect_cancleCollection: function(key, token, groupId, menuId, success, fail) {
		var data = {
			key: key,
			token: token,
			groupId: groupId,
			menuId: menuId
		};
		var u = historySever + "/menu/user/menu/group/collection/cancel";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

//面包机历史记录
var bread = {
	//历史记录
	breadHistory: function(key, token, menuId, menuType, menuName, delay, deviceId, success, fail) {
		var param = {
			key: key,
			token: token,
			menuId: menuId,
			menuType: menuType,
			menuName: menuName,
			delay: delay,
			deviceId: deviceId
		};
		var history = historySever + "/menu/user/menu/history";
		HttpRequest.doRequest("POST", param, history, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//历史记录列表
	breadHistoryList: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token
		};
		var historyRecodeList = URLSever + "/menu/user/menu/history/list";
		HttpRequest.doRequest("POST", param, historyRecodeList, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//历史记录列表删除
	breadHistoryDeleteList: function(key, token, id, success, fail) {
		var param = {
			key: key,
			token: token,
			id: id
		};
		var historyDeleteList = URLSever + "/menu/user/menu/history/delete";
		HttpRequest.doRequest("POST", param, historyDeleteList, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
}
//面包机二代今日推荐

var IOTBreadSecond = {
	diaryRecommend: function(uuid, mac, did, success, fail) {
		var param = {
			uuid: uuid,
			mac: mac,
			did: did,
		};
		var u = historySeverManagement + "/admin/device/information/getInformationStatus";
		// 			var u= "http://192.168.31.22:8081/manager/device/information/getInformationStatus";
		//		requestKuaYuURL("get", u, param, success, fail);
		console.log(param)
		//		 var u = "http://120.24.170.30:8082/admin/device/information/getInformationStatus";
		requestKuaYuURL("GET", u, param, success, fail);

	},
	diaryRecommendList: function(success, fail) {
		var u = "http://192.168.31.22:8081/manager/device/informationType/deviceInformationTypes";
		requestKuaYuURL("GET", u, "", success, fail);
		//		HttpRequest.doRequest("POST", "", u, fSuccess, fFail);
		//
		//		function fSuccess(data) {
		//			success(data);
		//		}
		//
		//		function fFail(data) {
		//			fail(data);
		//		}

	},
	deviceFirmwareUpdata: function(key, token, did, uuid, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did,
			uuid: uuid
		};
		var u = URLSever + "device/version/check";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	deviceVersionInfo: function(key, token, brand, type, model, success, fail) {
		var param = {
			key: key,
			token: token,
			brand: brand,
			type: type,
			model: model
		};
		var u = URLSever + "user/device/version/get";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	updateDeviceInfo: function(key, token, did, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did
		};
		var u = URLSever + "push/device/up";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	getUpdataState: function(key, token, did, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did,
		};
		console.log(param)
		var u = URLSever + "device/status";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},

	DIYMenuList: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token,
		};
		var u = historySever + "/menu/menus/diy/list";

		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	myDIYShareList: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token,
		};
		var u = historySever + "/menu/menus/share/list";

		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	commentBreadMenu: function(key, token, menuID, menuType, star, imageurl, success, fail) {
		var param = {
			key: key,
			token: token,
			menuId: menuID,
			menuType: menuType,
			star: star,
			image: imageurl,
		};
		var u = historySever + "/menu/menus/comment";
		console.log("评论参数==" + JSON.stringify(param))
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);

		}

		function fFail(data) {
			fail(data);
		}
	},
	DIYBreadMenuSave: function(key, token, uuid, order, manuName, menuingredients, menuType, imageurl, totalTime, introduce, did, delay, savesuccess, savefail) {
		var param = {
			key: key,
			token: token,
			uuid: uuid,
			order: order,
			menuName: manuName,
			menuIngredients: menuingredients,
			menuType: menuType,
			image: imageurl,
			totalTime: totalTime,
			introduce: introduce,
			did: did,
			delay: delay,
		};
		var data = {};
		data.token = token;
		data["menu_name"] = manuName;
		data["menu_totaltimes"] = totalTime;
		data["menu_diy"] = 2;
		data["did"] = 2;
		data["menu_cmd"] = introduce;
		//		data["baseinfo.menu_name"] = manuName;
		//		data["baseinfo.menu_name"] = manuName;
		//		data["baseinfo.menu_name"] = manuName;

		//	  IOTRecipe.DIYBreadMenuSave("2",)
		console.log("sava === " + JSON.stringify(param));
		var u = historySever + "/menu/menus/diy/save";
		IOTDIYRecipe.saveRecipe(data, saveRecipefSuccess, saveRecipefFail);

		//		HttpRequest.doRequest("GET", param, u, fSuccess, fFail);
		function saveRecipefSuccess(data) {
			savesuccess(data);
		}

		function saveRecipefFail(data) {
			fail(data);
		}
	},
	scanRecommandFood: function(key, barCode, success, fail) {
		var param = {
			key: key,
			barCode: barCode,
		};
		var u = historySever + "/menu/menus/recommend";

		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	getBreadSecondFoodInfo: function(key, success, fail) {
		var param = {
			key: key,
		};
		var u = "http://eg-live.com/getGoods.htm";
		HttpRequest.doRequest("get", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	breadSecondUserRecord: function(key, token, did, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did,
		};
		var u = historySever + "/menu/menus/history";
		HttpRequest.doRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//取消菜谱历史记录
	deleteMyDIYMenu: function(key, token, menuId, success, fail) {
		var data = {
			key: key,
			token: token,
			id: menuId,
		};
		//		console.log("ppppppppppppppppppp" + JSON.stringify(data))
		var u = historySever + "/menu/user/menu/delete";

		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {

			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}

}

function requestKuaYuURL(requestType, u, data, success, fail) {

	$.ajax({
		url: u,
		type: requestType,
		async: false,
		dataType: "jsonp",
		data: data,
		jsonp: "callbackparam", //服务端用于接收callback调用的function名的参数
		jsonpCallback: "success_jsonpCallback", //callback的function名称,服务端会把名称和data一起传递回来
		success: function(json) {
			success(json);
		},
		error: function() {
			fail("error");
		}
	});
}

function getHistorySeverManagementAdress() {
	return pictureAddress;
}

//水壶
var IOTKettle = {
	//定时任务
	timingTast: function(key, token, type, name, cmd, did, pushdate, week, success, fail) {
		var param = {
			key: key,
			token: token,
			type: type,
			name: name,
			cmd: cmd,
			did: did,
			pushdate: pushdate,
			week: week,
		};
		var url = URLSever + "user/timing/device";
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		//		requestKuaYuURL("GET", url, param, success, fail);
		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//预约列表查询
	timingList: function(key, token, success, fail) {
		var param = {
			key: key,
			token: token,
		};
		var url = URLSever + "user/timing/list";
		//		requestKuaYuURL("GET", url, param, success, fail);
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//开启关闭预约
	timingSet: function(key, token, id, state, success, fail) {
		var param = {
			key: key,
			token: token,
			id: id,
			state: state,
		};
		var url = URLSever + "user/timing/set";
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//删除预约列表
	timingDelete: function(key, token, id, success, fail) {
		var param = {
			key: key,
			token: token,
			id: id,
		};
		var url = URLSever + "user/timing/delete";
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//预约列表更新
	timingUpdate: function(key, token, id, cmd, name, week, pushdate, type, success, fail) {
		var param = {
			key: key,
			token: token,
			id: id,
			cmd: cmd,
			name: name,
			week: week,
			pushdate: pushdate,
			type: type
		};
		var url = URLSever + "user/timing/update";
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	kettleData: function(key, time, did, success, fail) {
		var param = {
			key: key,
			time: time,
			did: did
		};
		var url = URLSever + "kettle/data/query";
		HttpRequest.doRequest("POST", param, url, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

/***
 *设备系统
 */
var IOTsap = {
	device_state: function(token, did, success, fail) {

		var timestamp = Date.parse(new Date());
		timestamp = timestamp / 1000;
		var param = {
			token: token,
			did: did
		};
		param.timestamp = timestamp;
		var url = sapURL + "device/status";
		HttpRequest.doAsyncRequest("POST", param, url, csuccess, cfail);

		function csuccess(data) {
//			console.log(data)
			//success(data.uart, data.did);
//			console.log("dataisjson?"+isJSON(data.uart));
			if(data.uart=="9"||data.uart==""){
				success("",data.did);
			}else if(!isJSON(data.uart)){    //判断是否为json字符串
				success(data.uart,data.did);
			}else{
				var uartObject=JSON.parse(data.uart);  //转成json对象
				console.log("uartBody"+uartObject.uartBody);
				success(uartObject.uartBody,data.did);
			}
		}

		function cfail(data) {
			fail(data, did);
		}

	},
	device_state3: function(token, did, success, fail) {

		var timestamp = Date.parse(new Date());
		timestamp = timestamp / 1000;
		var param = {
			token: token,
			did: did
		};
		param.timestamp = timestamp;
		var url = sapURL + "device/status";
		HttpRequest.doAsyncRequest("POST", param, url, csuccess, cfail);

		function csuccess(data) {
			//success(data.uart, data.did);
//			console.log("dataisjson?"+isJSON(data.uart));
			if(data.uart=="9"||data.uart==""){
				success("",data.did);
			}else if(!isJSON(data.uart)){
				success(data.uart,data.did);
			}else{
				var uartObject=JSON.parse(data.uart);
//				console.log("uartBody"+uartObject.uartBody);
				success(uartObject.data,data.did);
			}
		}

		function cfail(data) {
			fail(data, did);
		}

	},
	device_control: function(token, did, uartbody, success, fail) {
		var param = {
			token: token,
			did: did,
			uartboby: uartbody
		};
		var timestamp = Date.parse(new Date());
		timestamp = timestamp / 1000;
		param.timestamp = timestamp;
		console.log("aaa")
		var url = sapURL + "device/control";
		HttpRequest.doAsyncRequest("get", param, url, csuccess, cfail);

		function csuccess(data) {
			success(data);
		}

		function cfail(data) {
			fail(data);
		}

	},
	device_control_diy: function(token, did, uart, menuid, success, fail) {
		var param = {
			token: token,
			did: did,
			uart: uart,
			menuid: menuid
		};
		var url = menuSever + "/menu/16/control";
		HttpRequest.doAsyncRequest("post", param, url, csuccess, cfail);

		function csuccess() {
			success();
		}

		function cfail(data) {
			fail(data);
		}

	},
	device_info: function(key, token, did, success, fail) {
		var param = {
			key: key,
			token: token,
			did: did
		};
		var u = URLSever + "device/details";
		HttpRequest.doAsyncRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	//修改设备名字
	device_name_change: function(key, token, did, name, success, fail) {
		var data = {
			key: key,
			token: token,
			did: did,
			name: name
		};
		var u = URLSever + "user/device/comment/update";
		HttpRequest.doRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	},
	/**
	 * 根据uuid获得支持的设备名称
	 * @param uuid
	 * @param success
	 * @param fail
	 */
	device_select_product: function(uuid, success, fail) {
		var data = {
			uuid: uuid
		};
		var u = sapURL + "device/getdeviceproduct";
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	}
};

/**
 * 用来管理和食谱相关的网络请求
 * @type {{saveRecipe, saveRecipeCommand, recipeList, deleteDIYRecipe, recipeDetail, saveRecipeImages, editRecipe}}
 */
var IOTDIYRecipe = function() {
	// var menuSever = historySever;
	// var testHost = 'http://192.168.61.105:8180';
	// var testHost = 'http://192.168.61.101:8080';

	/**
	 * 根据云食谱进行烹饪
	 * @param token
	 * @param command
	 * @param success
	 * @param fail
	 */
	var cookAccording2Recipe = function(token, command, success, fail) {
		var u = menuSever + "/menu/16/delete";

		var param = {
			'token': token,
			'command': command
		};
		HttpRequest.doAsyncRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	/**
	 * 删除云食谱接口
	 * @param menuID 云食谱ID
	 * @param success 成功回调
	 * @param fail 失败回调
	 */
	var deleteDIYRecipe = function(token, menuID, success, fail) {
		var u = menuSever + "/menu/16/delete";
		var param = {
			'token': token,
			'menuid': menuID
		};
		HttpRequest.doAsyncRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	/**
	 * 保存DIY食谱的指令
	 * @param menuID    云食谱ID
	 * @param command   该食谱命令
	 * @param uuid  本设备的uuid
	 * @param success   成功回调
	 * @param fail  失败回调
	 */
	var saveRecipeCommand = function(menuID, command, uuid, success, fail) {
		var u = menuSever + "/menu/16/uart/save";
		// var u = testHost + '/scloudmenu/menu/16/uart/save';

		var param = {
			'menu_cmd': command,
			'menu_id': menuID,
			'uuid': uuid
		};
		HttpRequest.doAsyncRequest("POST", param, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	/**
	 * 保存云食谱图片
	 * @param token 用户的token
	 * @param rst localResizeIMG返回的rst对象
	 * @param success
	 * @param fail
	 */
	var saveRecipeImages = function(token, rst, success, fail) {

		var u = menuSever + "/file";
		// var u = testHost + '/scloudmenu/file';

		// 额外添加参数
		rst.formData.append('token', token);

		$.ajax({
			url: u,
			data: rst.formData,
			processData: false,
			contentType: false,
			type: 'POST',
			success: fSuccess,
			error: fFail
		});

		function fSuccess(data) {
			data = JSON.parse(data);
			if(data.resultCode === '1' && data.data) {
				success(data.data);
			} else {
				fail(data);
			}
		}

		function fFail(data) {
			fail(data);
		}
	};

	/**
	 * 保存DIY食谱基本信息
	 * @param request   基本信息的请求体
	 * @param success   成功回调，返回云食谱ID
	 * @param fail  失败回调
	 */
	var saveRecipe = function(request, success, fail) {
		var u = menuSever + "/menu/16/save";
		// var u = testHost + '/scloudmenu/menu/16/save';

		HttpRequest.doAsyncRequest("POST", request, u, fSuccess, fFail);
		function fSuccess(data) {
			console.log("success"+data.remarks);
			success(data);
		}

		function fFail(data) {
			console.log("fail"+data.remarks);
			fail(data);
		}
	};

	/**
	 * 获取diy食谱列表
	 * @param token
	 * @param did
	 * @param currentPage
	 * @param success
	 * @param fail
	 */
	var recipeList = function(token, did, currentPage, success, fail) {
		var u = menuSever + "/menu/16/user/diy/list";
		// var u = testHost + '/scloudmenu/menu/16/user/diy/list';

		var data = {
			'token': token,
			'did': did,
			'currentPage': currentPage
		};
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	/**
	 * 请求云食谱详情
	 * @param menuid 云食谱ID
	 * @param success 成功回调
	 * @param fail 失败回调
	 */
	var recipeDetail = function(menuid, success, fail) {
		var u = menuSever + "/menu/16/info";
		// var u = testHost + '/scloudmenu/menu/16/info';
		var data = {
			'menuid': menuid
		};
		HttpRequest.doAsyncRequest("POST", data, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	var editRecipe = function(request, success, fail) {
		var u = menuSever + "/16/save";
		// var u = testHost + '/scloudmenu/menu/16/save';

		HttpRequest.doAsyncRequest("POST", request, u, fSuccess, fFail);

		function fSuccess(data) {
			success(data);
		}

		function fFail(data) {
			fail(data);
		}
	};

	return {
		saveRecipe: saveRecipe,
		saveRecipeCommand: saveRecipeCommand,
		recipeList: recipeList,
		deleteDIYRecipe: deleteDIYRecipe,
		recipeDetail: recipeDetail,
		saveRecipeImages: saveRecipeImages,
		editRecipe: editRecipe
	};
}();

function refreshCurrentPage(token) {
	localStorage.isLogin = true;
	var loginUserInfo = JSON.parse(localStorage.loginUserInfo);
	if(loginUserInfo == null) {
		loginUserInfo = {};
	}
	loginUserInfo.token = token;
	localStorage.loginUserInfo = JSON.stringify(loginUserInfo);
	location.reload(true);
}