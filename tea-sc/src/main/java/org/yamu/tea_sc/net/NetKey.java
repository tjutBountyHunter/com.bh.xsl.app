package org.yamu.tea_sc.net;

/**
 * Created by mjt89 on 2018/10/2 0002
 */

public class NetKey {

    //    public static String HOST_ADDR = "47.93.200.190";
    public static String HOST_ADDR = "192.168.0.100";
    public static String PORT = "8081/";
    public static String API_HOST = "http://" + HOST_ADDR;
    public static String BASE_URL = API_HOST + ":" + PORT;

    public static String ROOT_PATH = BASE_URL + "xsl/";
    public static String USER_PATH = ROOT_PATH + "xsluser/";

    public static final String LOGIN = USER_PATH + "login/";
    public static final String REQUEST_SCHOOLS = USER_PATH + "schoolClasses/";
    public static final String REQUEST_COLLEGE = USER_PATH + "collegeClasses/";

    public static final String TASK_HALL = ROOT_PATH + "task/pageQueryC";

    public static final String ACCEPT_TASK = ROOT_PATH + "task/accept";

}
