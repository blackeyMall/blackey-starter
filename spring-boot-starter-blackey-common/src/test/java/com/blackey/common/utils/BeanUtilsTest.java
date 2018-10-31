package com.blackey.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.junit.Test;

/**
 * Bean test
 *
 * @author wangwei
 * @date 2018/10/31
 */
public class BeanUtilsTest {


    class User{
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
}
