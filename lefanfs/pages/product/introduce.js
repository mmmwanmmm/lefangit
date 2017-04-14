// pages/product/introduce.js
var md5 = require('../../utils/md5.js');
Page({
  data: {
    results: []
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.banner();
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
  }, banner: function () {
    var that = this;
    var api_sign = md5.hexMD5('adCode=company&api_code=select-adinfo-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "select-adinfo-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "adCode": "company", "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'select-adinfo-list/' + api_sign,
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
        if (res.data != null && res.data !== '') {
          if (res.data.code == '0000') {
            that.setData({
              results: res.data.results
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
  toProductDetail: function (e) {
    var that = this;
    var index = e.currentTarget.id;
    var id = that.data.results[index].adHerf;
    wx.navigateTo({
      url: id,
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
})