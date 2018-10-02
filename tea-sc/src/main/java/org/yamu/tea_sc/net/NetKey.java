package org.yamu.tea_sc.net;

/**
 * Created by mjt89 on 2018/10/2 0002.
 */

public class NetKey {

    public static String HOST_ADDR = "47.93.200.190";
    public static String PORT = "8081";
    public static String API_HOST = "http://" + HOST_ADDR;
    public static String ROOT_PATH = "/xsl_service";
    public static String BASE_URL = "http://" + HOST_ADDR + ":" + PORT + ROOT_PATH;
    public static final String LOGIN = BASE_URL + "/xsl/xsluser/login";
    public static final String TASK_HALL_INIT = BASE_URL + "/task/pageQueryC";
}
