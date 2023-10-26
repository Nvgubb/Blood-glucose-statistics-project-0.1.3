package com.liangzubiao.sugerblood.visualization;

import java.util.List;

/** 
* @Description: 对BloodSugerRecordShow类的数据进行可视化操作
* @Author: Nvgu
* @CreateDate: 2023/10/26
* @ModifiedDate: 2023/10/26
*/
public class BloodSugerRecordShow implements ShowList , ShowValue {
    @Override
    public void printList(List list) {
        list.forEach(System.out :: println);
    }

    @Override
    public void printValue(String name , Object data) {
        System.out.println(name + ":" + data + "mmol/L");
    }
}
