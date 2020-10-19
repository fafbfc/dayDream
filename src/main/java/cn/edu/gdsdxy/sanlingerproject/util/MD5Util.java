package cn.edu.gdsdxy.sanlingerproject.util;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class MD5Util
{
    public static final String FROM_SALT = "dayDream";

//  DigestUtils.md5Hex(): md5加密
    public static String MD5FromFrontend(String password)
    {
        String saltPassword = "" + FROM_SALT.charAt(0) + FROM_SALT.charAt(1) + password + FROM_SALT.charAt(2) + FROM_SALT.charAt(3);
        String md5Password = DigestUtils.md5Hex(saltPassword);
        return md5Password;
    }

//    数据库的加密密码
    public static String MD5ToDb(String passwork, String salt)
    {
        if( salt == null )
        {
            salt = MD5Util.FROM_SALT;
        }
        String saltPassword = passwork + salt;
        String md5Password = DigestUtils.md5Hex(saltPassword);
        return md5Password;
    }

    public static void main(String[] args)
    {
        String md5Password = MD5Util.MD5FromFrontend("1");
        md5Password = MD5Util.MD5ToDb(md5Password, MD5Util.FROM_SALT);
        System.out.println(md5Password);
    }
}