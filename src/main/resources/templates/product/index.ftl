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
                    <form role="form" method="post" action="/seller/product/save">
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
                        <div class="form-group">
                            <label>菜的版本</label>
                            <input name="bannerVersion" type="text" class="form-control" value="${(productInfo.bannerVersion)!''}"/>
                        </div>
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
                        <div class="form-group">
                            <label>菜类型的父节点</label>
                            <input name="parentId" type="number" class="form-control" value="${(productInfo.parentId)!''}"/>
                        </div>


                        <div class="form-group">
                            <label>aa</label>
                            <input name="aa" type="text" class="form-control" value="${(productInfo.aa)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>aa1</label>
                            <input name="aa1" type="text" class="form-control" value="${(productInfo.aa1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>bb</label>
                            <input name="bb" type="text" class="form-control" value="${(productInfo.bb)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>bb1</label>
                            <input name="bb1" type="text" class="form-control" value="${(productInfo.bb1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>cc</label>
                            <input name="cc" type="text" class="form-control" value="${(productInfo.cc)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>cc1</label>
                            <input name="cc1" type="text" class="form-control" value="${(productInfo.cc1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>dd</label>
                            <input name="dd" type="text" class="form-control" value="${(productInfo.dd)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>dd1</label>
                            <input name="dd1" type="text" class="form-control" value="${(productInfo.dd1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ee</label>
                            <input name="ee" type="text" class="form-control" value="${(productInfo.ee)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ee1</label>
                            <input name="ee1" type="text" class="form-control" value="${(productInfo.ee1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ff</label>
                            <input name="ff" type="text" class="form-control" value="${(productInfo.ff)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ff1</label>
                            <input name="ff1" type="text" class="form-control" value="${(productInfo.ff1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>gg</label>
                            <input name="gg" type="text" class="form-control" value="${(productInfo.gg)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>gg1</label>
                            <input name="gg1" type="text" class="form-control" value="${(productInfo.gg1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>hh</label>
                            <input name="hh" type="text" class="form-control" value="${(productInfo.hh)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>hh1</label>
                            <input name="hh1" type="text" class="form-control" value="${(productInfo.hh1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ii</label>
                            <input name="ii" type="text" class="form-control" value="${(productInfo.ii)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ii1</label>
                            <input name="ii1" type="text" class="form-control" value="${(productInfo.ii1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>jj</label>
                            <input name="jj" type="text" class="form-control" value="${(productInfo.jj)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>jj1</label>
                            <input name="jj1" type="text" class="form-control" value="${(productInfo.jj1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>kk</label>
                            <input name="kk" type="text" class="form-control" value="${(productInfo.kk)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>kk1</label>
                            <input name="kk1" type="text" class="form-control" value="${(productInfo.kk1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ll</label>
                            <input name="ll" type="text" class="form-control" value="${(productInfo.ll)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>ll1</label>
                            <input name="ll1" type="text" class="form-control" value="${(productInfo.ll1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>nn</label>
                            <input name="nn" type="text" class="form-control" value="${(productInfo.nn)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>nn1</label>
                            <input name="nn1" type="text" class="form-control" value="${(productInfo.nn1)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>mm</label>
                            <input name="mm" type="text" class="form-control" value="${(productInfo.mm)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>mm1</label>
                            <input name="mm1" type="text" class="form-control" value="${(productInfo.mm1)!''}"/>
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