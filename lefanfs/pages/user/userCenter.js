// pages/user/userCenter.js
var util = require('../../utils/util.js');
Page({
  data: {
    userInfo:{}
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
   var userinfo_ =  wx.getStorageSync('loginUserInfo');
   if(userinfo_==null||userinfo_===''){
     var usera = wx.getStorageSync('userInfo');
      userinfo_ = {"icon":usera.avatarUrl,"nickname":usera.nickName};
   }
    this.setData({
      userInfo:userinfo_
    })
    // console.log(this.data.userInfo);
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  nextPage: function (e) {
    var tar = e.target.id;
    switch (e.currentTarget.id) {
      case "1":
        var bo = util.loginStatus('../advisory/myMsg');
        if(bo){
wx.navigateTo({
          url: '../advisory/myMsg',
          success: function(res){
            // success
          },
          fail: function() {
            // fail
          },
          complete: function() {
            // complete
          }
        })
        }
        
        break;
      case "2":
      var bo = util.loginStatus('../loan/myApplyforloan');
      if(bo){
        wx.navigateTo({
          url: "../loan/myApplyforloan",
          success: function (res) {
            // success
          },
          fail: function () {
            // fail
          },
          complete: function () {
            // complete
          }
        })
      }
        break;
      case "3":
      var bo = util.loginStatus('../user/userMsg');
      if(bo){
        wx.navigateTo({
          url: "userMsg",
          success: function (res) {
            // success
          },
          fail: function () {
            // fail
          },
          complete: function () {
            // complete
          }
        })
      }
        break;
      case "4":
      var bo = util.loginStatus('../extension/myExtend');
      if(bo){
        wx.navigateTo({
          url: "../extension/myExtend",
          success: function (res) {
            // success
          },
          fail: function () {
            // fail
          },
          complete: function () {
            // complete
          }
        })
      }
        break;
      case "5":
        var bo = util.loginStatus('../advisory/msg');
        if(bo){
          wx.navigateTo({
            url: '../advisory/msg',
            success: function(res){
              // success
            },
            fail: function() {
              // fail
            },
            complete: function() {
              // complete
            }
          })
        }
        break;
    }
  }
})