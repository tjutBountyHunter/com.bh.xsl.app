package org.yamu.tea_sc.sign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDeviceHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import org.yamu.tea_core.delegate.TeaDelegate;
import org.yamu.tea_sc.R;
import org.yamu.tea_sc.R2;
import org.yamu.tea_sc.home.HomeDelegate;
import org.yamu.tea_sc.sign.authentication.AuthItemCreator;
import org.yamu.tea_sc.sign.authentication.AuthItemEntity;
import org.yamu.tea_sc.sign.authentication.AuthItemTag;
import org.yamu.tea_sc.sign.authentication.AuthNetHandler;
import org.yamu.tea_sc.sign.authentication.IAuthNetCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by mjt89 on 2018/10/2
 */

public class AuthDelegate extends TeaDelegate {

    @BindView(R2.id.topbar_auth)
    QMUITopBar mTopBar = null;
    @BindView(R2.id.glv_auth)
    QMUIGroupListView mListView = null;
    @BindView(R2.id.bt_action_auth)
    QMUIRoundButton mAction_auth = null;

    @OnClick(R2.id.bt_action_auth)
    void onclickAction() {
        startWithPop(new HomeDelegate());
    }


    private static AuthDelegate INSTANCE = null;


    private final LinkedHashMap<AuthItemEntity, QMUICommonListItemView> mItemMap = new LinkedHashMap<>();
    private final LinkedHashMap<AuthItemTag, AuthItemEntity> entities = new LinkedHashMap<>();
    private LinkedHashMap<AuthItemTag, List<String>> mItemDatas = new LinkedHashMap<>();

    private int mCurrentDialogStyle = R.style.QMUI_Dialog;

    public static AuthDelegate newInstance() {
        if (INSTANCE == null) {
            Bundle args = new Bundle();
            INSTANCE = new AuthDelegate();
            INSTANCE.setArguments(args);
        }
        return INSTANCE;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_auth;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTopBar();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initGroupListView();
        AuthNetHandler.onRequestSchools(getContext(), callback, AuthItemTag.SCHOOL);
    }

    private void initTopBar() {
        mTopBar.setTitle("实名认证");
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }

    private final void initGroupListView() {
        AuthItemCreator.initItems(mListView, entities);


        AuthItemEntity entity = new AuthItemEntity(7, AuthItemEntity.ItemType.CUSTOM);
        entity.setmTitle("学生证照片");
        entity.setmTag(AuthItemTag.STUDENT_CARD_PHOTO);
        QMUICommonListItemView itemWithImage = mListView.createItemView(entity.getmTitle());
        itemWithImage.setTag(entity.getmTag());
        itemWithImage.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        ImageView xiangji = new ImageView(getContext());
        xiangji.setAdjustViewBounds(true);
        xiangji.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        xiangji.setMaxHeight(100);
        xiangji.setImageResource(R.mipmap.xueshengzheng);
        itemWithImage.addAccessoryCustomView(xiangji);
        entity.setItemView(itemWithImage);
        entities.put(entity.getmTag(), entity);

        QMUIGroupListView.Section section = QMUIGroupListView.newSection(getContext());

        Iterator iter = entities.entrySet().iterator();
        QMUICommonListItemView itemView = null;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (entry.getKey() != AuthItemTag.STUDENT_CARD_PHOTO) {
                itemView = ((AuthItemEntity) entry.getValue()).getItemView();
                section.addItemView(itemView, onClickListener);
            }
        }
        section.addTo(mListView);

        QMUIGroupListView.newSection(getContext())
                .setTitle("身份信息核实")
                .addItemView(itemWithImage, onClickListener)
                .addTo(mListView);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof QMUICommonListItemView) {
                AuthItemTag tag = (AuthItemTag) v.getTag();
                if (tag == AuthItemTag.NAME || tag == AuthItemTag.SCHOOL_NUMBER) {
                    showEditTextDialog(entities.get(tag));
                } else if (tag == AuthItemTag.STUDENT_CARD_PHOTO) {

                } else {
                    requestData(tag);
                    showSingleChoiceDialog(entities.get(tag));
                }
            }
        }
    };


    private void showEditTextDialog(final AuthItemEntity entity) {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle(entity.getmTitle())
                .setPlaceholder(entity.getmHint())
                .setInputType(InputType.TYPE_CLASS_DATETIME)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            mItemMap.get(entity).setDetailText(text);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), entity.getmError(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void requestData(final AuthItemTag tag) {
        if (tag == AuthItemTag.SCHOOL) {
//            AuthNetHandler.onRequestSchools(getContext(), this);
//            entities.get(tag).setmChildItem();
        }
    }

    private void showSingleChoiceDialog(final AuthItemEntity entity) {
        final List<String> textItems = entity.getmChildItems();
        if (textItems == null || textItems.size() == 0)
            return;
        final QMUIDialog.CheckableDialogBuilder builder = new QMUIDialog.CheckableDialogBuilder(getActivity())
                .addItems(textItems.toArray(new String[textItems.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        entity.getItemView().setDetailText(textItems.get(which));
                        dialog.dismiss();
                        refreshItemData(entity, entities, textItems.get(which));
                    }
                });

        builder.create(mCurrentDialogStyle).show();
    }

    private final void refreshItemData(AuthItemEntity entity, LinkedHashMap<AuthItemTag, AuthItemEntity> entities, String param) {
        if (entity.getmTag() == AuthItemTag.SCHOOL) {
            if (mItemDatas.get(AuthItemTag.COLLEGE) != null)
                mItemDatas.get(AuthItemTag.COLLEGE).clear();
            AuthItemEntity et = entities.get(AuthItemTag.COLLEGE);
            et.getItemView().setDetailText("未填");
            if (mItemDatas.get(AuthItemTag.MAJOR) != null)
                mItemDatas.get(AuthItemTag.MAJOR).clear();
            et = entities.get(AuthItemTag.MAJOR);
            et.getItemView().setDetailText("未填");
            AuthNetHandler.onRequestCollege(getContext(), callback, AuthItemTag.COLLEGE, param);
        }
    }

    IAuthNetCallback callback = new IAuthNetCallback<List<String>>() {
        @Override
        public void onSuccess(List<String> data, AuthItemTag tag) {
            if (!entities.get(tag).getmChildItems().isEmpty())
                entities.get(tag).getmChildItems().clear();
            if (mItemDatas.get(tag) == null) {
                mItemDatas.put(tag, new ArrayList<String>());
            } else {
                mItemDatas.get(tag).clear();
            }
            mItemDatas.get(tag).addAll(data);
            entities.get(tag).setmChildItems(data);
        }

        @Override
        public void onFaile() {

        }
    };
}
