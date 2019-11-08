package dev.steelahhh.news.core

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import dev.steelahhh.news.R

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val title: TextView
    private val button: MaterialButton

    var retryVisible: Boolean = true
        set(value) {
            field = value
            button.isVisible = field
        }

    @StringRes
    var textRes: Int = -1
        set(value) {
            field = value
            if (textRes != -1) {
                text = context.getString(value)
            }
        }

    var text: String = ""
        set(value) {
            field = value
            title.text = value
        }

    var onRetryClick: (() -> Unit)? = null
        set(value) {
            field = value
            button.setOnClickListener {
                field?.invoke()
            }
        }

    init {
        inflate(context, R.layout.view_error, this)

        title = findViewById(R.id.try_again_text)
        button = findViewById(R.id.try_again_button)
    }
}
