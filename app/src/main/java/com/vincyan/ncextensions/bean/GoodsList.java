package com.vincyan.ncextensions.bean;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/5/3 14:46
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class GoodsList {


    private List<StoreCommendListBean> storeCommendList;

    public List<StoreCommendListBean> getStoreCommendList() {
        return storeCommendList;
    }

    public void setStoreCommendList(List<StoreCommendListBean> storeCommendList) {
        this.storeCommendList = storeCommendList;
    }

    public static class StoreCommendListBean {
        /**
         * commonId : 23501
         * goodsName : 测试积分商品4
         * jingle : 测试积分商品4
         * categoryId : 1035
         * categoryId1 : 256
         * categoryId2 : 1034
         * categoryId3 : 1035
         * specJson : null
         * goodsSpecValueJson : [{"goodsId":23502,"specValueIds":""}]
         * storeId : 1
         * brandId : 0
         * goodsBody : <p>
         <img
         src="http://192.168.1.232/upload/image/e2/c7/e2c7abfb74a96441006cee4020b0f94d.jpg" /> <img
         src="http://192.168.1.232/upload/image/d3/02/d302740bee8cbb22b19b1776dfb0de5f.jpg" /> <img src="http://192.168.1.232/upload/image/5f/ac/5facf24e47a7b0a9b0c8614ff0383df4.jpg" /></p>
         * mobileBody : [{"type":"image","value":"http://192.168.1.232/upload/image/e5/f0/e5f01f28b0bb62d6e5934c774336d9d2.jpg"},{"type":"image","value":"http://192.168.1.232/upload/image/72/f5/72f5ff4159d5cdeda7ac10477c4ae521.jpg"},{"type":"image","value":"http://192.168.1.232/upload/image/1f/16/1f16cd875c201140fc2635f2e4eaff14.jpg"},{"type":"image","value":"http://192.168.1.232/upload/image/95/bb/95bbd7129d74d9f90be207d790739f44.jpg"},{"type":"image","value":"http://192.168.1.232/upload/image/b6/43/b6439d077e932f271be7915d1cf7bda7.jpg"},{"type":"text","value":"我是一行文字"},{"type":"text","value":"我是二行文字"}]
         * goodsState : 1
         * stateRemark : null
         * goodsVerify : 1
         * verifyRemark : null
         * createTime : 2016-11-23 10:59:35
         * updateTime : 2017-04-28 15:15:02
         * areaId1 : 0
         * areaId2 : 0
         * areaInfo : null
         * goodsFreight : 0
         * freightTemplateId : 0
         * freightWeight : 1
         * freightVolume : 1
         * formatTop : 0
         * formatBottom : 0
         * isCommend : 1
         * batchNum0 : 1
         * batchNum0End : 0
         * batchNum1 : 0
         * batchNum1End : 0
         * batchNum2 : 0
         * batchPrice0 : 1111
         * batchPrice1 : 0
         * batchPrice2 : 1111
         * webPrice0 : 1111
         * webPrice1 : 0
         * webPrice2 : 1111
         * webPriceMin : 1111
         * webUsable : 0
         * appPrice0 : 1111
         * appPrice1 : 0
         * appPrice2 : 1111
         * appPriceMin : 1111
         * appUsable : 0
         * wechatPrice0 : 1111
         * wechatPrice1 : 0
         * wechatPrice2 : 1111
         * wechatPriceMin : 1111
         * wechatUsable : 0
         * promotionId : 0
         * promotionStartTime : 2016-12-22 11:00:11
         * promotionEndTime : 2016-12-22 11:10:25
         * promotionState : 0
         * promotionDiscountRate : 0
         * promotionType : 3
         * promotionTypeText : 定金预售
         * goodsModal : 1
         * goodsSpecNames : null
         * unitName : 把
         * goodsFavorite : 0
         * goodsClick : 0
         * evaluateNum : 7
         * goodsRate : 100
         * goodsSaleNum : 39
         * imageName : image/5f/ac/5facf24e47a7b0a9b0c8614ff0383df4.jpg
         * imageSrc : http://192.168.1.232/upload/image/5f/ac/5facf24e47a7b0a9b0c8614ff0383df4.jpg
         * joinBigSale : 1
         * goodsImageList : []
         * goodsList : []
         * isGift : 0
         * groupId : 0
         * isGroupEdit : 0
         * isDistribution : 1
         * isPointsGoods : 1
         * contractItem1 : 0
         * contractItem2 : 0
         * contractItem3 : 0
         * contractItem4 : 0
         * contractItem5 : 0
         * contractItem6 : 0
         * contractItem7 : 0
         * contractItem8 : 0
         * contractItem9 : 0
         * contractItem10 : 0
         * web : 0
         * app : 0
         * wechat : 0
         */

        private int commonId;
        private String goodsName;
        private String jingle;
        private int categoryId;
        private int categoryId1;
        private int categoryId2;
        private int categoryId3;
        private Object specJson;
        private String goodsSpecValueJson;
        private int storeId;
        private int brandId;
        private String goodsBody;
        private String mobileBody;
        private int goodsState;
        private Object stateRemark;
        private int goodsVerify;
        private Object verifyRemark;
        private String createTime;
        private String updateTime;
        private int areaId1;
        private int areaId2;
        private Object areaInfo;
        private int goodsFreight;
        private int freightTemplateId;
        private int freightWeight;
        private int freightVolume;
        private int formatTop;
        private int formatBottom;
        private int isCommend;
        private int batchNum0;
        private int batchNum0End;
        private int batchNum1;
        private int batchNum1End;
        private int batchNum2;
        private int batchPrice0;
        private int batchPrice1;
        private int batchPrice2;
        private int webPrice0;
        private int webPrice1;
        private int webPrice2;
        private int webPriceMin;
        private int webUsable;
        private int appPrice0;
        private int appPrice1;
        private int appPrice2;
        private int appPriceMin;
        private int appUsable;
        private int wechatPrice0;
        private int wechatPrice1;
        private int wechatPrice2;
        private int wechatPriceMin;
        private int wechatUsable;
        private int promotionId;
        private String promotionStartTime;
        private String promotionEndTime;
        private int promotionState;
        private int promotionDiscountRate;
        private int promotionType;
        private String promotionTypeText;
        private int goodsModal;
        private Object goodsSpecNames;
        private String unitName;
        private int goodsFavorite;
        private int goodsClick;
        private int evaluateNum;
        private int goodsRate;
        private int goodsSaleNum;
        private String imageName;
        private String imageSrc;
        private int joinBigSale;
        private int isGift;
        private int groupId;
        private int isGroupEdit;
        private int isDistribution;
        private int isPointsGoods;
        private int contractItem1;
        private int contractItem2;
        private int contractItem3;
        private int contractItem4;
        private int contractItem5;
        private int contractItem6;
        private int contractItem7;
        private int contractItem8;
        private int contractItem9;
        private int contractItem10;
        private int web;
        private int app;
        private int wechat;
        private List<?> goodsImageList;
        private List<?> goodsList;

        public int getCommonId() {
            return commonId;
        }

        public void setCommonId(int commonId) {
            this.commonId = commonId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getJingle() {
            return jingle;
        }

        public void setJingle(String jingle) {
            this.jingle = jingle;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getCategoryId1() {
            return categoryId1;
        }

        public void setCategoryId1(int categoryId1) {
            this.categoryId1 = categoryId1;
        }

        public int getCategoryId2() {
            return categoryId2;
        }

        public void setCategoryId2(int categoryId2) {
            this.categoryId2 = categoryId2;
        }

        public int getCategoryId3() {
            return categoryId3;
        }

        public void setCategoryId3(int categoryId3) {
            this.categoryId3 = categoryId3;
        }

        public Object getSpecJson() {
            return specJson;
        }

        public void setSpecJson(Object specJson) {
            this.specJson = specJson;
        }

        public String getGoodsSpecValueJson() {
            return goodsSpecValueJson;
        }

        public void setGoodsSpecValueJson(String goodsSpecValueJson) {
            this.goodsSpecValueJson = goodsSpecValueJson;
        }

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getGoodsBody() {
            return goodsBody;
        }

        public void setGoodsBody(String goodsBody) {
            this.goodsBody = goodsBody;
        }

        public String getMobileBody() {
            return mobileBody;
        }

        public void setMobileBody(String mobileBody) {
            this.mobileBody = mobileBody;
        }

        public int getGoodsState() {
            return goodsState;
        }

        public void setGoodsState(int goodsState) {
            this.goodsState = goodsState;
        }

        public Object getStateRemark() {
            return stateRemark;
        }

        public void setStateRemark(Object stateRemark) {
            this.stateRemark = stateRemark;
        }

        public int getGoodsVerify() {
            return goodsVerify;
        }

        public void setGoodsVerify(int goodsVerify) {
            this.goodsVerify = goodsVerify;
        }

        public Object getVerifyRemark() {
            return verifyRemark;
        }

        public void setVerifyRemark(Object verifyRemark) {
            this.verifyRemark = verifyRemark;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getAreaId1() {
            return areaId1;
        }

        public void setAreaId1(int areaId1) {
            this.areaId1 = areaId1;
        }

        public int getAreaId2() {
            return areaId2;
        }

        public void setAreaId2(int areaId2) {
            this.areaId2 = areaId2;
        }

        public Object getAreaInfo() {
            return areaInfo;
        }

        public void setAreaInfo(Object areaInfo) {
            this.areaInfo = areaInfo;
        }

        public int getGoodsFreight() {
            return goodsFreight;
        }

        public void setGoodsFreight(int goodsFreight) {
            this.goodsFreight = goodsFreight;
        }

        public int getFreightTemplateId() {
            return freightTemplateId;
        }

        public void setFreightTemplateId(int freightTemplateId) {
            this.freightTemplateId = freightTemplateId;
        }

        public int getFreightWeight() {
            return freightWeight;
        }

        public void setFreightWeight(int freightWeight) {
            this.freightWeight = freightWeight;
        }

        public int getFreightVolume() {
            return freightVolume;
        }

        public void setFreightVolume(int freightVolume) {
            this.freightVolume = freightVolume;
        }

        public int getFormatTop() {
            return formatTop;
        }

        public void setFormatTop(int formatTop) {
            this.formatTop = formatTop;
        }

        public int getFormatBottom() {
            return formatBottom;
        }

        public void setFormatBottom(int formatBottom) {
            this.formatBottom = formatBottom;
        }

        public int getIsCommend() {
            return isCommend;
        }

        public void setIsCommend(int isCommend) {
            this.isCommend = isCommend;
        }

        public int getBatchNum0() {
            return batchNum0;
        }

        public void setBatchNum0(int batchNum0) {
            this.batchNum0 = batchNum0;
        }

        public int getBatchNum0End() {
            return batchNum0End;
        }

        public void setBatchNum0End(int batchNum0End) {
            this.batchNum0End = batchNum0End;
        }

        public int getBatchNum1() {
            return batchNum1;
        }

        public void setBatchNum1(int batchNum1) {
            this.batchNum1 = batchNum1;
        }

        public int getBatchNum1End() {
            return batchNum1End;
        }

        public void setBatchNum1End(int batchNum1End) {
            this.batchNum1End = batchNum1End;
        }

        public int getBatchNum2() {
            return batchNum2;
        }

        public void setBatchNum2(int batchNum2) {
            this.batchNum2 = batchNum2;
        }

        public int getBatchPrice0() {
            return batchPrice0;
        }

        public void setBatchPrice0(int batchPrice0) {
            this.batchPrice0 = batchPrice0;
        }

        public int getBatchPrice1() {
            return batchPrice1;
        }

        public void setBatchPrice1(int batchPrice1) {
            this.batchPrice1 = batchPrice1;
        }

        public int getBatchPrice2() {
            return batchPrice2;
        }

        public void setBatchPrice2(int batchPrice2) {
            this.batchPrice2 = batchPrice2;
        }

        public int getWebPrice0() {
            return webPrice0;
        }

        public void setWebPrice0(int webPrice0) {
            this.webPrice0 = webPrice0;
        }

        public int getWebPrice1() {
            return webPrice1;
        }

        public void setWebPrice1(int webPrice1) {
            this.webPrice1 = webPrice1;
        }

        public int getWebPrice2() {
            return webPrice2;
        }

        public void setWebPrice2(int webPrice2) {
            this.webPrice2 = webPrice2;
        }

        public int getWebPriceMin() {
            return webPriceMin;
        }

        public void setWebPriceMin(int webPriceMin) {
            this.webPriceMin = webPriceMin;
        }

        public int getWebUsable() {
            return webUsable;
        }

        public void setWebUsable(int webUsable) {
            this.webUsable = webUsable;
        }

        public int getAppPrice0() {
            return appPrice0;
        }

        public void setAppPrice0(int appPrice0) {
            this.appPrice0 = appPrice0;
        }

        public int getAppPrice1() {
            return appPrice1;
        }

        public void setAppPrice1(int appPrice1) {
            this.appPrice1 = appPrice1;
        }

        public int getAppPrice2() {
            return appPrice2;
        }

        public void setAppPrice2(int appPrice2) {
            this.appPrice2 = appPrice2;
        }

        public int getAppPriceMin() {
            return appPriceMin;
        }

        public void setAppPriceMin(int appPriceMin) {
            this.appPriceMin = appPriceMin;
        }

        public int getAppUsable() {
            return appUsable;
        }

        public void setAppUsable(int appUsable) {
            this.appUsable = appUsable;
        }

        public int getWechatPrice0() {
            return wechatPrice0;
        }

        public void setWechatPrice0(int wechatPrice0) {
            this.wechatPrice0 = wechatPrice0;
        }

        public int getWechatPrice1() {
            return wechatPrice1;
        }

        public void setWechatPrice1(int wechatPrice1) {
            this.wechatPrice1 = wechatPrice1;
        }

        public int getWechatPrice2() {
            return wechatPrice2;
        }

        public void setWechatPrice2(int wechatPrice2) {
            this.wechatPrice2 = wechatPrice2;
        }

        public int getWechatPriceMin() {
            return wechatPriceMin;
        }

        public void setWechatPriceMin(int wechatPriceMin) {
            this.wechatPriceMin = wechatPriceMin;
        }

        public int getWechatUsable() {
            return wechatUsable;
        }

        public void setWechatUsable(int wechatUsable) {
            this.wechatUsable = wechatUsable;
        }

        public int getPromotionId() {
            return promotionId;
        }

        public void setPromotionId(int promotionId) {
            this.promotionId = promotionId;
        }

        public String getPromotionStartTime() {
            return promotionStartTime;
        }

        public void setPromotionStartTime(String promotionStartTime) {
            this.promotionStartTime = promotionStartTime;
        }

        public String getPromotionEndTime() {
            return promotionEndTime;
        }

        public void setPromotionEndTime(String promotionEndTime) {
            this.promotionEndTime = promotionEndTime;
        }

        public int getPromotionState() {
            return promotionState;
        }

        public void setPromotionState(int promotionState) {
            this.promotionState = promotionState;
        }

        public int getPromotionDiscountRate() {
            return promotionDiscountRate;
        }

        public void setPromotionDiscountRate(int promotionDiscountRate) {
            this.promotionDiscountRate = promotionDiscountRate;
        }

        public int getPromotionType() {
            return promotionType;
        }

        public void setPromotionType(int promotionType) {
            this.promotionType = promotionType;
        }

        public String getPromotionTypeText() {
            return promotionTypeText;
        }

        public void setPromotionTypeText(String promotionTypeText) {
            this.promotionTypeText = promotionTypeText;
        }

        public int getGoodsModal() {
            return goodsModal;
        }

        public void setGoodsModal(int goodsModal) {
            this.goodsModal = goodsModal;
        }

        public Object getGoodsSpecNames() {
            return goodsSpecNames;
        }

        public void setGoodsSpecNames(Object goodsSpecNames) {
            this.goodsSpecNames = goodsSpecNames;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public int getGoodsFavorite() {
            return goodsFavorite;
        }

        public void setGoodsFavorite(int goodsFavorite) {
            this.goodsFavorite = goodsFavorite;
        }

        public int getGoodsClick() {
            return goodsClick;
        }

        public void setGoodsClick(int goodsClick) {
            this.goodsClick = goodsClick;
        }

        public int getEvaluateNum() {
            return evaluateNum;
        }

        public void setEvaluateNum(int evaluateNum) {
            this.evaluateNum = evaluateNum;
        }

        public int getGoodsRate() {
            return goodsRate;
        }

        public void setGoodsRate(int goodsRate) {
            this.goodsRate = goodsRate;
        }

        public int getGoodsSaleNum() {
            return goodsSaleNum;
        }

        public void setGoodsSaleNum(int goodsSaleNum) {
            this.goodsSaleNum = goodsSaleNum;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public String getImageSrc() {
            return imageSrc;
        }

        public void setImageSrc(String imageSrc) {
            this.imageSrc = imageSrc;
        }

        public int getJoinBigSale() {
            return joinBigSale;
        }

        public void setJoinBigSale(int joinBigSale) {
            this.joinBigSale = joinBigSale;
        }

        public int getIsGift() {
            return isGift;
        }

        public void setIsGift(int isGift) {
            this.isGift = isGift;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public int getIsGroupEdit() {
            return isGroupEdit;
        }

        public void setIsGroupEdit(int isGroupEdit) {
            this.isGroupEdit = isGroupEdit;
        }

        public int getIsDistribution() {
            return isDistribution;
        }

        public void setIsDistribution(int isDistribution) {
            this.isDistribution = isDistribution;
        }

        public int getIsPointsGoods() {
            return isPointsGoods;
        }

        public void setIsPointsGoods(int isPointsGoods) {
            this.isPointsGoods = isPointsGoods;
        }

        public int getContractItem1() {
            return contractItem1;
        }

        public void setContractItem1(int contractItem1) {
            this.contractItem1 = contractItem1;
        }

        public int getContractItem2() {
            return contractItem2;
        }

        public void setContractItem2(int contractItem2) {
            this.contractItem2 = contractItem2;
        }

        public int getContractItem3() {
            return contractItem3;
        }

        public void setContractItem3(int contractItem3) {
            this.contractItem3 = contractItem3;
        }

        public int getContractItem4() {
            return contractItem4;
        }

        public void setContractItem4(int contractItem4) {
            this.contractItem4 = contractItem4;
        }

        public int getContractItem5() {
            return contractItem5;
        }

        public void setContractItem5(int contractItem5) {
            this.contractItem5 = contractItem5;
        }

        public int getContractItem6() {
            return contractItem6;
        }

        public void setContractItem6(int contractItem6) {
            this.contractItem6 = contractItem6;
        }

        public int getContractItem7() {
            return contractItem7;
        }

        public void setContractItem7(int contractItem7) {
            this.contractItem7 = contractItem7;
        }

        public int getContractItem8() {
            return contractItem8;
        }

        public void setContractItem8(int contractItem8) {
            this.contractItem8 = contractItem8;
        }

        public int getContractItem9() {
            return contractItem9;
        }

        public void setContractItem9(int contractItem9) {
            this.contractItem9 = contractItem9;
        }

        public int getContractItem10() {
            return contractItem10;
        }

        public void setContractItem10(int contractItem10) {
            this.contractItem10 = contractItem10;
        }

        public int getWeb() {
            return web;
        }

        public void setWeb(int web) {
            this.web = web;
        }

        public int getApp() {
            return app;
        }

        public void setApp(int app) {
            this.app = app;
        }

        public int getWechat() {
            return wechat;
        }

        public void setWechat(int wechat) {
            this.wechat = wechat;
        }

        public List<?> getGoodsImageList() {
            return goodsImageList;
        }

        public void setGoodsImageList(List<?> goodsImageList) {
            this.goodsImageList = goodsImageList;
        }

        public List<?> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<?> goodsList) {
            this.goodsList = goodsList;
        }
    }
}
