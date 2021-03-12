package V2.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


import javax.sql.DataSource;
import java.util.*;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@Configuration
@MapperScan(basePackages = "V2.mapper")
public class DynamicDataSourceConfig {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    private DynamicDataSource dynamicDataSource;

    private DataSource master;

    private List<DataSource> slaves;

    @Bean(name = "master")
    public DataSource masterDataSource() {
        DataSource dataSource = DataSourceBuilder.create()
                .url(dataSourceProperties.getMaster().getJdbcUrl())
                .driverClassName(dataSourceProperties.getMaster().getDriverClassName())
                .username(dataSourceProperties.getMaster().getUsername())
                .password(dataSourceProperties.getMaster().getPassword())
                .build();
        this.master = dataSource;
        return this.master;
    }

    @Bean(name = "slaves")
    public List<DataSource> slaveDataSources(){
        List<DataSource> dataSources = new ArrayList<>();
        dataSourceProperties.getSlaves().forEach(slaveDataSource ->{
            DataSource dataSource = DataSourceBuilder.create()
                    .url(slaveDataSource.getJdbcUrl())
                    .driverClassName(slaveDataSource.getDriverClassName())
                    .username(slaveDataSource.getUsername())
                    .password(slaveDataSource.getPassword())
                    .build();
            dataSources.add(dataSource);
        });
        this.slaves = dataSources;
        return dataSources;
    }

    @Bean
    @DependsOn({"master", "slaves"})
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        DataSource master = this.master;
        List<DataSource> slaves = this.slaves;
        targetDataSources.put("master", master);
        for (int i=0; i < slaves.size(); i++){
            targetDataSources.put("slave-" + i, slaves.get(i));
        }

        this.dynamicDataSource =  new DynamicDataSource(master, targetDataSources);
        return this.dynamicDataSource;
    }


    public int getSlaveCounts(){
        return dataSourceProperties.getSlaves().size();
    }

    @Bean(name = "sqlSessionFactory")
    @DependsOn("master")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:V3.mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
