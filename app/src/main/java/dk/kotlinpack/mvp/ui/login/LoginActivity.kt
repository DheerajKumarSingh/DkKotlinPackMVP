package dk.kotlinpack.mvp.ui.login
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import dk.kotlinpack.R
import dk.kotlinpack.mvp.MainApplication
import dk.kotlinpack.mvp.model.User
import dk.kotlinpack.mvp.presenter.LoginUserPresenter
import dk.kotlinpack.mvp.service.ServiceUserFCMToken
import dk.kotlinpack.mvp.service.WorkerResultReceiver
import dk.kotlinpack.mvp.ui.base.BaseActivity
import javax.inject.Inject

class LoginActivity: BaseActivity(),LoginUserPresenter.View, WorkerResultReceiver.Receiver {


    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {

    }

    override fun getUserInfoSuccess(userInfo: User) {
    }

    override fun getUserInfoError(message: String?) {
        Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
    }

    override fun loading() {
    }

    override fun dismissLoading() {
    }

    lateinit var workerReciever:WorkerResultReceiver

    @Inject
    lateinit var presenter: LoginUserPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as MainApplication).component
            .inject(this)
        presenter.injectView(this)
        setContentView(R.layout.activity_login)
         workerReciever=WorkerResultReceiver(Handler())
        workerReciever.setReceiver(this)

    }
     fun clickUser(view: View){
      //  presenter.getUserInfo("userLogin")
         ServiceUserFCMToken.enqueueWork(this, workerReciever)
    }
}