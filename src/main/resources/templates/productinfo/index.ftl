<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/seller/work/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" type="text" class="form-control" value="${(productInfo.productName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" type="text" class="form-control" value="${(productInfo.productPrice)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input name="productStock" type="number" class="form-control" value="${(productInfo.productStock)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" type="text" class="form-control" value="${(productInfo.productDescription)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label>
                            <img height="100" width="100" src="${(productInfo.productIcon)!''}" alt="">
                            <input name="productIcon" type="text" class="form-control" value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control">
                                <#list categoryList as category>
                                <option value="${category.categoryType}"
                                <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                selected
                            </#if>
                            >${category.categoryName}
                            </option>
                           </#list>
                           </select>
                       </div>
                <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">



                <div class="form-group">
                    <label>菜版本的图片地址</label>
                    <input name="bannerUrl" type="text" class="form-control" value="${(productInfo.bannerUrl)!''}"/>
                </div>
                <div class="form-group">
                    <label>商品评分</label>
                    <input name="productScore" type="text" class="form-control" value="${(productInfo.productScore)!''}"/>
                </div>
                <div class="form-group">
                    <label>商品月销量</label>
                    <input name="productSales" type="number" class="form-control" value="${(productInfo.productSales)!''}"/>
                </div>
                <div class="form-group">
                    <label>配送距离</label>
                    <input name="distance" type="text" class="form-control" value="${(productInfo.distance)!''}"/>
                </div>
                <div class="form-group">
                    <label>配送时间</label>
                    <input name="deliveryTime" type="text" class="form-control" value="${(productInfo.deliveryTime)!''}"/>
                </div>
               <#-- <div class="form-group">
                    <label>菜的版本</label>
                    <input name="bannerVersion" type="text" class="form-control" value="${(productInfo.bannerVersion)!''}"/>
                </div>-->
                <div class="form-group">
                    <label>材料名字</label>
                    <input name="materialName" type="text" class="form-control" value="${(productInfo.materialName)!''}"/>
                </div>
                <div class="form-group">
                    <label>材料的量</label>
                    <input name="materialQuantity" type="text" class="form-control" value="${(productInfo.materialQuantity)!''}"/>
                </div>
                <div class="form-group">
                    <label>卡路里</label>
                    <input name="calories" type="text" class="form-control" value="${(productInfo.calories)!''}"/>
                </div>
                <div class="form-group">
                    <label>步骤1</label>
                    <input name="firstStep" type="text" class="form-control" value="${(productInfo.firstStep)!''}"/>
                </div>
                <div class="form-group">
                    <label>步骤2</label>
                    <input name="secondStep" type="text" class="form-control" value="${(productInfo.secondStep)!''}"/>
                </div>
                <div class="form-group">
                    <label>注意事项</label>
                    <input name="attention" type="text" class="form-control" value="${(productInfo.attention)!''}"/>
                </div>
               <#-- <div class="form-group">
                    <label>菜类型的父节点</label>
                    <input name="parentId" type="number" class="form-control" value="${(productInfo.parentId)!''}"/>
                </div>-->
                        <div class="form-group">
                            <label>用户名</label>
                            <input name="userName" type="text" class="form-control" value="${(productInfo.userName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>菜名</label>
                            <input name="name" type="text" class="form-control" value="${(productInfo.name)!''}"/>
                        </div>





                <button type="submit" class="btn btn-default">提交</button>
                </form>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>