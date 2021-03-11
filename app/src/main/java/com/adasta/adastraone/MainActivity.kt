package com.adasta.adastraone

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.adasta.adastraone.databinding.ActivityMainBinding
import com.adasta.adastraone.model.Character
import com.adasta.adastraone.model.CharactersAdapter
import com.adasta.adastraone.model.PresenterImpl
import com.adasta.adastraone.network.NetworkConnection
import com.adasta.adastraone.presenter.ViewPresenter
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.toast
import retrofit2.Response

class MainActivity : AppCompatActivity(), ViewPresenter.MainView {

    var presenterImplementation: PresenterImpl? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var mAdapter: CharactersAdapter? = null
    var arrayList: ArrayList<Character.Results>? = null

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initUI()
        presenterImplementation = PresenterImpl(this)
        presenterImplementation!!.loadData(1)

        binding.btnTry.setOnClickListener { recreate() }
    }

    private fun initUI() {
        linearLayoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.fitsSystemWindows = true
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterImplementation!!.onStop()
    }

    override fun validateError() {
        Snackbar.make(
            binding.llView,
            "Please check your internet connection",
            Snackbar.LENGTH_SHORT
        )
            .setAction("OK", View.OnClickListener { /*Take Action*/ }).show()

        binding.llNoConnection.visibility = View.VISIBLE
    }

    override fun showProgressbar() {
        binding.progressbar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        binding.progressbar.visibility = View.GONE
    }

    override fun onSuccess(reposnseModel: Response<Character>) {
        if (reposnseModel.body() != null) {
            binding.llNoConnection.visibility = View.GONE
            arrayList = reposnseModel.body()!!.results

            mAdapter = CharactersAdapter(arrayList!!, this)
            binding.recyclerView.adapter = mAdapter
        }
    }

    override fun onError(throwable: Throwable) {
        binding.llNoConnection.visibility = View.VISIBLE
        toast("Something went wrong")
    }

    override fun onErrorToast() {
        toast("Again! Something wrong with internet or server!")
    }

    override fun checkInternet(): Boolean {
        return NetworkConnection.isNetworkConnected(applicationContext)
    }

//    override fun checkInternet(): Boolean {
//        findNavController().navigate(R.id.action_navigation_main_to_longRecordFragment)
//    }

}