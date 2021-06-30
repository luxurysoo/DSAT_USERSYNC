/**
 * Database 설정
 * 
 * DB 1개당 하나의 DatabaseConfig를 생성해야한다.
 * 서브 DataConfig를 사용할 시 서브 dataconfig에서는 @Primary 어노테이션 제거
 * @Primary는 단 1개의 DB에서만 사용
 *
 * @author  
 */

package com.innoinc.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


/**
 * propertySource db.properties 경로 지정
 * MapperScan Mapper 경로 지정
 */
@Configuration
@PropertySource({"classpath:/db.properties"})
@MapperScan(value="com.innoinc.dao.postgres181", sqlSessionFactoryRef="postgres181SqlSessionFactory")
public class Postgres181DatabaseConfig {
    
    /**
     * DataSource 설정 
     *
     * HikariCP를 사용
     * 
     * ConfigurationProperties db.properties 속성을 매핑한다.
     */
	@Bean(name = "postgres181DataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.postgres181.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }
 

    
    /**
     * SessionFactory 설정
     * 
     * setTypeAliasePackage model의 경로 지정
     * setMapperLocations Mapper.xml의 경로 지정
     */
    @Bean(name = "postgres181SqlSessionFactory")
    @Primary
    public SqlSessionFactory postgres181SqlSessionFactory(@Qualifier("postgres181DataSource") DataSource postgresDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(postgresDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.innoinc.model.postgres181");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/postgres181/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * SeesionTemplate 설정
     * 
     * @param postgresSqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean(name = "postgres181SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate postgresSqlSessionTemplate(SqlSessionFactory postgres181SqlSessionFactory) throws Exception {
 
        return new SqlSessionTemplate(postgres181SqlSessionFactory);
    }
    

    /**
     * TxManger 설정
     * 
     * @param postgresDataSource
     * @return
     */
    @Bean(name = "postgres181TxManager")
    public PlatformTransactionManager postgresTxManager(DataSource postgresDataSource) {
        return new DataSourceTransactionManager(postgresDataSource);
    }
    
}
