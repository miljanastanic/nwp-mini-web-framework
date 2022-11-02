package framework.engine;

import framework.annotations.Controller;
import framework.annotations.GET;
import framework.annotations.POST;
import framework.annotations.Path;

import java.io.File;

import java.lang.reflect.Method;
import java.util.List;

public class DependencyInjectionEngine {

    private List<MethodMapper> routes;

    public DependencyInjectionEngine() throws ClassNotFoundException {
        filesIteration();
    }

    private void filesIteration() throws ClassNotFoundException {
        File dir = new File("C:\\Users\\milja\\Desktop\\Faks\\7 semestar\\nwp-domaci2\\src\\main\\java\\testclasses");
        iterate(dir.listFiles());
    }


    private void iterate(File[] files) throws ClassNotFoundException {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName() + "\n");
                iterate(file.listFiles());
            } else {
                String classname = file.getName().split("\\.")[0];
                findClasses(classname);
            }
        }
    }

    @SuppressWarnings({"rawtypes"})
    private void findClasses(String classname){
        try {
            Class test = Class.forName("testclasses." + classname);
            if (test.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) test.getAnnotation(Controller.class);
                System.out.println("Controller is present in class " + classname);
                findMethods(test);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void findMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            String name = m.getName();
            try {
                Method method = cl.getDeclaredMethod(name);
                if (method.isAnnotationPresent(Path.class)) {

                    Path path = method.getAnnotation(Path.class);

                    if(method.isAnnotationPresent(GET.class)) {
                        System.out.println("Putanja " + path.path() + " metode " + name + " sa anotacijom GET");
                        //MethodMapper mp = new MethodMapper("GET", path.path(), cl, method);
                        //routes.add(mp);
                    }
                    if(method.isAnnotationPresent(POST.class)){
                        System.out.println("Putanja " + path.path() + " metode " + name + " sa anotacijom POST");
                        //MethodMapper mp = new MethodMapper("POST", path.path(), cl, method);
                        //routes.add(mp);
                    }


                }
            }catch(NoSuchMethodException e){
                e.printStackTrace();
            }
        }
    }


}
