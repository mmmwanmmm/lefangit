// pages/extension/remarks.js
var base64 = require('../../utils/base64.js');
var md5 = require('../../utils/md5.js');
Page({
  data: {
    files: [],
    data: {},
    imageUrl: '',
    customerDesc_: '',
    customerText_: ''
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.setData({
      data: JSON.parse(options.data),
      customerDesc_: JSON.parse(options.data).customerDesc,
      customerText_: JSON.parse(options.data).customerText
    })
    if (JSON.parse(options.data).customerBusinessCard != null && JSON.parse(options.data).customerBusinessCard !== '') {
      this.setData({
        files: new Array("http://openapi.shlefan.com/pic/images" + JSON.parse(options.data).customerBusinessCard),
        imageUrl: JSON.parse(options.data).customerBusinessCard
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
  chooseImage: function (e) {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        that.setData({
          // files: that.data.files.concat(res.tempFilePaths)
          files: res.tempFilePaths
        });
        that.uploadImage(res.tempFilePaths);
      }
    })
  }, previewImage: function (e) {
    wx.previewImage({
      current: e.currentTarget.id, // 当前显示图片的http链接
      urls: this.data.files // 需要预览的图片http链接列表
    })
  },
  submit: function () {
    wx.showToast({
      title: '数据提交中...',
      icon: 'loading',
      duration: 10000
    })

    var that = this;
    var data = that.data;
    var userToken = wx.getStorageSync('loginUserInfo').userToken;
    var api_sign = md5.hexMD5('api_code=promotedInfo-update&api_key=ca89e65c77be0d3f0d732cc3134edaf4&customerDesc=' + data.customerDesc_ + '&customerText=' + data.customerText_ + '&customerBusinessCard=' + data.imageUrl + '&id=' + that.data.data.id + '&user_token=' + userToken + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "promotedInfo-update", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "customerDesc": data.customerDesc_, "customerText": data.customerText_, "customerBusinessCard": data.imageUrl, "id": that.data.data.id, "user_token": userToken, "api_sign": api_sign });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'promotedInfo-update/' + api_sign,
      data: {
        "data": data
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
      header: {
        "user_token": userToken,
        "Content-Type": "application/x-www-form-urlencoded"
      }, // 设置请求的 header
      success: function (res) {
        wx.hideToast()
        // success
        // console.log("---------" + JSON.stringify(res))
        if (res.data != null && res.data !== '') {
          if (res.data.code == '0000') {
            wx.showToast({
              title: '提交成功',
              icon: 'icon',
              duration: 10000
            })
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
  },
  uploadImage: function (e) {
    wx.showToast({
      title: '上传中...',
      icon: 'loading',
      duration: 10000
    })
    var that = this;
    wx.uploadFile({
      url: 'https://openapi.shlefan.com/lefanfsapicenter/file/uploadImage',
      filePath: e[0],
      name: 'name',
      // header: {}, // 设置请求的 header
      // formData: {}, // HTTP 请求中其他额外的 form data
      success: function (res) {
        wx.hideToast()
        var data = JSON.parse(res.data);
        // success
        if (data.success) {
          wx.showToast({
            title: '上传成功',
            icon: 'icon',
            duration: 10000
          })
          that.setData({
            imageUrl: data.images[0].userFilePath
          })
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
  },
  customerDesc: function (e) {
    this.setData({
      customerDesc_: e.detail.value
    })
  },
  customerText: function (e) {
    this.setData({
      customerText_: e.detail.value
    })
  }
})