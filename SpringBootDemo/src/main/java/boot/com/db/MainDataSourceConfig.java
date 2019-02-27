package boot.com.db;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "boot.com.db.mainDs",entityManagerFactoryRef = "mainManagerFactory",transactionManagerRef="mainTransactionManager")
public class MainDataSourceConfig {
	
	/**
	 * ����Դ����
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties("main.datasource")
	public DataSourceProperties mainSourceProperties(){
		return new DataSourceProperties();
	}
	
	/**
	 * ����Դ����
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties("main.datasource")
	public DataSource mainDataSource(){
		return mainSourceProperties().initializeDataSourceBuilder().build();
	}
	
	/**
     * ʵ��������
     * @param builder ��springע������������ȸ���typeע�루�����ȡ����@Primary�Ķ��󣩣��������nameע��
     * @return
     */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(mainDataSource())
                .packages("boot.com.entity")
                .persistenceUnit("mainDs")
                .build();
    }

    /**
     * ����������
     * @return
     */
    @Bean(name = "secondTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(mainDataSource());
    }

    @Bean
    @Primary
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }

}
