package santiagoarias.prototipo_a.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import santiagoarias.prototipo_a.R;
import santiagoarias.prototipo_a.SplashScreen;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Explode explode;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            explode = new Explode();
            explode.setDuration(SplashScreen.TRANSITION_DURATION);
            explode.setInterpolator(new DecelerateInterpolator());
            getWindow().setEnterTransition(explode);
        }
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_upward_black_24dp));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        finishAfterTransition();
                    }
                }
            });

        }
        Bundle bundle = getIntent().getExtras();


        setTitle(bundle.getString("title"));
        TextView desc = (TextView) findViewById(R.id.event_Desc);
        TextView timeS = (TextView) findViewById(R.id.time_Start);
        TextView timeF = (TextView) findViewById(R.id.time_Finish);
        ImageView banner = (ImageView) findViewById(R.id.backgroundE);

        desc.setText(bundle.getString("desc"));
        timeS.setText(bundle.getString("timeStart"));
        timeF.setText(bundle.getString("timeFinish"));
        banner.setImageResource(bundle.getInt("image"));

    }
}
