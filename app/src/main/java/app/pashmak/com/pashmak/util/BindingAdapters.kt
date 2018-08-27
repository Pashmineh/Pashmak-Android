package app.pashmak.com.pashmak.util

import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageView
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
}