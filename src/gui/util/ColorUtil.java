package gui.util;

import java.awt.*;

/**
 * <p>
 * 功能描述:   接口
 * </p>
 *
 * @author wxf
 * @date 2022/7/20
 */
public class ColorUtil {

    /**
     * blueColor 淡蓝色
     * grayColor 灰色
     * backgroundColor 背景色
     * warningColor 警告红色
     */
    public static Color blueColor = Color.decode("#3399FF");
    public static Color grayColor = Color.decode("#999999");
    public static Color backgroundColor = Color.decode("#eeeeee");
    public static Color warningColor = Color.decode("#FF3333");

    /**
     * getByPercentage根据进度显示不同的颜色
     * 如图所示，当进度是接近0的时候，就会显示绿色
     * 当进度接近100的时候，就会显示红色
     * 并在不同的颜色间过渡
     * @param per
     * @return
     * 蓝色不变, 当红色变深, 绿色就变浅, 绿色变深, 红色就变浅,
     * 红色的公式是:255 * rate + 51*(1 -  rate)
     * 绿色的公式是:255 * (1 - rate) + 51 * rate
     * rate越大,红色越多,绿色越少
     * rate越小,红色越小,绿色越多
     * 有一些此消彼长的意思
     * 两个公式化简一下就是站长给的公式了
     */
    public static Color getByPercentage(int per) {
        if (per > 100)
            per = 100;
        int r = 51;
        int g = 255;
        int b = 51;
        float rate = per / 100f;
        r = (int) ((255 - 51) * rate + 51);
        g = 255 - r + 51;
        return new Color(r, g, b);
    }

}
