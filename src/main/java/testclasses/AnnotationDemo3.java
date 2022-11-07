package testclasses;

import framework.annotations.*;
import framework.request.Request;
import framework.request.enums.Method;
import framework.response.JsonResponse;
import framework.response.Response;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AnnotationDemo3 {

    @Autowired(verbose = false)
    private Test5 bean3;

    @Autowired(verbose = false)
    private Test6 bean4;

    @Autowired(verbose = false)
    private Test7 service2;

    @GET
    @Path(path = "/test3")
    public Response getmethod(Request request){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("route_location", request.getLocation());
        responseMap.put("route_method", request.getMethod().toString());
        responseMap.put("parameters", request.getParameters());
        Response response = new JsonResponse(responseMap);
        return response;
    }

    @POST
    @Path(path = "/test4")
    public Response postmethod(Request request){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("route_location", request.getLocation());
        responseMap.put("route_method", request.getMethod().toString());
        responseMap.put("parameters", request.getParameters());
        Response response = new JsonResponse(responseMap);
        return response;
    }

    public AnnotationDemo3() {
    }
}
