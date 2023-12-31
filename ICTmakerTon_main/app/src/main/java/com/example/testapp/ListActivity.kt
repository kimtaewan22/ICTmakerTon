package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView



class ListActivity : AppCompatActivity() {

    // on below line we are creating variables for
    // our swipe to refresh layout, recycler view,
    // adapter and list.
    lateinit var courseRV: RecyclerView
    lateinit var courseRVAdapter: CourseRVAdapter
    lateinit var courseList: ArrayList<CourseRVModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // on below line we are initializing our views with their ids.
        courseRV = findViewById(R.id.idRVCourses)

        // on below line we are initializing our list
        courseList = ArrayList()

        // on below line we are initializing our adapter
        courseRVAdapter = CourseRVAdapter(courseList)

        // on below line we are setting adapter to our recycler view.
        courseRV.adapter = courseRVAdapter

        courseRVAdapter.onItemClick = {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("food",it)
            startActivity(intent)
        }

        // on below line we are adding data to our list
        courseList.add(CourseRVModal("마가레트", R.drawable.chocolate,"4 "))
        courseList.add(CourseRVModal("볼펜", R.drawable.pencil,"5"))
        courseList.add(CourseRVModal("마우스", R.drawable.mouse,"2"))
        courseList.add(CourseRVModal("태블릿", R.drawable.tablet,"3"))
        courseList.add(CourseRVModal("스니커즈", R.drawable.snickers,"6"))

        // on below line we are notifying adapter
        // that data has been updated.
        courseRVAdapter.notifyDataSetChanged()

    }

    // calling on create option menu
    // layout to inflate our menu file.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // below line is to get our inflater
        val inflater = menuInflater

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu)

        // below line is to get our menu item.
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.
        val searchView: SearchView = searchItem.getActionView() as SearchView

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<CourseRVModal> = ArrayList()

        // running a for loop to compare elements.
        for (item in courseList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.courseName.toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
//            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            courseRVAdapter.filterList(filteredlist)
        }
    }
}
