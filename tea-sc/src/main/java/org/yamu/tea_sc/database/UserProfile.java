package org.yamu.tea_sc.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mjt89 on 2018/10/2 0002
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private Long userId = 0L;
    private String name = null;
    private String gender = null;
    private String phone = null;
    private String school = null;
    private String hunterLevel = null;
    private String level = null;
    @Generated(hash = 1201982191)
    public UserProfile(Long userId, String name, String gender, String phone,
            String school, String hunterLevel, String level) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.school = school;
        this.hunterLevel = hunterLevel;
        this.level = level;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSchool() {
        return this.school;
    }
    public void setSchool(String school) {
        this.school = school;
    }
    public String getHunterLevel() {
        return this.hunterLevel;
    }
    public void setHunterLevel(String hunterLevel) {
        this.hunterLevel = hunterLevel;
    }
    public String getLevel() {
        return this.level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", hunterLevel='" + hunterLevel + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
