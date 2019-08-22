package com.runcoding.handler.type;


import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xukai
 * @param <T>
 */
public class JsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {


    private Class<T> clazz;
 
    public JsonTypeHandler(Class<T> clazz) {
        if (clazz == null){
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }
 
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, this.toJson(parameter));
    }
 
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName), clazz);
    }
 
    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex), clazz);
    }
 
    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex), clazz);
    }
 
    private String toJson(T object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    private T toObject(String content, Class<?> clazz) {
        if(content == null || content.isEmpty()){
            return (T) content;
        }
        try {
            return (T) JSON.parseObject(content,clazz);
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
        return (T) content;
    }
 

}