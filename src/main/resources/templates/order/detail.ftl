<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">


    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-4 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>影片id</th>
                            <th>影片名称</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderDTO.uuid}</td>
                            <td>${orderDTO.filmName}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--订单详情表数据-->
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>影片主图</th>
                            <th>影片评分</th>
                            <th>影片预售数量</th>
                            <th>影片上映时间</th>
                            <th>影片状态</th>
                        </tr>
                        </thead>
                        <tbody>
<#--                        <#list orderDTO.orderDetailList as orderDetail>-->
                        <tr>
<#--                            <td>${orderDTO.imgAddress}</td>-->
                            <td><img height="100" width="100" src="${orderDTO.imgAddress}" alt=""></td>
                            <td>${orderDTO.filmScore}</td>
                            <td>${orderDTO.filmPresalenum}</td>
                            <td>${orderDTO.filmTime}</td>
                            <td>
                                <#if orderDTO.filmStatus == 1>
                                    正在热映
                                </#if>
                                <#if orderDTO.filmStatus == 2>
                                    即将上映
                                </#if>
                                <#if orderDTO.filmStatus == 3>
                                    经典影片
                                </#if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            <#--操作-->
<#--                <div class="col-md-12 column">-->
<#--                <#if orderDTO.getOrderStatusEnum().message == "新订单">-->
<#--                    <a href="/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>-->
<#--                    <a href="/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>-->
<#--                </#if>-->
<#--                </div>-->
            </div>
        </div>
    </div>
</div>

</body>
</html>