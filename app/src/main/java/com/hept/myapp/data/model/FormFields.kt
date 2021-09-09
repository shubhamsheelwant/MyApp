package com.example.myapp

import com.google.gson.annotations.SerializedName

   
data class FormFields (

   @SerializedName("label") var label : String,
   @SerializedName("text_ans") var textAns : String,
   @SerializedName("required") var required : Int,
   @SerializedName("key") var key : String,
   @SerializedName("field_type") var fieldType : String,
   @SerializedName("drop_down_options") var dropDownOptions : List<DropDownOptions>
)

class DropDownOptions (
   @SerializedName("id") val id:Int,
   @SerializedName("view_text") val viewText: String)
