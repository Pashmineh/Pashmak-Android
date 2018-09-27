package app.pashmak.com.pashmak.ui.main.checkin

import android.content.Context
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import app.pashmak.com.pashmak.R


class ScrollAwareFabBehavior : CoordinatorLayout.Behavior<ConstraintLayout> {

    //TODO dont remove these lines
    public constructor() : super()
    public constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    var mDySinceDirectionChange: Int = 0
    var isAnimationRunning = false
    lateinit var textView: TextView
    lateinit var autoTransition: AutoTransition

    override fun onLayoutChild(parent: CoordinatorLayout, child: ConstraintLayout, layoutDirection: Int): Boolean {
        textView = child.findViewById(R.id.txt_manual)
        autoTransition = AutoTransition().also { it.duration = 80 }
        autoTransition.addListener(object : android.transition.Transition.TransitionListener {
            override fun onTransitionEnd(transition: android.transition.Transition?) { isAnimationRunning = false }
            override fun onTransitionResume(transition: android.transition.Transition?) {}
            override fun onTransitionPause(transition: android.transition.Transition?) {}
            override fun onTransitionCancel(transition: android.transition.Transition?) { isAnimationRunning = false }
            override fun onTransitionStart(transition: android.transition.Transition?) { isAnimationRunning = true }
        })

        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (child.visibility != View.VISIBLE || isAnimationRunning)
            return

        if (dy > 0 && mDySinceDirectionChange < 0
                || dy < 0 && mDySinceDirectionChange > 0) {
            mDySinceDirectionChange = 0
        }

        mDySinceDirectionChange += dy

        if (mDySinceDirectionChange > (child.height * 1.5) && textView.visibility == View.VISIBLE) {

            val constraintSet = ConstraintSet()
            constraintSet.clone(child)
            constraintSet.setVisibility(R.id.txt_manual, View.GONE)
            TransitionManager.beginDelayedTransition(child, autoTransition)
            constraintSet.applyTo(child)

        } else if (mDySinceDirectionChange < 0 && textView.visibility == View.GONE) {

            val constraintSet = ConstraintSet()
            constraintSet.clone(child)
            constraintSet.setVisibility(R.id.txt_manual, View.VISIBLE)
            TransitionManager.beginDelayedTransition(child, autoTransition)
            constraintSet.applyTo(child)
        }

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}