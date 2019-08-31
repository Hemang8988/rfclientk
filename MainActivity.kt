package com.example.crudappkotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudappkotlin.App
import com.example.crudappkotlin.R
import com.example.crudappkotlin.adapter.UserAdapter
import com.example.crudappkotlin.model.UserResponse
import com.example.crudappkotlin.utils.NetworkUtil
import com.example.crudappkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{
    private val TAG = "MainActivity"
    override fun onClick(view: View?) {

        val position = view!!.tag as Int
        when (view!!.id){
            R.id.img_user->{
                Toast.makeText(this,"Image Clicked",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("USER_OBJ", userList[position])
                startActivity(intent)
            }
            else->{
                Toast.makeText(this,"Item clicked",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("USER_OBJ", userList[position])
                startActivity(intent)
            }

        }

    }
    private lateinit var adapter:UserAdapter
    private lateinit var userViewModel:UserViewModel
    val userList = ArrayList<UserResponse.User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        initRecyclerView()
        userViewModel.allUser.observe(this, Observer {
            it?.let {
                progress_circular.visibility = View.GONE
                userList.clear()
                userList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })

        userViewModel.errorObj.observe(this, Observer {
            it?.let {
                Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
            }
        })

        if(App.prefs!!.isInseted){
            Log.d(TAG,"No need to call api fetch from local")
        }else{
            Log.d(TAG,"making api call")
            if(NetworkUtil(this).isInternetAvailable()){
                progress_circular.visibility = View.VISIBLE
                userViewModel.callUserApi()
            }else{
                Toast.makeText(this,"Internet not available",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = UserAdapter(this, userList, this)
        rv_user.adapter = adapter
        rv_user.layoutManager = LinearLayoutManager(this)
    }
}
