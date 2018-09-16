package com.qsh.shopping.util.ordercode;
/**
 * 自动生成有规则的订单号(或编号)
 * 生成的格式是: 2012092600001 前面几位为当前的日期,后面五位为系统自增长类型的编号
 * 原理: 
 *      1.获取当前日期格式化值;
 *      2.读取文件,上次编号的值+1最为当前此次编号的值
 *      (新的一天会重新从1开始编号)
 */
public abstract class QshSerialNumber {

    public synchronized String getSerialNumber() {
        return process();
    }
    protected abstract String process();
}

