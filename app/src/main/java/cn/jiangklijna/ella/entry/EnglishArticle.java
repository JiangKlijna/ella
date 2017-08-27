package cn.jiangklijna.ella.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leil7 on 2017/8/22. ella
 */
@Entity
public class EnglishArticle {

    @Id
    private int id;
    private String title;
    private String url;
    private String img;
    private String date;
    private int flag;
    @Generated(hash = 2078708353)
    public EnglishArticle(int id, String title, String url, String img, String date,
                          int flag) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.img = img;
        this.date = date;
        this.flag = flag;
    }
    @Generated(hash = 1421194240)
    public EnglishArticle() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getFlag() {
        return this.flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }

}
