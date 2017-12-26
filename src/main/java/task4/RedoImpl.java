package task4;

import java.lang.reflect.Field;

public class RedoImpl {

    public static String toString(Object object) throws IllegalAccessException {
        String output = "";
        String toReturn = "";

        Class classOfObject = object.getClass();
        final Field[] fields = classOfObject.getDeclaredFields();
        for (Field field : fields) {
            String type = field.getType().getSimpleName();
            String name = field.getName();
            field.setAccessible(true);
            String value = field.get(object).toString();
            output = (type + " " + name + " = " + value);
            toReturn = toReturn + " " + output;
        }
        return toReturn;
    }

    public static void main(String[] args) throws IllegalAccessException {
        RedoToString line = new RedoToString();
        String result = toString(line);
        System.out.println(result);
    }
}
