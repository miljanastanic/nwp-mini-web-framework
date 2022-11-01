package framework.annotations;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Bean {

    Scope scope() default Scope.SINGELTON;
}
