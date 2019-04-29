package dk.kotlinpack.mvp.service
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver

 class WorkerResultReceiver(handler: Handler) : ResultReceiver(handler) {
    private var mReceiver: Receiver? = null

    fun setReceiver(receiver: Receiver) {
        mReceiver = receiver
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
        if (mReceiver != null) {
            mReceiver!!.onReceiveResult(resultCode, resultData)
        }
    }
    interface Receiver {
        fun onReceiveResult(resultCode: Int, resultData: Bundle?)
    }

    companion object {
        private val TAG = "WorkerResultReceiver"
    }
}