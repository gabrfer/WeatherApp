package fer.kotlin.weatherapp.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import fer.kotlin.weatherapp.R
import android.widget.TextView


class PageFragment : Fragment() {
    private var title: String? = null
    private var image: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        image = arguments.getInt("image", 0)
        title = arguments.getString("title")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.page_enter_item, container, false)
        //val tvTitle = view.findViewById(R.id.tvTitle) as TextView
        //tvTitle.text = title

        //val ivIcon = view.findViewById(R.id.ivIcon) as ImageView
        //ivIcon.setImageResource(image)
        return view
    }

    companion object {

        fun newInstance(title: String, resImage: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("image", resImage)
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }
}
