package com.chinaso.so.basecomponent.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @author yanxu
 * @date:2018/10/18
 * @description:
 */

public class PreferenceUtils {
    private static final String FILL_NAME = "def_app_config";
    private static SharedPreferences mSharedPreferences;
    private static PreferenceUtils instance;

    private PreferenceUtils(Context context) {
        //单例
        mSharedPreferences = context.getSharedPreferences(FILL_NAME, Context.MODE_PRIVATE);
    }

    //放到application 中进行全局初始化
    public static synchronized void init(Context context) {
        if (instance == null) {
            instance = new PreferenceUtils(context);
        }
    }

    /**
     * @param @return 参数
     * @return PreferenceUtils    返回类型
     * @throws
     * @Title: getInstance
     * @Description: 获取单例
     */
    public static PreferenceUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("preference should be init in application");
        }
        if (mSharedPreferences == null) {
            throw new RuntimeException("sharedPreferences init fail");
        }
        return instance;
    }

    /**
     * 存入某个key对应的value值
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        edit.apply();
    }

    /**
     * 得到某个key对应的值
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object get(String key, Object defValue) {
        if (defValue instanceof String) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public void putObject(String key, Object saveObject) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.apply();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param key 储存对象的key
     * @return object 返回根据key得到的对象
     */
    public Object getObject(String key) {
        String string = mSharedPreferences.getString(key, null);
        if (string != null) {
            return String2Object(string);
        } else {
            return null;
        }
    }


    /**
     * 返回所有数据
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    /**
     * 清除所有内容
     */
    public void clear() {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.clear();
        edit.apply();
    }

}
