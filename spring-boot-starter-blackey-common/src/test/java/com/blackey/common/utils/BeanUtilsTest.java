package com.blackey.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blackey.common.annotation.Desensitized;
import com.blackey.common.enums.SensitiveTypeEnum;
import com.blackey.common.result.Result;
import com.blackey.common.result.ResultCodeEnum;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;

/**
 * Bean test
 *
 * @author wangwei
 * @date 2018/10/31
 */
public class BeanUtilsTest {


    class User implements Serializable{
        @Desensitized(type = SensitiveTypeEnum.CHINESE_NAME)
        private String name = "kkkk";
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    class UserForm{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "UserForm{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }


    @Test
    public void testHutoolBeanUtil(){

        UserForm userForm = new UserForm();
        userForm.setAge(21);
        User user = new User();
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreNullValue(true);
        BeanUtil.copyProperties(userForm,user,copyOptions);
        System.out.println(user.toString());


        BeanUtil.copyProperties(userForm,user);
        System.out.println(user);
    }


    @Test
    public void testClone() throws Exception{
        User user = new User();
        user.setAge(22);
        user.setName("王威");

        User user1 = new User();
        user1.setAge(23);
        user1.setName("王哲");

        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);


        Result result = new Result(ResultCodeEnum.SUCCESS);
        result.setData(users);

        if(result.getData() instanceof Collection){
            String json1 = JSON.toJSONString(result.getData(), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
            System.out.println(json1);
        }

        Result result1 = ObjectUtil.clone(result);

        /* 定义一个计数器，用于避免重复循环自定义对象类型的字段 */
        Set<Integer> referenceCounter = new HashSet<Integer>();

        /* 对克隆实体进行脱敏操作 */
        DesensitizedUtils.replace(ObjectUtils.getAllFields(result1), result1, referenceCounter);


        /* 利用fastjson对脱敏后的克隆对象进行序列化 */
        String json = JSON.toJSONString(result1, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);

    }
}
