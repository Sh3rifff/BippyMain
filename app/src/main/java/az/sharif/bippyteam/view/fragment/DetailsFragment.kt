package az.sharif.bippyteam.view.fragment

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import az.sharif.bippyteam.R
import az.sharif.bippyteam.model.Article
import az.sharif.bippyteam.service.NewsDatabase
import az.sharif.bippyteam.viewmodel.DetailsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsFragment:Fragment() {

    private var articleUUid=0
    val articleLiveData= MutableLiveData<Article>()

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

        arguments?.let {
            articleUUid=DetailsFragmentArgs.fromBundle(it).uuId

        }

    }


    }


