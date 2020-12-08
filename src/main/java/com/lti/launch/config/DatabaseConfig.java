package com.lti.launch.config;

import com.lti.launch.db.mybatis.mapper.mariadb.MariaDBConnMapper;
import com.lti.launch.db.mybatis.mapper.postgresql.PostgresSqlConnMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

public class DatabaseConfig {

    @Configuration
    @MapperScan( basePackages ="com.lti.launch.db.mybatis.mapper.postgresql"
            , annotationClass = PostgresSqlConnMapper.class
            , sqlSessionFactoryRef = "postgresSqlSessionFactory")
//    @PropertySource("classpath:/application.properties")
    @EnableTransactionManagement
    public static class MybatisPostgresSQL {
        @Autowired
        private ApplicationContext applicationContext;

        @Bean(name = "postgresDataSource")
        @Primary
        // @ConfigurationProperties(prefix = "spring.postgresql.datasource")
        public DataSource dataSource()  {
//            return DataSourceBuilder.create().type(HikariDataSource.class).build();

            Properties props = new Properties();
            props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
            props.setProperty("dataSource.user", "canvas");
            props.setProperty("dataSource.password", "qwaszx12");
            props.setProperty("dataSource.databaseName", "canvas_production");
            props.setProperty("dataSource.portNumber", "5432");
            props.setProperty("dataSource.serverName", "112.169.121.122");

            HikariConfig hikariConfig = new HikariConfig(props);
//            hikariConfig.setDriverClassName("org.postgresql.Driver");
//            hikariConfig.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
//            hikariConfig.setJdbcUrl("jdbc:postgresql://112.169.121.122:5432/canvas_production?charSet=UTF-8&prepareThreshold=1");
//            hikariConfig.setUsername("canvas");
//            hikariConfig.setPassword("qwaszx12");

            return new HikariDataSource(hikariConfig);
        }

        @Bean(name = "postgresSqlSessionFactory")
        @Primary
        public SqlSessionFactory sqlSessionFactory(@Qualifier("postgresDataSource") DataSource dataSource) throws Exception {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/postgresql/**/*.xml"));
            return sqlSessionFactoryBean.getObject();
        }

        @Bean(name = "postgresSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

    }

}
