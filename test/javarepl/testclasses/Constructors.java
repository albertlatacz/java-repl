package javarepl.testclasses;

public class Constructors {
    public static Object defaultAccessTestClass() {
        return new DefaultAccessTestClass();
    }

    public static Object protectedAccessTestClass() {
        return new ProtectedAccessTestClass();
    }

    public static Object privateAccessTestClass() {
        return new PrivateAccessTestClass();
    }

    public static Object anonymousInnerBaseTestInterface() {
        return new BaseTestInterface() {
        };
    }

    public static Object anonymousInnerGenericTestClass() {
        return new GenericTestClass<BaseTestInterface>() {
        };
    }


    private static class PrivateAccessTestClass extends PublicBaseTestClass implements BaseTestInterface{}
}
