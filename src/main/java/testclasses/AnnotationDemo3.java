package testclasses;

import framework.annotations.*;

@Controller
public class AnnotationDemo3 {

    @Autowired(verbose = false)
    private Test1 bean3;

    @Autowired(verbose = false)
    private Test4 bean4;

    @Autowired(verbose = false)
    private Test2 service2;

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
