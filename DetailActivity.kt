package com.example.crudappkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudappkotlin.R
import com.example.crudappkotlin.model.UserResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val userObj = intent.getSerializableExtra("USER_OBJ")
        userObj?.let {
            it as UserResponse.User
            txt_name.text = it.firstName+" "+it.lastName
            txt_email.text = it.email
            Picasso.with(this).load(it.avatar).into(img_use)
        }?:let {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show()
        }
    }
}
