package com.ToolBox.UI;

import java.io.InputStream;
import java.net.URL;

/**
 * 用类加载器加载资源文件
 *
 * @author 杨弘
 */
public class FileResource {
    /**
     * 关于图片
     */
    final URL aboutURL = getClass().getResource("/com/ToolBox/resource/About.jpg");
    /**
     * 背景图片
     */
    final URL backgroundURL = getClass().getResource("/com/ToolBox/resource/Background.jpg");
    /**
     * 图标
     */
    final URL toolboxURL = getClass().getResource("/com/ToolBox/resource/Toolbox.png");
    /**
     * 启动画面
     */
    final URL splashURL = getClass().getResource("/com/ToolBox/resource/Splash.jpg");
    /**
     * 初始汇率资源
     */
    public final InputStream exchangeRateIS = getClass().getResourceAsStream("/com/ToolBox/resource/exchangeRate.json");
}