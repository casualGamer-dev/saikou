package ani.gojo.others

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ani.gojo.R


class Xpandable @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    var expanded: Boolean? = true

    init {
        context.withStyledAttributes(attrs, R.styleable.Xpandable) {
            expanded = getBoolean(R.styleable.Xpandable_isExpanded, false)
        }
    }

    override fun onAttachedToWindow() {

        getChildAt(0)!!.setOnClickListener {
            expanded ?: return@setOnClickListener
            val curr = expanded!!
            expanded = null
            if (curr) hideAll() else showAll()
            postDelayed({
                expanded = !curr
            }, 300)
        }

        if(!expanded!!) children.forEach {
            if (it != getChildAt(0)){
                it.visibility = GONE
            }
        }
        super.onAttachedToWindow()
    }


    private fun hideAll() {
        children.forEach {
            if (it != getChildAt(0)){
                ObjectAnimator.ofFloat(it, "scaleY", 1f, 0.5f).setDuration(200).start()
                ObjectAnimator.ofFloat(it, "translationY", 0f, -32f).setDuration(200).start()
                ObjectAnimator.ofFloat(it, "alpha", 1f, 0f).setDuration(200).start()
                it.postDelayed({
                    it.visibility = GONE
                }, 300)
            }

        }
    }

    private fun showAll() {
        children.forEach {
            if (it != getChildAt(0)){
                it.visibility = VISIBLE
                ObjectAnimator.ofFloat(it, "scaleY", 0.5f, 1f).setDuration(200).start()
                ObjectAnimator.ofFloat(it, "translationY", -32f, 0f).setDuration(200).start()
                ObjectAnimator.ofFloat(it, "alpha", 0f, 1f).setDuration(200).start()
            }
        }
    }

}