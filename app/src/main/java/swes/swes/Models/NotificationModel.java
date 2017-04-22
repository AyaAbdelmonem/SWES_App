package swes.swes.Models;

/**
 * Created by Sameh on 4/21/2017.
 */

public class NotificationModel {
    String Tittle;
    String Details;

    public NotificationModel(String tittle,String details)
    {
        this.Tittle=tittle;
        this.Details=details;

    }

    public NotificationModel()
    {

    }
    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
