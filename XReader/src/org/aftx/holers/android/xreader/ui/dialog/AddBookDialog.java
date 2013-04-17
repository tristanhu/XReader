package org.aftx.holers.android.xreader.ui.dialog;

import org.aftx.holers.android.xreader.R;
import org.aftx.holers.android.xreader.ui.dialog.base.BaseDialog;

import android.content.Context;
import android.os.Handler;

public class AddBookDialog extends BaseDialog {

    // ����˵��
    // context:������
    // dialogid:�Ի���ID
    // title:�Ի������
    // callback:һ������Bundle�����Ļص��ӿ�
    // suffix:��Ҫѡ����ļ���׺��������Ҫѡ��wav��mp3�ļ���ʱ������Ϊ".wav;.mp3;"��ע�������Ҫһ���ֺ�(;)
    // images:�������ݺ�׺��ʾ��ͼ����ԴID��
    // ��Ŀ¼ͼ�������ΪsRoot;
    // ��Ŀ¼������ΪsParent;
    // �ļ��е�����ΪsFolder;
    // Ĭ��ͼ�������ΪsEmpty;
    // ������ֱ�Ӹ��ݺ�׺��������������.wav�ļ�ͼ�������Ϊ"wav"

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
