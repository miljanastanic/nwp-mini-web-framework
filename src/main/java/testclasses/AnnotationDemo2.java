package testclasses;

import framework.annotations.*;

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
