//日期格式化

export function formatDate(date,fmt){
    var o = {
        "M+":date.getMonth() + 1,//月份
        "D+":date.getDay(),//日
        "h+":date.getHours(),//hours
        "m+":date.getMinutes(),//分钟
        's+':date.getSeconds(),//秒,
    }

    if(/(y+)/.test(fmt)){
        //RegExp.$1 是RegExp的一个属性,指的是与正则表达式匹配的第一个 子匹配(以括号为标志)字符串，以此类推，RegExp.$2，RegExp.$3，..RegExp.$99总共可以有99个匹配
        fmt = fmt.replace(RegExp.$1,(date.getFullYear()+'').substr(4 - RegExp.$1.length));
    }
    for(var k in o){
        if(new RegExp("("+k+")").test(fmt)){
            fmt = fmt.replace(RegExp.$1,(RegExp.$1.length===1)?(o[k]):(("00"+o[k]).substr((""+o[k]).length)))
        }
    }
    return fmt;
}