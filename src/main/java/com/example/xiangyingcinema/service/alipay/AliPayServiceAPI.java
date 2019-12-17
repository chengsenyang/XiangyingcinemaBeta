package com.example.xiangyingcinema.service.alipay;

import com.example.xiangyingcinema.vo.alipay.AliPayInfoVO;
import com.example.xiangyingcinema.vo.alipay.AliPayResultVO;

public interface AliPayServiceAPI {

    AliPayInfoVO getQRCode(String orderId);

    AliPayResultVO getOrderStatus(String orderId);

}