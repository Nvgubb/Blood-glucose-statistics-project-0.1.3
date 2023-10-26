package com.liangzubiao.sugerblood.uitl;

/**
 * @Description: 验证工具，验证数据是否合法
 * @Author: Nvgu
 * @CreateDate: 2023/10/25
 * @ModifiedDate: 2023/10/25
 */
public class ValidationUtil {
    static public boolean checkInsertValue(Float data) {
        String pattern = "^\\d+\\.\\d{1,2}$";
        String str = Float.toString(Math.abs(data));
        boolean loop = false;
        if (str.matches(pattern)) {
            if (data >= 3.9f && data <= 7.8f) {
                loop = true;
            } else {
                System.out.println("数字范围有误");
            }
        } else {
            System.out.println("数字结构有误");
        }
        return loop;
    }

}
