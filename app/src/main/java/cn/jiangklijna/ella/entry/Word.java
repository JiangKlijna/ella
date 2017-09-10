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
    @Override
    public String toString() {
        return "Word{" +
                "en='" + en + '\'' +
                ", zh='" + zh + '\'' +
                '}';
    }
    @Generated(hash = 1870981031)
    public Word(String en, String zh) {
        this.en = en;
        this.zh = zh;
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
}
