package dk.kotlinpack.mvp.ui.profile
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import dk.kotlinpack.R
import dk.kotlinpack.mvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.profileactivity.*

class MainActivity : BaseActivity() {

    private var t: ActionBarDrawerToggle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dk.kotlinpack.R.layout.profileactivity)
        t = ActionBarDrawerToggle(this, drawerlayout, dk.kotlinpack.R.string.Open, R.string.Close)
        drawerlayout!!.addDrawerListener(t!!)
        t!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        nv.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                val id = item.itemId
                when (id) {
                    R.id.account -> {
                        closeDrawer()
                        showFragment(UserProfileFragment::class.java, null, true)
                        return true
                    }
                    dk.kotlinpack.R.id.settings -> {
                        closeDrawer()
                        showFragment(PartnerProfileFragment::class.java, null, true)
                        return true
                    }
                    dk.kotlinpack.R.id.mycart -> {
                        closeDrawer()
                        showFragment(PartnerProfileFragment::class.java, null, true)
                        return true
                    }
                    else -> {
                        closeDrawer()
                        showFragment(PartnerProfileFragment::class.java, null, true)
                        return true}
                }
            }
        })
        showFragment(UserChatFragment::class.java, null, false)
    }

    private fun closeDrawer() {
        drawerlayout.closeDrawer(Gravity.START)
        t!!.isDrawerIndicatorEnabled = false
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)// show back button
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.v("option","option")
        return if (t!!.onOptionsItemSelected(item)) true
           else
            super.onOptionsItemSelected(item)
    }

/*  manage actionbutton & home button
* @ convert action button to back arrow
* @close drawable layout */

    override fun onBackPressed() {
        super.onBackPressed()
        if(drawerlayout.isDrawerOpen(Gravity.START)){
            drawerlayout.closeDrawer(Gravity.START)
        }
        if (supportFragmentManager.backStackEntryCount > 0) {
            t!!.isDrawerIndicatorEnabled = false
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)// show back button
            drawerlayout!!.addDrawerListener(t!!)
        } else {
            t!!.isDrawerIndicatorEnabled = true
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            drawerlayout!!.addDrawerListener(t!!)
            t!!.syncState()
        }
    }
}
