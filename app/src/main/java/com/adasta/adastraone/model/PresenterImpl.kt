package com.adasta.adastraone.model

import android.util.Log
import com.adasta.adastraone.network.ApiClient
import com.adasta.adastraone.presenter.ViewPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class PresenterImpl : ViewPresenter.MainPresenter {

    var mainView: ViewPresenter.MainView? = null

    @NonNull
    var disposable: Disposable? = null

    constructor(mainView: ViewPresenter.MainView?) {
        this.mainView = mainView
    }

    override fun loadData(pageNumber: Int) {
        mainView!!.showProgressbar()

        if (mainView!!.checkInternet()) {
//            Log.e("Problem", "checkInternet")



            disposable = ApiClient.instance
                .getListOfCharacters(pageNumber.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listResponse ->
                    mainView!!.hideProgressbar()
                    val responseCode = listResponse.code()
                    when (responseCode) {
                        200, 201, 202 -> {
                            mainView!!.onSuccess(listResponse)
                            Log.e("PresenterImp", "200, 201, 202")
                        }

                        401, 402, 500, 501 -> {
                            mainView!!.onSuccess(listResponse)
                            Log.e("PresenterImp", "200, 201, 202")

                        }
                    }
                }, { error ->
                    mainView!!.hideProgressbar()
                    if (error is HttpException) {
                        val response = error.response()
                        Log.e("PresenterImp", "response = $response")

                        when (response!!.code()) {
                            //Responce Code
                        }
                    } else {
                        //Handle Other Errors
                    }
                    mainView!!.onError(error)
                })
        } else {
            mainView!!.hideProgressbar()
            mainView!!.validateError()
        }
    }

    override fun onStop() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}
