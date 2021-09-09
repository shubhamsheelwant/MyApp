package com.example.myapp

import com.google.gson.annotations.SerializedName

   
data class KeyFormFields (

   @SerializedName("key") var key : String,
   @SerializedName("form_fields") var formFields : List<FormFields>

)