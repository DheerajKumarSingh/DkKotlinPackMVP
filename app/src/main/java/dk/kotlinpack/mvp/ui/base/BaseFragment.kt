package dk.kotlinpack.mvp.ui.base
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import dk.kotlinpack.mvp.MainApplication

abstract class BaseFragment : Fragment() {

    protected lateinit var backHandlerInterface: BackHandlerInterface
    abstract val tagText: String
    abstract fun onBackPressed(): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is BackHandlerInterface) {
            throw ClassCastException("Hosting activity must implement BackHandlerInterface")
        } else {
            backHandlerInterface = (activity as BackHandlerInterface?)!!
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity!!.application as MainApplication).component
            .inject(this)
    }

    var progressBar: ProgressBar? = null
    private lateinit var dialog: Dialog

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (null != context) {
            dialog = Dialog(context)
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            val relativeLayout = RelativeLayout(context)
            val layoutParams =
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            relativeLayout.layoutParams = layoutParams

            progressBar = ProgressBar(context)
            val layoutParams_progress =
                RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            layoutParams_progress.addRule(RelativeLayout.CENTER_IN_PARENT)

            val linearlayoutParams_progress =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

            linearlayoutParams_progress.gravity = Gravity.CENTER
            progressBar!!.background = ColorDrawable(Color.TRANSPARENT)
            progressBar!!.layoutParams = layoutParams_progress
            relativeLayout.addView(progressBar)
            dialog.window!!.setContentView(relativeLayout, layoutParams)
            dialog.window!!.setBackgroundDrawable(
                ColorDrawable(Color.TRANSPARENT)
            )
        }
    }

    protected fun showLoading(isCancelable:Boolean) {
        if (!(dialog.isShowing)) {
            dialog.setCancelable(isCancelable)
            dialog.show()

        }

    }

    protected fun hideLoading() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }


    override fun onStart() {
        super.onStart()
        backHandlerInterface.setSelectedFragment(this)
    }

    interface BackHandlerInterface {
        fun setSelectedFragment(baseFragment: BaseFragment)
    }
}