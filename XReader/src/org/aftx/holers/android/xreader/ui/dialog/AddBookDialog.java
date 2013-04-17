package org.aftx.holers.android.xreader.ui.dialog;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.dialog.base.BaseDialog;

import android.content.Context;
import android.os.Handler;

public class AddBookDialog extends BaseDialog {

    // 参数说明
    // context:上下文
    // dialogid:对话框ID
    // title:对话框标题
    // callback:一个传递Bundle参数的回调接口
    // suffix:需要选择的文件后缀，比如需要选择wav、mp3文件的时候设置为".wav;.mp3;"，注意最后需要一个分号(;)
    // images:用来根据后缀显示的图标资源ID。
    // 根目录图标的索引为sRoot;
    // 父目录的索引为sParent;
    // 文件夹的索引为sFolder;
    // 默认图标的索引为sEmpty;
    // 其他的直接根据后缀进行索引，比如.wav文件图标的索引为"wav"

    public AddBookDialog(Context context, Handler handler) {
        super(context, handler);

        setTitle(R.string.title_add_book);
        FileSelectView view = new FileSelectView(this.getContext());
        view.setHandler(handler);
        setView(view);

        dialog = create();

        view.setDialog(dialog);

        dialog.show();
    }
}
