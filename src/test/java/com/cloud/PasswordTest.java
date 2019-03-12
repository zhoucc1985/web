package com.cloud;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Author huangYl
 * @Date 2018/10/29 11:09
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordTest {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void testPassword() {
        //加盐 加散列次数
        Md5Hash md5Hash2 = new Md5Hash("123", "cloudIAShyl", 2);
        System.out.println(md5Hash2);
    }

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("Sunmnet@123");
        System.out.println(result);
    }
}
