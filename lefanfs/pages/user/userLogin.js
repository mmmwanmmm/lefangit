// pages/user/userLogin.js
var util = require('../../utils/util.js');
var md5 = require('../../utils/md5.js');
var userWxOpenId_ = "userWxOpenId";   //用户openId的key
var userInfo_ = "userInfo";//用户信息的key
var loginStatus_ = "loginStatus";     //用户登录状态的key
Page({
  data: {
    phone: "",
    sms: "",
    count: 60,
    address: null,
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    console.log(options.promotedId);
    this.setData({
      address: options.address,
    })
    if (options.promotedId != null) {
      wx.setStorageSync('promotedId', options.promotedId);
      wx.login({
        success: function (res) {
          util.reuqestOpenid(res);
          wx.getUserInfo({
            success: function (res) {
              // success
              wx.setStorageSync('userInfo', res.userInfo);
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
  userLogin: function (e) {
    var that = this;
    var phone = this.data.phone;
    var sms = this.data.sms;
    var userInfo = wx.getStorageSync(userInfo_);
    if (phone == null || phone === '') {
      wx.showModal({
        title: '提示',
        content: '请填写手机号码',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if (sms == null || sms === '') {
      wx.showModal({
        title: '提示',
        content: '请填写短信验证码',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if (wx.getStorageSync(userWxOpenId_) == null ||wx.getStorageSync(userWxOpenId_)==='') {
      wx.login({
        success: function (res) {
          util.reuqestOpenid(res);
        },
        fail: function () {
          // fail
        },
        complete: function () {
          // complete
        }
      })
      wx.showModal({
        title: '提示',
        content: 'openId为空',
        showCancel: false,
        success: function (res) {
          var openId = wx.getStorageSync(userWxOpenId_);
          console.log("openId:" + openId);
        }
      })
      return;
    }
    wx.showToast({
      title: '登录中...',
      icon: 'loading',
      duration: 10000
    })
    var openId = wx.getStorageSync(userWxOpenId_);
    var promotedId = wx.getStorageSync('promotedId');
    var api_sign = md5.hexMD5('api_code=user-login&api_key=ca89e65c77be0d3f0d732cc3134edaf4&gender=' + userInfo.gender + '&img=' + userInfo.avatarUrl + '&nickName=' + userInfo.nickName + '&openId=' + openId + '&phone=' + phone + '&promotedId=' + promotedId + '&validateCode' + sms + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "user-login", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "gender": userInfo.gender, "img": userInfo.avatarUrl, "nickName": userInfo.nickName, "phone": "" + phone + "", "promotedId": promotedId, "validateCode": "" + sms + "", "openId": openId, "api_sign": "" + api_sign + "" });
    console.log("上行参数：" + data);
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'user-login/' + api_sign,
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
        if (res.data != null && res.data !== '') {
          if (res.data.code === '1100') {
            wx.showToast({
              title: '登录成功',
              icon: 'icon',
              duration: 10000
            })

            wx.setStorageSync(loginStatus_, true);
            wx.setStorageSync("loginUserInfo", res.data.results);
            console.log(promotedId);
            if (promotedId != null && promotedId !== '') {
              wx.setStorageSync('promotedId', null);
              wx.switchTab({
                url: '/pages/index/index',
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
              if (that.data.address == null) {
                wx.navigateBack({
                  delta: 1, // 回退前 delta(默认为1) 页面
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
                console.log('不等空' + that.data.address);
                wx.redirectTo({
                  url: that.data.address,
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

            }
          } else {
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
        setTimeout(function () {
          wx.hideToast()
        }, 2000)
        // fail
      },
      complete: function () {
        setTimeout(function () {
          wx.hideToast()
        }, 2000)
        // complete
      }
    })
  },


  userSms: function (e) {
    var phone = this.data.phone
    if (this.data.count == 60) {
      if (phone != null && phone !== '') {
        this.tick()
        wx.showToast({
          title: '验证码获取中...',
          icon: 'loading',
          duration: 10000
        })
        var api_sign = md5.hexMD5('api_code=user-sendSMS&api_key=ca89e65c77be0d3f0d732cc3134edaf4&telphone=' + phone + '&32f48148e40fcf5586c269f65e6045b5');
        var data = JSON.stringify({ "api_code": "user-sendSMS", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "telphone": "" + phone + "", "api_sign": "" + api_sign + "" });
        wx.request({
          url: wx.getStorageSync('apiUrl') + 'user-sendSMS/' + api_sign,
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
            if (res.data != null && res.data !== '') {
              if (res.data.code === '1000') {
                wx.showToast({
                  title: '验证码发送成功！',
                  icon: 'icon',
                  duration: 10000
                })
              } else {
                wx.showModal({
                  title: '提示',
                  content: '验证码获取失败',
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
            setTimeout(function () {
              wx.hideToast()
            }, 2000)
            // fail
          },
          complete: function () {
            setTimeout(function () {
              wx.hideToast()
            }, 2000)
            // complete
          }
        })
      } else {
        wx.showModal({
          title: '提示',
          content: '手机号码不能为空',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
            }
          }
        })
      }
    }
  },
  inputPhone: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },
  inputSms: function (e) {
    this.setData({
      sms: e.detail.value
    })
  }, tick: function () {
    var vm = this
    if (vm.data.count > 0) {
      vm.setData({
        count: vm.data.count - 1
      });
      setTimeout(function () {
        return vm.tick()
      }, 1000)
    } else {
      vm.setData({
        count: 60
      });
    }
  }
})