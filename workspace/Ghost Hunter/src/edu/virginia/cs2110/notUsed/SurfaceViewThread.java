//package edu.virginia.cs2110.notUsed;
//
//import edu.virginia.cs2110.dqxy.Human;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.view.SurfaceHolder;
//
//public class SurfaceViewThread extends Thread {
//
//	private SurfaceHolder holder;
//	private boolean run;
//	private MapSurfaceView mapview;
//
//	public SurfaceViewThread(SurfaceHolder holder, MapSurfaceView mapview) {
//		this.holder = holder;
//		run = true;
//		this.mapview=mapview;
//		
//		
//		
//	}
//
//	@Override
//	public void run() {
//		int counter = 0;
//		Canvas canvas = null;
//		while (run) {
//			// 具体绘制工作
//			try {
//				// 获取Canvas对象，并锁定之
//				canvas = holder.lockCanvas();
//
//				// 设定Canvas对象的背景颜色
//				
//				doDraw(canvas);
//				
//				Thread.sleep(30);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (canvas != null) {
//					// 解除锁定，并提交修改内容
//					holder.unlockCanvasAndPost(canvas);
//				}
//			}
//		}
//	}
//
//	public boolean isRun() {
//		return run;
//	}
//
//	public void setRun(boolean run) {
//		this.run = run;
//	}
//	
//	private void doDraw(Canvas canvas) {
//		canvas.drawColor(Color.WHITE);// clear screen
//		
//		Paint p = new Paint();
//		p.setColor(Color.BLACK);
//		p.setTextSize(30);
//		Paint redPaint = null;
//		float h = canvas.getHeight();
//		float w = canvas.getWidth();
//		Human human = mapview.getHuman();
//
//		// draw the human
//		canvas.drawBitmap(human.getImage(), human.getX(), human.getY(), redPaint);
//		canvas.drawRect(w * 0.30F, h * 0.03F,
//				(float) (w * 0.70F * (10) / 10), h * 0.07F, redPaint);
//		canvas.drawCircle(50, 50, 30, redPaint);
//
//		// draw the ghost
////		canvas.drawBitmap(ghost.getImage(), ghost.getX(), ghost.getY(),
////				translucentPaint);
//	}
//
//}
