// pages/extension/index.js
var util = require('../../utils/util.js');
var md5 = require('../../utils/md5.js');
Page({
  data: {
    isAgree: true,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
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
  onQrCode: function () {
    var bo = util.loginStatus();
    if (bo) {
      var userToken = wx.getStorageSync('loginUserInfo').userToken;
      var api_sign = md5.hexMD5('api_code=user-promoted-indetails&api_key=ca89e65c77be0d3f0d732cc3134edaf4&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
      var data = JSON.stringify({ "api_code": "user-promoted-indetails", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "user_token": userToken, "api_sign": api_sign });
      wx.request({
        url: wx.getStorageSync('apiUrl') + 'user-promoted-indetails/' + api_sign,
        data: {
          "data": data
        },
        method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
        header: {
          "user_token": userToken,
          "Content-Type": "application/x-www-form-urlencoded"
        }, // 设置请求的 header
        success: function (res) {
          var bo = 'addInfo';
          // success
          // console.log("---------" + JSON.stringify(res))
          if (res.data != null && res.data !== '') {
            if (res.data.code == '0000') {
              if (res.data.results == null) {
                bo = 'addInfo';
              } else {
                if (res.data.results.state === 2) {
                  bo = 'qrcode';
                } else {
                  wx.setStorageSync('addInfoData', JSON.stringify(res.data.results));
                  bo = 'addInfo'
                }
              }
            } else if (res.data.code == '1104') {
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
              return;
            }
          }
          wx.setStorageSync('qrCode', res.data.results);
          wx.navigateTo({
            url: bo,
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
        fail: function () {
          // fail
        },
        complete: function () {
          // complete
        }
      })
    }

  },setModalysStatus: function (e) {
    // console.log("设置显示状态，1显示0不显示", e.currentTarget.dataset.status);
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: "linear",
      delay: 0
    })
    this.animation = animation
    animation.translateY(300).step()
    this.setData({
      animationData: animation.export()
    })
    if (e.currentTarget.dataset.status == 1) {
      this.setData(
        {
          showModalysStatus: true
        }
      );
    }
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation
      })
      if (e.currentTarget.dataset.status == 0) {
        this.setData(
          {
            showModalysStatus: false
          }
        );
      }
    }.bind(this), 200)
  }
})