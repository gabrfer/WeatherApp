package fer.kotlin.weatherapp.ui.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecast
import kotlinx.android.synthetic.main.fragment_enter_1.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [enter_fragment_1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [enter_fragment_1.newInstance] factory method to
 * create an instance of this fragment.
 */
class enter_fragment_1 : Fragment() {

    // TODO: Rename and change types of parameters
    private var actualForecast : DsForecast? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            actualForecast = arguments.getParcelable("forecast")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_enter_1, container, false)

        txtTempMax.text = actualForecast?.temperatureMax
        txtTempMin.text = actualForecast?.temperatureMin

        txtRain.text = actualForecast?.precipIntensity
        txtWindSpeed.text = actualForecast?.windSpeed

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
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
         * @return A new instance of fragment enter_fragment_1.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(actualForecast: DsForecast): enter_fragment_1 {
            val fragment = enter_fragment_1()
            val args = Bundle()
            args.putParcelable(ARG_PARAM1, actualForecast)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
