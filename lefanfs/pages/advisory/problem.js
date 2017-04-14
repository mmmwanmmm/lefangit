// pages/advisory/problem.js
var util = require('../../utils/util.js');
Page({
  data:{
    results:{}
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    var that = this;
    // 页面初始化 options为页面跳转所带来的参数
    // console.log(JSON.stringify(options.msg))
      var json = JSON.parse(options.msg);
      json.questionTime = util.formatTime(new Date(json.questionTime));
      json.answerTime = util.formatTime(new Date(json.answerTime));
      that.setData({
        results:json
      })
      // console.log(that.data.results);
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  }
})