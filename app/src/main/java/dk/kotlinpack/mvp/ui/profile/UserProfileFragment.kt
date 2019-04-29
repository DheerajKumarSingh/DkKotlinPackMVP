package dk.kotlinpack.mvp.ui.profile
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import dk.kotlinpack.R
import dk.kotlinpack.mvp.MainApplication
import dk.kotlinpack.mvp.model.User
import dk.kotlinpack.mvp.presenter.ProfilePresenter
import dk.kotlinpack.mvp.ui.base.BaseFragment
import javax.inject.Inject

class UserProfileFragment:BaseFragment(),ProfilePresenter.View {


    @Inject
    lateinit var presenter: ProfilePresenter

    override fun getUserProfileSuccess(userInfo: User) {
    }

    override fun getUserInfoError(message: String?) {
        Toast.makeText(activity,""+message,Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
    }

    override fun loading() {
        showLoading(true)
    }

    override fun dismissLoading() {
        hideLoading()
    }

    override val tagText: String
        get() = ""

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.userprofile_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity!!.application as MainApplication).component
            .inject(this)
        presenter.injectView(this)
        presenter.getUserInfo("userlogo")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}