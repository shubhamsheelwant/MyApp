package com.example.myapp

import com.google.gson.annotations.SerializedName

   
data class FormData (

   @SerializedName("key_form_fields") var keyFormFields : List<KeyFormFields>

)