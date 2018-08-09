package com.whmnrc.feimei.utils.pay;

import java.io.Serializable;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/06/15
 *     desc   : 支付参数
 *     version: 1.0
 * </pre>
 */
public interface PayParam extends Serializable {

    //返回产品Id 对应的创建订单接口中的 productId
    int getProductId();

    String getPayHintString();

}
