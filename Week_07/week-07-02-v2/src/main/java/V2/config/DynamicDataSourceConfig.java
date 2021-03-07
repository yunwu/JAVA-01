package V2.config;

import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@Configuration
@MapperScan(basePackages = {"V2.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class DynamicDataSourceConfig {

    //TODO 直接读取list存在问题
    @Value("spring.datasource.slaves")
    private List<SlaveDataSource> slaveDataSources;

    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        DataSource dataSource = DataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean(name = "slaves")
    public List<DataSource> slaveDataSources(){
        List<DataSource> dataSources = new ArrayList<>();
        slaveDataSources.forEach(slaveDataSource ->{
            DataSource dataSource = DataSourceBuilder.create()
                    .url(slaveDataSource.getJdbcUrl())
                    .driverClassName(slaveDataSource.getDriverClassName())
                    .username(slaveDataSource.getUserName())
                    .password(slaveDataSource.getPassword())
                    .build();
            dataSources.add(dataSource);
        });
        return dataSources;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        DataSource master = masterDataSource();
        List<DataSource> slaves = slaveDataSources();
        targetDataSources.put("master", master);
        for (int i=0; i < slaves.size(); i++){
            targetDataSources.put("slave-" + i, slaves.get(i));
        }

        return new DynamicDataSource(master, targetDataSources);
    }

    public int getSlaveCounts(){
        return slaveDataSources.size();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        //建议xml放到DAO接口一起
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));

        return bean.getObject();
    }


    @Data
    class SlaveDataSource{
        @Value("jdbc-url")
        private String jdbcUrl;
        @Value("driver-class-name")
        private String driverClassName;
        @Value("username")
        private String userName;
        @Value("password")
        private String password;
    }
}
