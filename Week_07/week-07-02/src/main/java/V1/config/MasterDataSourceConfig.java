package V1.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@Configuration
@MapperScan(basePackages = {"V1.V3.mapper.write"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("master") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //建议xml放到DAO接口一起
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:V3.mapper/write/*.xml"));
        //bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));

        return bean.getObject();
    }
}
