package surfaceViewExample;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements
		SurfaceHolder.Callback {
	private SurfaceHolder holder;
	private MyThread myThread;

	public MySurfaceView(Context context) {
		super(context);

		// ͨ��SurfaceView���SurfaceHolder����
		holder = getHolder();

		// Ϊholder��ӻص��ṹSurfaceHolder.Callback
		holder.addCallback(this);

		// ����һ�������̣߳���holder������Ϊ�������룬�����ڻ����߳��оͿ��Ի��holder
		// ���󣬽����ڻ����߳��п���ͨ��holder������Canvas���󣬲���Canvas�Ͻ��л���
		myThread = new MyThread(holder);
	}

	// ʵ��SurfaceHolder.Callback�ӿ��е��������������������߳��е��ã��������ڻ����߳��е��õ�
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// �����̡߳��������������ʱ��˵��Surface�Ѿ���Ч��
		myThread.setRun(true);
		myThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// �����̡߳��������������ʱ��˵��Surface����Ҫ��������
		myThread.setRun(false);
	}
}