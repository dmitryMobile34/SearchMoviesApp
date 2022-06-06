package com.example.filmplotonfragmentstest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.filmplotonfragmentstest.R
import com.example.filmplotonfragmentstest.api.RetrofitInterface
import com.example.filmplotonfragmentstest.datamodel.Film
import com.example.filmplotonfragmentstest.interfaces.ResultListener
import com.example.filmplotonfragmentstest.viewmodel.CommonViewModel
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment () : Fragment() {

    private lateinit var viewModel: CommonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        viewModel = ViewModelProvider(requireActivity())
            .get(CommonViewModel::class.java) // init view model

        val searchButton: Button = view.findViewById(R.id.searchButton)
        val searchEditText: EditText = view.findViewById(R.id.searchEditText)

        val shapeAppearanceModel = ShapeAppearanceModel()
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, 8f)
            .build()

        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)

        shapeDrawable.setStroke(2.0f, ContextCompat.getColor(requireContext(), R.color.black))
        shapeDrawable.setFillColor(ContextCompat.getColorStateList(requireContext(), R.color.transparent))
        ViewCompat.setBackground(searchEditText, shapeDrawable)

        searchButton.setOnClickListener {
            searchMovies(searchEditText, object: ResultListener<Film> {
                override fun onSuccess(successModel: Film) {
//                    val bundle = Bundle()
//                    bundle.putString("poster", successModel.Poster)
//                    bundle.putString("title", successModel.Title)
//                    bundle.putString("year", successModel.Year)
//                    bundle.putString("genre", successModel.Genre)
//                    bundle.putString("director", successModel.Director)
//                    bundle.putString("plot", successModel.Plot)
//                    bundle.putString("metascore", successModel.Metascore)
//                    bundle.putString("imdbRating", successModel.imdbRating)
//
//                    //for ML
//                    bundle.putString("Actors", successModel.Actors)
//                    bundle.putString("Awards", successModel.Awards)
//                    bundle.putString("BoxOffice", successModel.BoxOffice)
//                    bundle.putString("Country", successModel.Country)
//                    bundle.putString("Released", successModel.Released)
//                    bundle.putString("Runtime", successModel.Runtime)
//                    bundle.putString("Writer", successModel.Writer)

                    viewModel

                    viewModel.list.observe(viewLifecycleOwner, {
                        listView.adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            it
                        )
                    })

                    Navigation
                        .findNavController(view)
                        .navigate(R.id.action_searchFragment2_to_filmFragment
//                            bundle
                        )

                }

                override fun onFail(any: String?) { }

                override fun onError(e: Throwable?) { }

            })
        }

        return view
    }

     fun searchMovies(editText: EditText, resultListener: ResultListener<Film>) {
         val apiInterface = RetrofitInterface.create().getMovies(editText.text.toString())
         apiInterface.enqueue(object: Callback<Film> {
             override fun onResponse(call: Call<Film>, response: Response<Film>) {
                 var callbackResult = response.body()!!
                 resultListener.onSuccess(callbackResult)
            }

            override fun onFailure(call: Call<Film>, t: Throwable) {
                Toast
                    .makeText(context, "Something went wrong. Try to check your internet connection.", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

}