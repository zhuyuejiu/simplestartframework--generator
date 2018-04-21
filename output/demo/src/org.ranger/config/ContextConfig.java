package org.ranger.config;

import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(value = "org.ranger")
@EnableTransactionManagement
@PropertySource(value="classpath:resources/db.properties")
@EnableWebMvc
public class ContextConfig {
		
	@Value(value = "${db.driver}")
	private String driver;
	@Value(value = "${db.url}")
	private String url;
	@Value(value = "${db.username}")
	private String username;
	@Value(value = "${db.password}")
	private String password;
	@Value(value = "${db.maxActive}")
	private int maxActive;
	@Value(value = "${db.maxIdle}")
	private int maxIdle;
	@Value(value = "${db.maxWaitMillis}")
	private long maxWaitMillis;
	@Value(value = "${db.testOnBorrow}")
	private boolean testOnBorrow;
	@Value(value = "${db.validationQuery}")
	private String validationQuery;
	@Value(value = "${db.maxTotal}")
	private int maxTotal;
	

	/**
	 * 文件上传解释器
	 * @return
	 */
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public CommonsMultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(2000000);
		return multipartResolver;
	}
		

	/**
	 * jsp视图解释器
	 * @return
	 */
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public InternalResourceViewResolver jspViewResolver(){
		InternalResourceViewResolver jspViewResolver=new InternalResourceViewResolver();
		jspViewResolver.setViewClass(JstlView.class);
		jspViewResolver.setContentType("text/html; charset=UTF-8");
		jspViewResolver.setPrefix("/WEB-INF/jsp/");
		jspViewResolver.setSuffix(".jsp");
		return jspViewResolver;
	}
	
	/**
	 * 数据源配置 
	 * @return 获得数据源
	 */
	@Bean(name="dataSource")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public DataSource dataSource(){
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setPassword(password);
		dataSource.setUsername(username);
		return dataSource;
	}
	
	/**
	 * 获得SessionFactory
	 * @return 返回SessionFactory
	 */
	@Bean(name="sqlSessionFactory")
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public SqlSessionFactory sessionFactory(){
		SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
		try {
			sessionFactoryBean.setDataSource(this.dataSource());
			
			sessionFactoryBean.afterPropertiesSet();
			return sessionFactoryBean.getObject();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建一个事务代理
	 * @return 返回事务代理对象
	 */
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public DataSourceTransactionManager transactionManager(){
		DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
		transactionManager.setDataSource(this.dataSource());
		return transactionManager;
	}
	
	/**
	 * 扫描mapper接口，并将动态类对象赋入它
	 * 静态表示，本类就可以直接调用
	 * @return
	 */
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public static MapperScannerConfigurer configurer(){
		MapperScannerConfigurer configurer=new MapperScannerConfigurer();
		configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		configurer.setBasePackage("org.ranger.mapper");
		//使用哪个存储的类型的注解作为组件扫描的注入类
		configurer.setAnnotationClass(Repository.class);
		return configurer;
	}
	
	
	/**
	 * 静态表示，在这个类还没有创建对象已经实现化方法的对象
	 * 
	 * @return
	 */
	@Bean
	@Scope(value=BeanDefinition.SCOPE_SINGLETON)
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	

}
