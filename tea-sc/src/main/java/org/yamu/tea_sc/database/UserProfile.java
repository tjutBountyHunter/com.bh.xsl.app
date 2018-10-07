package org.yamu.tea_sc.database;

import com.alibaba.fastjson.annotation.JSONField;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by mjt89 on 2018/10/2 0002
 */

@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    @JSONField(name = "id")
    private Long userId = 0L;
    @JSONField(name = "hunterid")
    private Long hunterId = 0L;
    @JSONField(name = "masterid")
    private Long masterId = 0L;
    private String name = null;
    @JSONField(name = "sex")
    private String gender = null;
    private String phone = null;
    @JSONField(name = "schoolinfo")
    private String school = null;
    private String hunterLevel = null;
    private String level = null;
    private String signature = null;
    private String email = null;

    @Generated(hash = 1625987961)
    public UserProfile(Long userId, Long hunterId, Long masterId, String name,
                       String gender, String phone, String school, String hunterLevel,
                       String level, String signature, String email) {
        this.userId = userId;
        this.hunterId = hunterId;
        this.masterId = masterId;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.school = school;
        this.hunterLevel = hunterLevel;
        this.level = level;
        this.signature = signature;
        this.email = email;
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

    public Long getHunterId() {
        return this.hunterId;
    }

    public void setHunterId(Long hunterId) {
        this.hunterId = hunterId;
    }

    public Long getMasterId() {
        return this.masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
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

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", hunterId=" + hunterId +
                ", masterId=" + masterId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", school='" + school + '\'' +
                ", hunterLevel='" + hunterLevel + '\'' +
                ", level='" + level + '\'' +
                ", signature='" + signature + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
