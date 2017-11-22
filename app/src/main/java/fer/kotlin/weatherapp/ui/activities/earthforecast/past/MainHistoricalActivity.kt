package fer.kotlin.weatherapp.ui.activities.earthforecast.past

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fer.kotlin.weatherapp.R
import kotlinx.android.synthetic.main.activity_main_historical.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.app.DatePickerDialog
import android.content.Context
import android.widget.*
import fer.kotlin.weatherapp.domain.datasource.DsForecastProvider
import fer.kotlin.weatherapp.domain.model.*
import fer.kotlin.weatherapp.utils.*
import org.jetbrains.anko.startActivity
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import android.view.inputmethod.InputMethodManager


class MainHistoricalActivity : AppCompatActivity(), AlertDialogOkListener {

    lateinit var municipiosAll: List<Herria>
    var municipios: List<Herria> = emptyList()
    var provincias: List<String> = emptyList()
    var selectedDate: IntArray = IntArray(3)
    lateinit var selectedMunicipio: Herria


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_historical)

        //Date From
        editTextDate.setOnClickListener {
            showDatePickerDialog(editTextDate)
        }


        doAsync {

            municipiosAll = DsForecastProvider().requestHerriak(HerriaKFilter())
            provincias = municipiosAll.distinctBy { it.probintzia }.map { it.probintzia }.sortedBy { it }

            uiThread {

                btnProv.setOnClickListener {
                    val alertDialogProv = AlertDialogRadio(this@MainHistoricalActivity, "Selecciona provincia" , provincias, ListObjectType.PROVINCIA)
                    alertDialogProv.showAlertDialogRadio()
                }

                btnMunicipio.setOnClickListener {
                    val alertDialogStation = AlertDialogRadio(this@MainHistoricalActivity, "Selecciona municipio", municipios, ListObjectType.MUNICIPIO)
                    alertDialogStation.showAlertDialogRadio()
                }

                val municipiosAutoAdapter = ArrayAdapter<Herria>(this@MainHistoricalActivity, android.R.layout.select_dialog_item, municipiosAll)
                txtMunicipio.threshold = 1 //will start working from first character
                txtMunicipio.setAdapter(municipiosAutoAdapter)

                txtMunicipio.onItemClickListener = OnItemClickListener {
                    arg0, arg1, position, arg3 ->
                    selectedMunicipio = municipiosAll[position]

                    val input = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    input.hideSoftInputFromWindow(arg1.applicationWindowToken, 0)
                    //input.hideSoftInputFromWindow(arg1.windowToken, 0)
                }

                btnSearch.setOnClickListener {
                    startActivity<DetailHistoricalActivity>(DetailHistoricalActivity.LATITUD to selectedMunicipio.latitudea,
                                                            DetailHistoricalActivity.LONGITUD to selectedMunicipio.longitudea,
                                                            DetailHistoricalActivity.FECHA to selectedDate)
                }
            }
        }
    }

    fun showDatePickerDialog(editText: Button) {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            // +1 because january is zero
            val selected = day.toString() + " / " + (month + 1) + " / " + year
            editText.text = selected

            if (editText.id == R.id.editTextDate) {
                selectedDate = intArrayOf(year, month, day)
            }

        })
        newFragment.show(fragmentManager, "datePicker")
    }


    override fun AlertDialogOK(index: Int, listObjectType: ListObjectType) {
        when (listObjectType) {

            ListObjectType.PROVINCIA -> {
                btnProv.text = provincias[index]

                municipios = municipiosAll.filter {
                    it.probintzia == provincias[index]
                }

                btnMunicipio.text = "Selecciona municipio"
            }

            ListObjectType.MUNICIPIO -> {
                btnMunicipio.text = municipios[index].izena
                selectedMunicipio = municipios[index]
            }
        }
    }

}