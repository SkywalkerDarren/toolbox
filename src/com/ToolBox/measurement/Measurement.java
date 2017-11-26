package com.ToolBox.measurement;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 测量单位
 * 设定默认相对国际单位的换算
 * 增加温度，角度的换算函数
 *
 * @author 杨弘，徐祥亮
 */
public class Measurement {
    /**
     * 测量类型
     */
    public final String[] measureType = {"长度", "面积", "体积",
            "速度", "质量", "温度", "角度"};
    /**
     * 长度单位
     */
    public final HashMap<String, BigDecimal> length = new HashMap<>();
    /**
     * 面积单位
     */
    public final HashMap<String, BigDecimal> area = new HashMap<>();
    /**
     * 体积单位
     */
    public final HashMap<String, BigDecimal> volume = new HashMap<>();
    /**
     * 速度单位
     */
    public final HashMap<String, BigDecimal> speed = new HashMap<>();
    /**
     * 质量单位
     */
    public final HashMap<String, BigDecimal> mass = new HashMap<>();
    /**
     * 温度单位
     */
    public final HashMap<String, String> temp = new HashMap<>();
    /**
     * 角度单位
     */
    public final HashMap<String, String> angle = new HashMap<>();
    /**
     * 存储各个单位（除温度，角度）的哈希表
     */
    final HashMap<String, HashMap<String, BigDecimal>> hashMapType = new HashMap<>();

    /**
     * 初始化各单位到国际单位的换算值，除温度，角度
     */
    public Measurement() {
        hashMapType.put("长度", length);
        hashMapType.put("面积", area);
        hashMapType.put("体积", volume);
        hashMapType.put("速度", speed);
        hashMapType.put("质量", mass);

        length.put("千米(km)", BigDecimal.valueOf(1000));
        length.put("米(m)", BigDecimal.valueOf(1));
        length.put("分米(dm)", BigDecimal.valueOf(0.1));
        length.put("厘米(cm)", BigDecimal.valueOf(0.01));
        length.put("毫米(mm)", BigDecimal.valueOf(0.001));
        length.put("微米(μm)", BigDecimal.valueOf(0.000001));
        length.put("纳米(nm)", BigDecimal.valueOf(0.000000001));
        length.put("英寸(in)", BigDecimal.valueOf(0.0254));
        length.put("英尺(ft)", BigDecimal.valueOf(0.3048));
        length.put("码(yd)", BigDecimal.valueOf(0.9144));
        length.put("英里(mi)", BigDecimal.valueOf(1609.344));
        length.put("海里(nmi)", BigDecimal.valueOf(1853.249));
        length.put("里", BigDecimal.valueOf(500));
        length.put("丈", BigDecimal.valueOf(3.333333333));
        length.put("尺", BigDecimal.valueOf(0.333333333));
        length.put("寸", BigDecimal.valueOf(0.033333333));
        length.put("分", BigDecimal.valueOf(0.003333333));
        length.put("厘", BigDecimal.valueOf(0.000333333));
        length.put("毫", BigDecimal.valueOf(0.000033333));

        area.put("平方千米(km^2)", BigDecimal.valueOf(1000000));
        area.put("公顷(ha)", BigDecimal.valueOf(10000));
        area.put("公亩(are)", BigDecimal.valueOf(100));
        area.put("平方米(m^2)", BigDecimal.valueOf(1));
        area.put("平方分米(dm^2)", BigDecimal.valueOf(0.01));
        area.put("平方厘米(cm^2)", BigDecimal.valueOf(0.0001));
        area.put("平方毫米(mm^2)", BigDecimal.valueOf(0.000001));
        area.put("平方英里(sq.mi)", BigDecimal.valueOf(2589988));
        area.put("英亩(acre)", BigDecimal.valueOf(4046.864798));
        area.put("平方竿(sq.rd)", BigDecimal.valueOf(25.29285264));
        area.put("平方码(sq.yd)", BigDecimal.valueOf(0.83612736));
        area.put("平方英尺(sq.ft)", BigDecimal.valueOf(0.09290304));
        area.put("平方英寸(sq.in)", BigDecimal.valueOf(0.00064516));
        area.put("顷", BigDecimal.valueOf(66666.666666666));
        area.put("亩", BigDecimal.valueOf(666.666666666));
        area.put("分", BigDecimal.valueOf(66.666666666));

        volume.put("立方米(m^3)", BigDecimal.valueOf(1));
        volume.put("立方厘米(cm^3)", BigDecimal.valueOf(0.000001));
        volume.put("立方分米(dm^3)", BigDecimal.valueOf(0.001));
        volume.put("立方毫米(mm^3)", BigDecimal.valueOf(0.000000001));
        volume.put("升(l)", BigDecimal.valueOf(0.001));
        volume.put("分升(dl)", BigDecimal.valueOf(0.0001));
        volume.put("厘升(cl)", BigDecimal.valueOf(0.00001));
        volume.put("毫升(ml)", BigDecimal.valueOf(0.000001));
        volume.put("微升(ul)", BigDecimal.valueOf(0.000000001));
        volume.put("公石(hl)", BigDecimal.valueOf(0.1));
        volume.put("立方码(cu yd)", BigDecimal.valueOf(0.764555));
        volume.put("立方英尺(cu ft)", BigDecimal.valueOf(0.028316846592));
        volume.put("立方英寸(cu in)", BigDecimal.valueOf(0.00001638706));
        volume.put("品脱(pint)", BigDecimal.valueOf(0.000568));
        volume.put("夸脱(quart)", BigDecimal.valueOf(0.00114));
        volume.put("英制加仑(uk gal)", BigDecimal.valueOf(0.00454609));
        volume.put("美制加仑(us gal)", BigDecimal.valueOf(0.003785412));
        volume.put("英制液体盎司(oz)", BigDecimal.valueOf(0.00002841));
        volume.put("美制液体盎司(oz)", BigDecimal.valueOf(0.00002957));

        speed.put("米/秒(m/s)", BigDecimal.valueOf(1));
        speed.put("千米/秒(km/s)", BigDecimal.valueOf(1000));
        speed.put("千米/时(km/h)", BigDecimal.valueOf(0.27777778));
        speed.put("英寸/秒(in/s)", BigDecimal.valueOf(0.0254));
        speed.put("英里/时(mile/h)", BigDecimal.valueOf(0.44704));
        speed.put("节", BigDecimal.valueOf(0.51444445));
        speed.put("马赫(mach)", BigDecimal.valueOf(340.29));

        mass.put("吨(t)", BigDecimal.valueOf(1000));
        mass.put("千克(kg)", BigDecimal.valueOf(1));
        mass.put("克(g)", BigDecimal.valueOf(0.001));
        mass.put("毫克(mg)", BigDecimal.valueOf(0.000001));
        mass.put("微克(μg)", BigDecimal.valueOf(0.000000001));
        mass.put("克拉(ct)", BigDecimal.valueOf(0.0002));
        mass.put("英吨(lt)", BigDecimal.valueOf(1016));
        mass.put("美吨(st)", BigDecimal.valueOf(907));
        mass.put("英担(hundredweight)", BigDecimal.valueOf(50.8));
        mass.put("夸特(quarter)", BigDecimal.valueOf(12.7));
        mass.put("英石(stone)", BigDecimal.valueOf(6.35));
        mass.put("磅(lb)", BigDecimal.valueOf(0.454));
        mass.put("盎司(oz)", BigDecimal.valueOf(0.0283));
        mass.put("打兰(dr)", BigDecimal.valueOf(0.00177));
        mass.put("格令(gr)", BigDecimal.valueOf(0.0000648));
        mass.put("担", BigDecimal.valueOf(50));
        mass.put("斤", BigDecimal.valueOf(0.5));
        mass.put("两", BigDecimal.valueOf(0.03125));
        mass.put("钱", BigDecimal.valueOf(0.003125));

        temp.put("摄氏度(°C)", "C");
        temp.put("华氏度(°F)", "F");
        temp.put("开氏度(K)", "K");
        temp.put("兰氏度(°R)", "R");
        temp.put("列氏度(°Re)", "Re");

        angle.put("百分度(grad)", "grad");
        angle.put("度(°)", "°");
        angle.put("弧度(rad)", "rad");
    }

    /**
     * 指示一个类型，给定一定数额，并从源单位到目标单位的换算，以及返回结果
     *
     * @param source 源单位
     * @param target 目标单位
     * @param number 换算数值
     * @param type   换算类型
     * @return 换算结果
     */
    public BigDecimal conver(String source, String target, BigDecimal number, String type) {
        BigDecimal s;
        BigDecimal t;
        switch (type) {
            case "长度":
            case "面积":
            case "体积":
            case "速度":
            case "质量":
                s = hashMapType.get(type).get(source);
                t = hashMapType.get(type).get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "温度":
                String st = temp.get(source);
                String tt = temp.get(target);

                BigDecimal rt;
                switch (st) {
                    case "C":
                        rt = CtoC(number);
                        break;
                    case "K":
                        rt = KtoC(number);
                        break;
                    case "F":
                        rt = FtoC(number);
                        break;
                    case "R":
                        rt = RtoC(number);
                        break;
                    case "Re":
                        rt = RetoC(number);
                        break;
                    default:
                        throw new IllegalArgumentException("单位不存在");
                }
                switch (tt) {
                    case "C":
                        rt = CtoC(rt);
                        break;
                    case "K":
                        rt = CtoK(rt);
                        break;
                    case "F":
                        rt = CtoF(rt);
                        break;
                    case "R":
                        rt = CtoR(rt);
                        break;
                    case "Re":
                        rt = CtoRe(rt);
                        break;
                    default:
                        throw new IllegalArgumentException("单位不存在");
                }
                return rt.setScale(16, RoundingMode.HALF_UP);
            case "角度":
                String sa = angle.get(source);
                String ta = angle.get(target);

                BigDecimal ra;
                switch (sa) {
                    case "°":
                        ra = degToDeg(number);
                        break;
                    case "grad":
                        ra = gradToDeg(number);
                        break;
                    case "rad":
                        ra = radToDeg(number);
                        break;
                    default:
                        throw new IllegalArgumentException("单位不存在");
                }
                switch (ta) {
                    case "°":
                        ra = degToDeg(ra);
                        break;
                    case "grad":
                        ra = degToGrad(ra);
                        break;
                    case "rad":
                        ra = degToRad(ra);
                        break;
                    default:
                        throw new IllegalArgumentException("单位不存在");
                }
                return ra.setScale(16, RoundingMode.HALF_UP);
            default:
                throw new IllegalArgumentException("未实现");
        }

    }

    //温度换算
    private BigDecimal KtoC(BigDecimal s) {
        return s.subtract(BigDecimal.valueOf(273.15));
    }

    private BigDecimal FtoC(BigDecimal s) {
        return s.subtract(BigDecimal.valueOf(32)).multiply(BigDecimal.valueOf(5).divide(BigDecimal.valueOf(9), MathContext.DECIMAL128));
    }

    private BigDecimal RtoC(BigDecimal s) {
        return s.subtract(BigDecimal.valueOf(491.67)).multiply(BigDecimal.valueOf(5).divide(BigDecimal.valueOf(9), MathContext.DECIMAL128));
    }

    private BigDecimal RetoC(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(5).divide(BigDecimal.valueOf(4), MathContext.DECIMAL128));
    }

    private BigDecimal CtoC(BigDecimal s) {
        return s;
    }

    private BigDecimal CtoK(BigDecimal s) {
        return s.add(BigDecimal.valueOf(273.15));
    }

    private BigDecimal CtoF(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(9).divide(BigDecimal.valueOf(5), MathContext.DECIMAL128)).add(BigDecimal.valueOf(32));
    }

    private BigDecimal CtoR(BigDecimal s) {
        return s.add(BigDecimal.valueOf(273.15)).multiply(BigDecimal.valueOf(9).divide(BigDecimal.valueOf(5), MathContext.DECIMAL128));
    }

    private BigDecimal CtoRe(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(4).divide(BigDecimal.valueOf(5), MathContext.DECIMAL128));
    }

    //角度换算
    private BigDecimal degToDeg(BigDecimal s) {
        return s;
    }

    private BigDecimal degToRad(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(Math.PI).divide(BigDecimal.valueOf(180), MathContext.DECIMAL128));
    }

    private BigDecimal degToGrad(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(9), MathContext.DECIMAL128));
    }

    private BigDecimal radToDeg(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(180).divide(BigDecimal.valueOf(Math.PI), MathContext.DECIMAL128));
    }

    private BigDecimal gradToDeg(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(9)).divide(BigDecimal.valueOf(10), MathContext.DECIMAL128);
    }
}