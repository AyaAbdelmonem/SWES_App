package swes.swes.classes;

/**
 * Created by Sameh on 6/9/2017.
 */

public class NotificationHolder {

    String title ;
    String desc;

    public NotificationHolder(String title, String desc) {
        this.title = title;
        this.desc = desc;
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
}
