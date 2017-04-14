// pages/loan/applyforloan.js
var md5 = require('../../utils/md5.js');
Page({
  data: {
    province: [{ "areaId": 0, "areaName": "选择省份" },
		{
			"areaId": 1,
			"areaName": "上海市"
		},
		{
			"areaId": 5,
			"areaName": "北京市"
		},
		{
			"areaId": 9,
			"areaName": "广东省"
		},
		{
			"areaId": 17,
			"areaName": "天津市"
		},
		{
			"areaId": 20,
			"areaName": "河北省"
		},
		{
			"areaId": 23,
			"areaName": "山西省"
		},
		{
			"areaId": 26,
			"areaName": "内蒙古自治区"
		},
		{
			"areaId": 29,
			"areaName": "辽宁省"
		},
		{
			"areaId": 32,
			"areaName": "吉林省"
		},
		{
			"areaId": 35,
			"areaName": "黑龙江省"
		},
		{
			"areaId": 41,
			"areaName": "江苏省"
		},
		{
			"areaId": 44,
			"areaName": "浙江省"
		},
		{
			"areaId": 47,
			"areaName": "安徽省"
		},
		{
			"areaId": 50,
			"areaName": "福建省"
		},
		{
			"areaId": 53,
			"areaName": "江西省"
		},
		{
			"areaId": 56,
			"areaName": "山东省"
		},
		{
			"areaId": 59,
			"areaName": "河南省"
		},
		{
			"areaId": 62,
			"areaName": "湖北省"
		},
		{
			"areaId": 65,
			"areaName": "湖南省"
		},
		{
			"areaId": 68,
			"areaName": "重庆市"
		},
		{
			"areaId": 71,
			"areaName": "四川省"
		},
		{
			"areaId": 74,
			"areaName": "贵州省"
		},
		{
			"areaId": 77,
			"areaName": "云南省"
		},
		{
			"areaId": 80,
			"areaName": "西藏自治区"
		},
		{
			"areaId": 83,
			"areaName": "陕西省"
		},
		{
			"areaId": 86,
			"areaName": "甘肃省"
		},
		{
			"areaId": 89,
			"areaName": "青海省"
		},
		{
			"areaId": 92,
			"areaName": "宁夏回族自治区"
		},
		{
			"areaId": 95,
			"areaName": "新疆维吾尔自治区"
		},
		{
			"areaId": 101,
			"areaName": "广西壮族自治区"
		},
		{
			"areaId": 104,
			"areaName": "海南省"
		}
	],
    provinceIndex: 11,
    city: [{ "areaId": 0, "areaName": "选择城市" },
		{
			"areaId": 116,
			"areaName": "南京市"
		},
		{
			"areaId": 117,
			"areaName": "徐州市"
		},
		{
			"areaId": 118,
			"areaName": "连云港市"
		},
		{
			"areaId": 119,
			"areaName": "宿迁市"
		},
		{
			"areaId": 120,
			"areaName": "淮安市"
		},
		{
			"areaId": 121,
			"areaName": "盐城市"
		},
		{
			"areaId": 122,
			"areaName": "扬州市"
		},
		{
			"areaId": 123,
			"areaName": "泰州市"
		},
		{
			"areaId": 124,
			"areaName": "镇江市"
		},
		{
			"areaId": 125,
			"areaName": "常州市"
		},
		{
			"areaId": 126,
			"areaName": "无锡市"
		},
		{
			"areaId": 127,
			"areaName": "苏州市"
		},
		{
			"areaId": 128,
			"areaName": "南通市"
		}
	],
    cityIndex: 0,
    county: [{ "areaId": 0, "areaName": "选择地区" }],
    countyIndex: 0,
    isAgree: true,
    provinceCode: '41',
    cityCode: '',
    countyCode: '',
    name: '',
    phone: '',
    jt: '1',
    address: '',
    dkMoney: '',
    dkyt: '1'
  },
  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    //  this.getProvince(0, 0);
    // console.log(JSON.stringify(this.data.province));
    console.log()
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

  bindProvinceChange: function (e) {
    // console.log('picker country 发生选择改变，携带值为', e.detail.value);
var provinceCode_ = this.data.province[e.detail.value].areaId;
    this.setData({
      provinceIndex: e.detail.value,
      provinceCode: provinceCode_,
      cityIndex: 0,
      countyIndex: 0
    })
    if (e.detail.value != 0) {
      this.getProvince(provinceCode_, 1);
    }
  },
  bindCityChange: function (e) {
    //  console.log('picker country 发生选择改变，携带值为', e.detail.value);
    var cityCode_ = this.data.city[e.detail.value].areaId;
    this.setData({
      cityIndex: e.detail.value,
      cityCode: cityCode_,
      countyIndex: 0
    })
    if (e.detail.value != 0) {
      this.getProvince(cityCode_, 2);
    }
  },
  bindCountyChange: function (e) {
    // console.log('picker country 发生选择改变，携带值为', e.detail.value);
    var countyCode_ = this.data.county[e.detail.value].areaId;
    this.setData({
      countyCode: countyCode_,
      countyIndex: e.detail.value
    })
  },
  bindAgreeChange: function (e) {
    this.setData({
      isAgree: !!e.detail.value.length
    });
  },
  setModalStatus: function (e) {
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
          showModalStatus: true
        }
      );
    }else{
      wx.navigateBack({
                delta: 1, // 回退前 delta(默认为1) 页面
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
    setTimeout(function () {
      animation.translateY(0).step()
      this.setData({
        animationData: animation
      })
      if (e.currentTarget.dataset.status == 0) {
        this.setData(
          {
            showModalStatus: false
          }
        );
      }
    }.bind(this), 200)
  },
  setModalysStatus: function (e) {
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
  },
  getProvince: function (e, s) {
    wx.showToast({
      title: '请求数据中...',
      icon: 'loading',
      duration: 10000
    })
    var that = this;
    var api_sign = md5.hexMD5('api_code=area-children&api_key=ca89e65c77be0d3f0d732cc3134edaf4&areaParentId=' + e + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "area-children", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "areaParentId": e, "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'area-children/' + api_sign,
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
          if (res.data.code == '0000') {
            if (s === 0) {
              that.setData({
                province: [{ "areaId": 0, "areaName": "选择省份" }].concat(res.data.results)
              });
            } else if (s === 1) {
              that.setData({
                city: [{ "areaId": 0, "areaName": "选择城市" }].concat(res.data.results)
              });
            } else if (s === 2) {
              that.setData({
                county: [{ "areaId": 0, "areaName": "选择地区" }].concat(res.data.results)
              });
            }
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
  jiaotong: function (e) {
    this.setData({
      jt: e.detail.value
    })
  },
  name: function (e) {
    this.setData({
      name: e.detail.value
    })
  },
  phone: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },
  addr: function (e) {
    this.setData({
      address: e.detail.value
    })
  },
  money: function (e) {
    this.setData({
      dkMoney: e.detail.value
    })
  },
  daikuan: function (e) {
    this.setData({
      dkyt: e.detail.value
    })
  },
  addLoan:function(e){
    var data = this.data;
    if(data.name==null||data.name===''){
        wx.showModal({
        title: '提示',
        content: '请输入您的姓名',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if(data.phone==null||data.phone===''){
        wx.showModal({
        title: '提示',
        content: '请输入您的手机号码',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if(data.provinceCode ===''||data.provinceCode===0){
         wx.showModal({
        title: '提示',
        content: '请选择省份',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if(data.cityCode ===''||data.cityCode===0){
         wx.showModal({
        title: '提示',
        content: '请选择城市',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if(data.countyCode ===''||data.countyCode===0){
         wx.showModal({
        title: '提示',
        content: '请选择地区',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    // if(data.address ===''||data.address==null){
    //    wx.showModal({
    //     title: '提示',
    //     content: '请输入详细地址',
    //     showCancel: false,
    //     success: function (res) {
    //       if (res.confirm) {
    //       }
    //     }
    //   })
    //   return;
    // }
    if(data.dkMoney ===''||data.dkMoney==null){
       wx.showModal({
        title: '提示',
        content: '请输入贷款金额',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
    if(!data.isAgree){
      wx.showModal({
        title: '提示',
        content: '请同意隐私协议',
        showCancel: false,
        success: function (res) {
          if (res.confirm) {
          }
        }
      })
      return;
    }
var userInfo = wx.getStorageSync('loginUserInfo');
var that = this;
    var api_sign = md5.hexMD5('api_code=insert-loan-application&api_key=ca89e65c77be0d3f0d732cc3134edaf4&accidentProvince=' + that.data.provinceCode + '&accidentCity='+that.data.cityCode+'&accidentDistrict='+that.data.countyCode+'&accidentAddress='+that.data.address+'&isTrafficAccident='+that.data.jt+'&loanMoney='+that.data.dkMoney+'&loanPurpose='+that.data.dkyt+'&userName='+that.data.name+'&userPhone='+that.data.phone+'&user_token='+userInfo.userToken+'&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "insert-loan-application", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "accidentProvince": that.data.provinceCode,"accidentCity":that.data.cityCode,"accidentDistrict":that.data.countyCode,"accidentAddress":that.data.address,"isTrafficAccident":that.data.jt,"loanMoney":that.data.dkMoney,"loanPurpose":that.data.dkyt,"userName":that.data.name,"userPhone":that.data.phone,"user_token":userInfo.userToken, "api_sign":api_sign});
    wx.showToast({
        title: '提交申请中...',
        icon: 'loading',
        duration: 10000
      })
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'insert-loan-application/' + api_sign,
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
          if (res.data.code == '0000') {
            wx.showToast({
                title: '申请提交成功',
                icon: 'icon',
                duration: 10000
              })
              that.setModalStatus(e);
              
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
          }else{
            wx.showToast({
                title: res.data.msg,
                icon: 'icon',
                duration: 10000
              })
          }
        }else{
          wx.showToast({
                title: res.errMsg,
                icon: 'icon',
                duration: 10000
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
  loadImage:function(e){
    var url = e.currentTarget.id;
    wx.previewImage({
      // current: 'String', // 当前显示图片的链接，不填则默认为 urls 的第一张
      urls: [url],
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
})