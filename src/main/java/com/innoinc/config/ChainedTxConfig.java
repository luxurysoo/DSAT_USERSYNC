/**
 * 트랜젝션 설정
 * 
 * DatabaseConfig에 생성한 txmanger been을 묶는 ChainedTxConfig
 * 여러개의 DatabaseConfig가 각 txmanger been을 전부 추가해야 한다.
 *
 * @author 박민성
 */

package com.innoinc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class ChainedTxConfig {
    /**
     * 각 DatabaseConfig의 txmanger been을 파라메타, retrun에 추가 전부 추가
     * ex)
     *   PlatformTransactionManager postgresTxManager, mssqlTxManager) {
     *   return new ChainedTransactionManager(
     *   		postgresTxManager, mssqlTxManager);
     * 
     * @param postgresTxManager
     * @return
     */
    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
    		PlatformTransactionManager postgresTxManager, PlatformTransactionManager oracleTxManager) {
        return new ChainedTransactionManager(
        		postgresTxManager, oracleTxManager);
    }
}
