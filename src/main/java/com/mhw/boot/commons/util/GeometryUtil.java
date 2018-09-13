package com.mhw.boot.commons.util;

import com.mhw.boot.commons.exception.ThirdPartyException;
import com.mhw.boot.commons.exception.ThirdPartyException;

/**
 * 几何图形计算工具类
 *
 * @Author mhw_mhw
 * @Date 2018/7/4 10:20
 * @Version
 */
public class GeometryUtil {

    private final static String AREA = "area";
    private final static String PERIMETER = "perimeter";

    /**
     * 矩形计算，目前可计算面积和周长
     *
     * @param length 长
     * @param width 宽
     * @param method 计算方式，包括面积(area)和周长(perimeter)
     * @return
     * @author mhw_mhw
     * @data 2018/7/4 10:21
     */
    public static double rectangle(double length, double width, String method) {

        if(length <= 0 || width <= 0) {
            throw new ThirdPartyException("长与宽必须大于0！");
        }

        if(AREA.equals(method.toLowerCase())) {
            return length * width;
        }

        if(PERIMETER.equals(method.toLowerCase())) {
            return (length + width) *2;
        }

        throw new ThirdPartyException("计算方式不符合要求！");
    }

    /**
     * 圆的计算，计算方式包括面积和周长
     *
     * @param radius 半径
     * @param method 计算方式，包括面积(area)和周长(perimeter)
     * @return
     * @author mhw_mhw
     * @data 2018/7/4 10:37
     */
    public static double circle(double radius, String method) {
        if(radius <= 0) {
            throw new ThirdPartyException("圆的半径必须大于0！");
        }

        switch (method.toLowerCase()) {
            case AREA : return Math.PI * radius * radius;
            case PERIMETER : return 2 * Math.PI * radius;
            default: throw new ThirdPartyException("计算方式不符合要求！");
        }

    }

    public static double triangle(double a, double b, double c, String method) {
        if(a <= 0 || b <= 0 || c <= 0) {
            throw new ThirdPartyException("边长必须大于0！");
        }

        //三角形周长
        double perimeter = a + b + c;

        //三角形的最大边长要小于其它两条边的边长之和->最大边要小于周长的一半
        double maxSide = Math.max(a, b);
        maxSide = Math.max(maxSide, c);
        if(maxSide >= perimeter / 2) {
            throw new ThirdPartyException("三角形的最大边长要小于其它两条边的边长之和!");
        }

        if(AREA.equals(method.toLowerCase())) {
            //海伦公式
            double p = perimeter / 2;
            return Math.sqrt(p * (p - a) * (p - b) * (p - c));
        }

        if(PERIMETER.equals(method.toLowerCase())) {
            return perimeter;
        }

        throw new ThirdPartyException("计算方式不符合要求！");
    }

    public static void main(String[] args) {
        double length = 1;
        double width = 2;
        double radius = 2;
        double a = 1;
        double b = 1;
        double c = 1;
        System.out.println("面积为：" + triangle(a, b, c, "area"));
        System.out.println("周长为：" + triangle(a, b, c, "perimeter"));
    }


}
