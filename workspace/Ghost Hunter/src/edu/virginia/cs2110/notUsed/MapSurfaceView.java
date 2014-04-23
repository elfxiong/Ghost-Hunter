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
//		// 通过SurfaceView获得SurfaceHolder对象
//		holder = getHolder();
//
//		// 为holder添加回调结构SurfaceHolder.Callback
//		holder.addCallback(this);
//
//		// 创建一个绘制线程，将holder对象作为参数传入，这样在绘制线程中就可以获得holder
//		// 对象，进而在绘制线程中可以通过holder对象获得Canvas对象，并在Canvas上进行绘制
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
//	// 实现SurfaceHolder.Callback接口中的三个方法，都是在主线程中调用，而不是在绘制线程中调用的
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//	}
//
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		// 启动线程。当这个方法调用时，说明Surface已经有效了
//		myThread.setRun(true);
//		myThread.start();
//	}
//
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		// 结束线程。当这个方法调用时，说明Surface即将要被销毁了
//		myThread.setRun(false);
//	}
//	
//	
//	
//
//}
