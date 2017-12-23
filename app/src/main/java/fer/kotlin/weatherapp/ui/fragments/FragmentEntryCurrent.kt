package fer.kotlin.weatherapp.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecast
import fer.kotlin.weatherapp.domain.model.DsForecastCurrently
import fer.kotlin.weatherapp.utils.loadForecastIconUrl
import kotlinx.android.synthetic.main.fragment_entry_current.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentEntryCurrent.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentEntryCurrent.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEntryCurrent : Fragment() {

    // TODO: Rename and change types of parameters
    private var currentLocationName : String = ""
    private var currentForecast : DsForecastCurrently? = null
    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            currentLocationName = arguments.getString("CURRENT_LOCATION_NAME")
            currentForecast = arguments.getParcelable("CURRENT_FORECAST")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_entry_current, container, false)

        //view.txtCity.text = currentLocationName
        //view.txtDate.text = currentForecast!!.time?.DateUnixToDayOfMonth()
        //view.txtDate.text = "13 Diciembre"

        view.txtTempMax.text = """${String.format("%.1f", currentForecast?.temperature?.toDouble())}º"""
        view.imgIconWeather.loadForecastIconUrl(currentForecast?.icon!!, ctx = view.context)

        view.txtRain.text = """${String.format("%.2f", currentForecast?.precipIntensity?.toDouble())} mm"""
        view.txtWindSpeed.text = """${String.format("%.1f", currentForecast?.windSpeed?.toDouble())} kmh"""
        //TODO - Obtener calidad del aire de la API correspondiente
        view.txtAirQuality.text = "25"

        return view
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
         * @return A new instance of fragment FragmentEntryCurrent.
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
