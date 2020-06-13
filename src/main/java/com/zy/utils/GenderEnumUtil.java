package com.zy.utils;

import com.zy.enums.GenderEnum;

public class GenderEnumUtil {

    public static boolean isInclude(String msg){
        boolean include = false;
        for (GenderEnum value : GenderEnum.values()) {
            if (value.getMsg()==msg){
                include = true;
                break;
            }
        }
        return include;
    }

}
