package com.muxin.system.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.muxin.system.ann.CustomIdSeq;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Projectname: muxin
 * @Filename: CustomIdGenerator
 * @Author: yangxz
 * @Data:2022/11/10 17:53
 * @Description: 自定义主键生成器
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    @Value("${spring.dbType:mysql}")
    private String dbType;

    @Autowired
    DataSource dataSource ;



    @Override
    public Long nextId(Object entity) {
        if ("oracle".equalsIgnoreCase(dbType)){
            return genOracleId(entity);
        }
        //mysql 主键自增不需要生成
        return null;
    }


    /**
     * oracle 生成ID
     * @param entity
     * @return
     */
    private Long genOracleId(Object entity){
        try (Connection connection = dataSource.getConnection();){
            Statement statement = connection.createStatement();
            Class<?> aClass = ReflectionUtil.forName(entity.getClass().getName());
            String seqKey="SEQ_ORACLE_LONG_KEY";
            if (aClass.isAssignableFrom(CustomIdSeq.class)){
                CustomIdSeq customIdSeq = aClass.getAnnotation(CustomIdSeq.class);
                seqKey= customIdSeq.seqKey();
            }
            String sql = "select %s.nextval from dual";
            sql = String.format(sql, seqKey);
            // 得到结果
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                Long nextval = rs.getLong("nextval");
                return nextval;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}