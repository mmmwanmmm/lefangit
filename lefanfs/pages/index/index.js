//index.js
//获取应用实例
var util = require('../../utils/util.js');
var md5 = require('../../utils/md5.js');
var app = getApp()
Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    userRes:{},
    results:[],
  },
  onLoad: function (options) {
    var that = this
    //调用应用实例的方法获取全局数据
    app.getUserInfo(function(userInfo){
      //更新数据
      // console.log(userInfo);
      wx.setStorageSync('userInfo', userInfo);
      that.setData({
        userInfo:userInfo
      })
    })
    wx.login({
      success: function(res){
        that.setData({
        userRes:res
      })
      util.reuqestOpenid(res);
      },
      fail: function() {
        // fail
      },
      complete: function() {
        // complete
      }
    })
  },
  onShow:function(){
this.loginIndex();
  },
  indexToSubpage : function(e){
    switch(e.currentTarget.id){
      case "1":
      var bo = util.loginStatus('../loan/applyforloan');
    if(bo){
        wx.navigateTo({
      url: '../loan/applyforloan',
      success: function(res){
        // success
        console.log('su');
      },
      fail: function() {
        // fail
        console.log('fail');
      },
      complete: function() {
        // complete
        console.log('complete');
      }
    })
    }
      break;
      case "2":
        wx.navigateTo({
      url: '../loan/applyprocess',
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
      break;
      case "3":
wx.switchTab({
      url: '../case/userCase',
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
      break;
      case "4":
      wx.navigateTo({
      url: '../product/introduce',
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
    break;
    case "5":
    var bo = util.loginStatus();
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
    case "6":
    wx.makePhoneCall({
      phoneNumber: "400-630-3071",
      success: function(res) {
        // success
      }
    })
    break;
    case "7":
    // var bo = util.loginStatus('../proxy/proxyApply');
    // if(bo){
        wx.navigateTo({
      url: '../proxy/proxyApply',
      success: function(res){
        // success
        console.log('su');
      },
      fail: function() {
        // fail
        console.log('fail');
      },
      complete: function() {
        // complete
        console.log('complete');
      }
    })
    // }
    break;
    case "8":
    // var bo = util.loginStatus('../disability/estimate');
    // if(bo){
        wx.navigateTo({
      url: '../disability/estimate',
      success: function(res){
        // success
        console.log('su');
      },
      fail: function() {
        // fail
        console.log('fail');
      },
      complete: function() {
        // complete
        console.log('complete');
      }
    })
    // }
    break;
    case "9":
    // var bo = util.loginStatus('../indemnity/measure');
    // if(bo){
        wx.navigateTo({
      url: '../indemnity/measure',
      success: function(res){
        // success
        console.log('su');
      },
      fail: function() {
        // fail
        console.log('fail');
      },
      complete: function() {
        // complete
        console.log('complete');
      }
    })
    // }
    break;
    }
  },
  loginIndex:function(){
    var that = this;
    var api_sign = md5.hexMD5('adCode=banner&api_code=select-adinfo-list&api_key=ca89e65c77be0d3f0d732cc3134edaf4&32f48148e40fcf5586c269f65e6045b5');
      var data = JSON.stringify({ "api_code": "select-adinfo-list", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4","adCode":"banner", "api_sign": "" + api_sign + "" });
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
          if(res.data!=null&&res.data!==''){
              if(res.data.code=='0000'){
                  that.setData({
                    results:res.data.results
                  });
                  // console.log(JSON.stringify(that.data.results));
              }
          }
        },
        fail: function (e) {
          // fail
          // console.log('fail'+JSON.stringify(e))
        },
        complete: function (e) {
          // complete
          //  console.log('complete'+JSON.stringify(e))
        }
      })
  },
  image1:function(e){
    var id = e.currentTarget.id;
    if("/pages/case/userCase"===id){
          wx.switchTab({
            url: id,
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
      wx.navigateTo({
        url: id,
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
})
