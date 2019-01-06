package santiagoarias.prototipo_a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import santiagoarias.prototipo_a.Activities.MainActivity;

public class SplashScreen extends AppCompatActivity {
    private final int TIME_SPLASH = 2000;
    public static final int TRANSITION_DURATION = 700;
    public static Activity activity;
    Animation anim;
    ImageView l1;
    ImageView l2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Slide fade = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            fade = new Slide(Gravity.LEFT);
            fade.setDuration(TRANSITION_DURATION);
            fade.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(fade);
        }
        activity = this;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent events = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(events, ActivityOptionsCompat.makeSceneTransitionAnimation(SplashScreen.this).toBundle());

            }
        },TIME_SPLASH);
        l1 = (ImageView) findViewById(R.id.logo);
        l2 = (ImageView) findViewById(R.id.welcome);
        anim = AnimationUtils.loadAnimation(this, R.anim.uptown);
        l1.setAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.downtoup);

        l2.setAnimation(anim);
    }



}
