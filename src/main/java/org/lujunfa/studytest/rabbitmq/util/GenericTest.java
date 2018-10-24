package org.lujunfa.studytest.rabbitmq.util;

/**
 * @Author:lujunfa
 * @Desccription   熟悉泛型类方法和泛型方法的区别，以及通配符的作用
 * @Date:created in 9:06 2018/10/24
 **/
class Store<T,K>{
    T t;K K;
    public Store(T t,K k){
        this.t = t;
        this.K = k;
    }

    //泛型类方法
    public  T getT(){
        return t;
    }


}
public class GenericTest {


    /*//泛型方法
    public static <S> List<S> getList(S s){
        List<S> list= new ArrayList<>();
        list.add(s);
        return list;
    }

    public static  <E> void setList(E e, List<E> es){
        es.add(e);
    }

    public static <S,Z> Store<S,Z> getSzStore(S s,Z z){
        return new Store<>(s,z);
    }

    public static <S> void s(){
        System.out.println("dadq");
    }

    //通配符测试
    public static void print(Store<?,? extends Collection> store){
        System.out.println("通配符打印:"+store.getT());
    }

    public static void main(String[] args) {
        GenericTest genericTest = new GenericTest();
        Store<String,Integer> store  = new Store<>("图书店",1);
        System.out.println(store.toString());
        System.out.println(store.getT());

        List<Integer> list = getList(6);
        System.out.println(list.get(0));

        List<String> list2 = getList("ssdd");
        System.out.println(list2.get(0));

        List<Double> d = new ArrayList<>();
        setList(2.3d,d);
        System.out.println(d.get(0));

        System.out.println(getSzStore("lujunfa",12));
        s();

        print(new Store<>("asda",new ArrayList()));
    }*/

}
