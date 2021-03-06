package cn.jiangklijna.ella.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by leil7 on 2017/8/22. ella
 */
@Entity
public class EnglishArticle implements Serializable {

    public static final long serialVersionUID = 536871008;

    @Id
    private Long id;
    private String title;
    private String img;
    private String date;
    private String sound;
    private int type;

    @Override
    public String toString() {
        return "EnglishArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", date='" + date + '\'' +
                ", sound='" + sound + '\'' +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnglishArticle that = (EnglishArticle) o;
        return id == that.id && type == that.type;
    }

    @Generated(hash = 2072746161)
    public EnglishArticle(Long id, String title, String img, String date,
            String sound, int type) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.date = date;
        this.sound = sound;
        this.type = type;
    }

    @Generated(hash = 1122326809)
    public EnglishArticle() {
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getSound() {
        return this.sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
