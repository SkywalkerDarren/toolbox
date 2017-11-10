package com.ToolBox.UI;

import java.net.URL;

/**
 * 用类加载器加载资源文件
 *
 * @author 杨弘
 */
class Resource {
    final URL aboutURL = getClass().getResource("/com/ToolBox/UIResource/About.jpg");
    final URL backgroundURL = getClass().getResource("/com/ToolBox/UIResource/Background.jpg");
    final URL toolboxURL = getClass().getResource("/com/ToolBox/UIResource/Toolbox.png");
    final URL splashURL = getClass().getResource("/com/ToolBox/UIResource/Splash.jpg");
}
