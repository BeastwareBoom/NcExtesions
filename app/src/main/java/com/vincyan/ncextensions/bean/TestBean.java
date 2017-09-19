package com.vincyan.ncextensions.bean;

import java.util.List;

/**
 * ght (c) 2007-2017 ShopNC Inc. All rights reserved.
 *
 * @author lzz
 *         Created 2017/6/1 16:07
 * @license http://www.shopnc.net
 * @link http://www.shopnc.net
 * @description
 */
public class TestBean {

    @Override
    public String toString() {
        return "TestBean{" +
                "itemList=" + itemList +
                '}';
    }

    private List<ItemListBean> itemList;

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean {

        @Override
        public String toString() {
            return "ItemListBean{" +
                    "itemId=" + itemId +
                    ", specialId=" + specialId +
                    ", itemType='" + itemType + '\'' +
                    ", itemData='" + itemData + '\'' +
                    ", itemSort=" + itemSort +
                    ", wap=" + wap +
                    ", wechat=" + wechat +
                    ", android=" + android +
                    ", ios=" + ios +
                    ", itemTypeText='" + itemTypeText + '\'' +
                    '}';
        }

        /**
         * itemId : 75
         * specialId : 0
         * itemType : ad
         * itemData : [{"image":"image/f3/cd/f3cd12cb76ff1be193dbc091b56fde2b.jpg","type":"keyword","data":"冰箱","imageUrl":"https://uploadjava.bizpower.com/image/f3/cd/f3cd12cb76ff1be193dbc091b56fde2b.jpg"},{"image":"image/18/09/18092294969201596540241d96bc0592.jpg","type":"store","data":"23","imageUrl":"https://uploadjava.bizpower.com/image/18/09/18092294969201596540241d96bc0592.jpg"},{"image":"image/c1/b0/c1b03c41d7eeec63eece551efdeb03af.jpg","type":"group","data":"","imageUrl":"https://uploadjava.bizpower.com/image/c1/b0/c1b03c41d7eeec63eece551efdeb03af.jpg"},{"image":"image/09/b5/09b56be258853ac27ec6ecc946453b65.jpg","type":"store","data":"2","imageUrl":"https://uploadjava.bizpower.com/image/09/b5/09b56be258853ac27ec6ecc946453b65.jpg"}]
         * itemSort : 0
         * wap : 1
         * wechat : 1
         * android : 1
         * ios : 1
         * itemTypeText : 焦点广告图模块
         */

        private int itemId;
        private int specialId;
        private String itemType;
        private String itemData;
        private int itemSort;
        private int wap;
        private int wechat;
        private int android;
        private int ios;
        private String itemTypeText;

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getSpecialId() {
            return specialId;
        }

        public void setSpecialId(int specialId) {
            this.specialId = specialId;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getItemData() {
            return itemData;
        }

        public void setItemData(String itemData) {
            this.itemData = itemData;
        }

        public int getItemSort() {
            return itemSort;
        }

        public void setItemSort(int itemSort) {
            this.itemSort = itemSort;
        }

        public int getWap() {
            return wap;
        }

        public void setWap(int wap) {
            this.wap = wap;
        }

        public int getWechat() {
            return wechat;
        }

        public void setWechat(int wechat) {
            this.wechat = wechat;
        }

        public int getAndroid() {
            return android;
        }

        public void setAndroid(int android) {
            this.android = android;
        }

        public int getIos() {
            return ios;
        }

        public void setIos(int ios) {
            this.ios = ios;
        }

        public String getItemTypeText() {
            return itemTypeText;
        }

        public void setItemTypeText(String itemTypeText) {
            this.itemTypeText = itemTypeText;
        }
    }
}
