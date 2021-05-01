package com.shopping.service.impl;

import com.shopping.domain.*;
import com.shopping.mapper.PayMentMapper;
import com.shopping.service.*;
import com.shopping.utils.Constant;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: PayMentServiceImpl
 * @DATE: 2020/4/22
 **/
@Service
public class PayMentServiceImpl implements PayMentService {
    @Resource
    private PayMentMapper payMentMapper;
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private UserDealrecordService userDealrecordService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map pay(PayMent payMent) {
        Map<String,Object> map = new HashMap<>();
        OrderInfo orderInfo = (OrderInfo)orderService.getOrderByOrderNo(payMent.getOrderno()).get("orderInfo");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        User getUser = userService.getUserById(user.getUserid());
        if(userService.userConsume(user.getUserid(),orderInfo.getOrderprice())>0){ // 扣减余额
            OrderInfo order = new OrderInfo();
            order.setOrderno(orderInfo.getOrderno());
            order.setOrderstatu(Constant.ORDER_STATU_SHIPPED);
            if(orderService.updateOrder(order)>0){
                BigDecimal money = getUser.getBalance();
                for(OrderDetail orderDetail : orderInfo.getOrderDetailList()){
                    money = money.subtract(orderDetail.getCost());

                    // 增添销量
                    productService.addProductSalesVolume(orderDetail.getProductid(),orderDetail.getNumber());

                    UserDealrecord userDealrecord = new UserDealrecord();
                    userDealrecord.setUserid(user.getUserid());
                    userDealrecord.setProductname(orderDetail.getProduct().getName());
                    userDealrecord.setNumber(orderDetail.getNumber());
                    userDealrecord.setPrice(orderDetail.getCost().negate());
                    userDealrecord.setUserprice(money);
                    userDealrecord.setCreatetime(new Date());
                    // 用户钱包流水
                    userDealrecordService.adduserDealrecord(userDealrecord);
                }
                payMent.setUserid(user.getUserid());
                payMent.setLoginname(user.getLoginname());
                payMent.setCost(orderInfo.getOrderprice());
                payMent.setPaystatu(Constant.PAY_MENT_YES_PREPAID);
                payMent.setCreatetime(new Date());
                if(payMentMapper.insert(payMent)>0){
                    map.put("code","1000");
                    map.put("retMsg","支付成功！");
                }else{
                    throw new RuntimeException("支付失败！");
                }
            }else{
                throw new RuntimeException("订单支付失败！");
            }
        }else{
            map.put("retMsg","余额不足！");
            throw new RuntimeException("余额不足！");
        }
        return map;
    }
}
