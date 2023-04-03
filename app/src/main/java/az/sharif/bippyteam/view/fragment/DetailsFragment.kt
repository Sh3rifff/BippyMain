package az.sharif.bippyteam.view.fragment

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import az.sharif.bippyteam.R
import az.sharif.bippyteam.util.downloadFromUrl
import az.sharif.bippyteam.util.placeHolderProgressBar
import org.w3c.dom.Text

class DetailsFragment:Fragment() {

    private var articleUUid=0
    private lateinit var title:TextView
    private lateinit var image: ImageView
    private lateinit var sourceName:TextView
    private lateinit var date:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         title=view.findViewById<TextView>(R.id.tvDesc)
         image=view.findViewById<ImageView>(R.id.imageView)
         sourceName= view.findViewById<TextView>(R.id.tvSourceDetails)
         date=view.findViewById<TextView>(R.id.tvDate)

        arguments?.let {
            articleUUid=DetailsFragmentArgs.fromBundle(it).uuId
            title.setText(DetailsFragmentArgs.fromBundle(it).title)
            sourceName.setText(DetailsFragmentArgs.fromBundle(it).sourceName)
            date.setText(DetailsFragmentArgs.fromBundle(it).date)

            image.downloadFromUrl(DetailsFragmentArgs.fromBundle(it).urlToImage,
                placeHolderProgressBar(view.context)
            )

            //Toast.makeText(context,"Deneme+${DetailsFragmentArgs.fromBundle(it).title}",Toast.LENGTH_LONG).show()

        }




    }



}