package com.whmnrc.feimei.beans;

/**
 * 描述：公用
 *
 * @author linzheng
 */
public class BaseBean<T>  {


    /**
     * type : 0
     * code : 0
     * message : string
     * resultdata : {}
     */

    private T resultdata;
    /**
     * type : 0
     * code : 0
     * message : string
     * resultdata : {}
     */

    private int type;
    /**
     * code : 0
     * message : string
     * resultdata : {}
     */

    private int code;
    private String message;

    public BaseBean() {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }




    public T getResultdata() {
        return resultdata;
    }

    public void setResultdata(T resultdata) {
        this.resultdata = resultdata;
    }


}
