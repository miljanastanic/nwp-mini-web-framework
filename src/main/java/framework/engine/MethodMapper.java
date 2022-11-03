package framework.engine;


import java.lang.reflect.Method;
import java.util.List;

public class MethodMapper {

    private String httpMethod;
    private String path;
    private Class controller;
    private Method method;

    public MethodMapper(String httpMethod, String path, Class controller, Method method) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.controller = controller;
        this.method = method;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Class controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
