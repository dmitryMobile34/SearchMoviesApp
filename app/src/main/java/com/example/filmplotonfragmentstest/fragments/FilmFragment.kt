package com.example.filmplotonfragmentstest.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.filmplotonfragmentstest.R
import com.squareup.picasso.Picasso


class FilmFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film, container, false)

        val posterMain = view.findViewById<ImageView>(R.id.posterMain)
        val posterBack = view.findViewById<ImageView>(R.id.posterBack)
        val filmGenre = view.findViewById<TextView>(R.id.filmGenre)
        val filmDirector = view.findViewById<TextView>(R.id.filmDirector)
        val filmYear = view.findViewById<TextView>(R.id.filmYear)
        val filmPlot = view.findViewById<TextView>(R.id.filmPlot)
        val filmTitle = view.findViewById<TextView>(R.id.filmTitle)
        val imdbRating = view.findViewById<TextView>(R.id.scoreImdb)
        val metaRating = view.findViewById<TextView>(R.id.scoreMetacritic)
        val backButton = view.findViewById<ImageView>(R.id.backButton)

        createPoster(posterMain)
        createBackground(posterBack)
        fillFilmData(filmTitle, filmGenre, filmDirector, filmYear, filmPlot, imdbRating, metaRating)

        backButton.setOnClickListener {
            Navigation
                .findNavController(view)
                .navigate(R.id.action_filmFragment_to_searchFragment2)
        }

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
                    //when user touch down
                }
                MotionEvent.ACTION_UP -> {
                    Navigation
                        .findNavController(view)
                        .navigate(R.id.action_filmFragment_to_descriptionFragment, bundle)
                }
            }
            true
        }




        return view
    }

    fun createBackground(imageView: ImageView) {
        Picasso
            .get()
            .load(arguments?.getString("poster"))
            .fit()
            .centerCrop()
            .into(imageView)

        imageView.setColorFilter(
            ContextCompat.getColor(
            requireContext(),
            R.color.black_tr),
            PorterDuff.Mode.SRC_OVER
        )

    }

    fun createPoster(imageView: ImageView) {
        Picasso
            .get()
            .load(arguments?.getString("poster"))
            .fit()
            .centerCrop()
            .into(imageView)
    }

    fun fillFilmData(
        filmTitle: TextView, filmGenre: TextView, filmDirector: TextView, filmYear: TextView,
        filmPlot: TextView, imdbRating: TextView, metaRating: TextView,
    ) {
        filmTitle.text = arguments?.getString("title")
        filmGenre.text = arguments?.getString("genre")
        filmDirector.text = arguments?.getString("director")
        filmYear.text = arguments?.getString("year")
        filmPlot.text = arguments?.getString("plot")
        imdbRating.text = arguments?.getString("metascore")
        metaRating.text = arguments?.getString("imdbRating")
    }


}