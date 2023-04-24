package company.withrooms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.withrooms.adapters.DeveloperListAdapter
import company.withrooms.viewmodels.MenuViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = DeveloperListAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        menuViewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        menuViewModel.allDevelopers.observe(this) { developers ->
            developers?.let { adapter.setDevelopers(it) }
        }
    }
}
