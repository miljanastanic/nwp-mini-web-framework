package testclasses;

import framework.annotations.Bean;
import framework.annotations.Scope;

@Bean(scope = Scope.PROTOTYPE)
public class Test4 {

    public Test4() {
    }
}
