package dk.kotlinpack.mvp.service
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.os.ResultReceiver
import android.support.annotation.NonNull
import android.support.v4.app.JobIntentService
import android.util.Log
import dk.kotlinpack.mvp.MainApplication
import dk.kotlinpack.mvp.api.NetWorkAPI
import dk.kotlinpack.mvp.rx.RxThread
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class ServiceUserFCMToken : JobIntentService() {

    @Inject
    lateinit var api: NetWorkAPI
    @Inject
    lateinit var rxThread: RxThread
    private var mResultReceiver: ResultReceiver? = null

    override fun onHandleWork(@NonNull intent: Intent) {
        (this.application as MainApplication).component
            .inject(this)
        Log.d(TAG, "onHandleWork() called with: intent = [$intent]")
        if (intent.action != null) {
            when (intent.action) {
                ACTION_DOWNLOAD -> {
                    mResultReceiver = intent.getParcelableExtra<Parcelable>(RECEIVER) as ResultReceiver?
                    getUserInfo("loginservice")
                    /*for (i in 0..9) {
                        try {
                            Thread.sleep(1000)
                            val bundle = Bundle()
                            bundle.putString("data", String.format("Showing From JobIntent Service %d", i))
                            mResultReceiver!!.send(SHOW_RESULT, bundle)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }

                    }*/
                }
            }
        }
    }

    companion object {
        private val TAG = "Worker"
        val RECEIVER = "receiver"
        val SHOW_RESULT = 123
        internal val DOWNLOAD_JOB_ID = 1000
        private val ACTION_DOWNLOAD = "action.DOWNLOAD_DATA"

        fun enqueueWork(context: Context, workerResultReceiver: WorkerResultReceiver) {
            val intent = Intent(context, ServiceUserFCMToken::class.java)
            intent.putExtra(RECEIVER, workerResultReceiver)
            intent.action = ACTION_DOWNLOAD
            enqueueWork(context, ServiceUserFCMToken::class.java, DOWNLOAD_JOB_ID, intent)
        }
    }

    private val disposable = CompositeDisposable()
    fun getUserInfo(username: String) {
        disposable.add(api.getUser(username)
            .compose(rxThread.applyAsync())
            .doOnTerminate {
            }
            .subscribe({
            }, {
            })
        )
    }


}

