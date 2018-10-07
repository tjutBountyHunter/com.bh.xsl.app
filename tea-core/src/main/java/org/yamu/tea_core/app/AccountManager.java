package org.yamu.tea_core.app;

import org.yamu.tea_core.util.storage.TeaPreference;

/**
 * fa
 * Created by mjt89 on 2018/10/2 0002.
 */

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    // 保存用户登录状态 ,登陆后调用
    public static void setSignState(boolean state) {
        TeaPreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return TeaPreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
