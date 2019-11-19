package dk.kotlinpack.mvp.ui.base
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import dk.kotlinpack.mvp.ui.base.BaseFragment.BackHandlerInterface
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.util.Log


abstract class BaseActivity : AppCompatActivity(), BackHandlerInterface {

    private var baseFragment: BaseFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //getSupportFragmentManager().addOnBackStackChangedListener(this)
    }

    override fun setSelectedFragment(baseFragment: BaseFragment) {
        this.baseFragment=baseFragment
    }


    fun <T : Fragment> showFragment(
        fragmentClass: Class<T>, bundle: Bundle?,
        addToBackStack: Boolean
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(
            fragmentClass.simpleName
        )
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance()
                fragment.arguments = bundle
            } catch (e: InstantiationException) {
                throw RuntimeException("New Fragment should have been created", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("New Fragment should have been created", e)
            }
        }

        fragmentTransaction.setCustomAnimations(
            dk.kotlinpack.R.anim.slide_in_from_right,
            dk.kotlinpack.R.anim.slide_out_to_left, android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        fragmentTransaction.replace(
            dk.kotlinpack.R.id.container, fragment!!,
            fragmentClass.simpleName
        )
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()

     //   shouldDisplayHomeUp()
    }




    override fun onSupportNavigateUp(): Boolean {
       if( supportFragmentManager.backStackEntryCount >1){
            getSupportFragmentManager().popBackStack();
        }

        return true;
    }


    override fun onBackPressed() {
        Log.v("base activity onback","base activity onback"+supportFragmentManager.backStackEntryCount)
        super.onBackPressed()
    }


}