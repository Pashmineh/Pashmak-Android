package app.pashmak.com.pashmak.ui.main.checkin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import app.pashmak.com.pashmak.R
import androidx.core.view.ViewCompat



class ScrollAwareFabBehavior: CoordinatorLayout.Behavior<ConstraintLayout>
{
    //TODO dont remove these lines
    public constructor() : super()
    public constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    var mDySinceDirectionChange: Int = 0
    lateinit var textView: TextView

    override fun onLayoutChild(parent: CoordinatorLayout, child: ConstraintLayout, layoutDirection: Int): Boolean {
        textView = child.findViewById(R.id.txt_manual)
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: ConstraintLayout, dependency: View): Boolean {
        return true
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: ConstraintLayout, dependency: View): Boolean {
        return true
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: ConstraintLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (dy > 0 && mDySinceDirectionChange < 0
                || dy < 0 && mDySinceDirectionChange > 0) {
            mDySinceDirectionChange = 0
        }

        mDySinceDirectionChange += dy

        if (mDySinceDirectionChange > child.height && textView.visibility == View.VISIBLE) {
            textView.visibility = View.GONE
        } else if (mDySinceDirectionChange < 0 && textView.visibility == View.GONE) {
            textView.visibility = View.VISIBLE

        }
    }
}