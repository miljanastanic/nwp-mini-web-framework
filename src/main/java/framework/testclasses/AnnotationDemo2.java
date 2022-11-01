package framework.testclasses;

import framework.annotations.Controller;
import framework.annotations.GET;
import framework.annotations.POST;
import framework.annotations.Path;

@Controller
public class AnnotationDemo2 {
    @GET
    public void method1(){
        System.out.println("This is method1");
    }

    @POST
    public void method2(){
        System.out.println("This is method2");
    }

    @Path(path = "/metoda3")
    public void method3(){
        System.out.println("This is method3");
    }

    public AnnotationDemo2(){

    }
}
