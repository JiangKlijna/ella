package cn.jiangklijna.ella.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.net.Uri
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.ui.activity.ActAudio
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequestBuilder


/**
 * Created by jiangKlijna on 8/13/2017.
 */
class EnglishCardView(context: Context) : LinearLayout(context, null, 0), View.OnClickListener {

    val title: TextView
    val img: SimpleDraweeView
    var a: EnglishArticle? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_englishcard, this, true)
        title = f<TextView>(R.id.view_ec_title)
        img = f<SimpleDraweeView>(R.id.view_ec_img)
        (title.parent as View).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(context, ActAudio::class.java)
        intent.putExtra(EnglishArticle::class.java.simpleName, a)
        context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, img, img.transitionName).toBundle())
//        context.startActivity(intent)
    }

    fun set(a: EnglishArticle) {
        this.a = a
        title.text = a.title
        img.setImageURI(a.img)
        getFrescoCacheBitmap(a.img)
        setColor()
    }

    fun setColor() {
        if (s != null) {
            title.setTextColor(s!!.bodyTextColor)
            val bg = (title.parent as LinearLayout).background as RippleDrawable
//            bg.setColor(ColorStateList.valueOf(s!!.rgb))
            val rd = bg.getDrawable(0) as GradientDrawable?
            rd?.setColor(s!!.rgb)
        }
    }

    var tag = false
    fun getFrescoCacheBitmap(uri: String) {
        if (tag) return
        tag = true
        val imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(uri))
                .setProgressiveRenderingEnabled(true)
                .build()
        val dataSource = Fresco.getImagePipeline().fetchDecodedImage(imageRequest, context)
        dataSource.subscribe(object : BaseBitmapDataSubscriber() {
            public override fun onNewResultImpl(bitmap: Bitmap?) {
                if (bitmap == null) return
                Palette.from(bitmap).generate {
                    if (it != null) s = getColorByPalette(it)
                    setColor()
                }
            }

            override fun onFailureImpl(p0: DataSource<CloseableReference<CloseableImage>>?) {}
        }, CallerThreadExecutor.getInstance())
    }

    var s: Palette.Swatch? = null
    fun getColorByPalette(p: Palette): Palette.Swatch? {
        if (p.lightMutedSwatch != null) return p.lightMutedSwatch
        if (p.lightVibrantSwatch != null) return p.lightVibrantSwatch
        if (p.mutedSwatch != null) return p.mutedSwatch
        if (p.vibrantSwatch != null) return p.vibrantSwatch
        if (p.darkVibrantSwatch != null) return p.darkVibrantSwatch
        if (p.darkMutedSwatch != null) return p.darkMutedSwatch
        for (s in p.swatches) if (s != null) return s
        return null
    }

}