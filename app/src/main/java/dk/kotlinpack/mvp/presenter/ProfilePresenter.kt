package dk.kotlinpack.mvp.presenter
import dk.kotlinpack.mvp.api.NetWorkAPI
import dk.kotlinpack.mvp.model.User
import dk.kotlinpack.mvp.rx.RxThread
import io.reactivex.disposables.CompositeDisposable

open class ProfilePresenter @javax.inject.Inject constructor(private val api: NetWorkAPI,
                                                             private val rxThread: RxThread
) {
    private lateinit var view: View
    private val disposable = CompositeDisposable()

    interface View : BaseView {
        fun getUserProfileSuccess(userInfo: User)
        fun getUserInfoError(message: String?)
        fun hideKeyboard()
    }

    fun injectView(view: View) {
        this.view = view
    }

    fun getUserInfo(username: String) {
        view.loading()
        disposable.add(api.getUser(username)
            .compose(rxThread.applyAsync())
            .doOnTerminate {
                view.dismissLoading()
                view.hideKeyboard()
            }
            .subscribe({
                view.getUserProfileSuccess(it)
            }, {
                view.getUserInfoError(it.message)
            })
        )
    }

    fun onDestroy() {
        disposable.clear()
    }

}