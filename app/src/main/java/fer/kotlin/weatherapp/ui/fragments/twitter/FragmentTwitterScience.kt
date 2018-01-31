package fer.kotlin.weatherapp.ui.fragments.twitter

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fer.kotlin.weatherapp.R
import fer.kotlin.weatherapp.domain.model.TweetModelList
import fer.kotlin.weatherapp.ui.adapters.TweetListAdapter
import kotlinx.android.synthetic.main.fragment_twitter_meteo.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FragmentTwitterScience.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FragmentTwitterScience.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTwitterScience : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null
    private var tweetList: TweetModelList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            //daysForecastList = arguments.getParcelableArrayList<DsForecastDaily>("LIST_DAYS_FORECAST")
            tweetList = arguments.getParcelable<TweetModelList>("TWEETS_SCIENCE") as TweetModelList
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view: View = inflater!!.inflate(R.layout.fragment_twitter_meteo, container, false)
        val ctx: Context = view.context

        val adapter = TweetListAdapter(tweetList!!)

        val recyclerView: RecyclerView = view.tweetsList
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
        fun onFragmentInteraction(uri: Uri)
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
         * @return A new instance of fragment FragmentTwitterMeteo.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): FragmentTwitterMeteo {
            val fragment = FragmentTwitterMeteo()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
