package com.jltech.wscars.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jltech.wscars.api.model.response.cars.Car
import com.jltech.wscars.api.model.response.categories.CategoriesResponse
import com.jltech.wscars.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeCarsAdapter: HomeCarsAdapter
    private lateinit var categoriesCarsAdapter: CategoriesCarsAdapter
    private val viewModel: HomeViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        onClick()

    }

    private fun setupObserver() {
        viewModel.successGetCars.observe(viewLifecycleOwner) {
            it.cars?.let { listCars -> initListCars(listCars) }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupUI() {
        val listCategories = viewModel.listCategory()
        viewModel.getCars()
        initListCategories(listCategories)
    }

    private fun onClick() {

        binding.apply {

        }

    }

    private fun initListCars(list: List<Car?>) {
        binding.rvRecommended.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            homeCarsAdapter = HomeCarsAdapter(emptyList()) { car ->
                //    findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSearchStoresFragment(categoryId = category.id))
            }

            adapter = homeCarsAdapter
        }

        homeCarsAdapter.updateData(list)

    }

    private fun initListCategories(list: List<CategoriesResponse>) {
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoriesCarsAdapter = CategoriesCarsAdapter(emptyList()) { category ->
            }

            adapter = categoriesCarsAdapter
        }

        categoriesCarsAdapter.updateData(list)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}