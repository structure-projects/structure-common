package cn.structure.common.utils;

/**
 * @author CHUCK
 * @Title: StringUtil
 * @Package com.neusoft.cheryownersclub.common.utils
 * @Description: StringUtil
 * @date: 2019/1/18 16:50
 * @Version V1.0.0
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param string
     * @return boolean
     **/
    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!StringUtil.isWhitespace(string.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为空白字符
     * @param ： c
     * @return boolean
     **/

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }

    /**
     * unicode 转字符串
     * @param ： unicode
     * @return java.lang.String
     **/

    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    /**
     * 去掉英文单引号以及首尾空格
     * @param ： string
     * @return java.lang.String
     **/

    public static String trimAndRemoveQuot(String string) {
        return string.replaceAll("['*]*", "").trim();
    }

    /**
     * 去掉英文单引号以及所有空格
     * @param ： string
     * @return java.lang.String
     **/

    public static String removeAllBlankAndQuot(String string) {
        return string.replaceAll("['*| *|　*|\\s*]*", "").trim();
    }

    /**
     * 手机号中间4位替换为掩码
     * @param ： phone
     * @return java.lang.String
     **/

    public static String midleReplaceStar(String phone) {
        String result = null;
        if (!isBlank(phone)) {
            if (phone.length() < 7) {
                result = phone;
            } else {
                String start = phone.substring(0, 3);
                String end = phone.substring(phone.length() - 4, phone.length());
                StringBuilder sb = new StringBuilder();
                sb.append(start).append("****").append(end);
                result = sb.toString();
            }
        }
        return result;
    }
}
