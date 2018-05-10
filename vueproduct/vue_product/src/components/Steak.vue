<template>
    <div class="steak">
    <!--导航栏_S-->
		<header class="mui-bar mui-bar-nav">
		    <a class="mui-pull-left mui-action-back"><i></i></a>
		    <h1 class="mui-title">{{title}}</h1>
		    <!--<a id="steark_set" class="mui-pull-right" href="#popover"><s></s></a>-->		    
		    <router-link :to='{name:"test"}' class="mui-pull-right"><s></s></router-link>		    
		</header>
		<div id="popover" class="mui-popover list">
			<ul class="table-view">
				<li class="table-view-cell" id="steak_info"><a href="#">设备信息</a></li>
				<li class="table-view-cell" id="cooking_records"><a href="#">烹饪记录</a></li>
				<li class="table-view-cell" id="share_device"><a href="#">分享设备</a></li>
				
				<li @click="clickFn" class="table-view-cell" id="test2" ><a>test</a></li>
			<!--<router-link tag="li" :to='{name:"test"}' class="table-view-cell" id="test1" ><a>test</a></router-link>-->
			</ul>
		</div>
		
		<!--导航栏_E-->
		<div class="mui-content">
			<!--设备状态_S-->
			
			<div class="state">
				<!--童锁_S-->
				<div class="childlock">
					<div class="lockbg" id="childLock"></div>
					<p>童锁</p>
				</div>
				<!--童锁_E-->
				<!--扫一扫_S-->
				<div class="scan">
					<div class="scanbg"></div>
					<p>扫一扫</p>
				</div>
				<!--扫一扫_E-->
				<div class="round">  
					<canvas id="canvas" width="400" height="400" v-drawProgressBar="time"></canvas>
				</div>
				<a href="#pop" class="stemdiv" id="changeTemperature">
					<span class="stem">上盘:<span id="upDiskTemperature">0</span>℃</span>
					<span class="stem">下盘:<span id="downDiskTemperature">0</span>℃</span>
					<s></s>
				</a>
				<div class="startbtn">开始</div>
				<!--<input type="button" name="startbtn" class="startbtn" value="开始" />-->
			</div>
			<!--设备状态_E-->
			<!--食谱菜单_S-->
			<div class="menu_div">
				<div class="menu_top clearfix">
					<div class="menu_top_l">
						<div class="menu_box">
							<div class="title"><s></s><p>云食谱</p></div>
							<p class="detail">食谱一键制作</p>
							<a></a>
						</div>
					</div>
					<div class="menu_top_r">
						<div class="menu_box">
							<div class="title"><s></s><p>DIY食谱</p></div>
							<p class="detail">秀出自己的特色</p>
							<a></a>
						</div>
					</div>
				</div>
				<div class="menu_bottom clearfix">
					<div class="menu_bottom_l">
						<div class="menu_box">
							<div class="title"><p>选购牛扒</p><s></s></div>
							<p class="detail">高质量食材</p>
							<a></a>
						</div>
					</div>
					<div id="menu_bottom_r" class="menu_bottom_r">
						<div class="menu_box">
							<div class="title"><p>你懂牛扒吗</p><s></s></div>
							<p class="detail">来源于欧洲中世纪</p>
							<a></a>
						</div>
					</div>
				</div>
			</div>
			<!--食谱菜单_E-->
		</div>
		<!--弹出层_S-->
		<div id="pop" class="mui-popover set">
			<!--功能按钮区_S-->
			<ul class="functions clearfix" id="functions">
				<li class="btn">
					<div class="icon1-s"></div>
					<p class="type">煎烤</p>
				</li>
				<li class="btn">
					<div class="icon2"></div>
					<p class="type">解冻</p>
				</li>
			</ul>
			<!--功能按钮区_E-->
			<!--滑块_S-->
			<div class="range">
				<div class="range_top">
					<div class="input clearfix">
						<span class="temp">上盘</span>
						
						<div class="mui-input-row mui-input-range">
							<div id="lineDiv1" class="lineDiv">
					            <div id="minDiv1" class="minDiv">
					                <div class="val"><span id="vals1">0</span><span>℃</span></div>
					            </div>
					        </div>
					        <i class="repeater"></i>
						</div>
					</div>
					<p>
						<span class="lower">0℃</span>
						<span class="middle">30℃</span>
						<span class="suggest">建议170℃</span>
						<span class="upper">250℃</span>
					</p>
				</div>
				<div class="range_middle">
					<div class="input clearfix">
						<span class="temp">下盘</span>
						<div class="mui-input-row mui-input-range">
							<div id="lineDiv2" class="lineDiv">
					            <div id="minDiv2" class="minDiv">
					                <div class="val"><span id="vals2">0</span><span>℃</span></div>
					            </div>
					        </div>
							<i class="repeater"></i>
						</div>
					</div>
					<p>
						<span class="lower">0℃</span>
						<span class="middle">30℃</span>
						<span class="suggest">建议170℃</span>
						<span class="upper">250℃</span>
					</p>
				</div>
				<div class="range_bottom">
					<div class="input clearfix">
						<span class="temp">时间</span>
						<div class="mui-input-row mui-input-range">
							<div id="lineDiv3" class="lineDiv">
					            <div id="minDiv3" class="minDiv">
					                <div class="val"><span id="vals3">00:00</span></div>
					            </div>
					        </div>
						</div>
					</div>
					<p><span class="lower">00:00</span><span class="upper">15:00</span></p>
				</div>
			</div>
			<!--滑块_E-->
			<!--确定按钮_S-->
			<button class="startBtn startBtn_n" id="startButton">确定</button>
			<!--确定按钮_E-->
			<!--取消按钮_S-->
			<button class="dismiss" id="dismiss"></button>
			<!--取消按钮_E-->
		</div>
		<!--弹出层_E-->
    </div>
</template>

<script>
// import  '../assets/js/api.js'
// import  '../assets/js/IOTUtilities.js'
// import  '../assets/js/IOTSteakController.js'
import  '../assets/js/range.js'

export default {
    name: 'Steak',
    data () {
        return {
            title: '牛扒机',
			time:{
				minute:'0',
				second:'0',
				state:'状态更新中'
			}
		}
	},
	created:function(){
		this.show()
	},
	directives:{
		drawProgressBar:{
			bind:function(el,binding){
				var ctx=el.getContext('2d');  
				ctx.scale(2,2);
				ctx.canvas.width=400;  
				ctx.canvas.height=400;  
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
				ctx.arc(200,200,194,-90*Math.PI/180,(0*3.6-90)*Math.PI/180);  
				ctx.stroke();  
				//绘制中间的文字  
				ctx.font='28px PingFangSC-Regular';  
				ctx.fillStyle='#898989 100%';  
				ctx.textBaseline='middle';  
				ctx.textAlign='center';  
				ctx.fillText('剩余时间',200,100); 
				
				ctx.font='72px DISPLAYFREETFB';  
				ctx.fillStyle='#4A4A4A 100%';  
				ctx.textBaseline='middle';  
				ctx.textAlign='center';
				if((binding.value.minute+"").length<2){
					binding.value.minute="0"+binding.value.minute;
				}
				if((binding.value.second+"").length<2){
					binding.value.second="0"+binding.value.second;
				}
				ctx.fillText(binding.value.minute+':'+binding.value.second,200,180); 
				
				ctx.font='28px PingFangSC-Regular';  
				ctx.fillStyle='#898989 100%';  
				ctx.textBaseline='middle';  
				ctx.textAlign='center';
				ctx.fillText('(min)',200,250); 
				
				ctx.font='28px PingFangSC-Regular';  
				ctx.fillStyle='#898989 100%';  
				ctx.textBaseline='middle';  
				ctx.textAlign='center';
				ctx.fillText(binding.value.state,200,320);
			}
		}
	},
  	methods:{
		show(){
			var sds = 'hddis';
			console.log(sds);
		},
		clickFn(){
			this.$router.push({path:'/test'});
			var mark = document.getElementsByClassName('mui-backdrop')[0];
			mark.classList.remove('mui-active');
			mark.style.display="none";
		}
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->

<style lang="css" src="../assets/css/reset.css"></style>
<style lang="css" src="../assets/css/IOTSteak.css"></style>