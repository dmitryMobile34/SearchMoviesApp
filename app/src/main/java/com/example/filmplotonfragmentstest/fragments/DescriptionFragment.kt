package com.example.filmplotonfragmentstest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.filmplotonfragmentstest.R
import com.squareup.picasso.Picasso

class DescriptionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)

        val descFilmActors = view.findViewById<TextView>(R.id.descFilmActors)
        val descFilmAwards = view.findViewById<TextView>(R.id.descFilmAwards)
        val descFilmUSABoxOffice = view.findViewById<TextView>(R.id.descFilmUSABoxOffice)
        val descFilmCountry = view.findViewById<TextView>(R.id.descFilmCountry)
        val descFilmRealised = view.findViewById<TextView>(R.id.descFilmRealised)
        val descFilmRuntime = view.findViewById<TextView>(R.id.descFilmRuntime)
        val descFilmWriters = view.findViewById<TextView>(R.id.descFilmWriters)
        val descGenre = view.findViewById<TextView>(R.id.descGenre)
        val descDirector = view.findViewById<TextView>(R.id.descDirector)
        val descYear = view.findViewById<TextView>(R.id.descYear)
        val descPoster = view.findViewById<ImageView>(R.id.descPoster)

        fillDescData(
            descFilmActors, descFilmAwards, descFilmUSABoxOffice, descFilmCountry,
            descFilmRealised, descFilmRuntime, descFilmWriters, descGenre, descDirector,
            descYear, descPoster
        )

        val bundle = Bundle()
        bundle.putString("Actors", arguments?.getString("Actors"))
        bundle.putString("Awards", arguments?.getString("Awards"))
        bundle.putString("BoxOffice", arguments?.getString("BoxOffice"))
        bundle.putString("Country", arguments?.getString("Country"))
        bundle.putString("Released", arguments?.getString("Released"))
        bundle.putString("Runtime", arguments?.getString("Runtime"))
        bundle.putString("Writer", arguments?.getString("Writer"))
        bundle.putString("Poster", arguments?.getString("poster"))
        bundle.putString("Genre", arguments?.getString("genre"))
        bundle.putString("Director", arguments?.getString("director"))
        bundle.putString("Year", arguments?.getString("year"))

        view.isClickable = false

        view.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    Navigation
                        .findNavController(view)
                        .navigate(R.id.action_descriptionFragment_to_filmFragment, bundle)
                }
                MotionEvent.ACTION_UP -> {

                }
            }
            true
        }

        return view
    }

    fun fillDescData(descFilmActors: TextView, descFilmAwards: TextView, descFilmUSABoxOffice: TextView,
                     descFilmCountry: TextView, descFilmRealised: TextView, descFilmRuntime: TextView,
                     descFilmWriters: TextView, descGenre: TextView, descDirector: TextView,
                     descYear: TextView, descPoster: ImageView) {
        descFilmActors.text = arguments?.getString("Actors")
        descFilmAwards.text = arguments?.getString("Awards")
        descFilmUSABoxOffice.text = arguments?.getString("BoxOffice")
        descFilmCountry.text = arguments?.getString("Country")
        descFilmRealised.text = arguments?.getString("Released")
        descFilmRuntime.text = arguments?.getString("Runtime")
        descFilmWriters.text = arguments?.getString("Writer")
        descGenre.text = arguments?.getString("Genre")
        descDirector.text = arguments?.getString("Director")
        descYear.text = arguments?.getString("Year")

        Picasso
            .get()
            .load(arguments?.getString("Poster"))
            .fit()
            .centerCrop()
            .into(descPoster)
    }
}