package com.lbl.concurrent.activeobject.core;

import com.lbl.concurrent.activeobject.result.Result;

/**
 * 接受异步消息的主动对象
 */
public interface ActiveObject {

    /**
     * 创建count个字符c
     * @param count
     * @param c
     * @return
     */
    Result makeString(int count, char c);

    /**
     * 显示字符串
     * @param text
     */
    void displayString(String text);
}
