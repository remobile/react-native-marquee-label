package com.remobile.marqueeLabel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;


public class RCTMarqueeLabel  extends SurfaceView implements SurfaceHolder.Callback{
    public Context  mContext;

    private  String mText; //内容

    private float mTextSize = 100; //字体大小

    private int mTextColor = Color.RED; //字体的颜色

    private int mBackgroundColor=Color.WHITE;//背景色

    private boolean mIsRepeat;//是否重复滚动

    private int mStartPoint;// 开始滚动的位置  0是从最左面开始    1是从最末尾开始

    private int mDirection;//滚动方向 0 向左滚动   1向右滚动

    private float mScrollDuration;//滚动速度

    private SurfaceHolder holder;

    private TextPaint mTextPaint;

    private Typeface mTypeface;

    private MarqueeViewThread mThread;

    private String margueeString;

    private int textWidth=0,textHeight=0;

    private int ShadowColor=Color.BLACK;

    public int currentX=0;// 当前x的位置

    public int sepX=5;//每一步滚动的距离

    public RCTMarqueeLabel(Context context) {
        this(context,null);
    }

    public RCTMarqueeLabel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RCTMarqueeLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init(attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startScroll();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopScroll();
    }

    void setText(String text) {
        mText = text;
    }

    void setTextSize(float size) {
        mTextSize = size;
    }

    void setTextColor(int color) {
        mTextColor = color;
    }

    void setPaintBackgroundColor(int color) {
        mBackgroundColor = color;
    }

    void setIsRepeat(boolean flag) {
        mIsRepeat = flag;
    }

    void setStartPoint(int point) {
        mStartPoint = point;
    }

    void setDirection(int direction) {
        mDirection = direction;
    }

    void setScrollDuration(int scrollDuration) {
        mScrollDuration = scrollDuration;
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        mTextColor = Color.RED;
        mTextSize = 48;
        mBackgroundColor=Color.WHITE;
        mIsRepeat=true;
        mStartPoint=0;
        mDirection=0;
        mScrollDuration= 10;

        holder = this.getHolder();
        holder.addCallback(this);
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    protected void measurementsText(String msg) {
        margueeString=msg;
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTypeface(mTypeface);
        mTextPaint.setStrokeWidth(0.5f);
        mTextPaint.setFakeBoldText(true);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
//        mTextPaint.setShadowLayer(5, 3, 3, ShadowColor);
        textWidth = (int)mTextPaint.measureText(margueeString);
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        textHeight = (int) fontMetrics.bottom;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        if(mStartPoint==0)
            currentX=0;
        else
            currentX=width-getPaddingLeft()-getPaddingRight();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder=holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mThread!=null)
            mThread.isRun = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(mThread!=null)
            mThread.isRun = false;
    }

    /**
     * 开始滚动
     */
    public  void startScroll(){
        if(mThread!=null&&mThread.isRun)
            return;
        if(!TextUtils.isEmpty(mText)){
            measurementsText(mText);
        }
        mThread = new MarqueeViewThread(holder);//创建一个绘图线程
        mThread.start();
    }

    /**
     * 停止滚动
     */
    public  void stopScroll(){
        if(mThread!=null){
            mThread.isRun = false;
            mThread.interrupt();
        }
        mThread=null;
    }
    /**
     * 线程
     */
    class MarqueeViewThread extends Thread{

        private SurfaceHolder holder;

        public boolean isRun ;//是否在运行


        public  MarqueeViewThread(SurfaceHolder holder) {
            this.holder =holder;
            isRun = true;
        }

        public void onDraw() {
            try {
                synchronized (holder) {
                    if (TextUtils.isEmpty(margueeString)) {
                        Thread.sleep(1000);//睡眠时间为1秒
                        return;
                    }
                    Canvas canvas = holder.lockCanvas();
                    int paddingLeft = getPaddingLeft();
                    int paddingTop = getPaddingTop();
                    int paddingRight = getPaddingRight();
                    int paddingBottom = getPaddingBottom();

                    int contentWidth = getWidth() - paddingLeft - paddingRight;
                    int contentHeight = getHeight() - paddingTop - paddingBottom;

                    int centeYLine = paddingTop + contentHeight / 2;//中心线

                    if(mDirection==0) {//向左滚动
                        if(currentX <=-textWidth){
                            if(!mIsRepeat){//如果是不重复滚动
                                mHandler.sendEmptyMessage(ROLL_OVER);
                            }
                            currentX=contentWidth;
                        }else{
                            currentX-=sepX;
                        }
                    }else {//  向右滚动
                        if(currentX>=contentWidth){
                            if(!mIsRepeat){//如果是不重复滚动
                                mHandler.sendEmptyMessage(ROLL_OVER);
                            }
                            currentX=-textWidth;
                        }else{
                            currentX+=sepX;
                        }
                    }

                    canvas.drawColor(mBackgroundColor);
                    canvas.drawText(margueeString,currentX, centeYLine+dip2px(getContext(),textHeight)/2,mTextPaint);
                    holder.unlockCanvasAndPost(canvas);//结束锁定画图，并提交改变。

                    int c= (int)(mScrollDuration*1000/(textWidth/sepX));

                    Thread.sleep(c);//睡眠时间为移动的频率
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (isRun) {
                onDraw();
            }

        }

    }

    public static  final  int  ROLL_OVER =100;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case ROLL_OVER:
                    stopScroll();
                    if(mOnMargueeListener!=null){
                        mOnMargueeListener.onRollOver();
                    }
                    break;
            }
        }
    };

    /**
     * dip转换为px
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void reset(){
        int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        if(mStartPoint==0)
            currentX=0;
        else
            currentX=contentWidth;
    }
    /**
     * 滚动回调
     */
    public interface OnMargueeListener{
        void onRollOver();//滚动完毕
    }

    OnMargueeListener mOnMargueeListener;

    public void setOnMargueeListener(OnMargueeListener mOnMargueeListener){
        this.mOnMargueeListener=mOnMargueeListener;
    }
}