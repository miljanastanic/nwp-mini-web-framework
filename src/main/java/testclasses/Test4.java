package testclasses;

import framework.annotations.Bean;
import framework.annotations.Qualifier;
import framework.annotations.Scope;

@Bean(scope = Scope.PROTOTYPE)
@Qualifier("SomeValue")
public class Test4 {

    public Test4() {
    }
}
