package app.pashmak.com.pashmak.util

import android.content.res.ColorStateList
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import app.pashmak.com.pashmak.app.GlideApp
import app.pashmak.com.pashmak.ui.customviews.CircleImageView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("bindValue")
    fun bindEditTextValue(editText: EditText, field: MutableLiveData<String>) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                field.value = s.toString()
            }
        })

    }

    @JvmStatic
    @BindingAdapter("srcVector")
    fun setVectorResource(view: ImageView, @DrawableRes drawable: Int) {
        view.setImageResource(drawable)
    }

    @JvmStatic
    @BindingAdapter("isTransparent")
    fun isTransparent(view: View, value: Boolean) {
        if (value)
            view.alpha = 0.2f
        else
            view.alpha = 1f
    }

    @JvmStatic
    @BindingAdapter("changeVisibility")
    fun changeVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter(value = arrayOf("imageUrl", "placeholder"), requireAll = false)
    fun loadImageUrl(imageView: AppCompatImageView, url: String, placeHolder: Drawable) {
        if (url.isNotEmpty())
            GlideApp
                    .with(imageView)
                    .load(url)
                    .placeholder(placeHolder)
                    .into(imageView)
        else
            GlideApp.with(imageView)
                    .load(placeHolder)
                    .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("changeBorderColor")
    fun changeBorderColor(imageView: CircleImageView, color: Int) {
        imageView.borderColor = color
    }


    @JvmStatic
    @BindingAdapter("isSelectedView")
    fun changeViewSelectionState(view: ViewGroup, isSelected: Boolean) {
        view.isSelected = isSelected
    }

    @JvmStatic
    @BindingAdapter("clipLevel")
    fun setClipLevel(view: View, level: Int) {
        if(view.background is ClipDrawable)
            view.background.level = level
    }

    @JvmStatic
    @BindingAdapter("backgroundColorRes")
    fun setBackgroundColor(view: View, colorRes: Int) {
        view.setBackgroundResource(colorRes)
    }

    @JvmStatic
    @BindingAdapter("textColorRes")
    fun setTextColorRes(view: TextView, colorRes: Int) {
        view.setTextColor(ContextCompat.getColor(view.context, colorRes))
    }

    @JvmStatic
    @BindingAdapter("tintColorRes")
    fun setImageTint(view: ImageView, colorRes: Int) {
        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(ContextCompat.getColor(view.context, colorRes)))
    }
}