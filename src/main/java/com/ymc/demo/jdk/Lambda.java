package com.ymc.demo.jdk;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.*;

/**
 * @author yiautos
 * @date 2020/1/15 14:33
 */
public class Lambda {

    public static void main(String[] args)
    {
        System.out.println("================== JDK1.8特性 ==================");
        Comparator<Integer> cpt2 = (x, y) -> Integer.compare(x,y);
        TreeSet<Integer> set2 = new TreeSet<>(cpt2);
        System.out.println(set2);
        System.out.println("================== Consumer<T> 消费型接口 ==================");
        changeStr("hello",(str) -> println(str));
        System.out.println("================== Supplier<T> 供给型接口 ==================");
        String value = getValue(() -> "hello");
        System.out.println(value);
        System.out.println("================== Function 《T,R》：:函数式接口，有参有返回值 ==================");
        Long result = changeNum(100L, (x) -> x + 200L);
        System.out.println(result);
        System.out.println("================== Predicate《T》： 断言型接口，有参有返回值，返回值是boolean类型 ==================");
        boolean results = changeBoolean("hello", (str) -> str.length() >= 5);
        System.out.println(results);


        System.out.println("================== 方法引用 ==================");
/**
 *注意：
 *   1.lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
 *   2.若lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
 *
 */
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);

        // 方法引用-对象::实例方法
        Consumer<Integer> con2 = System.out::println;
        con2.accept(200);

        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        Integer result3 = biFun2.apply(100, 200);
        System.out.println(result3);

        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        Boolean result2 = fun2.apply("hello", "world");
        System.out.println(result2);



        System.out.println("================== 构造器引用 ==================");
        // 构造方法引用  类名::new
        Supplier<Employee> sup = () -> new Employee();
        Employee e = new Employee();
        e.setSex("男");
        e.setName("张杰");
        System.out.println(sup.get().toString());
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());



        System.out.println("================== 数组引用 ==================");
        // 数组引用
        Function<Integer, String[]> fun = (x) -> new String[x];
        Function<Integer, String[]> fun4 = String[]::new;
        String[] strArray = fun4.apply(10);
        Arrays.stream(strArray).forEach(System.out::println);

    }
    /**
     *  Predicate<T> 断言型接口
     * @param str
     * @param pre
     * @return
     */
    private static boolean changeBoolean(String str, Predicate<String> pre){
        return pre.test(str);
    }

    /**
     *  Supplier<T> 供给型接口
     * @param sup
     * @return
     */
    private static String getValue(Supplier<String> sup) {
        return sup.get();
    }

    /**
     *  Function<T,R> 函数式接口
     * @param num
     * @param fun
     * @return
     */
    private static Long changeNum(Long num, Function<Long, Long> fun){
        return fun.apply(num);
    }

    /**
     * 消费型接口，有参无返回值
     * @param str
     * @param con
     */
    private static void changeStr(String str, Consumer<String> con){
        con.accept(str);
    }

    private static void println(String str)
    {
        System.out.println(str);
    }
}
