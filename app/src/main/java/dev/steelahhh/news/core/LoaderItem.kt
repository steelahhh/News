package dev.steelahhh.news.core

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.airbnb.epoxy.ModelView
import dev.steelahhh.news.R

/**
 * A view that is used to display a loader in the list while loading next page
 */
@ModelView(defaultLayout = R.layout.view_loader)
class LoaderItem @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attributeSet, defStyleAttr)
