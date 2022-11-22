package com.muxin.common.datasource;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class IdTypeEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private static final String ID_TYPE_KEY = "mybatis-plus.global-config.db-config.id-type";

    private static final Set<DbType> INPUT_ID_TYPES = new HashSet<>(Arrays.asList(DbType.ORACLE, DbType.ORACLE_12C,
            DbType.POSTGRE_SQL, DbType.KINGBASE_ES, DbType.DB2, DbType.H2));

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 如果获取不到 DbType，则不进行处理
        DbType dbType = getDbType(environment);
        if (dbType == null) {
            return;
        }

        MybatisPlusConfig.DB_TYPE=dbType;

        // 如果非 NONE，则不进行处理
        IdType idType =null;
        idType = getIdType(environment);
        if (idType!=null&&idType != IdType.NONE) {
            log.info("idType:{}",idType);
            return;
        }
        // 情况一，用户输入 ID，适合 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库
        if (INPUT_ID_TYPES.contains(dbType)) {
            idType=IdType.INPUT;
            setIdType(environment,idType);
            log.info("idType:{}",idType);
            return;
        }
        // 情况二，自增 ID，适合 MySQL 等直接自增的数据库
        idType=IdType.AUTO;
        setIdType(environment, idType);
        log.info("idType:{}",idType);
    }

    public IdType getIdType(ConfigurableEnvironment environment) {
        return environment.getProperty(ID_TYPE_KEY, IdType.class);
    }

    public void setIdType(ConfigurableEnvironment environment, IdType idType) {
        environment.getSystemProperties().put(ID_TYPE_KEY, idType);
        log.info("[setIdType][修改 MyBatis Plus 的 idType 为({})]", idType);
    }

    public static DbType getDbType(ConfigurableEnvironment environment) {
        String jdbcUrl = environment.getProperty("spring.datasource.url");
        if (StrUtil.isEmpty(jdbcUrl)) {
            return null;
        }
        return JdbcUtils.getDbType(jdbcUrl);
    }

}
