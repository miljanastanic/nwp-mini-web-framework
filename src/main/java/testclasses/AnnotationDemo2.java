package testclasses;

import framework.annotations.Controller;
import framework.annotations.GET;
import framework.annotations.POST;
import framework.annotations.Path;

@Controller
public class AnnotationDemo2 {



    @GET
    @Path(path = "/test1")
    public void getmetoda(){
        System.out.println("This is method1");
    }

    @POST
    @Path(path = "/test2")
    public void postmetoda(){
        System.out.println("This is method2");
    }

    public AnnotationDemo2(){

    }
}
