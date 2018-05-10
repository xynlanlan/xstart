/**
 * Created by ccd on 2017/5/3.
 */

/**
 * 不知道干什么的公用对象，提供多种公共函数
 * @type {{}}
 */
var IOTUtilities = {
	tool: function() {
		/**
		 * 获取当前节点的索引
		 * @param ele 需要获取索引的元素
		 * @returns {number} 索引值
		 */
		var getChildrenIndex = function(ele) {
			var i = 0;
			while(ele = ele.previousElementSibling) {
				i++;
			}
			return i;
		};

		/**
		 * 数组去除重复元素
		 * @param array 有重复元素的数组
		 * @returns {Array} 去除重复元素的数组
		 */
		var removeArrayDuplication = function(array) {
			var uq = {};
			var rq = [];
			var prefix = '';
			for(var i = 0; i < 8; i++) {
				if(typeof array[i] === 'string') {
					prefix = '_str';
				} else {
					prefix = '';
				}
				if(!uq[array[i] + prefix]) {
					uq[array[i] + prefix] = true;
					rq.push(array[i]);
				}
			}
			return rq;
		};

		return {
			getChildrenIndex: getChildrenIndex,
			removeArrayDuplication: removeArrayDuplication
		}
	}(),

	param: function() {
		/**获取URL中的明文参数
		 *
		 * @param {Object} name 参数的key
		 */
		var getQueryString = function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if(r !== null) return unescape(r[2]);
			return null;
		};

		var getMQTTTopic = function() {
			var topic;
			try {
				topic = JSON.parse(localStorage.loginUserInfo).topic;
			} catch(e) {
				try {
					topic = IOTUtilities.param.getQueryString('topic');
					if(!(topic && topic.length > 0)) {
						IOTUtilities.native.isLoginFail("登录已过时,请重新登录!");
					}
				} catch(e) {
					IOTUtilities.native.isLoginFail("登录已过时,请重新登录!");
				}
			}
			return topic;
		};

		return {
			getQueryString: getQueryString,
			getMQTTTopic: getMQTTTopic
		}
	}(),

	/**
	 * 和字符串相关工具函数
	 */
	string: function() {
		var timeToString = function(num) {
			if(num < 10) {
				return "0" + num;
			} else {
				return num;
			}
		};

		var minToString = function(upper, lower) {
			var string = "";
			if(upper > 0 && upper < 256) {
				string = string + upper + tempToString(lower);
			} else {
				string = string + lower;
			}
			return string;
		};

		var tempToString = function(num) {
			if(num < 10) {
				return "00" + num;
			} else if(num > 9 && num < 100) {
				return "0" + num;
			} else {
				return num;
			}
		};

		var decimal2Hex = function(decimal) {
			var integer = Number(decimal);
			var hex = integer.toString(16);
			if(hex.length === 1) {
				return '0' + hex;
			} else if(hex.length === 2) {
				return '' + hex;
			} else {
				return '00';
			}
		};

		/** 把秒数转化为所需要的时间字符串
		 *
		 * @param {Object} date 需要格式化的时间
		 */
		var dateFormatter = function(date) {
			var min = Math.floor(date / 60);
			var sec = date % 60;
			min = '' + min;
			sec = '' + sec;
			min = min.length < 2 ? '0' + min : min;
			sec = sec.length < 2 ? '0' + sec : sec;
			return min + ':' + sec;
		};

		return {
			timeToString: timeToString,
			minToString: minToString,
			tempToString: tempToString,
			decimal2Hex: decimal2Hex,
			dateFormatter: dateFormatter
		}
	}(),

	/**
	 * 与原生交互相关
	 */
	native: function() {
		/**
		 * 点击了分享按钮，调用原生端友盟分享
		 * @param URL 用来分享的URL
		 */
		var share = function(URL) {
			try {
				if(URL && URL.length > 0) {
					IOTNavigationManager.showShareButton(URL);
				} else {
					IOTNavigationManager.showShareButton();
				}
			} catch(e) {
				console.log('原生端未成功注入IOTNavigationManager对象或者未实现showShareButton方法\n' + e);
			}
		};

		var openWindow = function(url) {
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			var result = pathName.substr(0, index + 1);
			var URL = "//" + window.location.host + result + "/" + url;
			if(url.substr(0,7)==="http://")
			{
				URL = url
			}
			try {
				iotWindow.open(URL);
			} catch(e) {
				mui.openWindow({
					url: URL,
					id: 'url'
				})
			}
		};

		var getToken = function() {
			var token;
			try {
				token = IOTUserInfo.u_token();
			} catch(e) {
				if(token === undefined || token === null || token.length < 1) {
					token = IOTUtilities.param.getQueryString('token');
					if(!token || token.length < 1) {
						IOTUtilities.native.isLoginFail("登录已过时,请重新登录!");
					}
					var userModel = JSON.parse(localStorage.loginUserInfo);
					userModel.token = token;
					localStorage.loginUserInfo = JSON.stringify(userModel);
				}
			}
			return token;
		};

		var saveToken = function() {
			var token = IOTUtilities.param.getQueryString('token');
			if(token && token.length > 0) {
				var loginUserInfo;
				try {
					loginUserInfo = JSON.parse(localStorage.loginUserInfo);
				} catch(e) {
					loginUserInfo = {};
				}
				loginUserInfo.token = token;
				localStorage.loginUserInfo = JSON.stringify(loginUserInfo);
			} else {
				console.log('参数中没有token');
			}
		};

		var saveDid = function() {
			var did = IOTUtilities.param.getQueryString('did');
			if(did && did.length > 0) {
				localStorage.device_did = did;
			} else {
				console.log('参数中没有did');
			}
		};

		var isLoginFail = function(data) {
			if(data === "登录已过时,请重新登录!") {
				//清除缓存
				localStorage.setItem("loginUserInfo", "");
				localStorage.setItem("area", "");
				localStorage.setItem("device_share", "");
				localStorage.setItem("devicesInfo", "");
				localStorage.setItem("isLogin", "");
				localStorage.setItem('isLoginFail', 'true');
				self.location = appUrl + '/webapp/login.html';
			}
		};

		return {
			openWindow: openWindow,
			share: share,
			isLoginFail: isLoginFail,
			getToken: getToken,
			saveToken: saveToken,
			saveDid: saveDid
		}
	}(),

	/**
	 * 与界面交互有关
	 */
	interface: function() {
		/** 确定取消弹窗
		 *
		 * @param {Object} title 弹窗标题
		 * @param {Object} describe    弹窗描述
		 * @param {Object} confirm    确认回调
		 * @param {Object} cancel    取消回调
		 */
		var confirmDialog = function(title, describe, confirm, cancel) {
			try {
				var btnArray = ['取消', '确定'];
				mui.confirm(describe, title, btnArray, function(e) {
					if(e.index === 1) {
						confirm();
					} else {
						cancel();
					}
				});
			} catch(e) {
				console.log('mui或许没有导入\n' + e);
			}
		};

		var alertDialog = function(title, describe, confirm) {
			mui.alert(describe, title, function(e) {
				confirm(e);
			});
		};

		/**
		 * 设置navigationBar里面的标题
		 * @param newTitle 新的标题
		 */
		function setTitle(newTitle) {
			document.querySelector('.mui-bar-nav .mui-title').innerHTML = newTitle;
		}

		return {
			alertDialog: alertDialog,
			confirmDialog: confirmDialog,
			setTitle: setTitle
		}
	}(),
	/**
	 * 画进度条
	 * 当是面包机时,minute可做小时,second做分钟
	 */
	draw:function(){
		var drawProgressBar= function(w, h, val, minute,second,status,element) {
			 //先创建一个canvas画布对象，设置宽高  
            var c=document.getElementById(element);  
            var ctx=c.getContext('2d');  
            ctx.scale(2,2);
            ctx.canvas.width=w;  
            ctx.canvas.height=h;  
            //圆环有两部分组成，底部灰色完整的环，根据百分比变化的环  
            //先绘制底部完整的环  
            //起始一条路径  
            ctx.beginPath();  
            //设置当前线条的宽度  
            ctx.lineWidth=4;//10px  
            //设置笔触的颜色  
            ctx.strokeStyle='#CCCCCC';  
            //arc()方法创建弧/曲线（用于创建圆或部分圆）arc(圆心x,圆心y,半径,开始角度,结束角度)  
            ctx.arc(200,200,194,0,2*Math.PI);            
            //绘制已定义的路径  
            ctx.stroke();  
              
            //绘制根据百分比变动的环  
            ctx.beginPath();  
            ctx.lineWidth=6;  
            ctx.strokeStyle='#3CADFF';  
            //设置开始处为0点钟方向（-90*Math.PI/180）  
            //x为百分比值（0-100）  
            ctx.arc(200,200,194,-90*Math.PI/180,(val*3.6-90)*Math.PI/180);  
            ctx.stroke();  
            //绘制中间的文字  
            ctx.font='28px PingFangSC-Regular';  
            ctx.fillStyle='#898989 100%';  
            ctx.textBaseline='middle';  
            ctx.textAlign='center';  
            //ctx.fillText(val+'%',100,50);
            ctx.fillText('剩余时间',200,100); 
            
            ctx.font='72px DISPLAYFREETFB';  
            ctx.fillStyle='#4A4A4A 100%';  
            ctx.textBaseline='middle';  
            ctx.textAlign='center';
            if((minute+"").length<2){
            	minute="0"+minute;
            }
            if((second+"").length<2){
            	second="0"+second;
            }
            ctx.fillText(minute+':'+second,200,180); 
            
            ctx.font='28px PingFangSC-Regular';  
            ctx.fillStyle='#898989 100%';  
            ctx.textBaseline='middle';  
            ctx.textAlign='center';
            ctx.fillText('(min)',200,250); 
            
            ctx.font='28px PingFangSC-Regular';  
            ctx.fillStyle='#898989 100%';  
            ctx.textBaseline='middle';  
            ctx.textAlign='center';
            ctx.fillText(status,200,320);
		};
		return{
			drawProgressBar:drawProgressBar
		}
	}()
};