package fer.kotlin.weatherapp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.DsForecastDaily
import fer.kotlin.weatherapp.extensions.UnixToTime
import fer.kotlin.weatherapp.utils.*
import kotlinx.android.synthetic.main.fragment_entry_current_general_view.view.*
import kotlinx.android.synthetic.main.fragment_entry_current_moon_view.view.*
import kotlinx.android.synthetic.main.fragment_entry_current_sun_view.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentEntryDetails.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentEntryDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEntryDetails : Fragment() {

    // TODO: Rename and change types of parameters
    private lateinit var dayForecast : DsForecastDaily
    private lateinit var observableDsForecastDaily: ObservableDsForecastDaily

    private var mListener: OnFragmentInteractionListener? = null

    private val dsForecastCurrentlyChanged = object : Observer {
        override fun update(o: Observable, newValue: Any) {
            // a1 changed! (aka a changed)
            // newValue is the observable int value (it's the same as a1.getValue())
            dayForecast = newValue as DsForecastDaily
            fragmentManager.beginTransaction().detach(this@FragmentEntryDetails).attach(this@FragmentEntryDetails).commit()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            observableDsForecastDaily = arguments.getSerializable("CURRENT_FORECAST") as ObservableDsForecastDaily
            dayForecast = observableDsForecastDaily.getDsForecastDaily().get(index = 0)
            observableDsForecastDaily.addObserver(dsForecastCurrentlyChanged)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_entry_details, container, false)

        loadGeneralData(view)
        loadAirData(view)
        loadSunData(view)
        loadMoonData(view)

        return view
    }

    private fun loadGeneralData(view: View) {
        view.imgIcon.loadForecastIconUrl(dayForecast.icon, ctx = view.context)
        view.txtTempMin.text = """${String.format("%.1f", dayForecast.temperatureMin?.toDouble())}º"""
        view.txtTempMax.text = """${String.format("%.1f", dayForecast.temperatureMax?.toDouble())}º"""
        view.txtPrecip.text = """${String.format("%.2f", dayForecast.precipIntensity?.toDouble())} mm"""
        view.txtPrecipProb.text = """${String.format("%.1f", dayForecast.precipProbability?.toDouble())} %"""
        view.txtVientoVel.text = """${String.format("%.2f", dayForecast.windSpeed?.toDouble())} kmh"""
        view.txtVientoDir.text = """${dayForecast.windBearing} º"""
        view.txtHumedad.text = """${String.format("%.2f", dayForecast.humidity?.toDouble())} mm"""
        view.txtPresion.text = """${String.format("%.2f", dayForecast.pressure?.toDouble())} mm"""
    }

    private fun loadAirData(view: View) {
        //TODO - Obtener calidad del aire de la API correspondiente
    }

    private fun loadSunData(view: View) {
        view.txtUvIndex.text = dayForecast.uvIndex
        view.txtHoraMaxUv.text = dayForecast.uvIndexTime.UnixToTime()
        view.txtSunriseTime.text = dayForecast.sunriseTime.UnixToTime()
        view.txtSunsetTime.text = dayForecast.sunsetTime.UnixToTime()
    }

    private fun loadMoonData(view: View) {
        view.txtFaseLunar.text = dayForecast.moonPhase
        view.imgViewFaseLunar.loadMoonPhaseIconUrl(dayForecast.moonPhase.toDouble(), ctx = view.context)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "forecast"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentEntryDetails.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(actualForecast: DsForecast): FragmentEntryCurrent {
            val fragment = FragmentEntryCurrent()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, actualForecast)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
