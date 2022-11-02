package testclasses;

import framework.annotations.Controller;
import framework.annotations.GET;
import framework.annotations.POST;
import framework.annotations.Path;

@Controller
public class AnnotationDemo3 {

    @GET
    @Path(path = "/test3")
    public void getmethod(){
        System.out.println("Ovo je metoda1");
    }

    @POST
    @Path(path = "/test4")
    public void postmethod(){
        System.out.println("Ovo je metoda2");
    }

    public AnnotationDemo3() {
    }
}
