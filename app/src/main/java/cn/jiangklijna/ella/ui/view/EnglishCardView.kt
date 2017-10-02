package cn.jiangklijna.ella.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.graphics.Palette
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.jiangklijna.ella.R
import cn.jiangklijna.ella.common.f
import cn.jiangklijna.ella.entry.EnglishArticle
import cn.jiangklijna.ella.ui.activity.ActPlayer
import com.facebook.common.executors.CallerThreadExecutor
import com.facebook.common.references.CloseableReference
import com.facebook.datasource.DataSource
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber
import com.facebook.imagepipeline.image.CloseableImage
import com.facebook.imagepipeline.request.ImageRequest

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
		val intent = Intent(context, ActPlayer::class.java)
		intent.putExtra(EnglishArticle::class.java.simpleName, a)
//      context.startActivity(intent)
		context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(
				context as Activity, img, img.transitionName).toBundle())
	}

	fun set(a: EnglishArticle) {
		this.a = a
		title.text = a.title
		img.setImageURI(a.img)
		getFrescoCacheBitmap(a.img)
	}

	fun getFrescoCacheBitmap(uri: String) {
		val dataSource = Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(uri), context)
		dataSource.subscribe(bitmapSubscriber, CallerThreadExecutor.getInstance())
	}

	val bitmapSubscriber = object : BaseBitmapDataSubscriber() {
		public override fun onNewResultImpl(bitmap: Bitmap?) {
			if (bitmap == null) return
			Palette.from(bitmap).generate {
				if (it == null) return@generate
				val s = getColorByPalette(it)
				if (s == null) return@generate

				title.setTextColor(s.bodyTextColor)
				val bg = (title.parent as LinearLayout).background as RippleDrawable
//            	bg.setColor(ColorStateList.valueOf(s!!.rgb))
				val rd = bg.getDrawable(0) as GradientDrawable?
				rd?.setColor(s.rgb)
			}
		}

		override fun onFailureImpl(p0: DataSource<CloseableReference<CloseableImage>>?) {}
	}

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