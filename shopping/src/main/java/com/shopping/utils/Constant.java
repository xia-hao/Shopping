package com.shopping.utils;

/**
 * @PACKAGE_NAME: com.shopping.utils
 * @author: XIA
 * @NAME: Constant
 * @DATE: 2020/3/23
 **/
public class Constant {
    /***
     * 未验证
     */
    public final static String VERIFY_STATU_NOT = "1";
    /***
     * 已验证
     */
    public final static String VERIFY_STATU_END = "2";
    /***
     * 已过期
     */
    public final static String VERIFY_STATU_STALE = "3";

//    /***
//     * 注册
//     */
//    public final static String VERIFY_DESCRIBE_REGISTER = "1";
//    /***
//     * 找回密码
//     */
//    public final static String VERIFY_DESCRIBE_FIND_USERNAME = "2";
//    /***
//     * 找回用户名
//     */
//    public final static String VERIFY_DESCRIBE_FIND_PWD = "3";

    /***
     * 登录状态：正常
     */
    public final static String USER_STATU_NORMAL = "1";
    /***
     * 登录状态：冻结
     */
    public final static String USER_STATU_FREEZE = "2";



    /**
     * 订单状态：待支付
     * */
    public final static String ORDER_STATU_UNPAID = "1";

    /***
     * 订单状态：已支付
     */
    public final static String ORDER_STATU_PREPAID = "2";

    /***
     * 订单状态：已取消
     */
    public final static String ORDER_STATU_CANCELED = "3";

    /***
     * 订单状态：已发货
     */
    public final static String ORDER_STATU_SHIPPED = "4";

    /***
     * 订单状态：已收货
     */
    public final static String ORDER_STATU_RECEIVED = "5";

    /***
     * 订单状态：已退款
     */
    public final static String ORDER_STATU_REFUNDED = "6";

    /***
     * 订单明细状态：未退款
     */
    public final static String ORDER_DETAIL_NO_REFUND = "1";

    /***
     * 订单明细状态：已退款
     */
    public final static String ORDER_DETAIL_YES_REFUND = "2";

    /***
     * 支付状态:支付成功
     */
    public final static String PAY_MENT_YES_PREPAID = "2";

    /***
     * 支付状态:支付失败
     */
    public final static String PAY_MENT_NO_PREPAID = "3";

    /***
     * 退款状态:退款失败
     */
    public final static String REFUND_STATU = "2";
}
