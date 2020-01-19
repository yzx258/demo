package com.ymc.demo.jdk;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yucw
 * @date 2020/1/16 14:33
 */
public class StreamApiOptionalTest {

    public static void main(String[] args) {
        List<Employee> e = new ArrayList<Employee>();

        Employee e1 = new Employee("小明","1");
        Employee e2 = new Employee("章节","1");
        Employee e3 = new Employee("章ssss","2");
        e.add(e1);
        e.add(e2);
        e.add(e3);

        //Optional<Employee> el = e.stream().filter(es -> es.getSex().equals("1")).findFirst();
        //System.out.println(el);

        Optional<Employee> els = e.stream().filter(es -> es.getSex().equals("1")).sorted(Comparator.comparing(Employee::getName).reversed()).findFirst();
        System.out.println(els);
    }

    /**
     *      Optional.of(T t); // 创建一个Optional实例
     *      Optional.empty(); // 创建一个空的Optional实例
     *      Optional.ofNullable(T t); // 若T不为null，创建一个Optional实例，否则创建一个空实例
     *      isPresent();    // 判断是够包含值
     *      orElse(T t);   //如果调用对象包含值，返回该值，否则返回T
     *      orElseGet(Supplier s);  // 如果调用对象包含值，返回该值，否则返回s中获取的值
     *      map(Function f): // 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty();
     *      flatMap(Function mapper);// 与map类似。返回值是Optional
     *
     *      总结：Optional.of(null)  会直接报NPE
     */

    @Test
    public void test0()
    {
        List<Employee> e = new ArrayList<>();
        Employee e1 = new Employee("小明","1");
        Employee e2 = new Employee("A章节","1");
        Employee e3 = new Employee("章ssss","2");
        e.add(e1);
        e.add(e2);
        e.add(e3);

        /**
         * groupingBy属于排序
         */
        Map<String, Map<String, List<Employee>>> collect = e.stream().collect(Collectors.groupingBy(Employee::getSex, Collectors.groupingBy(Employee::getName)));
        System.out.println(collect);
    }

    @Test
    public void test1(){

        /**
         * 三元表达式
         */
        System.out.println("1".equals("3") ? "5" : "2");

        List<String> userIdList = new ArrayList<>();
        userIdList.add("1");
        userIdList.add("2");
        userIdList.add("3");

        String[] strings = userIdList.toArray(new String[0]);
        System.out.println(strings.length);

        /**
         * 去重数据
         */
        List<String> list = Arrays.asList("AA", "BB", "CC", "BB", "CC", "AA", "AA");
        long l = list.stream().distinct().count();
        System.out.println("No. of distinct elements:"+l);
        List<String> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);

    }



    @Test
    public void test2(){
        Optional<Object> op = Optional.of(new Employee("田七", "4"));
        System.out.println(op.isPresent());

        // No value present
        //System.out.println(op.get());
    }
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee("lisi", "111"));
        System.out.println(op.get());

        Optional<Object> op2 = Optional.ofNullable(null);
        System.out.println(op2);

    }
    @Test
    public void test5(){
        Optional<Employee> op1 = Optional.ofNullable(new Employee("张三", "5"));
        System.out.println(op1.orElse(new Employee("kkk","1")));
        System.out.println(op1.orElse(null));
    }

    @Test
    public void test6(){
        Optional<Employee> op1 = Optional.of(new Employee("田七", "4"));
        op1 = Optional.empty();
        Employee employee = op1.orElseGet(() -> new Employee());
        System.out.println(employee);
    }

    @Test
    public void test7(){
        Optional<Employee> op1 = Optional.of(new Employee("田七","1"));
        System.out.println(op1.map( (e) -> e.getName()).get());
    }

}
