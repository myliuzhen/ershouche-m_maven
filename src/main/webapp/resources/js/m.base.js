/***************************二手车全局基础类******************************/
//团车统计
/**正式环境**/
//var tongjiPath = "http://tongjipv.tuanche.com/usedcar/m/";
//var statjsPath = "http://static3.tuanche.com/wap01/script/commons/stat_uc.js";
/**预发布环境**/
var tongjiPath = "http://tongjipv.test.tuanche.com/usedcar/m/";
var statjsPath = "http://static.test.tuanche.com/wap01/script/commons/stat_uc.js";
/**
 * 先加载工具类，然后进行初始操作
 */
document.write("<script src='/ershouche/resources/js/min/m.commUtil.min.js'></script>");
document.write("<script src='/ershouche/resources/js/min/m.ready.min.js'></script>");
document.write("<script src='"+statjsPath+"'></script>");
