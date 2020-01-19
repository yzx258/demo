package com.ymc.demo.jdk;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yucw
 * @date 2020/1/16 11:19
 */
public class StreamApiTest {

    public static void main(String[] args) {
        // 1，校验通过Collection 系列集合提供的stream()或者paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2.通过Arrays的静态方法stream()获取数组流
        String[] str = new String[10];
        str[0] = "0";
        str[1] = "1";
        Stream<String> stream2 = Arrays.stream(str);

        // 3.通过Stream类中的静态方法of
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);

        //生成
        System.out.println(Stream.generate(() ->Math.random()));

        List<Employee> e = new ArrayList<>();
        Employee e1 = new Employee("小明","1");
        Employee e2 = new Employee("章节","1");
        Employee e3 = new Employee("章ssss","2");
        e.add(e1);
        e.add(e2);
        e.add(e3);
        /**
         *  生成新的流 通过map映射
         */
        e.stream().map((es) -> es.getName()).forEach((es) -> System.out.println(es));
        /**
         *      查找和匹配
         *          allMatch-检查是否匹配所有元素
         *          anyMatch-检查是否至少匹配一个元素
         *          noneMatch-检查是否没有匹配所有元素
         *          findFirst-返回第一个元素
         *          findAny-返回当前流中的任意元素
         *          count-返回流中元素的总个数
         *          max-返回流中最大值
         *          min-返回流中最小值
         */

        /**
         *  检查是否匹配元素
         */

        /**
         * 将数据重新返回
         */
        List<Employee> data = e.stream().map(es -> {
            Employee e1q = new Employee();
            e1q.setName(es.getName());
            e1q.setSex(es.getSex());
            return e1q;
        }).collect(Collectors.toList());
        System.out.println("========================");
        System.out.println(data);
        System.out.println("========================");

        /**
         * 将数据转换成Map
         */
        Map<String, String> collect = e.stream().filter(ess -> ess.getSex().equals("1")).collect(Collectors.toMap(Employee::getName, t -> t.getName()));
        System.out.println("性别符合为1的数据："+collect);

        /**
         * 将数据集合过滤
         */
        e.stream().filter(esl -> esl.getSex().equals("1")).forEach(es -> {
            System.out.println("名字："+es.getName());
        });


        /**
         * 排序
         */
        Optional<Employee> ss = e.stream().filter(es -> es.getName().equals("小明")).sorted(Comparator.comparing(Employee::getName).reversed()).findFirst();

        System.out.println(ss);

        boolean b1 = e.stream().filter(es -> es.getName().equals("章节"))
                .anyMatch((es) -> es.getName().equals("章节"));
        System.out.println(b1);

        boolean b3 = e.stream()
                .noneMatch((es) -> es.getName().equals("章ss"));
        System.out.println(b3);

        Optional<Employee> opt = e.stream()
                .findFirst();
        System.out.println(opt.get());

        Optional<Employee> opts = e.stream()
                .findAny();
        System.out.println(opts.get());


        /**
         *  reduce ：规约操作
         */
        List<Integer> lists = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer count2 = lists.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println("count2:" + count2);


        /**
         *  collect：收集操作
         */

        List<String> ageList = e.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        ageList.stream().forEach(System.out::println);


    }


    @Test
    public void test6()
    {
        /**
         *  reduce ：规约操作
         */
        List<Integer> lists = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer count2 = lists.stream()
                .reduce(Integer::sum).orElse(0).intValue();
        System.out.println("count2:" + count2);
    }
}
