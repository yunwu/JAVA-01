package V2.common;

import java.lang.annotation.*;

/**
 * @author wangdan
 * @date 2021/3/7
 */


@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReadOnly {
}
