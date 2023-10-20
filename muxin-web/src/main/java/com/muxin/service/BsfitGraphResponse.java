package com.muxin.service;


import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 该类定义了BsfitGraphResponse，主要用于接口返回
 *
 * @author fujn
 * @date 2021/12/23
 * @apiNode
 */
@Data
public class BsfitGraphResponse<T> implements Serializable,IResultCode {
    private static final long serialVersionUID = -2791356338016228077L;

    private  String code;

    private  T data;

    private  String msg;

    private  Long total;

    private Object[] msgArgs;

    //以下为兼容历史代码，建议配合前端转入data中
    private final Map<String,Object> otherMap=new HashMap<>();

    @Deprecated
    public Object getColumns() {
        return otherMap.get("columns");
    }
    @Deprecated
    public void setColumns(Object columns) {
        this.otherMap.put("columns",columns);
    }
    @Deprecated
    public Object getPaths() {
        return otherMap.get("paths");
    }
    @Deprecated
    public void setPaths(Object paths) {
        this.otherMap.put("paths",paths);
    }
    @Deprecated
    public Object getCount() {
        return otherMap.get("count");
    }
    @Deprecated
    public void setCount(Object count) {
        this.otherMap.put("count",count);
    }
    @Deprecated
    public Object getFail() {
        return otherMap.get("fail");
    }
    @Deprecated
    public void setFail(Object fail) {
        this.otherMap.put("fail",fail);
    }
    @Deprecated
    public Object getIsNull() {
        return otherMap.get("isNull");
    }
    @Deprecated
    public void setIsNull(Object isNull) {
        this.otherMap .put("isNull",isNull);
    }
    @Deprecated
    public Object getStates() {
        return otherMap.get("states");
    }
    @Deprecated
    public void setStates(Object states) {
        this.otherMap .put("states",states);
    }
    @Deprecated
    public Object getIsFirst() {
        return otherMap.get("isFirst");
    }
    @Deprecated
    public void setIsFirst(Object isFirst) {
        this.otherMap .put("isFirst",isFirst);
    }
    @Deprecated
    public Object getChecklist() {
        return otherMap.get("checklist");
    }
    @Deprecated
    public void setChecklist(Object checklist) {
        this.otherMap .put("checklist",checklist);
    }
    @Deprecated
    public Object getRules() {
        return otherMap.get("rules");
    }
    @Deprecated
    public void setRules(Object rules) {
        this.otherMap.put("rules",rules);
    }

    /**
     * 临时兼容
     * @return
     */
    public String getError() {
        if (this.code==null||this.code.startsWith("0")||this.code.equalsIgnoreCase("SUCCESS")
        ){
            return null;
        }
        return this.getMsg();
    }




    public BsfitGraphResponse() {
        this("00000");
    }

    public BsfitGraphResponse(String code) {
        this.code = code;
    }

    public BsfitGraphResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public BsfitGraphResponse(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public BsfitGraphResponse(String code, T data, Long total) {
        this.code = code;
        this.data = data;
        this.total = total;
    }
    public void putCodeArgs(String code, Object... args) {
        this.code = code;
        this.msgArgs =args;
    }
    public Boolean isSuccess() {
        return "00000".equals(this.getCode())||"SUCCESS".equals(this.getCode());
    }

    /**
     * code = 000000
     * 作用：用于成功返回
     * @param
     * @return
     * {
     *     code: SUCCESS,
     *     data: true
     * }
     */
    public static BsfitGraphResponse<Void> success() {
        return new BsfitGraphResponse<>("00000");
    }

    /**
     * code = 000000
     * 作用：用于成功返回，并且有data
     * @param data
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithData(T data) {
        return new BsfitGraphResponse<>("00000",data);
    }

    /**
     * code = 000000
     * 作用：用于成功返回，并且有data,但是将msg置空，前端有些接口返回msg不兼容
     * @param data
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithDataNoMsg(T data) {
        return new BsfitGraphResponse<>("00000",data);
    }

    /**
     * code = 000000
     * 作用：用于成功返回，并且有data,但是将msg置空，前端有些接口返回msg不兼容
     * @param data
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithDataMsg(T data,String msg) {
        return new BsfitGraphResponse<>("00000",data,msg);
    }

    /**
     * code = 000000
     * 作用：用于成功返回，并且有data,但是将msg置空，前端有些接口返回msg不兼容
     * @param msg
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithMsg(String msg) {
        return successWithDataMsg(null,msg);
    }

    /**
     * code = SUCCESS
     * 作用：用于成功返回，并且有data和count,常用于列表
     * @param data,count
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     *     count:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithDataCount(T data, Long count) {
        return new BsfitGraphResponse<>("00000", data, count);
    }

    /**
     * code = SUCCESS
     * 作用：用于成功返回，并且有data和count,常用于列表
     * @param data,count
     * @return
     * {
     *     code: SUCCESS,
     *     data:
     *     count:
     * }
     */
    public static <T> BsfitGraphResponse<T> successWithDataCount(T data, Integer count) {
        return new BsfitGraphResponse<>("00000", data, Long.valueOf(count));
    }

    /**
     * 根据code返回，默认msg
     * @param code
     * @return
     * @param <T>
     */
    public static <T> BsfitGraphResponse<T> resWithCode(String code) {
        return new BsfitGraphResponse<>(code);
    }


}
