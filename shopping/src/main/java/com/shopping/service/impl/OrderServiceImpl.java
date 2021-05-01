package com.shopping.service.impl;

import com.github.pagehelper.PageHelper;
import com.shopping.domain.*;
import com.shopping.mapper.OrderDetailMapper;
import com.shopping.mapper.OrderInfoMapper;
import com.shopping.service.OrderService;
import com.shopping.service.ProductSkuService;
import com.shopping.service.ShopCartService;
import com.shopping.utils.Constant;
import com.shopping.utils.utils.TimeUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: OrderInfoServiceImpl
 * @DATE: 2020/4/20
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderInfoMapper orderInfoMapper;
    @Resource
    private OrderDetailMapper orderDetailMapper;
    @Resource
    private ProductSkuService productSkuService;
    @Resource
    private ShopCartService shopCartService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map addOrder(OrderInfo orderInfo) throws Exception {
        Map<String, Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        orderInfo.setOrderno(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        orderInfo.setUserid(user.getUserid());
        orderInfo.setLoginname(user.getLoginname());
        orderInfo.setOrderstatu(Constant.ORDER_STATU_UNPAID);
        orderInfo.setDeadlinetime(TimeUtils.getNewTime(new Date(), 15));
        orderInfo.setCreatetime(new Date());
        map.put("code", "901");
        map.put("retMsg", "订单提交失败！");
        if (orderInfoMapper.insert(orderInfo) > 0) {
            for (OrderDetail orderDetail : orderInfo.getOrderDetailList()) {
                orderDetail.setOrderno(orderInfo.getOrderno());
                orderDetail.setIsRefund(Constant.ORDER_DETAIL_NO_REFUND);
                orderDetail.setCreatetime(new Date());
                if (productSkuService.minusProductSkuNumber(orderDetail.getSkuid(),
                        orderDetail.getProductid(), orderDetail.getNumber()) > 0) {
                    if (orderDetailMapper.insert(orderDetail) > 0) {
                        map.put("code", "1000");
                        map.put("retMsg", "订单提交成功！");
                        map.put("orderNo", orderInfo.getOrderno());
                        shopCartService.delShopCart(user.getUserid(), orderDetail.getProductid(), orderDetail.getSkuid());
                    } else {
                        throw new RuntimeException("订单提交失败！");
                    }
                } else {
                    throw new RuntimeException("库存不足！");
                }
            }
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int timingCancelOrder() {
        return orderInfoMapper.timingCancelOrder();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateOrder(OrderInfo orderInfo) {
        return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map selectOrderByUidAndStatu(OrderInfo orderInfo) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        orderInfo.setUserid(user.getUserid());
        map.put("code", "901");
        PageHelper.startPage(orderInfo.getPageNo(), orderInfo.getPageSize());
        List<OrderInfo> orderInfoList = orderInfoMapper.selectOrderByUidAndStatu(orderInfo);
        if (!orderInfoList.isEmpty()) {
            map.put("code", "1000");
            map.put("orderInfoList", orderInfoList);
            map.put("orderCount", orderInfoMapper.getOrderCount(orderInfo));
        }
        return map;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map getOrderByOrderNo(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderNo);
        map.put("code", "901");
        if (null != orderInfo) {
            map.put("code", "1000");
            map.put("orderInfo", orderInfo);
        }
        return map;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map delOrderByOrderNo(String orderNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "901");
        if(orderDetailMapper.deleteByOrderNo(orderNo)>0){
            if(orderInfoMapper.deleteByOrderNo(orderNo)>0){
                map.put("code", "1000");
                map.put("retMsg", "订单删除成功！");
            }
        }
        if(!map.get("code").equals("1000")){
            throw new RuntimeException("订单删除失败！");
        }
        return map;
    }
}
