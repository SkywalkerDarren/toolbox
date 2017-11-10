package com.ToolBox.measurement;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * ������λ
 * �趨Ĭ����Թ��ʵ�λ�Ļ���
 * �����¶ȣ��ǶȵĻ��㺯��
 *
 * @author ��룬������
 */
public class Measurement {
    public final String[] measureType = {"����", "���", "���",
            "�ٶ�", "����", "�¶�", "�Ƕ�"};

    public final HashMap<String, BigDecimal> length = new HashMap<>();
    public final HashMap<String, BigDecimal> area = new HashMap<>();
    public final HashMap<String, BigDecimal> volume = new HashMap<>();
    public final HashMap<String, BigDecimal> speed = new HashMap<>();
    public final HashMap<String, BigDecimal> mass = new HashMap<>();
    public final HashMap<String, String> temp = new HashMap<>();
    public final HashMap<String, String> angle = new HashMap<>();

    /**
     * ��ʼ������λ�����ʵ�λ�Ļ���ֵ�����¶ȣ��Ƕ�
     */
    public Measurement() {
        length.put("ǧ��(km)", BigDecimal.valueOf(1000));
        length.put("��(m)", BigDecimal.valueOf(1));
        length.put("����(dm)", BigDecimal.valueOf(0.1));
        length.put("����(cm)", BigDecimal.valueOf(0.01));
        length.put("����(mm)", BigDecimal.valueOf(0.001));
        length.put("΢��(��m)", BigDecimal.valueOf(0.000001));
        length.put("����(nm)", BigDecimal.valueOf(0.000000001));
        length.put("Ӣ��(in)", BigDecimal.valueOf(0.0254));
        length.put("Ӣ��(ft)", BigDecimal.valueOf(0.3048));
        length.put("��(yd)", BigDecimal.valueOf(0.9144));
        length.put("Ӣ��(mi)", BigDecimal.valueOf(1609.344));
        length.put("����(nmi)", BigDecimal.valueOf(1853.249));
        length.put("��", BigDecimal.valueOf(500));
        length.put("��", BigDecimal.valueOf(3.333333333));
        length.put("��", BigDecimal.valueOf(0.333333333));
        length.put("��", BigDecimal.valueOf(0.033333333));
        length.put("��", BigDecimal.valueOf(0.003333333));
        length.put("��", BigDecimal.valueOf(0.000333333));
        length.put("��", BigDecimal.valueOf(0.000033333));

        area.put("ƽ��ǧ��(km^2)", BigDecimal.valueOf(1000000));
        area.put("����(ha)", BigDecimal.valueOf(10000));
        area.put("��Ķ(are)", BigDecimal.valueOf(100));
        area.put("ƽ����(m^2)", BigDecimal.valueOf(1));
        area.put("ƽ������(dm^2)", BigDecimal.valueOf(0.01));
        area.put("ƽ������(cm^2)", BigDecimal.valueOf(0.0001));
        area.put("ƽ������(mm^2)", BigDecimal.valueOf(0.000001));
        area.put("ƽ��Ӣ��(sq.mi)", BigDecimal.valueOf(2589988));
        area.put("ӢĶ(acre)", BigDecimal.valueOf(4046.864798));
        area.put("ƽ����(sq.rd)", BigDecimal.valueOf(25.29285264));
        area.put("ƽ����(sq.yd)", BigDecimal.valueOf(0.83612736));
        area.put("ƽ��Ӣ��(sq.ft)", BigDecimal.valueOf(0.09290304));
        area.put("ƽ��Ӣ��(sq.in)", BigDecimal.valueOf(0.00064516));
        area.put("��", BigDecimal.valueOf(66666.666666666));
        area.put("Ķ", BigDecimal.valueOf(666.666666666));
        area.put("��", BigDecimal.valueOf(66.666666666));

        volume.put("������(m^3)", BigDecimal.valueOf(1));
        volume.put("��������(cm^3)", BigDecimal.valueOf(0.000001));
        volume.put("��������(dm^3)", BigDecimal.valueOf(0.001));
        volume.put("��������(mm^3)", BigDecimal.valueOf(0.000000001));
        volume.put("��(l)", BigDecimal.valueOf(0.001));
        volume.put("����(dl)", BigDecimal.valueOf(0.0001));
        volume.put("����(cl)", BigDecimal.valueOf(0.00001));
        volume.put("����(ml)", BigDecimal.valueOf(0.000001));
        volume.put("΢��(ul)", BigDecimal.valueOf(0.000000001));
        volume.put("��ʯ(hl)", BigDecimal.valueOf(0.1));
        volume.put("������(cu yd)", BigDecimal.valueOf(0.764555));
        volume.put("����Ӣ��(cu ft)", BigDecimal.valueOf(0.028316846592));
        volume.put("����Ӣ��(cu in)", BigDecimal.valueOf(0.00001638706));
        volume.put("Ʒ��(pint)", BigDecimal.valueOf(0.000568));
        volume.put("����(quart)", BigDecimal.valueOf(0.00114));
        volume.put("Ӣ�Ƽ���(uk gal)", BigDecimal.valueOf(0.00454609));
        volume.put("���Ƽ���(us gal)", BigDecimal.valueOf(0.003785412));
        volume.put("Ӣ��Һ�尻˾(oz)", BigDecimal.valueOf(0.00002841));
        volume.put("����Һ�尻˾(oz)", BigDecimal.valueOf(0.00002957));

        speed.put("��/��(m/s)", BigDecimal.valueOf(1));
        speed.put("ǧ��/��(km/s)", BigDecimal.valueOf(1000));
        speed.put("ǧ��/ʱ(km/h)", BigDecimal.valueOf(0.27777778));
        speed.put("Ӣ��/��(in/s)", BigDecimal.valueOf(0.0254));
        speed.put("Ӣ��/ʱ(mile/h)", BigDecimal.valueOf(0.44704));
        speed.put("��", BigDecimal.valueOf(0.51444445));
        speed.put("���(mach)", BigDecimal.valueOf(340.29));

        mass.put("��(t)", BigDecimal.valueOf(1000));
        mass.put("ǧ��(kg)", BigDecimal.valueOf(1));
        mass.put("��(g)", BigDecimal.valueOf(0.001));
        mass.put("����(mg)", BigDecimal.valueOf(0.000001));
        mass.put("΢��(��g)", BigDecimal.valueOf(0.000000001));
        mass.put("����(ct)", BigDecimal.valueOf(0.0002));
        mass.put("Ӣ��(lt)", BigDecimal.valueOf(1016));
        mass.put("����(st)", BigDecimal.valueOf(907));
        mass.put("Ӣ��(hundredweight)", BigDecimal.valueOf(50.8));
        mass.put("����(quarter)", BigDecimal.valueOf(12.7));
        mass.put("Ӣʯ(stone)", BigDecimal.valueOf(6.35));
        mass.put("��(lb)", BigDecimal.valueOf(0.454));
        mass.put("��˾(oz)", BigDecimal.valueOf(0.0283));
        mass.put("����(dr)", BigDecimal.valueOf(0.00177));
        mass.put("����(gr)", BigDecimal.valueOf(0.0000648));
        mass.put("��", BigDecimal.valueOf(50));
        mass.put("��", BigDecimal.valueOf(0.5));
        mass.put("��", BigDecimal.valueOf(0.03125));
        mass.put("Ǯ", BigDecimal.valueOf(0.003125));

        temp.put("���϶�(��C)", "C");
        temp.put("���϶�(��F)", "F");
        temp.put("���϶�(K)", "K");
        temp.put("���϶�(��R)", "R");
        temp.put("���϶�(��Re)", "Re");

        angle.put("�ٷֶ�(grad)", "grad");
        angle.put("��(��)", "��");
        angle.put("����(rad)", "rad");
    }

    /**
     * ָʾһ�����ͣ�����һ���������Դ��λ��Ŀ�굥λ�Ļ��㣬�Լ����ؽ��
     *
     * @param source Դ��λ
     * @param target Ŀ�굥λ
     * @param number ������ֵ
     * @param type   ��������
     * @return ������
     */
    public BigDecimal conver(String source, String target, BigDecimal number, String type) {
        BigDecimal s;
        BigDecimal t;
        switch (type) {
            case "\u957f\u5ea6":
                s = length.get(source);
                t = length.get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "\u9762\u79ef":
                s = area.get(source);
                t = area.get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "\u4f53\u79ef":
                s = volume.get(source);
                t = volume.get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "\u901f\u5ea6":
                s = speed.get(source);
                t = speed.get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "\u8d28\u91cf":
                s = mass.get(source);
                t = mass.get(target);
                return number.multiply(s.divide(t, 9, RoundingMode.HALF_UP));
            case "\u6e29\u5ea6":
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
                        throw new IllegalArgumentException("��λ������");
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
                        throw new IllegalArgumentException("��λ������");
                }
                return rt;
            case "�Ƕ�":
                String sa = angle.get(source);
                String ta = angle.get(target);

                BigDecimal ra;
                switch (sa) {
                    case "��":
                        ra = degToDeg(number);
                        break;
                    case "grad":
                        ra = gradToDeg(number);
                        break;
                    case "rad":
                        ra = radToDeg(number);
                        break;
                    default:
                        throw new IllegalArgumentException("��λ������");
                }
                switch (ta) {
                    case "��":
                        ra = degToDeg(ra);
                        break;
                    case "grad":
                        ra = degToGrad(ra);
                        break;
                    case "rad":
                        ra = degToRad(ra);
                        break;
                    default:
                        throw new IllegalArgumentException("��λ������");
                }
                return ra;
            default:
                throw new IllegalArgumentException("δʵ��");
        }

    }

    //�¶Ȼ���
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

    //�ǶȻ���
    private BigDecimal degToDeg(BigDecimal s) {
        return s;
    }

    private BigDecimal degToRad(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(Math.PI).divide(BigDecimal.valueOf(180), MathContext.DECIMAL128));
    }

    private BigDecimal degToGrad(BigDecimal s) {
        return s.divide(BigDecimal.valueOf(9), MathContext.DECIMAL128).multiply(BigDecimal.valueOf(10));
    }

    private BigDecimal radToDeg(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(180).divide(BigDecimal.valueOf(Math.PI), MathContext.DECIMAL128));
    }

    private BigDecimal gradToDeg(BigDecimal s) {
        return s.multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(9), MathContext.DECIMAL128);
    }
}
