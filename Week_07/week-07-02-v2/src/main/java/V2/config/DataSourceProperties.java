package V2.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.util.DriverDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wangdan
 * @date 2021/3/8
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@EnableConfigurationProperties(DataSourceProperties.class)
@Data
public class DataSourceProperties {

    private HikariDataSource master;

    private List<HikariDataSource> slaves;

}
