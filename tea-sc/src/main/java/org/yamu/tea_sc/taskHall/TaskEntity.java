package org.yamu.tea_sc.taskHall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mjt89 on 2018/10/4 0004
 */

public class TaskEntity {

    private String id;
    @JSONField(name = "taskId")
    private String taskId;
    @JSONField(name = "userName")
    private String name;
    @JSONField(name = "createdate")
    private String time;
    private String money;
    @JSONField(name = "descr")
    private String content;
    @JSONField(name = "deadline")
    private String deadLine;
    private int level;
    private int imageCount;
    private String[] imageUrl;
    @JSONField(name = "url")
    private String headImage;
    @JSONField(name = "name")
    private String tag;

    public TaskEntity() {
    }

    public TaskEntity(String id, String taskId, String name, String time, String money,
                      String content, String deadLine, int level, int imageCount, String[] imageUrl,
                      String headImage, String tag) {
        this.id = id;
        this.taskId = taskId;
        this.name = name;
        this.time = time;
        this.money = money;
        this.content = content;
        this.deadLine = deadLine;
        this.level = level;
        this.imageCount = imageCount;
        this.imageUrl = imageUrl;
        this.headImage = headImage;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", money='" + money + '\'' +
                ", content='" + content + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", level=" + level +
                ", imageCount=" + imageCount +
                ", imageUrl=" + Arrays.toString(imageUrl) +
                ", headImage='" + headImage + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
