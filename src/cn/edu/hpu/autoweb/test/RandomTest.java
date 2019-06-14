package cn.edu.hpu.autoweb.test;

import java.util.Random;

/**
 * Created by Aries on 2017/5/26.
 */
public class RandomTest {

    public static void main(String[] ogj){
        String code = "";
        for(int i=0;i<=10;i++){
            Random random = new Random();
            int a=random.nextInt(10);
            code += a;
        }
        System.out.print(code);
    }
}
