package com.atguigu.qqzone.pojo;

import java.sql.Date;

/**
 * @author Yan
 * @create 2023-04-28 16:03
 **/
public class UserDetail {
    private Integer id;
    private String realName;
    private String tel;
    private String email;
    private Date birth;
    private String star;
    //sql.Date年月日
    //util.Date年月日时分秒毫秒
    //sql.Time时分秒
    public UserDetail(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
