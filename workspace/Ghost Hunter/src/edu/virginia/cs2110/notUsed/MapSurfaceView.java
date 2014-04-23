//package edu.virginia.cs2110.notUsed;
//
//import java.io.Serializable;
//
//import edu.virginia.cs2110.dqxy.Ghost;
//import edu.virginia.cs2110.dqxy.Human;
//import android.content.Context;
//import android.graphics.Paint;
//import android.os.Bundle;
//import android.util.AttributeSet;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//public class MapSurfaceView extends SurfaceView implements Serializable,
//		SurfaceHolder.Callback {
//
//	private SurfaceHolder holder;
//	private SurfaceViewThread myThread;
//
//	private static final long serialVersionUID = -6303933339537109651L;
//	private Paint translucentPaint;
//	private Paint redPaint;
//	private Human human;
//	private double dangerLevel;
//	private Ghost ghost;
//
//	public Ghost getGhost() {
//		return ghost;
//	}
//
//	public void setGhost(Ghost ghost) {
//		this.ghost = ghost;
//	}
//
//	// Three constructors - hover on CircleView, auto generate all of them
//	public MapSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		init();
//
//	}
//
//	public MapSurfaceView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		init();
//	}
//
//	public MapSurfaceView(Context context) {
//		super(context);
//		init();
//	}
//
//	// init method sets up paint
//	public void init() {
//		// ͨ��SurfaceView���SurfaceHolder����
//		holder = getHolder();
//
//		// Ϊholder��ӻص��ṹSurfaceHolder.Callback
//		holder.addCallback(this);
//
//		// ����һ�������̣߳���holder������Ϊ�������룬�����ڻ����߳��оͿ��Ի��holder
//		// ���󣬽����ڻ����߳��п���ͨ��holder������Canvas���󣬲���Canvas�Ͻ��л���
//		myThread = new SurfaceViewThread(holder, this);
//		Bundle b = new Bundle();
////		b.pu
//
//		human = new Human(this.getContext());
//		ghost = new Ghost(10, 10, this.getContext());
//		redPaint = new Paint();
//		redPaint.setARGB(200, 100, 100, 100);
//		translucentPaint = new Paint();
//		translucentPaint.setAlpha(125);
//	}
//
//	public Human getHuman() {
//		return human;
//	}
//
//	public void setHuman(Human human) {
//		this.human = human;
//	}
//
//	// ʵ��SurfaceHolder.Callback�ӿ��е��������������������߳��е��ã��������ڻ����߳��е��õ�
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		// �����̡߳��������������ʱ��˵��Surface�Ѿ���Ч��
//		myThread.setRun(true);
//		myThread.start();
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		// �����̡߳��������������ʱ��˵��Surface����Ҫ��������
//		myThread.setRun(false);
//	}
//	
//	
//	
//
//}
