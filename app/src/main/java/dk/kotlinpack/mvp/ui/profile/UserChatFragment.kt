package dk.kotlinpack.mvp.ui.profile
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.kotlinpack.R
import dk.kotlinpack.mvp.ui.base.BaseFragment

import android.content.ContentValues.TAG
import android.view.MenuItem


class UserChatFragment:BaseFragment() {


    override val tagText: String
        get() = UserChatFragment.javaClass.name

    companion object {
        fun newInstance(): Fragment
        {
         var userChatFragment=   UserChatFragment ()
            return userChatFragment
        }
    }


    override fun onBackPressed(): Boolean {

        Log.v("onback click","onback click");
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(dk.kotlinpack.R.layout.userfragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      var activity=  activity as MainActivity
        activity.showActionBarButton();

    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
               activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


