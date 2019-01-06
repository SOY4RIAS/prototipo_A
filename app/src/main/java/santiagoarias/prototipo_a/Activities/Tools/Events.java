package santiagoarias.prototipo_a.Activities.Tools;

/**
 * Created by Santiago Arias on 15/11/2017 19:03.
 */

public class Events {
    private String title, desc, date, timeStart, timeFinish;
    private String image;
    public Long dateindicator;
    public Long dateStart;
    public Long dateFinish;

    public Events(String title, String desc, String image, String date, String timeStart, String timeFinish) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.date = date;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        Long f = Long.valueOf(this.date.replaceAll("[^\\d]", ""));
        Long ts = Long.valueOf(this.timeStart.replaceAll("[^\\d]", ""));

        Long tf = Long.valueOf(this.timeFinish.replaceAll("[^\\d]", ""));
        dateindicator = f+ts+tf;
        dateStart = f+ts;
        dateFinish = f+tf;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public Long getDateindicator() {

        return dateindicator;
    }

    public Long getDateStart() {
        return dateStart;
    }

    public Long getDateFinish() {
        return dateFinish;
    }
}
