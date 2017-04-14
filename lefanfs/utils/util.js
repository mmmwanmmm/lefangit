var md5 = require('/md5.js');
function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('-') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatTimeymd(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('-')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

   
/**
 * 获取用户的openId
 */
function reuqestOpenid(e) {
  var userWxOpenId_ = "userWxOpenId";   //用户openId的key
  var loginStatus_ = "loginStatus";     //用户登录状态的key
  var loginStatus = wx.getStorageSync(loginStatus_)
  if (!loginStatus) {
    var api_sign = md5.hexMD5('api_code=select-user-byCode&api_key=ca89e65c77be0d3f0d732cc3134edaf4&jsCode=' + e.code + '&32f48148e40fcf5586c269f65e6045b5');
    var data = JSON.stringify({ "api_code": "select-user-byCode", "api_key": "ca89e65c77be0d3f0d732cc3134edaf4", "jsCode": "" + e.code + "", "api_sign": "" + api_sign + "" });
    wx.request({
      url: wx.getStorageSync('apiUrl') + 'select-user-byCode/' + api_sign,
      data: {
        "data": data
      },
      method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECTW
      header: {
        "user_token": "",
        "Content-Type": "application/x-www-form-urlencoded"
      }, // 设置请求的 header
      success: function (res) {
        // success
        // console.log("---------" + JSON.stringify(res))
        if(res.data!=null){
          if(res.data.code==="0000"){
              // console.log('用户不存在，存储openId');
              wx.setStorageSync(userWxOpenId_,res.data.results.wechatId );
          }else if (res.data.code === "1103"){
                wx.setStorageSync(userWxOpenId_,res.data.results );
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
}

function loginStatus(e){
  var loginStatus_ = "loginStatus";     //用户登录状态的key
  var bo = wx.getStorageSync(loginStatus_);
     //判断用户是否有进行登录，如果没有跳入到登录页面
    if(!bo){
      if(e!=null&&e!==''){
wx.navigateTo({
        url: '/pages/user/userLogin?address='+e,
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
    return bo;
}

module.exports = {
  formatTime: formatTime,
  formatTimeymd:formatTimeymd,
  reuqestOpenid: reuqestOpenid,
  loginStatus:loginStatus
}
