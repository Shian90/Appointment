package com.example.app_ointmentt.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_ointmentt.IHomepage
import com.example.app_ointmentt.R
import com.example.app_ointmentt.adapter.HistoryAdapter
import com.example.app_ointmentt.dataset.HistoryRawData
import kotlinx.android.synthetic.main.fragment_doctor_history.view.*

class DoctorHistoryFragment : Fragment() {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var iHomeFragment: IHomepage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iHomeFragment.setToolbarTitle("History")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_doctor_history,container,false)
        initRecyclerView(rootView)
        bindData()
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iHomeFragment = activity as IHomepage
    }

    private fun bindData(){
        historyAdapter.submitList(HistoryRawData.members)
    }

    private fun initRecyclerView(rootView: View){
        rootView.historyRecyclerView.apply {
            historyAdapter =
                HistoryAdapter()
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}