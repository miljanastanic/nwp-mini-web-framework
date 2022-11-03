package framework.engine;

import framework.annotations.*;

import java.io.File;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DependencyInjectionEngine {

    private final HashMap<Class, MethodMapper> routes = new HashMap<>();
    private final HashMap<Class, Object> controllers = new HashMap<>();
    private final HashMap<Class, Object> instancesSingelton = new HashMap<>();
    private final HashMap<Class, Object> instances = new HashMap<>();


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
                //System.out.println("Controller is present in class " + classname);
                findMethods(test);
                findAtributes(test);
                Object instance = test.getDeclaredConstructor().newInstance();
                controllers.put(test, instance);

            }
        }catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e){
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
                        //System.out.println("Putanja " + path.path() + " metode " + name + " sa anotacijom GET");
                        MethodMapper mp = new MethodMapper("GET", path.path(), cl, method);
                        routes.put(cl, mp);
                    }
                    if(method.isAnnotationPresent(POST.class)){
                        //System.out.println("Putanja " + path.path() + " metode " + name + " sa anotacijom POST");
                        MethodMapper mp = new MethodMapper("POST", path.path(), cl, method);
                        routes.put(cl, mp);
                    }


                }
            }catch(NoSuchMethodException e){
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void findAtributes(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields) {
            Class type = f.getType();
            String name = f.getName();

            if(f.isAnnotationPresent(Autowired.class)) {
                Autowired auto = f.getAnnotation(Autowired.class);

                if(auto.verbose()){
                    System.out.println("Initialized " + type + " " + name + " in "
                            + cl.getName() + " on " + LocalDateTime.now() + " with " + auto.hashCode());
                }

                try {
                    if (type.isInterface()) {
                        //atribut je interfejs
                    } else {
                        String klasaF = f.getType().toString().split(" ")[1];
                        Class test = Class.forName(klasaF);

                        if (test.isAnnotationPresent(Bean.class)) {
                            Bean bean = (Bean) test.getAnnotation(Bean.class);
                            if(bean.scope() == Scope.SINGELTON){
                                Object instance = findSingeltons(test);
                                if(instance == null){
                                    instance = test.getDeclaredConstructor().newInstance();
                                    instancesSingelton.put(test, instance);
                                }

                            }else{
                                Object instance = test.getDeclaredConstructor().newInstance();
                                instances.put(test, instance);
                            }
                        }
                        else if (test.isAnnotationPresent(Service.class)) {
                            Object instance = findSingeltons(test);
                            if(instance == null){
                                instance = test.getDeclaredConstructor().newInstance();
                                instancesSingelton.put(test, instance);
                            }
                        }
                        else{
                            Object objekat = test.getDeclaredConstructor().newInstance();
                            instances.put(test, objekat);
                        }
                    }
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println(instancesSingelton);
        //System.out.println(instances);
    }

    private Object findSingeltons(Class cl){
        Object klasa = instancesSingelton.get(cl);
        return klasa;
    }


}
