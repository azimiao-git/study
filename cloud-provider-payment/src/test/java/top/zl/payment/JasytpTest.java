package top.zl.payment;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;


/**
 * @author zl
 * 2021/07/14
 */
public class JasytpTest {

    public static PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();

    public static void main(String[] args) {
        //加密
        String password = encryptPwd(System.getProperty("jasypt.encryptor.password"), "KON.Azusa");
        System.out.println(password);
    }

    /**
     * Jasypt生成加密结果
     * @param password 配置文件中设定的加密盐值
     * @param value 加密值
     */
    public static String encryptPwd(String password,String value){
        encryptor.setConfig(config(password));
        return encryptor.encrypt(value);
    }

    /**
     * 解密
     * @param password 配置文件中设定的加密盐值
     * @param value 解密密文
     */
    public static String decryptPwd(String password,String value){
        encryptor.setConfig(config(password));
        return encryptor.decrypt(value);
    }

    public static SimpleStringPBEConfig config(String password){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        //加密算法
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

}
