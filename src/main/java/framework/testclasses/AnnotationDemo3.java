package framework.testclasses;

import framework.annotations.Controller;
import framework.annotations.GET;
import framework.annotations.POST;
import framework.annotations.Path;

@Controller
public class AnnotationDemo3 {

    @GET
    public void metoda1(){
        System.out.println("Ovo je metoda1");
    }

    @POST
    public void metoda2(){
        System.out.println("Ovo je metoda2");
    }

    @Path(path = "/metoda3")
    public void metoda3(){
        System.out.println("Ovo je metoda3");
    }

    public AnnotationDemo3() {
    }
}
