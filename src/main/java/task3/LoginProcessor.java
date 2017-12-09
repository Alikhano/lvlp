package task3;

public class LoginProcessor {

    private LoginProcessor() {}

    public static void startProcessor(LoginChecker check) throws NoSuchFieldException {

        Class classofObject = check.getClass();
        final Field[] fields = classofObject.getDeclaredFields();
        for (Field field : fields) {
            final Login annotation = field.getAnnotation(Login.class);
            if (annotation == null) {
                if (field.getName() != "name") {
                    System.out.println("No field with a name");
                    throw new NoSuchFieldException();
                }

            }
            else {
                if (fields.length == 1) {
                    System.out.println("Only login field");
                    throw new NoSuchFieldException();
                }
                else if (check.getName() == "") {
                    System.out.println("Enter the name");
                }
                else {
                    String result = check.getName();
                    check.setLogin(new StringBuffer(result).reverse().toString());
                }
            }
        }
    }
}
