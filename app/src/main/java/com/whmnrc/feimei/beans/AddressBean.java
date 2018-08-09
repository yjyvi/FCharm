package com.whmnrc.feimei.beans;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/5/30.
 */

public class AddressBean {

    /**
     * type : 1
     * code : 0
     * message : 请求成功
     * resultdata : [{"Address":"??????","Id":863,"IsDefault":true,"IsQuick":false,"Phone":"17754436736","RegionFullName":"","RegionId":0,"RegionIdPath":"","ShipTo":"????","UserId":4109,"Address_Provice":null,"Address_Country":null,"Address_Address2":null,"Address_City":null,"Address_IsDefault":0,"Address_ZipCode":null,"Address_LastName":null,"Address_StateProvince":null},{"Address":"?????","Id":862,"IsDefault":false,"IsQuick":false,"Phone":"17754436736","RegionFullName":"","RegionId":0,"RegionIdPath":"","ShipTo":"??????","UserId":4109,"Address_Provice":null,"Address_Country":null,"Address_Address2":null,"Address_City":null,"Address_IsDefault":0,"Address_ZipCode":null,"Address_LastName":null,"Address_StateProvince":null}]
     */

    private int type;
    private int code;
    private String message;
    private List<ResultdataBean> resultdata;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultdataBean> getResultdata() {
        return resultdata;
    }

    public void setResultdata(List<ResultdataBean> resultdata) {
        this.resultdata = resultdata;
    }

    public static class ResultdataBean {
        /**
         * Address : ??????
         * Id : 863
         * IsDefault : true
         * IsQuick : false
         * Phone : 17754436736
         * RegionFullName :
         * RegionId : 0
         * RegionIdPath :
         * ShipTo : ????
         * UserId : 4109
         * Address_Provice : null
         * Address_Country : null
         * Address_Address2 : null
         * Address_City : null
         * Address_IsDefault : 0
         * Address_ZipCode : null
         * Address_LastName : null
         * Address_StateProvince : null
         */

        private String Address;
        private int Id;
        private boolean IsDefault;
        private boolean IsQuick;
        private String Phone;
        private String RegionFullName;
        private int RegionId;
        private String RegionIdPath;
        private String ShipTo;
        private int UserId;
        private String Address_Provice;
        private String Address_Country;
        private String Address_Address2;
        private String Address_City;
        private int Address_IsDefault;
        private String Address_ZipCode;
        private String Address_LastName;
        private String Address_StateProvince;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public boolean isIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }

        public boolean isIsQuick() {
            return IsQuick;
        }

        public void setIsQuick(boolean IsQuick) {
            this.IsQuick = IsQuick;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getRegionFullName() {
            return RegionFullName;
        }

        public void setRegionFullName(String RegionFullName) {
            this.RegionFullName = RegionFullName;
        }

        public int getRegionId() {
            return RegionId;
        }

        public void setRegionId(int RegionId) {
            this.RegionId = RegionId;
        }

        public String getRegionIdPath() {
            return RegionIdPath;
        }

        public void setRegionIdPath(String RegionIdPath) {
            this.RegionIdPath = RegionIdPath;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String ShipTo) {
            this.ShipTo = ShipTo;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String  getAddress_Provice() {
            return Address_Provice;
        }

        public void setAddress_Provice(String Address_Provice) {
            this.Address_Provice = Address_Provice;
        }

        public String getAddress_Country() {
            return Address_Country;
        }

        public void setAddress_Country(String Address_Country) {
            this.Address_Country = Address_Country;
        }

        public String getAddress_Address2() {
            return Address_Address2;
        }

        public void setAddress_Address2(String Address_Address2) {
            this.Address_Address2 = Address_Address2;
        }

        public String getAddress_City() {
            return Address_City;
        }

        public void setAddress_City(String Address_City) {
            this.Address_City = Address_City;
        }

        public int getAddress_IsDefault() {
            return Address_IsDefault;
        }

        public void setAddress_IsDefault(int Address_IsDefault) {
            this.Address_IsDefault = Address_IsDefault;
        }

        public String getAddress_ZipCode() {
            return Address_ZipCode;
        }

        public void setAddress_ZipCode(String Address_ZipCode) {
            this.Address_ZipCode = Address_ZipCode;
        }

        public String  getAddress_LastName() {
            return Address_LastName;
        }

        public void setAddress_LastName(String Address_LastName) {
            this.Address_LastName = Address_LastName;
        }

        public String getAddress_StateProvince() {
            return Address_StateProvince;
        }

        public void setAddress_StateProvince(String Address_StateProvince) {
            this.Address_StateProvince = Address_StateProvince;
        }
    }
}
