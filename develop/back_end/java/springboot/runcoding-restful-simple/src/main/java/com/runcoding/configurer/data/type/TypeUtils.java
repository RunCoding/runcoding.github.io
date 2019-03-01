package com.runcoding.configurer.data.type;

import com.runcoding.model.po.account.AccountPo;

/**
 * @author xukai
 * @date 2019-03-01
 * @desc:
 */
public class TypeUtils {

    public static Class<?>[] value = {AccountPo.class};

    public static Class<?>[] getValue() {
        return value;
    }
}
