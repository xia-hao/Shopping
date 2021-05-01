package com.shopping.service.impl;

import com.shopping.domain.*;
import com.shopping.mapper.PayMentMapper;
import com.shopping.mapper.RefundMapper;
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
import java.util.UUID;

/**
 * @PACKAGE_NAME: com.shopping.service.impl
 * @author: XIA
 * @NAME: RefundServiceImpl
 * @DATE: 2020/4/23
 **/
@Service
public class RefundServiceImpl implements RefundService {
    @Resource
    private RefundMapper refundMapper;
    @Resource
    private ProductService productService;
    @Resource
    private OrderService orderService;
    @Resource
    private UserService userService;
    @Resource
    private UserDealrecordService userDealrecordService;
    @Resource
    private ProductSkuService productSkuService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Map refund(Refund refund) {
        Map<String,Object> map = new HashMap<>();
        OrderInfo orderInfo = (OrderInfo)orderService.getOrderByOrderNo(refund.getOrderno()).get("orderInfo");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        map.put("code","901");
        User getUser = userService.getUserById(user.getUserid());
        if(userService.userIncome(user.getUserid(),orderInfo.getOrderprice())>0){ // 归还金额
            OrderInfo order = new OrderInfo();
            order.setOrderno(orderInfo.getOrderno());
            order.setOrderstatu(Constant.ORDER_STATU_REFUNDED);
            if(orderService.updateOrder(order)>0){
                BigDecimal money = getUser.getBalance();
                for(OrderDetail orderDetail : orderInfo.getOrderDetailList()){
                    money = money.add(orderDetail.getCost());

                    // 返回销量
                    productService.minusProductSalesVolume(orderDetail.getProductid(),orderDetail.getNumber());
                    //返回库存
                    productSkuService.addProductSkuNumber(orderDetail.getSkuid(),
                                                            orderDetail.getProductid(),
                                                            orderDetail.getNumber());

                    UserDealrecord userDealrecord = new UserDealrecord();
                    userDealrecord.setUserid(user.getUserid());
                    userDealrecord.setProductname(orderDetail.getProduct().getName());
                    userDealrecord.setNumber(orderDetail.getNumber());
                    userDealrecord.setPrice(orderDetail.getCost());
                    userDealrecord.setUserprice(money);
                    userDealrecord.setCreatetime(new Date());
                    // 用户钱包流水
                    userDealrecordService.adduserDealrecord(userDealrecord);
                }
                refund.setUserId(user.getUserid());
                refund.setRefundNo(UUID.randomUUID().toString().replace("-","").toUpperCase());
                refund.setBrefundMoney(orderInfo.getOrderprice());
                refund.setRefundStatus(Constant.REFUND_STATU);
                refund.setCreatetime(new Date());
                if(refundMapper.insert(refund)>0){
                    map.put("code","1000");
                    map.put("retMsg","退款成功！");
                }else{
                    throw new RuntimeException("退款失败！");
                }
            }else{
                throw new RuntimeException("订单退款失败！");
            }
        }else{
            throw new RuntimeException("退款失败！");
        }
        return map;
    }
}
