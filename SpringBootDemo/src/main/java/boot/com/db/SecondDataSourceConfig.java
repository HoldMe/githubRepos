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
	 * 数据源配置
	 * @return
	 */
	@Bean
	@ConfigurationProperties("second.datasource")
	public DataSourceProperties secondSourceProperties(){
		return new DataSourceProperties();
	}
	
	/**
	 * 数据源对象
	 * @return
	 */
	@Bean
	@ConfigurationProperties("second.datasource")
	public DataSource secondDataSource(){
		return secondSourceProperties().initializeDataSourceBuilder().build();
	}
	
	/**
     * 实体管理对象
     * @param builder 由spring注入这个对象，首先根据type注入（多个就取声明@Primary的对象），否则根据name注入
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
     * 事务管理对象
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


