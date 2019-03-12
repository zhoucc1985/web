package com.cloud.common.utils;

import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloud.common.dict.CommonDict;
import com.cloud.common.vo.ShiroUser;

/**
 * 当前登录用户工具类
 *
 * @author Pan jianneng
 * @time 2018/7/31 20:26
 */
public class UserUtils {

    private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);

    private static UserUtils instance;

    private static ReentrantLock lock = new ReentrantLock();

    public UserUtils() {
    }

    public static UserUtils getInstance() {
        if (instance == null) {
            lock.lock();
            instance = new UserUtils();
            lock.unlock();
        }
        return instance;
    }

	/**
     * 获得当前用户需要进行数据过滤的 组织机构编码
     *      如果是学校或者学校上级 取当前用户的组织机构
     *      如果是学校下级则取 schoolCode
     * @return
     */
    public String getFindDataOrgCode() {
        ShiroUser user = getCurrentlyUserInfo();
        String schoolOrgCode = user.getSchoolOrgCode();
        if(StringUtils.isEmpty(schoolOrgCode)) {
            return user.getOrgCode();
        }
        return schoolOrgCode;
    }

    /**
     * 获取当前登录用户信息
     *
     * @param
     * @return: com.entity.user.ScloudUser
     * @auther: Pan jianneng
     * @date: 2018/7/31 20:41
     */
    public ShiroUser getCurrentlyUserInfo() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 获取当前登录用户名称
     * 不是登录名称
     *
     * @param
     * @return: java.lang.String
     * @auther: Pan jianneng
     * @date: 2018/7/31 20:42
     */
    public String getCurrentLyUserName() {
        return getCurrentlyUserInfo().getLoginName();
    }

    public static String getUserSHA1Password(String plainPassword) {
        return getUserSHA1Password(plainPassword, getGenerateSaltStr());
    }

    public static String getUserSHA1Password(String plainPassword, String salt) {
        byte[] pwd = Digests.sha1(plainPassword.getBytes(), CommonUtils.getHexDecode(salt), CommonDict.HASH_ITERATIONS);
        return CommonUtils.getHexEncode(pwd);
    }

    public static byte[] getGenerateSalt() {
        byte[] salt = Digests.generateSalt(CommonDict.SALT_SIZE);
        return salt;
    }

    public static String getGenerateSaltStr() {
        byte[] salt = getGenerateSalt();
        return CommonUtils.getHexEncode(salt);
    }

    public static String getUserEncryptPassWord(String plainPassword, String salt) {
        return new SimpleHash(CommonDict.HASH_ALGORITHM, plainPassword,salt,CommonDict.HASH_ITERATIONS).toString();
    }

}
