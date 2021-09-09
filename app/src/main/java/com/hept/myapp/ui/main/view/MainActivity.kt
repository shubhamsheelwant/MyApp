package com.hept.myapp.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.FormFields
import com.hept.myapp.R
import com.hept.myapp.data.api.ApiHelper
import com.hept.myapp.data.api.RetrofitBuilder
import com.hept.myapp.ui.base.ViewModelFactory
import com.hept.myapp.ui.main.adapter.MainAdapter
import com.hept.myapp.ui.main.viewmodel.MainViewModel
import com.mindorks.retrofit.coroutines.utils.Status.ERROR
import com.mindorks.retrofit.coroutines.utils.Status.LOADING
import com.mindorks.retrofit.coroutines.utils.Status.SUCCESS
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupObservers()
    }

    /**
     * This method will initialise view model refrence
     */
    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    /**
     * This method is used to set up UI
     */
    private fun setupUI(formFields: List<FormFields>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this, formFields)
        recyclerView.adapter = adapter
    }

    /**
     *  this method will observe the API response.
     */
    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Log.i("shubh", "data: " + resource.data)
                        setupUI(resource.data!!.formData.keyFormFields.first().formFields)

                    }
                    ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Log.i("shubh", "data: " + resource.data)
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    companion object {
        const val LOGO = "logo"
        const val DROP_DOWN = "drop_down"
        const val EDIT_BOX = "edit_box"
        const val DATE = "date"
        const val TEXT_AREA = "text_area"
    }
}
