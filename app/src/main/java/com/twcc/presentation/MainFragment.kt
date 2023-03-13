package com.twcc.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.twcc.databinding.FragmentMainBinding
import com.twcc.domain.models.UserDomain

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels { MainFragmentViewModel.Factory }
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val onItemClick: (UserDomain) -> Unit = {
        initOnItemClick(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()

        val adapter = UserAdapter(onItemClick)
        initRecycler(adapter)

        viewModel.getDataLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initRecycler(adapter: UserAdapter) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initOnItemClick(user: UserDomain) {
        viewModel.onItemClicked(user)
        val action = MainFragmentDirections.actionMainFragmentToSecondFragment(user = user)
        findNavController().navigate(action)
    }
}

