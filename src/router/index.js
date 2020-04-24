import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history', // 去掉url中的#
  routes: [
    {
      path: '/',
      component: () => import("@/page/home/index"),
      children: [
        {
          path: '/',
          redirect: '/home'
        },
        {
          path: '/product',
          name: 'product',
          component: () => import("@/page/product/productlist")
        },
        {
          path: '/productdeatil',
          name: 'productdeatil',
          component: () => import("@/page/product/productDeatil"),
          children: [
            {
              path: '/productDiscuss',
              name: 'productDiscuss',
              component: () => import("@/page/product/productDiscuss")
            },
            {
              path: '/productDeatilImage',
              name: 'productDeatilImage',
              component: () => import("@/page/product/productDeatilImage")
            }
          ]
        },
        {
          path: '/home',
          name: 'home',
          component: () => import("@/page/home/homeproduct")
        }]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import("@/page/Login")
    },
    {
      path: '/register',
      name: 'register',
      component: () => import("@/page/register")
    },
    {
      path: '/findLoginName',
      name: 'findLoginName',
      component: () => import("@/page/user/findLoginName")
    },
    {
      path: '/findPwd',
      name: 'findPwd',
      component: () => import("@/page/user/findPwd")
    },
    {
      path: '/newsList',
      name: 'newsList',
      component: () => import("@/page/news/newsList")
    },
    {
      path: '/newsDeatil',
      name: 'newsDeatil',
      component: () => import("@/page/news/newsDeatil")
    },
    {
      path: '/pay',
      name: '/pay',
      component: () => import("@/page/pay/index")
    },
    {
      path: '/shopCat',
      name: 'shopCat',
      component: () => import("@/page/shopCat/index")
    },
    {
      path: '/order',
      name: 'order',
      component: () => import("@/page/order/index")
    },{
      path: '/collectible',
      name: 'collectible',
      component: () => import("@/page/collectible/index")
    },
    {
      path: '/fillOrderInfo',
      name: 'fillOrderInfo',
      component: () => import("@/page/shopCat/fillOrderInfo")
    },
    {
      path: '/help',
      name: 'help',
      component: () => import("@/page/help/index"),
      children: [
        {
          path: '/',
          redirect: '/newbie'
        },
        {
          path: '/newbie',
          name: 'newbie',
          component: () => import("@/page/help/Newbie")
        },
        {
          path: '/integralHelp',
          name: 'integralHelp',
          component: () => import("@/page/help/integralHelp")
        },
        {
          path: '/account',
          name: 'account',
          component: () => import("@/page/help/account")
        },
        {
          path: '/saleService',
          name: 'saleService',
          component: () => import("@/page/help/saleService")
        }
      ]
    }
  ]
})
