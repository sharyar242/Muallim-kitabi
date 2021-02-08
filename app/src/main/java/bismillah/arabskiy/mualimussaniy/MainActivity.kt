package bismillah.arabskiy.mualimussaniy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import bismillah.arabskiy.mualimussaniy.ui.about.AboutFragment
import bismillah.arabskiy.mualimussaniy.ui.aytiw.AytiwFragment
import bismillah.arabskiy.mualimussaniy.ui.allDataBase.AllFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    private lateinit var dataBaseFragment: AllFragment
    private lateinit var settings: Settings
    private lateinit var aytiwFragment: AytiwFragment
    private lateinit var aboutFragment: AboutFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settings = Settings(this)

        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = "Navigation Drawer"

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            (R.string.navigation_drawer_open),
            (R.string.navigation_drawer_close)
        ) {
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        dataBaseFragment = AllFragment(1)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, dataBaseFragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }



    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_aytiliw -> {
                aytiwFragment = AytiwFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, aytiwFragment)
                    .commit()
            }
            R.id.nav_oqiliw -> {
                val fragment = AllFragment(2)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_mad -> {
                val fragment = AllFragment(1)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_tashdidler -> {
                val fragment = AllFragment(4)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_oqilatin_xaripler -> {
                val fragment = AllFragment(3)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_wasl_waqf_mad -> {
                val fragment = AllFragment(5)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_idgom_iqlob -> {
                val fragment = AllFragment(6)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_kalimalar -> {
                val fragment = AllFragment(7)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }
            R.id.nav_ayatlar -> {
                val fragment = AllFragment(8)
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit()
            }

            R.id.nav_About -> {
                aboutFragment = AboutFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment,aboutFragment)
                    .commit()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}