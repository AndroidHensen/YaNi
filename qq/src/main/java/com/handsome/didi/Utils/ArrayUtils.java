package com.handsome.didi.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 许英俊 2017/7/22
 */
public class ArrayUtils {

    public static String[] toArray(List<String> list) {
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i);
        }
        return strings;
    }
}
