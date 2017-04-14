// pages/extension/myExtend.js
var md5 = require('../../utils/md5.js');
Page({
  data: {
    results: [],
    noData: false,
    resultSize:0,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    this.promotedInfoList();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  toRemarks: function (e) {
    var that = this;
    wx.navigateTo({
      url: 'remarks?data='+JSON.stringify(that.data.results[e.currentTarget.id]),
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
  promotedInfoList: function () {
    var that = this;
    var userToken = wx.getStorageSync('loginUserInfo').userToken;
    var api_sign = md5.hexMD5('api_code=promotedInfo-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "promotedInfo-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "user_token": userToken, "api_sign": api_sign });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'promotedInfo-list/' + api_sign,
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
          if (res.data.code == '0000') {
            if (res.data.results.length > 0) {
              that.setData({
                results: res.data.results,
                noData: false,
                resultSize:res.data.results.length
              })
            } else {
              that.setData({
                noData: true
              })
            }
          }else if (res.data.code ==='1104'){
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
          } else {
            that.setData({
              noData: true
            })
          }
        } else {
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