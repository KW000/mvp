package com.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.Selection;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;


/**
 * desc
 * Author:shimao
 * Date:2017.09.15 10:03
 */
public class KeyboardDialog extends Dialog {

    public interface ResultCallBack {
        void onItemClick(int position);
    }

    /**
     * 设备的高度
     */
    public int height = 0;
    /**
     * 设备的宽度
     */
    public int width = 0;

    private View view;
    private GridView gridView;
    private LinearLayout layout;
    private Button delete, ok;

    private KeyBoardDialogAdapter adapter;

    public KeyboardDialog(@NonNull Context context) {
        super(context);
        getSysWH();
        initView(context);
    }

    public KeyboardDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        getSysWH();
        initView(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(params);
    }

    private void initView(final Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog, null);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        layout = (LinearLayout) view.findViewById(R.id.line_layout);
        delete = (Button) view.findViewById(R.id.delete);
        ok = (Button) view.findViewById(R.id.ok);
        setViewHeight(layout);
        adapter = new KeyBoardDialogAdapter(context);
        gridView.setAdapter(adapter);
        setContentView(view);
    }


    private void setViewHeight(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height / 3;
        view.setLayoutParams(lp);
    }

    /**
     * 获取屏幕的宽高
     */
    private void getSysWH() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
    }

    public void showDialog(final EditText ed, final ResultCallBack callBack) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ed.setCursorVisible(true);
                if (position == 11) {
                    dismiss();
                    return;
                } else {
                    addKey(ed, adapter.getItem(position) + "");
                    callBack.onItemClick(position);
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ed.getText().toString())) {
                    delete(ed);
                }
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        show();
    }

    //按钮事件触发手动调用此方法
    private void addKey(final EditText phoneText, String key) {
        int index = 0;
        //设置一个变量判断是否有光标
        if (!TextUtils.isEmpty(phoneText.getText())) {
            //获得光标的位置
            index = Selection.getSelectionStart(phoneText.getText());
        } else {
            index = phoneText.getSelectionStart();
        }
        //将字符串转换为StringBuffer
        StringBuffer sb = new StringBuffer(phoneText.getText().toString().trim());
        //将字符插入光标所在的位置
        sb = sb.insert(index, key);
        phoneText.setText(sb.toString());
        //设置光标的位置保持不变
        Selection.setSelection(phoneText.getText(), index + 1);
    }

    //回退删除
    private void delete(final EditText phoneText) {
        StringBuffer sb = new StringBuffer(phoneText.getText().toString().trim());
        int index = 0;
        index = Selection.getSelectionStart(phoneText.getText());
        if (index > 0) {
            sb = sb.delete(index - 1, index);
        }
        phoneText.setText(sb.toString());
        if (index > 0) {
            Selection.setSelection(phoneText.getText(), index - 1);
        }
    }

    @Override
    public void show() {
        getWindow().setWindowAnimations(R.style.dialogWeixinAnim); // 设置窗口弹出动画
        super.show();

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
