package javarepl.completion.helpers;


import java.util.Date;


public class SimpleCompletionInstance {
    public void voidMethod() {
    }


    public int primitiveTypeReturningMethod() {
        return 0;
    }

    public Date complexTypeReturningMethod() {
        return new Date();
    }

    public int overloadedMethod() {
        return 0;
    }

    public int overloadedMethod(int param) {
        return param;
    }

    public int overloadedMethod(int param1, int param2) {
        return param1 + param2;
    }

}
