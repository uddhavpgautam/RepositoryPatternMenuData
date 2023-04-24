package company.withrooms

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.withrooms.adapters.MenuItemListAdapter
import company.withrooms.viewmodels.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val menuViewModel by viewModels<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MenuItemListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        menuViewModel.allMenuItems.observe(this) { menuItems ->
            menuItems?.let { adapter.setMenuItems(it) }
        }
    }
}
