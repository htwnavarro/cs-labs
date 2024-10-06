
class Main {
    public static void main(String[] args) {
        Harry var1 = new Harry();
        Harry var2 = new Larry();
        Larry var3 = new Jerry();
        Mary var4 = new Mary();
        Mary var5 = new Jerry();
        Object var6 = new Larry();

        System.out.println('1');
        var1.method1();
        System.out.println('2');
        var2.method1();
        System.out.println('3');
        var3.method1();
        System.out.println('4');
        var4.method1();
        System.out.println('5');
        var5.method1();
        System.out.println("6- compiler error");
        //compiler error
//        System.out.println(var6.method1());
        System.out.println("7");
        var1.method2();
        System.out.println("8");
        var2.method2();
        System.out.println("9");
        var3.method2();
        System.out.println("10 - compiler error");
        // compiler error
//        System.out.println(var1.method3());
        System.out.println("11 - compiler error");
//        System.out.println(var2.method3());
        System.out.println("12 - compiler error");
//        System.out.println(var3.method3());
        System.out.println("13 - runtime error");
//        ((Larry) var1).method1();
        System.out.println("14 - runtime error");
//        ((Mary) var2).method2()
        System.out.println("15 - runtime error");;
//        ((Jerry) var5).method1();
        System.out.println("16");
        ((Mary) var5).method3();
        System.out.println("17 - runtime error");
//        ((Jerry) var4).method3();
        System.out.println("18 - runtime error");
//        ((Mary) var6).method3();
    }
}