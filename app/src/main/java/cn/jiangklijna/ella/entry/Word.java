package cn.jiangklijna.ella.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by leil7 on 2017/8/23. ella
 */
@Entity
public class Word {

    @Id
    private String en;
    private String zh;
    private String us;
    private String uk;
    @Override
    public String toString() {
        return "Word{" +
                "en='" + en + '\'' +
                ", zh='" + zh + '\'' +
                ", us='" + us + '\'' +
                ", uk='" + uk + '\'' +
                '}';
    }
    @Generated(hash = 2062993348)
    public Word(String en, String zh, String us, String uk) {
        this.en = en;
        this.zh = zh;
        this.us = us;
        this.uk = uk;
    }

    @Generated(hash = 3342184)
    public Word() {
    }

    public String getEn() {
        return this.en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getZh() {
        return this.zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getUs() {
        return this.us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getUk() {
        return this.uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }
}
