# Universial ToolBox开发文档

> 组长：杨弘
>
> 组员：徐祥亮，朱可欣

# 目录

* [1 概述](#1-概述)
    * [1.1 开发背景](#11-开发背景)
    * [1.2 开发目标](#12-开发目标)
    * [1.3 参考资料](#13-参考资料)
    * [1.4 设计原则](#14-设计原则)
* [2 需求分析](#2-需求分析)
    * [2.1 需求陈述](#21-需求陈述)
    * [2.2 功能分析划分](#22-功能分析划分)
    * [2.3 运行环境](#23-运行环境)
* [3 总体设计](#3-总体设计)
* [4 详细设计](#4-详细设计)
* [5 实现](#5-实现)
* [6 维护](#6-维护)


## 1 概述

### 1.1 开发背景

为了提高计算机计算的相关人员，需要管理网站编码的站长以及经常依赖高精度计算的金融相关人员的计算方便而设计了一套计算及编码工具，还囊括了各种常用的小工具来提高系统的易用性，成为方便用户的日常使用的万能工具箱。

### 1.2 开发目标

该工具箱要求的工具有如下几个：

1. 计算换算工具
    1. 科学计算器
    1. 程序员计算器
    1. 汇率计算器
    1. 单位换算器
    1. 日期计算器
1. 编码解码工具
    1. Base64 编码解码
    1. Unicode 中文互相转换
    1. MD5 校验生成工具
    1. 文字/文件编码批量转换工具
1. 二维码生成工具
1. 截图工具

### 1.3 参考资料

工具箱所用到的package有

* *Big-math* 用于大数的高精度计算
* *zxing* 二维码生成及校验的工具包
* *gson* 用于json数据的解析
* *joda-time* 代替Date类的高精度日期工具

### 1.4 设计原则

要求跨平台可用，数据持久化，易于拓展新的工具功能。

## 2 需求分析

### 2.1 需求陈述

1. 科学计算器
    * 有四则运算，乘方运算，10为底的对数，阶乘和三角函数运算
    * 三角函数为弧度制
    * 具有优先级
    * 实现回退，清空，清除操作
    * 结果可复制
    * 监听键盘事件
1. 程序员计算器
    * 二进制，八进制，十进制，十六进制转换
    * 64位长整形运算
    * 全键盘支持四则运算，位运算
    * 实现回退，清空，清除操作
    * 结果可复制
    * 比特键盘可直接操纵二进制数据
1. 汇率计算器
    * 初始内置一套汇率数据
    * 若联网则可以更新最新汇率，并持久化保存数据
    * 显示当前汇率及更新日期
1. 单位换算器
    * 支持长度，面积，体积，速度，质量，温度，角度的单位换算
    * 支持大部分常用的市制，公制，英制，美制的标度换算
1. 日期计算器
    * 具有日期间隔计算，计算结果为周期和相差总天数
    * 具有日期增减计算，结果为某日期增减后所得的日期

除了日期计算器外，其他计算器均支持高精度计算，以及历史记录功能。历史记录功能需要记录计算器输出的结果，并可以在各个计算器之间互相调用（除日期计算器外），程序员计算器的历史记录要求只保存十进制数据，而且调用时将先转换为长整型十进制再使用。

1. Base64 编码解码，Unicode 中文互转
    * 要求能对数据进行编码及解码
    * 可以进行文本的复制及粘贴
1. MD5 校验生成工具
    * 要求可以对文件进行校验
    * 要求可以对字符串文本进行消息摘要
    * 生成为32位大写消息摘要
1. 文字/文件编码批量转换工具
    * 支持`utf-8`,`BIG5`,`GBK`,`GB2312`,`JIS`,`SJIS`的编码互相转换
    * 可以对目录下所有文本文件的编码进行转换
    * 可以对乱码字符串进行转换
1. 二维码生成工具
    * 支持纠错等级的选择
    * 支持图片大小的选择
    * 支持生成的二维码保存
    * 图片输出格式为`jpg`
    * 支持电脑上二维码的截图或文件识别
    * 二维码编码格式为`utf-8`
1. 截图工具
    * 截图时隐藏窗口
    * 右键退出截图
    * 支持对截图的简单绘画
    * 支持矩形，椭圆，钢笔，画笔粗细调节，颜色选择
    * 支持图片保存与复制
    * 图片输出格式为`png`

### 2.2 功能分析划分

总体分为前端和后端。

前端为各个UI的界面框架编写及背景美工。

* 启动画面
* 主界面
* 关于窗口
* 计算换算工具
    * 科学计算器
    * 程序员计算器
    * 汇率计算器
    * 单位换算器
    * 日期计算器
* 编码解码工具
    * Base64 编码解码
    * Unicode 中文互相转换
    * MD5 校验生成工具
    * 文字/文件编码批量转换工具
* 二维码生成工具
* 截图工具

后端划分为计算器包，汇率包，换算包，日期包，截图包，编码包,历史记录包，二维码包。

难点在于计算器包，截图包的实现。

要求输出的结果正确以及高效。

* 计算器包实现计算器的优先级逻辑，运算逻辑，表达式解析
* 汇率包实现最新汇率的抓取，汇率的持久化保存，json数据解析调用，货币保存
* 换算包实现各个单位对国际单位制的换算比例（除温度，角度），温度，角度则用公式进行换算
* 日期包实现日期计算，周期计算，日期获取
* 截图包实现截图的基本功能，图像控制
* 编码包实现对编码的解码和转换
* 历史记录包实现对结果的记录和使用
* 二维码包实现对二维码的编码解码

### 2.3 运行环境

要求有Java1.8的运行环境。

支持*nix，Windows，Mac。

## 3 总体设计

### 3.1 系统建模

### 3.1.3 类图设计

【使用UML画出各个类的属性、继承和方法】

### 3.2 接口设计

【各个子系统之间的接口和用户接口】

#### 3.2.1 内部接口设计

【各个部件是通过何种方式进行连接，比如通过远程数据库，http等】

#### 3.2.3 界面设计

## 4 详细设计

### 4.1 程序流程图

【具体来说就是把经过总体设计得到的各个模块详细的加以描述。】

### 4.2 伪代码编写

【使用中文或者英文进行伪代码编写，以后这些伪代码将会成为代码的注释】

## 5 实现

### 5.1 编码

#### 5.1.1 代码约定

#### 5.1.2 代码编写原则

### 5.2 测试要点

### 5.3 测试结果和总结

## 6 维护

### 6.1 维护方法

### 6.2 维护文档

### 6.3 功能拓展方法
