// pages/user/userMsg.js
var util = require('../../utils/util.js');
var md5 = require('../../utils/md5.js');
Page({
  data: {
    results: [],
    noData: false
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.messageInfoList();
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  toUserMsgDetails: function (e) {
    var index = e.currentTarget.id;
    var that = this;
    var a = that.data.results[index];
    wx.navigateTo({
      url: 'userMsgDetails?data=' + JSON.stringify(a),
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
  },
  messageInfoList: function () {
    var that = this;
    var userToken = wx.getStorageSync('loginUserInfo').userToken;
    var api_sign = md5.hexMD5
      ('api_code=messageInfo-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "messageInfo-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "user_token": userToken, "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'messageInfo-list/' + api_sign,
      data: {
        "data": data
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
      header: {
        "user_token": userToken,
        "Content-Type": "application/x-www-form-urlencoded"
      }, // 设置请求的 header
      success: function (res) {
        // success
        // console.log("---------" + JSON.stringify(res))
        if (res.data != null && res.data !== '') {
          if (res.data.code === '0000') {
            var myResults = res.data.results;
            if (myResults.length > 0) {
              for (var i = 0; i < myResults.length; i++) {
                myResults[i].sendTime = util.formatTimeymd(new Date(myResults[i].sendTime));
              }
              that.setData({
                results: myResults,
                noData: false
              })
            } else {
              //显示暂无数据view
              that.setData({
                noData: true
              })
            }
          } else if (res.data.code === '1104') {
            wx.setStorageSync('loginUserInfo', '');
            wx.setStorageSync('loginStatus', false);
            wx.navigateTo({
              url: '/pages/user/userLogin',
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
          } else {
            //显示暂无数据view
            that.setData({
              noData: true
            })
          }
        } else {
          //显示暂无数据view
          that.setData({
            noData: true
          })
        }

      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  }
})