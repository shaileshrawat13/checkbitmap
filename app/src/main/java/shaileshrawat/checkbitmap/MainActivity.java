package shaileshrawat.checkbitmap;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.support.v7.appcompat.R.styleable.ActionBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressbar;
    CountDownTimer ctimer;
    int intValue = 0;
    Handler handler = new Handler();
    ActionBar.LayoutParams layoutparams;
    RelativeLayout relativelayout;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativelayout = (RelativeLayout) findViewById(R.id.rl);
        // Creating progress bar via coding method.
        score = new TextView(MainActivity.this, null);

        score.setText("Score");
        progressbar = new ProgressBar(MainActivity.this, null, android.R.attr.progressBarStyleHorizontal);
       /* //progressbar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        progressbar.setProgressDrawable(getResources().getDrawable(R.drawable.progress));

        //progressbar.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


        layoutparams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutparams.setMargins(0, 0, 0, 0);

        progressbar.setLayoutParams(layoutparams);

        relativelayout.addView(progressbar);
        relativelayout.addView(score);
        ObjectAnimator anim = ObjectAnimator.ofInt(progressbar, "progress", 0, 100);
        anim.setDuration(1000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();*/
        //Custom background drawable allows you to draw how you want it to look if needed
        SeekBarBackgroundDrawable backgroundDrawable = new SeekBarBackgroundDrawable(getApplicationContext());
        ColorDrawable progressDrawable = new ColorDrawable(Color.BLUE);
        //Custom seek bar progress drawable. Also allows you to modify appearance.
        SeekBarProgressDrawable clipProgressDrawable = new SeekBarProgressDrawable(progressDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL, getApplicationContext());
        Drawable[] drawables = new Drawable[]{backgroundDrawable, clipProgressDrawable};

        //Create layer drawables with android pre-defined ids
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.progress);

        //Set to seek bar
        progressbar.setProgressDrawable(layerDrawable);


        /*new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(intValue < 100)
                {
                    intValue++;

                    handler.post(new Runnable() {

                        @Override
                        public void run() {


                            progressbar.setProgress(intValue);
                            score.setText(String.valueOf(100-intValue));

                        }
                    });
                    try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            }
        }).start();*/
    }

    public class SeekBarProgressDrawable extends ClipDrawable {

        private Paint mPaint = new Paint();
        private float dy;
        private Rect mRect;


        public SeekBarProgressDrawable(Drawable drawable, int gravity, int orientation, Context ctx) {
            super(drawable, gravity, orientation);
            mPaint.setColor(Color.WHITE);
            dy = ctx.getResources().getDimension(R.dimen.activity_horizontal_margin);
        }

        @Override
        public void draw(Canvas canvas) {
            if (mRect == null) {
                mRect = new Rect(getBounds().left, (int) (getBounds().centerY() - dy / 2), getBounds().right, (int) (getBounds().centerY() + dy / 2));
                setBounds(mRect);
            }

            super.draw(canvas);
        }


        @Override
        public void setAlpha(int i) {
            mPaint.setAlpha(i);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }

    public class SeekBarBackgroundDrawable extends Drawable {

        private Paint mPaint = new Paint();
        private float dy;

        public SeekBarBackgroundDrawable(Context ctx) {
            mPaint.setColor(Color.WHITE);
            dy = ctx.getResources().getDimension(R.dimen.activity_horizontal_margin);
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.drawRect(getBounds().left, getBounds().centerY() - dy / 2, getBounds().right, getBounds().centerY() + dy / 2, mPaint);
        }

        @Override
        public void setAlpha(int i) {
            mPaint.setAlpha(i);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }
}