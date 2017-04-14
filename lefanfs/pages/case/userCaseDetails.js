var WxParse = require('../../wxParse/wxParse.js');
var md5 = require('../../utils/md5.js');
// pages/home/rich_content/rich_content.js
Page({
  data: {},
  onLoad: function (options) {
    var title = options.title;
    var id = options.id;
    if(title !=null){
    wx.setNavigationBarTitle({
      title: title,
      success: function(res) {
        // success
      }
    })
    // console.log("接收到的参数："+options.article);
    var article = wx.getStorageSync('fwb');
  var vm = this;
  WxParse.wxParse('article', 'html', article, vm, 0);
    }
    if(id!=null){
        var that = this;
    var api_sign = md5.hexMD5('api_code=select-companyInfo-byId&api_key=ca89e65c77be0d3f0d732cc3134edaf4&id='+id+'&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "select-companyInfo-byId", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "id": id, "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'select-companyInfo-byId/' + api_sign,
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
              WxParse.wxParse('article', 'html', res.data.results.content, that, 0);
              wx.setNavigationBarTitle({
                title: res.data.results.title,
                success: function(res) {
                  // success
                }
              })
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
    }
  },
  onUnload: function () {
    // 页面关闭
    wx.setStorageSync('fwb', null);
  }
})