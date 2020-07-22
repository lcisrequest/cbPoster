package com.cbposter.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    public static final String EMPTY_STRING = ""; //$NON-NLS-1$

    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final int[] EMPTY_INT_ARRAY = new int[0];

    private static final char[] HEX_DIGIT =
            { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public enum Suffix {
        NO(""), //$NON-NLS-1$

        ONE_PTS("."), //$NON-NLS-1$

        THREE_PTS("..."); //$NON-NLS-1$

        private final String value;

        private Suffix(String suffix) {
            this.value = suffix;
        }

        public String getValue() {
            return value;
        }

        public int getLength() {
            return value.length();
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private StringUtil() {
    }

    public static String getTruncatedString(String name, int limit, Suffix suffix) {
        if (name != null && name.length() > limit) {
            int sLength = suffix.getLength();
            int end = limit - sLength;
            if (end > 0 && end + sLength < name.length()) {
                return name.substring(0, end).concat(suffix.getValue());
            }
        }
        return name;
    }

    public static Character getFirstCharacter(String val) {
        if (StringUtil.hasText(val)) {
            return Character.valueOf(val.charAt(0));
        }
        return null;
    }

    public static String[] getStringArray(String val, String delimiter) {
        if (delimiter != null && StringUtil.hasText(val)) {
            return val.split(Pattern.quote(delimiter));
        }
        return EMPTY_STRING_ARRAY;
    }

    public static int[] getIntegerArray(String val, String delimiter) {
        if (delimiter != null && StringUtil.hasText(val)) {
            String[] vl = val.split(Pattern.quote(delimiter));
            int[] res = new int[vl.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = getInt(vl[i]);
            }
            return res;
        }
        return EMPTY_INT_ARRAY;
    }

    public static Integer getInteger(String val) {
        if (StringUtil.hasText(val)) {
            try {
                return Integer.parseInt(val.trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse {} to Integer", val); //$NON-NLS-1$
            }
        }
        return null;
    }


    public static int getInt(String val) {
        if (StringUtil.hasText(val)) {
            try {
                return Integer.parseInt(val.trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse {} to int", val); //$NON-NLS-1$
            }
        }
        return 0;
    }

    public static int getInt(String value, int defaultValue) {
        int result = defaultValue;
        if (value != null) {
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse {} to int", value); //$NON-NLS-1$
            }
        }
        return result;
    }

    public static Double getDouble(String val) {
        if (StringUtil.hasText(val)) {
            try {
                return Double.parseDouble(val.trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Cannot parse {} to Double", val); //$NON-NLS-1$
            }
        }
        return null;
    }

    public static String splitCamelCaseString(String s) {
        StringBuilder builder = new StringBuilder();
        for (String w : s.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])")) { //$NON-NLS-1$
            builder.append(w);
            builder.append(' ');
        }
        return builder.toString().trim();
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }


    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); //$NON-NLS-1$
        return pattern.matcher(nfdNormalizedString).replaceAll(""); //$NON-NLS-1$
    }


    public static List<String> splitStringExceptQuotes(String s) {
        if (s == null) {
            return Collections.emptyList();
        }
        List<String> matchList = new ArrayList<>();
        Pattern patternSpaceExceptQuotes = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'"); //$NON-NLS-1$
        Matcher regexMatcher = patternSpaceExceptQuotes.matcher(s);
        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        return matchList;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_DIGIT[v >>> 4];
            hexChars[j * 2 + 1] = HEX_DIGIT[v & 0x0f];
        }
        return new String(hexChars);
    }

    public static String integerToHex(int val) {
        return Integer.toHexString(val).toUpperCase();
    }

    public static String bytesToMD5(byte[] val) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
        return bytesToHex(md.digest(val));
    }

    public static String getNullIfNull(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString();
    }

    public static String getEmptyStringIfNull(Object object) {
        if (object == null) {
            return ""; //$NON-NLS-1$
        }
        return object.toString();
    }

    public static String getEmptyStringIfNullEnum(Enum<?> object) {
        if (object == null) {
            return ""; //$NON-NLS-1$
        }
        return object.name();
    }


    public static String getChineseName() {
        String str = null;
        String name = null;
        int highPos, lowPos;
        Random random = new Random();
        //区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        highPos = (176 + Math.abs(random.nextInt(72)));
        random=new Random();
        //位码，0xA0打头，范围第1~94列
        lowPos = 161 + Math.abs(random.nextInt(94));

        byte[] bArr = new byte[2];
        bArr[0] = (new Integer(highPos)).byteValue();
        bArr[1] = (new Integer(lowPos)).byteValue();
        try {
            //区位码组合成汉字
            str = new String(bArr, "GB2312");
            int index=random.nextInt(Surname.length-1);
            //获得一个随机的姓氏
            name = Surname[index] +str;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    //百家姓
    private static final String[] Surname= {"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
            "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
            "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
            "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
            "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
            "宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
            "羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
            "谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
            "詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
            "蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
            "璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
            "宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
            "阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
            "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
            "督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
            "哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
            "疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
            "木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
            "闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
            "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
            "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
            "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
            "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"};
}
