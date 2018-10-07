package org.yamu.tea_sc.hunterMaket.Entity;


import org.yamu.tea_sc.R;

public class HunterMaketClass {
    private String id;
    private String title;
    private int imageResId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = ContentClazz.getTitle(title);
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int clazz) {
        this.imageResId = ContentClazz.getResId(clazz);
    }

    public enum ContentClazz {
        XueFu(0),
        ZiXun(1),
        FuWu(2),
        PaoTui(3),
        SuoWU(4),
        JiaoYi(5),
        ZhaoMu(6);

        ContentClazz(int ResId) {
        }

        public static int getResId(int clazz) {
            int resId = 0;
            switch (clazz) {
                case 0:
                    resId = R.drawable.ic_clazz_hunter_xuefu;
                    break;
                case 1:
                    resId = R.drawable.ic_clazz_hunter_zixun;
                    break;
                case 2:
                    resId = R.drawable.ic_clazz_hunter_fuwu;
                    break;
                case 3:
                    resId = R.drawable.ic_clazz_hunter_paotui;
                    break;
                case 4:
                    resId = R.drawable.ic_clazz_hunter_suowu;
                    break;
                case 5:
                    resId = R.drawable.ic_clazz_hunter_jiaoyi;
                    break;
                case 6:
                    resId = R.drawable.ic_clazz_hunter_zhaomu;
                    break;
                default:
                    resId = R.drawable.ic_clazz_hunter_xuefu;
                    break;
            }

            return resId;
        }

        public static String getTitle(int clazz) {
            String title = "";
            switch (clazz) {
                case 0:
                    title = "学辅";
                    break;
                case 1:
                    title = "咨询";
                    break;
                case 2:
                    title = "服务";
                    break;
                case 3:
                    title = "跑腿";
                    break;
                case 4:
                    title = "索物";
                    break;
                case 5:
                    title = "交易";
                    break;
                case 6:
                    title = "招募";
                    break;
                default:
                    title = "学辅";
                    break;
            }

            return title;
        }
    }
}
