package santiagoarias.prototipo_a.Activities.Tools;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import santiagoarias.prototipo_a.Activities.EventActivity;
import santiagoarias.prototipo_a.R;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;

/**
 * Created by Santiago Arias on 14/11/2017 17:11.
 */

public class Adapter extends ArrayAdapter<Events> {

    Activity interfaz;
    public Adapter(Activity context, List<Events> objects) {
        super(context, 0, objects);
        this.interfaz = context;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_thumbnail,parent,false);

        TextView title =(TextView) convertView.findViewById(R.id.eventTitle);
        TextView date =(TextView) convertView.findViewById(R.id.eventDate);
        TextView timeStart =(TextView) convertView.findViewById(R.id.timeStart);
        TextView timeFinish =(TextView) convertView.findViewById(R.id.timeFinish);

        title.setText((CharSequence) getItem(position).getTitle());
        date.setText((CharSequence) getItem(position).getDate());
        timeStart.setText((CharSequence) getItem(position).getTimeStart());
        timeFinish.setText((CharSequence) getItem(position).getTimeFinish());


      ImageView image = (ImageView) convertView.findViewById(R.id.eventImg);
      final int resource = interfaz.getResources()
                    .getIdentifier(getItem(position)
                                    .getImage(),"drawable",interfaz.getPackageName());


        Picasso.with(getContext()).load(resource).fit().into(image);





        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),EventActivity.class);
                intent.putExtra("title", getItem(position).getTitle());
                intent.putExtra("desc", getItem(position).getDesc());
                intent.putExtra("timeStart", getItem(position).getTimeStart());
                intent.putExtra("timeFinish", getItem(position).getTimeFinish());
                intent.putExtra("date", getItem(position).getDate());
                intent.putExtra("image",resource);

                getContext().startActivity(intent, makeSceneTransitionAnimation(interfaz).toBundle());

            }
        });

        return convertView;
    }
}
