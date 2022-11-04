package framework.engine;

import framework.annotations.Qualifier;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DependencyContainer {

    private final HashMap<Object, String> implementations = new HashMap<>();
    private final HashMap<Object, String> classValues = new HashMap<>();
    private final ArrayList<String> onlyValues = new ArrayList<>();

    public DependencyContainer() {
    }

    public void addClassValues(String value, Class cl){
        classValues.put(cl, value);
        onlyValues.add(value);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean addImplementations(Field f){
        try{
            Class type = f.getType();

            if( f.isAnnotationPresent(Qualifier.class) ) {
                Class cl = Class.forName(type.getName());
                if (findInterfaces(cl)) {
                    Qualifier q = (Qualifier) f.getAnnotation(Qualifier.class);
                    String value = q.value();
                    implementations.put(cl, value);
                    //System.out.println("Implementations" + implementations);
                }
                return true;

            }else{
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private boolean findInterfaces(Class cl){
        Object klasa = implementations.get(cl);

        return klasa == null;
    }

    public boolean QDuplicates(){

        String val1;
        String val2;
        if(onlyValues.size() == 1){
            return false;
        }
        for(int i=0; i<=onlyValues.size(); i++){
            val1 = onlyValues.get(i);
            for(int j=1; j<=onlyValues.size(); j++){
                val2 = onlyValues.get(j);
                if(val1.equalsIgnoreCase(val2)){
                    return true;
                }
            }
        }
        return false;
    }

}
