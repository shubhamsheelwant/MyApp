package com.example.myapp

import com.google.gson.annotations.SerializedName

   
data class FormResponse (

   @SerializedName("status") var status : Boolean,
   @SerializedName("form_data") var formData : FormData

)