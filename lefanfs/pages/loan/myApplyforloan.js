// pages/loan/myApplyforloan.js
var md5 = require('../../utils/md5.js');
var util = require('../../utils/util.js');
Page({
  data:{
    results: [],
    pageStatus: true,
    page: 1,
    pageSize: 10,
    noData: false
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
    this.loanApplicationList();
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
  },
  toApplyDetails:function(e){
    var data = this.data.results[e.currentTarget.id];
    wx.navigateTo({
      url: 'applyDetails?data='+JSON.stringify(data),
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
  loanApplicationList:function(){
    var userToken = wx.getStorageSync('loginUserInfo').userToken;
    var that = this;
    var api_sign = md5.hexMD5('api_code=loanApplication-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&page=' + that.data.page + '&pageSize=10&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "loanApplication-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "page": "" + that.data.page + "", "pageSize": "10", "user_token": userToken, "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'loanApplication-list/' + api_sign,
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
            if (res.data.results.length>0) {
              var commentList = res.data.results;
              for (var i = 0; i < commentList.length; i++) {
              commentList[i].createTime = util.formatTimeymd(new Date(commentList[i].createTime));
              }
              var results_ = that.data.results;
              results_ = results_.concat(commentList);
              that.setData({
                results: results_,
                pageStatus: false,
                noData: false
              });
            } else {
              that.setData({
                pageStatus: false,
                noData: true
              });
            }
            // console.log(JSON.stringify(that.data.results));
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
  onReachBottom:function () {
    var vm = this;
    if (!vm.data.noData) {
      var page_ = vm.data.page;
      vm.setData({
        pageStatus: true,
        page:page_+1
      });
      vm.loanApplicationList();
    }
  }
})