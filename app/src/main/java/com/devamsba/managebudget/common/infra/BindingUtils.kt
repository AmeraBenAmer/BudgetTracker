package com.devamsba.managebudget.common.infra

import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.devamsba.managebudget.R
import com.devamsba.managebudget.feat_history.domain.entity.HistoryEntity
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("settext")
fun setTextDouble(textView: TextView, item: HistoryEntity) {
    val context = textView.context
    // if (item != null)

    if (item.type == "Expenses") {
        textView.text = "- ${item.amount}"
        textView.setTextColor(context.resources.getColor(R.color.red_700))
    } else {
        textView.text = "+ ${item.amount}"
        textView.setTextColor(context.resources.getColor(R.color.green_500))

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("setFormattedDate")
fun setFormattedDate(textView: TextView, date :String) {
     var odt = OffsetDateTime.parse(date)
    var dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
    //System.out.println(dtf.format(odt))
        textView.text = "${dtf}"

}


@BindingAdapter("setDrawable")
fun setDrawable(imageView: ImageView, type: String) {

    val context = imageView.context
    if (type == "Incomes") {
        val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_circle_up)
        imageView.setImageDrawable(drawable)
        drawable?.setTint(context.resources.getColor(R.color.green_500))
//            imageView.backgroundTintMode = R.color.red_700

    } else {
        val drawable = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_circle_down)
        imageView.setImageDrawable(drawable)
        drawable?.setTint(context.resources.getColor(R.color.red_700))

    }
}