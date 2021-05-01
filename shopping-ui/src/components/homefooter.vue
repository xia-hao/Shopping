<template>
<div>
  <div class="b_btm_bg b_btm_c" id="footer">
    <div class="b_btm">
      <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
        <tr>
          <td width="72"><img src="static/images/b1.png" width="62" height="62" /></td>
          <td><h2>正品保障</h2>正品行货  放心购买</td>
        </tr>
      </table>
      <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
        <tr>
          <td width="72"><img src="static/images/b2.png" width="62" height="62" /></td>
          <td><h2>满38包邮</h2>满38包邮 免运费</td>
        </tr>
      </table>
      <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
        <tr>
          <td width="72"><img src="static/images/b3.png" width="62" height="62" /></td>
          <td><h2>天天低价</h2>天天低价 畅选无忧</td>
        </tr>
      </table>
      <table border="0" style="width:210px; height:62px; float:left; margin-left:75px; margin-top:30px;" cellspacing="0" cellpadding="0">
        <tr>
          <td width="72"><img src="static/images/b4.png" width="62" height="62" /></td>
          <td><h2>准时送达</h2>收货时间由你做主</td>
        </tr>
      </table>
    </div>
  </div>
  <div class="b_nav">
    <dl>
      <dt><a href="#">新手上路</a></dt>
      <dd><a href="#">售后流程</a></dd>
      <dd><a href="#">购物流程</a></dd>
      <dd><a href="#">订购方式</a></dd>
      <dd><a href="#">隐私声明</a></dd>
      <dd><a href="#">推荐分享说明</a></dd>
    </dl>
    <dl>
      <dt><a href="#">配送与支付</a></dt>
      <dd><a href="#">货到付款区域</a></dd>
      <dd><a href="#">配送支付查询</a></dd>
      <dd><a href="#">支付方式说明</a></dd>
    </dl>
    <dl>
      <dt><a href="#">会员中心</a></dt>
      <dd><a href="#">资金管理</a></dd>
      <dd><a href="#">我的收藏</a></dd>
      <dd><a href="#">我的订单</a></dd>
    </dl>
    <dl>
      <dt><a href="#">服务保证</a></dt>
      <dd><a href="#">退换货原则</a></dd>
      <dd><a href="#">售后服务保证</a></dd>
      <dd><a href="#">产品质量保证</a></dd>
    </dl>
    <dl>
      <dt><a href="#">联系我们</a></dt>
      <dd><a href="#">网站故障报告</a></dd>
      <dd><a href="#">购物咨询</a></dd>
      <dd><a href="#">投诉与建议</a></dd>
    </dl>
    <div class="b_tel_bg">
      <a href="#" class="b_sh1">新浪微博</a>
      <a href="#" class="b_sh2">腾讯微博</a>
      <p>
        服务热线：<br />
        <span>400-123-4567</span>
      </p>
    </div>
    <div class="b_er">
      <div class="b_er_c"><img src="static/images/er.gif" width="118" height="118" /></div>
      <img src="static/images/ss.png" />
    </div>
  </div>
  <div class="btmbg">
    <div class="btm">
      备案/许可证编号：蜀ICP备12009302号-1-www.dingguagua.com   Copyright © 2015-2018 尤洪商城网 All Rights Reserved. 复制必究 , Technical Support: Dgg Group <br />
      <img src="static/images/b_1.gif" width="98" height="33" /><img src="static/images/b_2.gif" width="98" height="33" />
      <img src="static/images/b_3.gif" width="98" height="33" /><img src="static/images/b_4.gif" width="98" height="33" />
      <img src="static/images/b_5.gif" width="98" height="33" /><img src="static/images/b_6.gif" width="98" height="33" />
    </div>
  </div>
</div>
</template>

<script>
    export default {
        name: "homefooter"
    }
</script>

<style scoped>
  .b_btm_bg {
    width: 100%;
    min-width: 1200px;
    height: 122px;
    overflow: hidden;
    margin-top: 30px;
  }

  .b_btm_c {
    background-color: #FFF;
  }

  .b_btm {
    width: 1200px;
    height: 122px;
    overflow: hidden;
  }

  .b_btm table {
    color: #888888;
    font-size: 14px;
  }

  .b_btm table h2 {
    color: #3e3e3e;
    font-size: 16px;
    font-weight: normal;
  }

  .b_nav {
    width: 1200px;
    overflow: hidden;
    margin: 20px auto;
  }

  .b_nav dl {
    width: 125px;
    height: 165px;
    overflow: hidden;
    float: left;
    display: inline;
    margin-right: 50px;
  }
  ul, li, dl, dt, dd {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  a{
    text-decoration: none;
  }
  .b_nav dl dt {
    height: 30px;
    line-height: 30px;
    overflow: hidden;
    color: #3e3e3e;
    font-size: 16px;
    margin-bottom: 10px;
  }

  .b_nav dl dt a {
    color: #3e3e3e;
  }

  .b_nav dl dt a:hover {
    color: #3e3e3e;
    text-decoration: underline;
  }

  .b_nav dl dd {
    height: 24px;
    line-height: 20px;
    overflow: hidden;
    color: #888888;
    font-size: 14px;
  }

  .b_nav dl dd a {
    color: #888888;
  }

  .b_nav dl dd a:hover {
    color: #ff4e00;
  }

  .b_er {
    width: 120px;
    height: 160px;
    overflow: hidden;
    text-align: center;
    float: right;
    display: inline;
    margin-right: 22px;
    margin-top: 5px;
  }

  .b_er_c {
    width: 118px;
    height: 118px;
    overflow: hidden;
    border: 1px solid #e9e9e9;
  }

  .b_tel_bg {
    width: 133px;
    height: 140px;
    overflow: hidden;
    color: #888888;
    float: right;
    margin-top: 5px;
  }

  .b_tel_bg a.b_sh1 {
    width: 100%;
    height: 35px;
    line-height: 35px;
    overflow: hidden;
    background: url('../../static/images/b_sh_1.png') no-repeat left center;
    color: #888888;
    font-size: 14px;
    text-indent: 32px;
    display: inline-block;
  }

  .b_tel_bg a.b_sh1:hover {
    color: #ff4e00;
  }

  .b_tel_bg a.b_sh2 {
    width: 100%;
    height: 35px;
    line-height: 35px;
    overflow: hidden;
    background: url(../../static/images/b_sh_2.png) no-repeat left center;

    color: #888888;
    font-size: 14px;
    text-indent: 32px;
    display: inline-block;
  }

  .b_tel_bg a.b_sh2:hover {
    color: #ff4e00;
  }

  .b_tel_bg p {
    margin-top: 10px;
  }

  .b_tel_bg p span {
    color: #ff4e00;
    font-size: 18px;
  }

  .btmbg {
    width: 100%;
    min-width: 1200px;
    overflow: hidden;
    padding: 30px 0 40px 0;
    border-top: 1px solid #eaeaea;
  }

  .btm {
    width: 1200px;
    overflow: hidden;
    color: #999999;
    font-family: "宋体";
    text-align: center;
  }

  .btm a {
    color: #999999;
  }

  .btm a:hover {
    color: #999999;
    text-decoration: underline;
  }

  .btm img {
    display: inline-block;
    margin: 15px 5px 5px 5px;
    border: 1px solid #d9d9d9;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
  }

  </style>
