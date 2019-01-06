package santiagoarias.prototipo_a.Activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import santiagoarias.prototipo_a.Activities.Tools.Events;
import santiagoarias.prototipo_a.R;

public class NotificationService extends Service {

    int NOTIFICATION_ID = 602497;
    Calendar currentDate;

    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    List<Events> eventos;

    @Override
    public void onCreate() {
        System.out.println("STARTING SERVICE");

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
            events = new Events(titulo, desc, imagen, fecha, hInicio, hFin);
            eventos.add(events);

        }

        Collections.sort(eventos, new Comparator<Events>() {
            public int compare(Events o1, Events o2) {
                return o1.dateindicator.compareTo(o2.dateindicator);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("Servicios Iniciado");


        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {

                        Thread.sleep(480000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    int a = (int) Math.floor(Math.random() * eventos.size());
                    System.out.println(eventos.get(a).getTitle() + a);
                    notificar(eventos.get(a));
                }
            }
        }).start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        System.out.println("Servicios Destruido");
    }


    public void notificar( Events events) {

        NotificationCompat.Builder mBuilder = null;
        NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext()
                .getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            intent.putExtra("title", events.getTitle());
            intent.putExtra("desc", events.getDesc());
            intent.putExtra("timeStart", events.getTimeStart());
            intent.putExtra("timeFinish", events.getTimeFinish());
            intent.putExtra("date", events.getDate());
            intent.putExtra("image", getApplicationContext().getResources().getIdentifier(events
                    .getImage(), "drawable", getApplicationContext().getPackageName()));

        }

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), (int) Math.floor(Math.random()*99999), intent, PendingIntent.FLAG_ONE_SHOT);
        int icono = R.mipmap.ic_launcher;


            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(icono)
                        .setContentTitle(events.getTitle())
                        .setContentText("¡Te recomendamos que lo visites!")
                        .setVibrate(new long[]{100, 250, 100, 500})
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setFullScreenIntent(null,true)
                        .setAutoCancel(true);
            else
                mBuilder = new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(icono)
                        .setContentTitle(events.getTitle())
                        .setContentText("¡Te recomendamos que lo visites!")
                        .setVibrate(new long[]{100, 250, 100, 500})
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setTimeoutAfter(60000)
                        .setAutoCancel(true);




        mNotifyMgr.notify("feriaTag"+NOTIFICATION_ID, NOTIFICATION_ID++, mBuilder.build());

    }

}
