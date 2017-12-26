package com.anxpp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.*;

public class WeixinSign {

    private static final XmlMapper xmlMapper = new XmlMapper();

    /**
     * <xml>
     * <appid>wxd930ea5d5a258f4f</appid>
     * <mch_id>10000100</mch_id>
     * <device_info>1000</device_info>
     * <body>test</body>
     * <nonce_str>ibuaiVcKdpRxkhJA</nonce_str>
     * <nonce_str2></nonce_str2>
     * <sign>764F958F89E2F0E9609378CAAD86FD62</sign>
     * </xml>
     */
    public static void main(String[] args) throws JsonProcessingException {
        String privateKey = "666";
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", "wxd930ea5d5a258f4f");
        param.put("mch_id", "10000100");
        param.put("device_info", "1000");
        param.put("nonce_str", "ibuaiVcKdpRxkhJA");
        param.put("nonce_str2", "");
        param.put("body", "test");

        String xml = xmlMapper.writeValueAsString(param);
        System.out.println(xml);

        System.out.println(sign(param, privateKey));

        System.out.println(sign(xml, privateKey));
    }

    /**
     * 签名策略
     *
     * @param xml        xml值
     * @param privateKey 私钥
     * @return 签名结果
     */
    private static String sign(String xml, String privateKey) {
        JavaType javaType = xmlMapper.getTypeFactory().constructParametricType(HashMap.class, String.class, String.class);
        try {
            Map<String, String> map = xmlMapper.readValue(xml, javaType);
            return sign(map, privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String sign(Map<String, String> param, String privateKey) {
        List<String> keys = new ArrayList<>(param.keySet());
        keys.sort(Comparator.naturalOrder());
        List<String> strings = new ArrayList<>(keys.size());
        for (String key : keys) {
            String value = param.get(key);
            if (StringUtils.hasText(value))
                strings.add(key + "=" + value);
        }
        String stringA = String.join("&", strings);
        return md5(stringA + "&key=" + privateKey);
    }

    /**
     * md5计算
     */
    private static String md5(String message) {
        String md5str = "";
        try {
            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 2 将消息变成byte数组
            byte[] input = message.getBytes();
            // 3 计算后获得字节数组,这就是那128位了
            byte[] buff = md.digest(input);
            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 二进制转十六进制
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder md5str = new StringBuilder();
        // 把数组每一字节换成16进制连成md5字符串
        int digital;
        for (byte aByte : bytes) {
            digital = aByte;
            if (digital < 0)
                digital += 256;
            if (digital < 16)
                md5str.append("0");
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString().toUpperCase();
    }
}
