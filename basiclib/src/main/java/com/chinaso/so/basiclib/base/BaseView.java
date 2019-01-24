package com.chinaso.so.basiclib.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @author yanxu
 * @date:2019/1/23
 * @description:
 */

public interface BaseView {
    LifecycleTransformer bindLifecycle();
}
