package com.hept.myapp.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class DatePickerUtil {

    companion object {
        /**
         * This method will show date picker dialog
         */
         fun showDatePicker(context: Context,date: TextView) {
            val datePickerDialog: DatePickerDialog
            val calendar = Calendar.getInstance()
            val currentYear = calendar[Calendar.YEAR] // current year
            val currentMonth = calendar[Calendar.MONTH] // current month
            val currentDay = calendar[Calendar.DAY_OF_MONTH] // current day

            datePickerDialog = DatePickerDialog(
                context,
                { view, year, monthOfYear, dayOfMonth -> // set day of month , month and year value in the edit text

                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    calendar.set(year, monthOfYear, dayOfMonth)
                    val dateString = dateFormat.format(calendar.time)
                    date.setText(
                        dateString
                    )
                }, currentYear, currentMonth, currentDay
            )
            datePickerDialog.show()
        }

    }
}
