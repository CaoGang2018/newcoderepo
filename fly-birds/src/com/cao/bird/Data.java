package com.cao.bird;

import java.net.URL;

/**
 * @author admin_cg
 * @data 2020/9/21 20:53
 */
public class Data {
    public static URL getIthBody(int i){
        return Data.class.getResource("/statics/" + i + ".png");
    }
    public static URL bgUrl = Data.class.getResource("/statics/bg.png");
    public static URL columnUrl = Data.class.getResource("/statics/column.png");
    public static URL gameOverUrl = Data.class.getResource("/statics/gameover.png");
    public static URL groundUrl = Data.class.getResource("/statics/ground.png");
    public static URL startUrl = Data.class.getResource("/statics/start.png");
}
