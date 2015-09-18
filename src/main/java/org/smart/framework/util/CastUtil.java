/**
 * author : zhaohang
 * email : zhaohangmail@126.com
 */
package org.smart.framework.util;

/**
 * 转型操作工具类
 *
 * @author zhaohang
 */
public class CastUtil {
	
	/**
	 * 转为 String 型
	 */
	public static String castString(Object obj) {
		return CastUtil.castString(obj, "");
	}
	
	/**
	 * 转为 String 型 (提供默认值)
	 */
	public static String castString(Object obj,String defaultValue) {
		return obj != null ? String.valueOf(obj) : defaultValue;
	}
	
	/**
	 * 转为 double 型
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}
	
	 /**
     * 转为 double 型(提供默认值)
     */
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if(obj != null){
			String strValue = castString(obj);
			if(StringUtil.isNotEmpty(strValue)){
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e){
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}
	
	/**
	 * 转为 long 型
	 */
	public static long castLong(Object obj) {
		return CastUtil.castLong(obj, 0);
	}
	
	/**
	 * 转为 long 型 (提供默认值)
	 */
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if(obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
	}
	
	/**
     * 转为 int 型
     */
    public static int castInt(Object obj) {
        return CastUtil.castInt(obj, 0);
    }

    /**
     * 转为 int 型(提供默认值)
     */
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }
	
    /**
     * 转为 boolean 型
     */
    public static boolean castBoolean(Object obj) {
        return CastUtil.castBoolean(obj, false);
    }

    /**
     * 转为 boolean 型(提供默认值)
     */
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }
    
    /**
     * 转为 String[] 型
     */
    public static String[] castStringArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int length = objArray.length;
        String[] strArray = new String[length];
        if (ArrayUtil.isNotEmpty(objArray)) {
            for (int i = 0; i < length; i++) {
                strArray[i] = castString(objArray[i]);
            }
        }
        return strArray;
    }
	
    /**
     * 转为 double[] 型
     */
    public static double[] castDoubleArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int length = objArray.length;
        double[] doubleArray = new double[length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < length; i++) {
                doubleArray[i] = castDouble(objArray[i]);
            }
        }
        return doubleArray;
    }
	
    /**
     * 转为 long[] 型
     */
    public static long[] castLongArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int length = objArray.length;
        long[] longArray = new long[length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < length; i++) {
                longArray[i] = castLong(objArray[i]);
            }
        }
        return longArray;
    }
	
    /**
     * 转为 int[] 型
     */
    public static int[] castIntArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int length = objArray.length;
        int[] intArray = new int[length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < length; i++) {
                intArray[i] = castInt(objArray[i]);
            }
        }
        return intArray;
    }
	
    /**
     * 转为 boolean[] 型
     */
    public static boolean[] castBooleanArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int length = objArray.length;
        boolean[] booleanArray = new boolean[length];
        if (!ArrayUtil.isEmpty(objArray)) {
            for (int i = 0; i < length; i++) {
                booleanArray[i] = castBoolean(objArray[i]);
            }
        }
        return booleanArray;
    }
}
