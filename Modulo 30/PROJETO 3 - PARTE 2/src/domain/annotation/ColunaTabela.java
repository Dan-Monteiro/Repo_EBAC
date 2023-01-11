package domain.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface ColunaTabela {
    String dbName();
    String setJavaName();
}
