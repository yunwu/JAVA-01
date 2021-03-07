package V2.common;

import V2.config.DynamicDataSource;
import V2.config.DynamicDataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangdan
 * @date 2021/3/7
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect{

    @Autowired
    private DynamicDataSourceConfig dataSourceConfig;

    @Pointcut("execution(* V2.mapper.*(..)))")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        ReadOnly ds = method.getAnnotation(ReadOnly.class);
        ReadOnly clazzAnnotion = (ReadOnly) signature.getDeclaringType().getAnnotation(ReadOnly.class);
        if (ds == null && clazzAnnotion == null) {
            DynamicDataSource.setDataSource("master");
            log.debug("set datasource is master");
        } else {
            //使用随机数字方式获取slave
            int index = (int)(Math.random()*(dataSourceConfig.getSlaveCounts()));
            DynamicDataSource.setDataSource("slave-" + index);
            log.debug("set datasource is slave-" + index);
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.debug("clean datasource");
        }
    }
}
