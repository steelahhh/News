package dev.steelahhh.news.features

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import dev.steelahhh.news.R
import kotlinx.android.synthetic.main.view_article.view.*

@ModelView(defaultLayout = R.layout.view_article)
class ArticleItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr) {

    @ModelProp
    lateinit var article: ArticlePreviewUi

    @CallbackProp
    @Nullable
    lateinit var listener: (article: ArticlePreviewUi) -> Unit

    @AfterPropsSet
    fun propsSet() {
        Glide.with(context)
            .load(article.image)
            .centerCrop()
            .into(image)
        title.text = article.title
        description.text = article.description
        date.text = article.date
        setOnClickListener {
            listener(article)
        }
    }
}
