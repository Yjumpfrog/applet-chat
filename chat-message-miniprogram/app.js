// app.js
App({
  onLaunch() {
    // 展示本地存储能力
    const logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    let menuButtonObject = wx.getMenuButtonBoundingClientRect();
    wx.getSystemInfo({
      success: res => {
        let statusBarHeight = res.statusBarHeight;
        //胶囊按钮与顶部的距离
        let navTop = menuButtonObject.top;
        //导航高度
        let navHeight = statusBarHeight + menuButtonObject.height + (menuButtonObject.top - statusBarHeight) * 2;
        this.globalData.navHeight = navHeight;
        this.globalData.navTop = navTop;
        this.globalData.windowHeight = res.windowHeight;
      },
      fail(err) {
        console.log(err);
      }
    })
  },
  globalData: {
    userInfo: null,
    navHeight:0,
    baseAPI: 'http://192.168.3.104:9999/message-server/',
    wsBaseAPI: 'ws://192.168.3.104:9999/message-server/',
  }
})