package com.example.filmmovie.ui.genres


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmmovie.databinding.FragmentGenresBinding
import com.example.filmmovie.ui.home.adapter.GenresAdapter
import com.example.filmmovie.ui.home.adapter.GenresMoviesAdapter
import com.example.filmmovie.utils.initRecycler
import com.example.filmmovie.viewmodel.GenresViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class GenresFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentGenresBinding

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var genresMoviesAdapter: GenresMoviesAdapter

    private val viewModel : GenresViewModel by viewModels()

    private var genresID = 1
    private val args: GenresFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadGenresList()

        genresAdapter.setOnItemClickListener {
            genresID = it.id!!.toInt()
            //Call api
            //if (genresID > 0) {
                viewModel.loadGenresMoviesList(genresID)
            //}
        }
        viewModel.loadGenresMoviesList(genresID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGenresBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner){
                genresAdapter.differ.submitList(it)
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false),
                    genresAdapter
                )
            }


            viewModel.genresMoviesList.observe(viewLifecycleOwner){
                genresMoviesAdapter.setData(it.data)
                //RecyclerView
                lastMoviesRecycler.initRecycler(LinearLayoutManager(requireContext()),genresMoviesAdapter)
            }
            //Click Item
            genresMoviesAdapter.setOnItemClickListener {
                val direction = GenresFragmentDirections.actionToDetail(it.id!!.toInt())
                findNavController().navigate(direction)
            }
        }
    }

}