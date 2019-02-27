package boot.com.db;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "boot.com.db.secondDs",entityManagerFactoryRef = "secondManagerFactory",transactionManagerRef="secondTransactionManager")
public class SecondDataSourceConfig {
	
	/**
	 * ����Դ����
	 * @return
	 */
	@Bean
	@ConfigurationProperties("second.datasource")
	public DataSourceProperties secondSourceProperties(){
		return new DataSourceProperties();
	}
	
	/**
	 * ����Դ����
	 * @return
	 */
	@Bean
	@ConfigurationProperties("second.datasource")
	public DataSource secondDataSource(){
		return secondSourceProperties().initializeDataSourceBuilder().build();
	}
	
	/**
     * ʵ��������
     * @param builder ��springע������������ȸ���typeע�루�����ȡ����@Primary�Ķ��󣩣��������nameע��
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .packages("boot.com.entity")
                .persistenceUnit("secondDs")
                .build();
    }

    /**
     * ����������
     * @return
     */
    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean(name="secondJdbcTemplate")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(secondDataSource());
    }

    @Bean(name="secondTransactionTemplate")
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }

}


