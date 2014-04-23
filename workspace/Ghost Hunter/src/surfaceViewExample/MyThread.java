package surfaceViewExample;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

// �����߳�
public class MyThread extends Thread {
	private SurfaceHolder holder;
	private boolean run;

	public MyThread(SurfaceHolder holder) {
		this.holder = holder;
		run = true;
	}

	@Override
	public void run() {
		int counter = 0;
		Canvas canvas = null;
		while (run) {
			// ������ƹ���
			try {
				// ��ȡCanvas���󣬲�����֮
				canvas = holder.lockCanvas();

				// �趨Canvas����ı�����ɫ
				canvas.drawColor(Color.WHITE);

				// ��������
				Paint p = new Paint();
				// ���û�����ɫ
				p.setColor(Color.BLACK);
				// �������ִ�С
				p.setTextSize(30);

				// ����һ��Rect����rect
				Rect rect = new Rect(100, 50, 380, 330);
				// ��canvas�ϻ���rect
				canvas.drawRect(rect, p);
				// ��canvas����ʾʱ��
				canvas.drawText("Interval = " + (counter++) + " seconds.", 100,
						410, p);
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (canvas != null) {
					// ������������ύ�޸�����
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
}