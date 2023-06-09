package company.withrooms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import company.withrooms.R
import company.withrooms.data.MenuItem


class MenuItemListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<MenuItemListAdapter.MenuItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var menuItems = emptyList<MenuItem>() // Cached copy

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return MenuItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val current = menuItems[position]
        holder.menuItemView.text = current.name
    }

    internal fun setMenuItems(menuItems: List<MenuItem>) {
        this.menuItems = menuItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = menuItems.size
}


