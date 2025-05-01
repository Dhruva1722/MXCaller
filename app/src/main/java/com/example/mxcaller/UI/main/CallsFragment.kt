package com.example.mxcaller.UI.main

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mxcaller.R
import com.example.mxcaller.databinding.FragmentCallsBinding
import com.example.mxcaller.utils.PermissionUtils

class CallsFragment : Fragment() {

    private lateinit var binding: FragmentCallsBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = MainAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.recyclerViewCalls.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCalls.adapter = adapter


        viewModel.callLogs.observe(viewLifecycleOwner) { logs ->
            adapter.submitList(logs)
        }

        // Trigger loading
        viewModel.fetchCallLogs(requireContext())
    }

}