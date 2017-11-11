package com.ToolBox.UI;

import java.net.URL;

/**
 * 用类加载器加载资源文件
 *
 * @author 杨弘
 */
public class FileResource {
    final URL aboutURL = getClass().getResource("/com/ToolBox/resource/About.jpg");
    final URL backgroundURL = getClass().getResource("/com/ToolBox/resource/Background.jpg");
    final URL toolboxURL = getClass().getResource("/com/ToolBox/resource/Toolbox.png");
    final URL splashURL = getClass().getResource("/com/ToolBox/resource/Splash.jpg");
    public final URL exchangeRateURL = getClass().getResource("/com/ToolBox/resource/exchangeRate.json");
}