package fer.kotlin.weatherapp.ui.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.DsForecastDaily
import fer.kotlin.weatherapp.ui.adapters.ForecastDaysAdapter
import kotlinx.android.synthetic.main.fragment_entry_days.*
import kotlinx.android.synthetic.main.fragment_entry_days.view.*
import android.support.v7.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.content_main.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentEntryDays.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentEntryDays.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEntryDays : Fragment() {

    private var daysForecastList: ArrayList<DsForecastDaily>? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            daysForecastList = arguments.getParcelableArrayList<DsForecastDaily>("LIST_DAYS_FORECAST")
            //mParam1 = arguments.getString(ARG_PARAM1)
            //mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_entry_days, container, false)
        val ctx: Context = view.context

        val adapter = ForecastDaysAdapter(daysForecastList!!)

        val recyclerView: RecyclerView = view.forecastList
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapter

        return view
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
        //fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentEntryDays.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FragmentEntryDays {
            val fragment = FragmentEntryDays()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
