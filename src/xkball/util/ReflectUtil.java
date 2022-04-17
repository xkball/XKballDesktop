package xkball.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ReflectUtil {
    public static Field[] getPublicStaticFinalFields(Class c){
        Field[] fields = c.getDeclaredFields();
        ArrayList<Field> fieldArrayList = new ArrayList<>();
        for (Field field : fields) {
            int test = field.getModifiers();
            boolean isPublic = Modifier.isPublic(test);
            boolean isStatic = Modifier.isStatic(test);
            boolean isFinal = Modifier.isFinal(test);
            if (isPublic && isStatic && isFinal) {
                fieldArrayList.add(field);
            }
        }
        Field[] result = new Field[fieldArrayList.size()];
        for(int i = 0;i<fieldArrayList.size();i++){
            result[i] = fieldArrayList.get(i);
        }
        return result;
    }
}
