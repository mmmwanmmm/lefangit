// pages/advisory/msg.js
var md5 = require('../../utils/md5.js');
Page({
  data:{
    results:[],
    userMessage:''
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
    this.selectQainfoList();
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  selectQainfoList:function(){
    var that = this;
    var api_sign = md5.hexMD5('api_code=select-qainfo-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&type=1&32f48148e40fcf5586c269f65e6045b5');
      var data = JSON.stringify({ "api_code": "select-qainfo-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4","type":"1", "api_sign": "" + api_sign + "" });
      wx.request({
        url: wx.getStorageSync('apiUrl') + 'select-qainfo-list/' + api_sign,
        data: {
          "data": data
        },
        method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
        header: {
          "user_token": wx.getStorageSync('loginUserInfo').userToken,
          "Content-Type": "application/x-www-form-urlencoded"
        }, // 设置请求的 header
        success: function (res) {
          // success
          // console.log("---------" + JSON.stringify(res))
          if(res.data!=null&&res.data!==''){
              if(res.data.code=='0000'){
                  that.setData({
                    results:res.data.results
                  });
                  // console.log(JSON.stringify(that.data.results));
              }
          }
        },
        fail: function () {
          // fail
        },
        complete: function () {
          // complete
        }
      })
  },
  toMyMsg:function(e){
    var that = this;
    var data = e.currentTarget.id;
    // console.log(that.data.results[data]);
    wx.navigateTo({
      url: 'problem?msg='+JSON.stringify(that.data.results[data]),
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
  },
  message:function(e){
    this.setData({
      userMessage:e.detail.value
    })
  },
  userCommentAdd:function(){
    
      var that = this;
      var msg = that.data.userMessage;
      if(msg!=null&&msg!==''){
      var userId = wx.getStorageSync('loginUserInfo')
    var api_sign = md5.hexMD5('api_code=user-comment-add&api_key=ca89e65c77be0d3f0d732cc3134edaf4&commentTo=1&comment='+msg+'&type=0&user_token='+userId.userToken+'&32f48148e40fcf5586c269f65e6045b5');
      var data = JSON.stringify({ "api_code": "user-comment-add", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4","commentTo":"1","comment":msg,"type":"0", "user_token":userId.userToken,"api_sign": "" + api_sign + "" });

      wx.showToast({
        title: '提交中...',
        icon: 'loading',
        duration: 10000
      })
      // console.log("上行参数："+data);
      wx.request({
        url: wx.getStorageSync('apiUrl') + 'user-comment-add/' + api_sign,
        data: {
          "data": data
        },
        method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
        header: {
          "user_token": wx.getStorageSync('loginUserInfo').userToken,
          "Content-Type": "application/x-www-form-urlencoded"
        }, // 设置请求的 header
        success: function (res) {
          wx.hideToast()
          // success
          // console.log("---------" + JSON.stringify(res))
          if(res.data!=null&&res.data!==''){
              if(res.data.code=='0000'){
                  wx.showToast({
                title: '提交成功！',
                icon: 'icon',
                duration: 10000
              })
              wx.navigateBack({
                delta: 1, // 回退前 delta(默认为1) 页面
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
                  // console.log(JSON.stringify(that.data.results));
              }else if (res.data.code==='1104'){
                wx.setStorageSync('loginUserInfo', '');
                wx.setStorageSync('loginStatus', false);
                  wx.navigateTo({
              url: '/pages/user/userLogin',
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
              }else{
                wx.showModal({
                title: '提示',
                content: res.data.msg,
                showCancel: false,
                success: function (res) {
                  if (res.confirm) {
                  }
                }
              })
              }
          }
        },
        fail: function () {
          // fail
          setTimeout(function () {
            wx.hideToast()
          }, 2000)
        },
        complete: function () {
          // complete
          setTimeout(function () {
            wx.hideToast()
          }, 2000)
        }
      })
    }else{
      wx.showModal({
                title: '提示',
                content: "请输入留言内容",
                showCancel: false,
                success: function (res) {
                  if (res.confirm) {
                  }
                }
              })
    }
      
  }
})