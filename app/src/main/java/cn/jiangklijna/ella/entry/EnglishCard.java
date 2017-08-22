package cn.jiangklijna.ella.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leil7 on 2017/8/22. ella
 */
@Entity
public class EnglishCard {

    @Id
    private int id;
    private String title;
    private String url;
    private long date;
    private int flag;

    @Generated(hash = 683937254)
    public EnglishCard(int id, String title, String url, long date, int flag) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.date = date;
        this.flag = flag;
    }

    @Generated(hash = 1421194240)
    public EnglishCard() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
