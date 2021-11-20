package ua.com.jetpack.reddit_app.extentions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


val IMAGE_CORNER_RADIUS: Float = 2F

fun ImageView.loadPostImage(link: String) {
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .transform(
                        FitCenter(), GranularRoundedCorners(
                            IMAGE_CORNER_RADIUS.dpToPx(),
                            IMAGE_CORNER_RADIUS.dpToPx(),
                            IMAGE_CORNER_RADIUS.dpToPx(),
                            IMAGE_CORNER_RADIUS.dpToPx()
                        )
                    )
            )
            .load(link)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}