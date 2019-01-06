package santiagoarias.prototipo_a.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import santiagoarias.prototipo_a.Activities.Tools.Adapter;
import santiagoarias.prototipo_a.Activities.Tools.Events;
import santiagoarias.prototipo_a.FullscreenActivity;
import santiagoarias.prototipo_a.R;
import santiagoarias.prototipo_a.SplashScreen;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Events> eventos;

    ListView lista;

    public static Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Transición Explode de entrada
//        Fade explode;
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            explode = new Fade();
//            explode.setDuration(SplashScreen.TRANSITION_DURATION);
//            explode.setInterpolator(new DecelerateInterpolator());
//            getWindow().setEnterTransition(explode);
//        }


        Intent intent = new Intent(this, NotificationService.class);
        startService(intent);
        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("PROYECTOS");
        setSupportActionBar(toolbar);

        eventos = new ArrayList();

        String[] array = getResources().getStringArray(R.array.eventsInfo);
        Events events;
        int iterador = 0;

        int num = array.length / 6;
        for (int i = 0; i < num; i++) {
            String titulo, fecha, desc, hInicio, hFin, imagen;
            titulo = array[iterador++];
            fecha = array[iterador++];
            desc = array[iterador++];
            hInicio = array[iterador++];
            hFin = array[iterador++];
            imagen = array[iterador++];
            System.out.println(imagen);
            events = new Events(titulo, desc, imagen, fecha, hInicio, hFin);
            eventos.add(events);

        }

        Collections.sort(eventos, new Comparator<Events>() {
            public int compare(Events o1, Events o2) {
                return o1.dateStart.compareTo(o2.dateStart);
            }
        });
        Adapter adapter = new Adapter(this, eventos);

        lista = (ListView) findViewById(R.id.eventLst);

        lista.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Boolean tag = false;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!tag) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                SplashScreen.activity.finish();
                Toast.makeText(getApplicationContext(), "Presiona Otra vez para salir", Toast.LENGTH_LONG).show();
                tag = true;
                return true;
            }
        } else {
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onClick(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
        startActivity(intent);
    }
}
