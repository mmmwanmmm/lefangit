// pages/advisory/myMsg.js
var md5 = require('../../utils/md5.js');
var util = require('../../utils/util.js');
Page({
  data: {
    results: [],
    pageStatus: true,
    page: 1,
    pageSize: 10,
    noData: false
  },
  onLoad: function (options) {
    
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    this.userCommentMyList();
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  userCommentMyList: function () {
    var userToken = wx.getStorageSync('loginUserInfo').userToken;
    var that = this;
    var api_sign = md5.hexMD5('api_code=user-comment-myList&api_key=ca89e65c77be0d3f0d732cc3134edaf4&page=' + that.data.page + '&pageSize=10&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "user-comment-myList", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "page": "" + that.data.page + "", "pageSize": "10", "user_token": userToken, "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'user-comment-myList/' + api_sign,
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
            if (res.data.results != null) {
              var commentList = res.data.results.commentList;
              for (var i = 0; i < commentList.length; i++) {
              commentList[i].createTime = util.formatTime(new Date(commentList[i].createTime));
              if(commentList[i].replyCommentDto!=null){
                  for(var j = 0;j<commentList[i].replyCommentDto.length;j++){
                    var a = commentList[i].replyCommentDto[j].createTime;
 commentList[i].replyCommentDto[j].createTime= util.formatTime(new Date(a));
                  }
              }
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
  onReachBottom: function () {
    var vm = this;
    if (!vm.data.noData) {
      var page_ = vm.data.page;
      vm.setData({
        pageStatus: true,
        page:page_+1
      });
      vm.userCommentMyList();
    }
  }
})