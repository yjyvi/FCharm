package com.whmnrc.feimei.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author yjyvi
 * @data 2018/8/7.
 */

public class OrganizationDetailsBean implements Parcelable {


    public OrganizationDetailsBean() {
    }

    /**
     * type : 1
     * code : 200
     * message : 成功
     * resultdata : {"Enterprise":{"ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","EnterpriseTypeID":"1","Name":"测试企业","Explain":"这里是企业说明啊","Subtitle":"测试副标题 列表","MainExplain":"这里是主营说明,列表的","Rating":4,"CreateTime":"1539100800","RegisteredCapital":"500万","RegistrationState":"在营","City":"黄石","Provincial":"湖北","Phone":"18671232222","QQ":"123545","Mail":"412215435@qq.com","Address":"查收到时","MainBusiness":"http://www.optic-female.cn/AppPage/Registration?ID=e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","Introduction":"企业概况企业概况企业概况企业概况","Price":1,"Label":"测试","IsRed":0,"IsRecommend":1,"IsTopRecommend":1,"Sort":1,"Industry_PID":"9a1a9432-3dbc-4000-854b-8eff361e1f8f","Industry_ID":"1680037d-becf-4f59-a4f7-f95549b5ea32","LegalPerson":"测试"},"Shareholder":[{"ID":"d4b207f9-e41e-4ddd-9c06-954471afadef","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","Name":"测试","Amount":"100万","Time":"2018-8-6","Ratio":"40%","IsShareholder":1,"IsBigShareholder":0,"IsLegalPerson":0,"IsSurveillance":0}],"Certificate":[{"ID":"941131f4-4024-457c-bf20-6458812ddbe9","Number":"EFAS1123154A","IssueTime":"2018-8-6","ValidityTime":"未公布","Name":"高新证","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78"}],"Relation":[{"ID":"7ebd7685-68cf-4ac1-9031-a28924772dc3","Type":0,"Name":"菲魅（武汉）通信技术有限公司","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":"d9cc5a27-cee9-476a-80a8-af7e1c7bfbc2"},{"ID":"ca004fde-f179-44fb-8a7b-4bd0e865d1c6","Type":0,"Name":"关联测试企业","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":""},{"ID":"f182d1ae-1efe-4304-958e-99d74836af62","Type":1,"Name":"武汉菲魅人力资源服务有限公司","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":"d6b06ff1-89d6-4896-ac1d-6efd5e1035d0"}],"EnterpriseOther":[{"Img":"http://www.optic-female.cn/Resource/PhotoFile/dbe374ec-7e2e-4f08-bac8-c743f8a08ce3.jpg","Subtitle":"测试副标题","Time":"1533484800","Title":"测试标题","Conten":"http://www.optic-female.cn/AppPage/Registration?ID=3fe382da-4205-4467-ad2d-572c15f6dbc4"}],"Comment":[{"UserID":"465a62bd-6564-4a14-ad28-8c841dcb80a0","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg","UserName":"测试","Conten":"测试评论","CreateTime":"1533571200","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]}],"CommentCount":1,"IsPay":1,"RecruitCount":10}
     */

    private int type;
    private int code;
    private String message;
    private ResultdataBean resultdata;

    protected OrganizationDetailsBean(Parcel in) {
        type = in.readInt();
        code = in.readInt();
        message = in.readString();
    }

    public static final Creator<OrganizationDetailsBean> CREATOR = new Creator<OrganizationDetailsBean>() {
        @Override
        public OrganizationDetailsBean createFromParcel(Parcel in) {
            return new OrganizationDetailsBean(in);
        }

        @Override
        public OrganizationDetailsBean[] newArray(int size) {
            return new OrganizationDetailsBean[size];
        }
    };

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

    public ResultdataBean getResultdata() {
        return resultdata;
    }

    public void setResultdata(ResultdataBean resultdata) {
        this.resultdata = resultdata;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeInt(code);
        dest.writeString(message);
    }

    public static class ResultdataBean  implements Parcelable{
        public ResultdataBean() {
        }

        /**
         * Enterprise : {"ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","EnterpriseTypeID":"1","Name":"测试企业","Explain":"这里是企业说明啊","Subtitle":"测试副标题 列表","MainExplain":"这里是主营说明,列表的","Rating":4,"CreateTime":"1539100800","RegisteredCapital":"500万","RegistrationState":"在营","City":"黄石","Provincial":"湖北","Phone":"18671232222","QQ":"123545","Mail":"412215435@qq.com","Address":"查收到时","MainBusiness":"http://www.optic-female.cn/AppPage/Registration?ID=e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","Introduction":"企业概况企业概况企业概况企业概况","Price":1,"Label":"测试","IsRed":0,"IsRecommend":1,"IsTopRecommend":1,"Sort":1,"Industry_PID":"9a1a9432-3dbc-4000-854b-8eff361e1f8f","Industry_ID":"1680037d-becf-4f59-a4f7-f95549b5ea32","LegalPerson":"测试"}
         * Shareholder : [{"ID":"d4b207f9-e41e-4ddd-9c06-954471afadef","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","Name":"测试","Amount":"100万","Time":"2018-8-6","Ratio":"40%","IsShareholder":1,"IsBigShareholder":0,"IsLegalPerson":0,"IsSurveillance":0}]
         * Certificate : [{"ID":"941131f4-4024-457c-bf20-6458812ddbe9","Number":"EFAS1123154A","IssueTime":"2018-8-6","ValidityTime":"未公布","Name":"高新证","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78"}]
         * Relation : [{"ID":"7ebd7685-68cf-4ac1-9031-a28924772dc3","Type":0,"Name":"菲魅（武汉）通信技术有限公司","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":"d9cc5a27-cee9-476a-80a8-af7e1c7bfbc2"},{"ID":"ca004fde-f179-44fb-8a7b-4bd0e865d1c6","Type":0,"Name":"关联测试企业","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":""},{"ID":"f182d1ae-1efe-4304-958e-99d74836af62","Type":1,"Name":"武汉菲魅人力资源服务有限公司","Enterprise_ID":"e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78","RelationEnterprise_ID":"d6b06ff1-89d6-4896-ac1d-6efd5e1035d0"}]
         * EnterpriseOther : [{"Img":"http://www.optic-female.cn/Resource/PhotoFile/dbe374ec-7e2e-4f08-bac8-c743f8a08ce3.jpg","Subtitle":"测试副标题","Time":"1533484800","Title":"测试标题","Conten":"http://www.optic-female.cn/AppPage/Registration?ID=3fe382da-4205-4467-ad2d-572c15f6dbc4"}]
         * Comment : [{"UserID":"465a62bd-6564-4a14-ad28-8c841dcb80a0","UserHeadImg":"http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg","UserName":"测试","Conten":"测试评论","CreateTime":"1533571200","CommentAdd":["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]}]
         * CommentCount : 1
         * IsPay : 1
         * RecruitCount : 10
         */



        private EnterpriseBean Enterprise;
        private int CommentCount;
        private int IsPay;
        private int RecruitCount;
        private List<ShareholderBean> Shareholder;
        private List<CertificateBean> Certificate;
        private List<RelationBean> Relation;
        private List<EnterpriseOtherBean> EnterpriseOther;
        private List<CommentBean> Comment;

        protected ResultdataBean(Parcel in) {
            Enterprise = in.readParcelable(EnterpriseBean.class.getClassLoader());
            CommentCount = in.readInt();
            IsPay = in.readInt();
            RecruitCount = in.readInt();
            Shareholder = in.createTypedArrayList(ShareholderBean.CREATOR);
            Certificate = in.createTypedArrayList(CertificateBean.CREATOR);
            Relation = in.createTypedArrayList(RelationBean.CREATOR);
            EnterpriseOther = in.createTypedArrayList(EnterpriseOtherBean.CREATOR);
        }

        public static final Creator<ResultdataBean> CREATOR = new Creator<ResultdataBean>() {
            @Override
            public ResultdataBean createFromParcel(Parcel in) {
                return new ResultdataBean(in);
            }

            @Override
            public ResultdataBean[] newArray(int size) {
                return new ResultdataBean[size];
            }
        };

        public EnterpriseBean getEnterprise() {
            return Enterprise;
        }

        public void setEnterprise(EnterpriseBean Enterprise) {
            this.Enterprise = Enterprise;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public int getIsPay() {
            return IsPay;
        }

        public void setIsPay(int IsPay) {
            this.IsPay = IsPay;
        }

        public int getRecruitCount() {
            return RecruitCount;
        }

        public void setRecruitCount(int RecruitCount) {
            this.RecruitCount = RecruitCount;
        }

        public List<ShareholderBean> getShareholder() {
            return Shareholder;
        }

        public void setShareholder(List<ShareholderBean> Shareholder) {
            this.Shareholder = Shareholder;
        }

        public List<CertificateBean> getCertificate() {
            return Certificate;
        }

        public void setCertificate(List<CertificateBean> Certificate) {
            this.Certificate = Certificate;
        }

        public List<RelationBean> getRelation() {
            return Relation;
        }

        public void setRelation(List<RelationBean> Relation) {
            this.Relation = Relation;
        }

        public List<EnterpriseOtherBean> getEnterpriseOther() {
            return EnterpriseOther;
        }

        public void setEnterpriseOther(List<EnterpriseOtherBean> EnterpriseOther) {
            this.EnterpriseOther = EnterpriseOther;
        }

        public List<CommentBean> getComment() {
            return Comment;
        }

        public void setComment(List<CommentBean> Comment) {
            this.Comment = Comment;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(Enterprise, flags);
            dest.writeInt(CommentCount);
            dest.writeInt(IsPay);
            dest.writeInt(RecruitCount);
            dest.writeTypedList(Shareholder);
            dest.writeTypedList(Certificate);
            dest.writeTypedList(Relation);
            dest.writeTypedList(EnterpriseOther);
        }

        public static class EnterpriseBean implements Parcelable {
            public EnterpriseBean() {
            }

            /**
             * ID : e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             * EnterpriseTypeID : 1
             * Name : 测试企业
             * Explain : 这里是企业说明啊
             * Subtitle : 测试副标题 列表
             * MainExplain : 这里是主营说明,列表的
             * Rating : 4
             * CreateTime : 1539100800
             * RegisteredCapital : 500万
             * RegistrationState : 在营
             * City : 黄石
             * Provincial : 湖北
             * Phone : 18671232222
             * QQ : 123545
             * Mail : 412215435@qq.com
             * Address : 查收到时
             * MainBusiness : http://www.optic-female.cn/AppPage/Registration?ID=e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             * Introduction : 企业概况企业概况企业概况企业概况
             * Price : 1
             * Label : 测试
             * IsRed : 0
             * IsRecommend : 1
             * IsTopRecommend : 1
             * Sort : 1
             * Industry_PID : 9a1a9432-3dbc-4000-854b-8eff361e1f8f
             * Industry_ID : 1680037d-becf-4f59-a4f7-f95549b5ea32
             * LegalPerson : 测试
             */

            private String ID;
            private String EnterpriseTypeID;
            private String Name;
            private String Explain;
            private String Subtitle;
            private String MainExplain;
            private int Rating;
            private String CreateTime;
            private String RegisteredCapital;
            private String RegistrationState;
            private String City;
            private String Provincial;
            private String Phone;
            private String QQ;
            private String Mail;
            private String Address;
            private String MainBusiness;
            private String Introduction;
            private int Price;
            private String Label;
            private int IsRed;
            private int IsRecommend;
            private int IsTopRecommend;
            private int Sort;
            private String Industry_PID;
            private String Industry_ID;
            private String LegalPerson;

            protected EnterpriseBean(Parcel in) {
                ID = in.readString();
                EnterpriseTypeID = in.readString();
                Name = in.readString();
                Explain = in.readString();
                Subtitle = in.readString();
                MainExplain = in.readString();
                Rating = in.readInt();
                CreateTime = in.readString();
                RegisteredCapital = in.readString();
                RegistrationState = in.readString();
                City = in.readString();
                Provincial = in.readString();
                Phone = in.readString();
                QQ = in.readString();
                Mail = in.readString();
                Address = in.readString();
                MainBusiness = in.readString();
                Introduction = in.readString();
                Price = in.readInt();
                Label = in.readString();
                IsRed = in.readInt();
                IsRecommend = in.readInt();
                IsTopRecommend = in.readInt();
                Sort = in.readInt();
                Industry_PID = in.readString();
                Industry_ID = in.readString();
                LegalPerson = in.readString();
            }

            public static final Creator<EnterpriseBean> CREATOR = new Creator<EnterpriseBean>() {
                @Override
                public EnterpriseBean createFromParcel(Parcel in) {
                    return new EnterpriseBean(in);
                }

                @Override
                public EnterpriseBean[] newArray(int size) {
                    return new EnterpriseBean[size];
                }
            };

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getEnterpriseTypeID() {
                return EnterpriseTypeID;
            }

            public void setEnterpriseTypeID(String EnterpriseTypeID) {
                this.EnterpriseTypeID = EnterpriseTypeID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getExplain() {
                return Explain;
            }

            public void setExplain(String Explain) {
                this.Explain = Explain;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public String getMainExplain() {
                return MainExplain;
            }

            public void setMainExplain(String MainExplain) {
                this.MainExplain = MainExplain;
            }

            public int getRating() {
                return Rating;
            }

            public void setRating(int Rating) {
                this.Rating = Rating;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getRegisteredCapital() {
                return RegisteredCapital;
            }

            public void setRegisteredCapital(String RegisteredCapital) {
                this.RegisteredCapital = RegisteredCapital;
            }

            public String getRegistrationState() {
                return RegistrationState;
            }

            public void setRegistrationState(String RegistrationState) {
                this.RegistrationState = RegistrationState;
            }

            public String getCity() {
                return City;
            }

            public void setCity(String City) {
                this.City = City;
            }

            public String getProvincial() {
                return Provincial;
            }

            public void setProvincial(String Provincial) {
                this.Provincial = Provincial;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String Phone) {
                this.Phone = Phone;
            }

            public String getQQ() {
                return QQ;
            }

            public void setQQ(String QQ) {
                this.QQ = QQ;
            }

            public String getMail() {
                return Mail;
            }

            public void setMail(String Mail) {
                this.Mail = Mail;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getMainBusiness() {
                return MainBusiness;
            }

            public void setMainBusiness(String MainBusiness) {
                this.MainBusiness = MainBusiness;
            }

            public String getIntroduction() {
                return Introduction;
            }

            public void setIntroduction(String Introduction) {
                this.Introduction = Introduction;
            }

            public int getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public String getLabel() {
                return Label;
            }

            public void setLabel(String Label) {
                this.Label = Label;
            }

            public int getIsRed() {
                return IsRed;
            }

            public void setIsRed(int IsRed) {
                this.IsRed = IsRed;
            }

            public int getIsRecommend() {
                return IsRecommend;
            }

            public void setIsRecommend(int IsRecommend) {
                this.IsRecommend = IsRecommend;
            }

            public int getIsTopRecommend() {
                return IsTopRecommend;
            }

            public void setIsTopRecommend(int IsTopRecommend) {
                this.IsTopRecommend = IsTopRecommend;
            }

            public int getSort() {
                return Sort;
            }

            public void setSort(int Sort) {
                this.Sort = Sort;
            }

            public String getIndustry_PID() {
                return Industry_PID;
            }

            public void setIndustry_PID(String Industry_PID) {
                this.Industry_PID = Industry_PID;
            }

            public String getIndustry_ID() {
                return Industry_ID;
            }

            public void setIndustry_ID(String Industry_ID) {
                this.Industry_ID = Industry_ID;
            }

            public String getLegalPerson() {
                return LegalPerson;
            }

            public void setLegalPerson(String LegalPerson) {
                this.LegalPerson = LegalPerson;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeString(EnterpriseTypeID);
                dest.writeString(Name);
                dest.writeString(Explain);
                dest.writeString(Subtitle);
                dest.writeString(MainExplain);
                dest.writeInt(Rating);
                dest.writeString(CreateTime);
                dest.writeString(RegisteredCapital);
                dest.writeString(RegistrationState);
                dest.writeString(City);
                dest.writeString(Provincial);
                dest.writeString(Phone);
                dest.writeString(QQ);
                dest.writeString(Mail);
                dest.writeString(Address);
                dest.writeString(MainBusiness);
                dest.writeString(Introduction);
                dest.writeInt(Price);
                dest.writeString(Label);
                dest.writeInt(IsRed);
                dest.writeInt(IsRecommend);
                dest.writeInt(IsTopRecommend);
                dest.writeInt(Sort);
                dest.writeString(Industry_PID);
                dest.writeString(Industry_ID);
                dest.writeString(LegalPerson);
            }
        }

        public static class ShareholderBean  implements Parcelable{
            public ShareholderBean() {
            }

            /**
             * ID : d4b207f9-e41e-4ddd-9c06-954471afadef
             * Enterprise_ID : e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             * Name : 测试
             * Amount : 100万
             * Time : 2018-8-6
             * Ratio : 40%
             * IsShareholder : 1
             * IsBigShareholder : 0
             * IsLegalPerson : 0
             * IsSurveillance : 0
             */

            private String ID;
            private String Enterprise_ID;
            private String Name;
            private String Amount;
            private String Time;
            private String Ratio;
            private int IsShareholder;
            private int IsBigShareholder;
            private int IsLegalPerson;
            private int IsSurveillance;

            protected ShareholderBean(Parcel in) {
                ID = in.readString();
                Enterprise_ID = in.readString();
                Name = in.readString();
                Amount = in.readString();
                Time = in.readString();
                Ratio = in.readString();
                IsShareholder = in.readInt();
                IsBigShareholder = in.readInt();
                IsLegalPerson = in.readInt();
                IsSurveillance = in.readInt();
            }

            public static final Creator<ShareholderBean> CREATOR = new Creator<ShareholderBean>() {
                @Override
                public ShareholderBean createFromParcel(Parcel in) {
                    return new ShareholderBean(in);
                }

                @Override
                public ShareholderBean[] newArray(int size) {
                    return new ShareholderBean[size];
                }
            };

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getEnterprise_ID() {
                return Enterprise_ID;
            }

            public void setEnterprise_ID(String Enterprise_ID) {
                this.Enterprise_ID = Enterprise_ID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getAmount() {
                return Amount;
            }

            public void setAmount(String Amount) {
                this.Amount = Amount;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public String getRatio() {
                return Ratio;
            }

            public void setRatio(String Ratio) {
                this.Ratio = Ratio;
            }

            public int getIsShareholder() {
                return IsShareholder;
            }

            public void setIsShareholder(int IsShareholder) {
                this.IsShareholder = IsShareholder;
            }

            public int getIsBigShareholder() {
                return IsBigShareholder;
            }

            public void setIsBigShareholder(int IsBigShareholder) {
                this.IsBigShareholder = IsBigShareholder;
            }

            public int getIsLegalPerson() {
                return IsLegalPerson;
            }

            public void setIsLegalPerson(int IsLegalPerson) {
                this.IsLegalPerson = IsLegalPerson;
            }

            public int getIsSurveillance() {
                return IsSurveillance;
            }

            public void setIsSurveillance(int IsSurveillance) {
                this.IsSurveillance = IsSurveillance;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeString(Enterprise_ID);
                dest.writeString(Name);
                dest.writeString(Amount);
                dest.writeString(Time);
                dest.writeString(Ratio);
                dest.writeInt(IsShareholder);
                dest.writeInt(IsBigShareholder);
                dest.writeInt(IsLegalPerson);
                dest.writeInt(IsSurveillance);
            }
        }

        public static class CertificateBean implements Parcelable {
            public CertificateBean() {
            }

            /**
             * ID : 941131f4-4024-457c-bf20-6458812ddbe9
             * Number : EFAS1123154A
             * IssueTime : 2018-8-6
             * ValidityTime : 未公布
             * Name : 高新证
             * Enterprise_ID : e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             */

            private String ID;
            private String Number;
            private String IssueTime;
            private String ValidityTime;
            private String Name;
            private String Enterprise_ID;

            protected CertificateBean(Parcel in) {
                ID = in.readString();
                Number = in.readString();
                IssueTime = in.readString();
                ValidityTime = in.readString();
                Name = in.readString();
                Enterprise_ID = in.readString();
            }

            public static final Creator<CertificateBean> CREATOR = new Creator<CertificateBean>() {
                @Override
                public CertificateBean createFromParcel(Parcel in) {
                    return new CertificateBean(in);
                }

                @Override
                public CertificateBean[] newArray(int size) {
                    return new CertificateBean[size];
                }
            };

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getNumber() {
                return Number;
            }

            public void setNumber(String Number) {
                this.Number = Number;
            }

            public String getIssueTime() {
                return IssueTime;
            }

            public void setIssueTime(String IssueTime) {
                this.IssueTime = IssueTime;
            }

            public String getValidityTime() {
                return ValidityTime;
            }

            public void setValidityTime(String ValidityTime) {
                this.ValidityTime = ValidityTime;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getEnterprise_ID() {
                return Enterprise_ID;
            }

            public void setEnterprise_ID(String Enterprise_ID) {
                this.Enterprise_ID = Enterprise_ID;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeString(Number);
                dest.writeString(IssueTime);
                dest.writeString(ValidityTime);
                dest.writeString(Name);
                dest.writeString(Enterprise_ID);
            }
        }

        public static class RelationBean implements Parcelable {
            public RelationBean() {
            }

            /**
             * ID : 7ebd7685-68cf-4ac1-9031-a28924772dc3
             * Type : 0
             * Name : 菲魅（武汉）通信技术有限公司
             * Enterprise_ID : e77a3cd1-1f49-4c0a-b33f-cd4a0177aa78
             * RelationEnterprise_ID : d9cc5a27-cee9-476a-80a8-af7e1c7bfbc2
             */

            private String ID;
            private int Type;
            private String Name;
            private String Enterprise_ID;
            private String RelationEnterprise_ID;

            protected RelationBean(Parcel in) {
                ID = in.readString();
                Type = in.readInt();
                Name = in.readString();
                Enterprise_ID = in.readString();
                RelationEnterprise_ID = in.readString();
            }

            public static final Creator<RelationBean> CREATOR = new Creator<RelationBean>() {
                @Override
                public RelationBean createFromParcel(Parcel in) {
                    return new RelationBean(in);
                }

                @Override
                public RelationBean[] newArray(int size) {
                    return new RelationBean[size];
                }
            };

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getType() {
                return Type;
            }

            public void setType(int Type) {
                this.Type = Type;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public String getEnterprise_ID() {
                return Enterprise_ID;
            }

            public void setEnterprise_ID(String Enterprise_ID) {
                this.Enterprise_ID = Enterprise_ID;
            }

            public String getRelationEnterprise_ID() {
                return RelationEnterprise_ID;
            }

            public void setRelationEnterprise_ID(String RelationEnterprise_ID) {
                this.RelationEnterprise_ID = RelationEnterprise_ID;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(ID);
                dest.writeInt(Type);
                dest.writeString(Name);
                dest.writeString(Enterprise_ID);
                dest.writeString(RelationEnterprise_ID);
            }
        }

        public static class EnterpriseOtherBean implements Parcelable{
            public EnterpriseOtherBean() {
            }

            /**
             * Img : http://www.optic-female.cn/Resource/PhotoFile/dbe374ec-7e2e-4f08-bac8-c743f8a08ce3.jpg
             * Subtitle : 测试副标题
             * Time : 1533484800
             * Title : 测试标题
             * Conten : http://www.optic-female.cn/AppPage/Registration?ID=3fe382da-4205-4467-ad2d-572c15f6dbc4
             */

            private String Img;
            private String Subtitle;
            private String Time;
            private String Title;
            private String Conten;

            protected EnterpriseOtherBean(Parcel in) {
                Img = in.readString();
                Subtitle = in.readString();
                Time = in.readString();
                Title = in.readString();
                Conten = in.readString();
            }

            public static final Creator<EnterpriseOtherBean> CREATOR = new Creator<EnterpriseOtherBean>() {
                @Override
                public EnterpriseOtherBean createFromParcel(Parcel in) {
                    return new EnterpriseOtherBean(in);
                }

                @Override
                public EnterpriseOtherBean[] newArray(int size) {
                    return new EnterpriseOtherBean[size];
                }
            };

            public String getImg() {
                return Img;
            }

            public void setImg(String Img) {
                this.Img = Img;
            }

            public String getSubtitle() {
                return Subtitle;
            }

            public void setSubtitle(String Subtitle) {
                this.Subtitle = Subtitle;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            public String getTitle() {
                return Title;
            }

            public void setTitle(String Title) {
                this.Title = Title;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(Img);
                dest.writeString(Subtitle);
                dest.writeString(Time);
                dest.writeString(Title);
                dest.writeString(Conten);
            }
        }

        public static class CommentBean {
            /**
             * UserID : 465a62bd-6564-4a14-ad28-8c841dcb80a0
             * UserHeadImg : http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg
             * UserName : 测试
             * Conten : 测试评论
             * CreateTime : 1533571200
             * CommentAdd : ["http://www.optic-female.cn/Resource/Qualification/6be97068-b077-4bb5-9b0b-05d1187a1990.jpg"]
             */

            private String UserID;
            private String UserHeadImg;
            private String UserName;
            private String Conten;
            private String CreateTime;
            private List<String> CommentAdd;

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getUserHeadImg() {
                return UserHeadImg;
            }

            public void setUserHeadImg(String UserHeadImg) {
                this.UserHeadImg = UserHeadImg;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getConten() {
                return Conten;
            }

            public void setConten(String Conten) {
                this.Conten = Conten;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public List<String> getCommentAdd() {
                return CommentAdd;
            }

            public void setCommentAdd(List<String> CommentAdd) {
                this.CommentAdd = CommentAdd;
            }
        }
    }
}
