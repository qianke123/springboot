package function;

public class Test {

    @org.junit.Test
    public void test() {
        String t = "D:\\intellij_workspace\\finaldesign\\src\\main\\static\\file\\zhangsan\\程序员\\hello.txt";
        String s = t.split("zhangsan\\\\程序员")[1];
        System.out.println(s);
    }
}
