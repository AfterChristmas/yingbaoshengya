package com.yingbao.career.utils;

/**
 * @Description:打印的回调
 * @Date: 2019/10/9 10:05
 * @Auther: wanyan
 */
public interface OnPrinterListener {
    void onPrintStart();
    void onPrintSuccess();
    void onPrintError(String errorMsg);
}
