window.onload = function() {
    range1('lineDiv1','minDiv1','vals1');
    range1('lineDiv2','minDiv2','vals2');
    range2('lineDiv3','minDiv3','vals3');
}
function range1(lineDiv,minDiv,vals){
	var lineDiv = document.getElementById(lineDiv); //长线条
    var minDiv = document.getElementById(minDiv); //小方块
    var vals = document.getElementById(vals);
    var ifBool = false; //判断鼠标是否按下
    //事件
    var start = function(e) {
        e.stopPropagation();
        ifBool = true;
//      console.log("鼠标按下")
    }
    var move = function(e) {
//      console.log("鼠标拖动")
        if(ifBool) {
            if(!e.touches) {    //兼容移动端
                var x = e.clientX;
            } else {     //兼容PC端
                var x = e.touches[0].pageX;
            }
            //var x = e.touches[0].pageX || e.clientX; //鼠标横坐标var x
            var lineDiv_left = getPosition(lineDiv).left; //长线条的横坐标
            var minDiv_left = x - lineDiv_left; //小方块相对于父元素（长线条）的left值
            if(minDiv_left >= lineDiv.offsetWidth - 24) {
                minDiv_left = lineDiv.offsetWidth - 24;
            }
            if(minDiv_left < 0) {
                minDiv_left = 0;
            }
            
            vals.innerText = parseInt((minDiv_left / (lineDiv.offsetWidth - 24)) * 250);
            var n = (minDiv_left / (lineDiv.offsetWidth) * 250)/2.5;
            //设置拖动后小方块的left值
            minDiv.style.left = n + "%";
            lineDiv.style.backgroundImage = '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
        }
    }
    var end = function(e) {
//          console.log("鼠标弹起")
            ifBool = false;
        }
    //鼠标按下方块
    minDiv.addEventListener("touchstart", start);
    minDiv.addEventListener("mousedown", start);
    //拖动
    window.addEventListener("touchmove", move);
    window.addEventListener("mousemove", move);
    //鼠标松开
    window.addEventListener("touchend", end);
    window.addEventListener("mouseup", end);
    //获取元素的绝对位置
    function getPosition(node) {
        var left = node.offsetLeft; //获取元素相对于其父元素的left值var left
        var top = node.offsetTop;
        current = node.offsetParent; // 取得元素的offsetParent
        // 一直循环直到根元素
        　　
        while(current != null) {　　
            left += current.offsetLeft;　　
            top += current.offsetTop;　　
            current = current.offsetParent;　　
        }
        return {
            "left": left,
            "top": top
        };
    }
}
function range2(lineDiv,minDiv,vals){
	var lineDiv = document.getElementById(lineDiv); //长线条
    var minDiv = document.getElementById(minDiv); //小方块
    var vals = document.getElementById(vals);
    var ifBool = false; //判断鼠标是否按下
    //事件
    var start = function(e) {
        e.stopPropagation();
        ifBool = true;
//      console.log("鼠标按下")
    }
    var move = function(e) {
//      console.log("鼠标拖动")
        if(ifBool) {
            if(!e.touches) {    //兼容移动端
                var x = e.clientX;
            } else {     //兼容PC端
                var x = e.touches[0].pageX;
            }
            //var x = e.touches[0].pageX || e.clientX; //鼠标横坐标var x
            var lineDiv_left = getPosition(lineDiv).left; //长线条的横坐标
            var minDiv_left = x - lineDiv_left; //小方块相对于父元素（长线条）的left值
            if(minDiv_left >= lineDiv.offsetWidth - 24) {
                minDiv_left = lineDiv.offsetWidth - 24;
            }
            if(minDiv_left < 0) {
                minDiv_left = 0;
            }
            
            vals.innerText = IOTUtilities.string.dateFormatter(parseInt((minDiv_left / (lineDiv.offsetWidth - 24)) * 900));
            var n = (minDiv_left / (lineDiv.offsetWidth) * 900)/9;
            //设置拖动后小方块的left值
            minDiv.style.left = n + "%";
            lineDiv.style.backgroundImage = '-webkit-linear-gradient(left ,#3CADFF 0%,#3CADFF ' + n + '%,#CCCCCC ' + n + '%, #CCCCCC 100%)'
//      		$('#pop .value3').text(IOTUtilities.string.dateFormatter(value));
//      		vals.innerText = IOTUtilities.string.dateFormatter(vals.innerText);
        }
    }
    var end = function(e) {
//          console.log("鼠标弹起")
            ifBool = false;
        }
    //鼠标按下方块
    minDiv.addEventListener("touchstart", start);
    minDiv.addEventListener("mousedown", start);
    //拖动
    window.addEventListener("touchmove", move);
    window.addEventListener("mousemove", move);
    //鼠标松开
    window.addEventListener("touchend", end);
    window.addEventListener("mouseup", end);
    //获取元素的绝对位置
    function getPosition(node) {
        var left = node.offsetLeft; //获取元素相对于其父元素的left值var left
        var top = node.offsetTop;
        current = node.offsetParent; // 取得元素的offsetParent
        // 一直循环直到根元素
        　　
        while(current != null) {　　
            left += current.offsetLeft;　　
            top += current.offsetTop;　　
            current = current.offsetParent;　　
        }
        return {
            "left": left,
            "top": top
        };
    }
}