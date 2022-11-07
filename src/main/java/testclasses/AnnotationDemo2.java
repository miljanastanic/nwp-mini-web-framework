package testclasses;

import framework.annotations.*;
import framework.request.Request;
import framework.request.enums.Method;
import framework.response.JsonResponse;
import framework.response.Response;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AnnotationDemo2 {

    @Autowired(verbose = true)
    private Test1 bean1;

    @Autowired(verbose = true)
    private Test4 bean2;

    @Autowired(verbose = true)
    private Test2 service1;

    @Autowired(verbose = true)
    private Test3 component1;

    @Autowired(verbose = true)
    @Qualifier("Implementation")
    private TestI interface1;

    @GET
    @Path(path = "/test1")
    public Response getmetoda(Request request){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("route_location", request.getLocation());
        responseMap.put("route_method", request.getMethod().toString());
        responseMap.put("parameters", request.getParameters());
        Response response = new JsonResponse(responseMap);
        return response;
    }

    @POST
    @Path(path = "/test2")
    public Response postmetoda(Request request){
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("route_location", request.getLocation());
        responseMap.put("route_method", request.getMethod().toString());
        responseMap.put("parameters", request.getParameters());
        Response response = new JsonResponse(responseMap);
        return response;
    }

    public AnnotationDemo2(){

    }
}
