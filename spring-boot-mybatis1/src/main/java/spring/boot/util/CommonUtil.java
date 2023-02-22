package spring.boot.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    public static String get30UUID(){
        // 6.开头两位，标识业务代码或机器代码（可变参数）
        String machineId = "cd";
        // 2.中间4位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        // 13位
        String l = String.valueOf(System.currentTimeMillis());
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if(hashCode < 0){
            hashCode = -hashCode;
        }
        //
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + l + String.format("%011d", hashCode);
        return value;
    }


}
