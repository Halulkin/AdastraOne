package com.adasta.adastraone.presenter

import com.adasta.adastraone.room.User
import com.adasta.adastraone.model.Character
import retrofit2.Response

interface ViewPresenter {

    interface MainView {
        fun onSuccess(reposnseModel: Response<Character>)
        fun onError(throwable: Throwable)
        fun onErrorToast()
        fun checkInternet(): Boolean
        fun validateError()
        fun showProgressbar()
        fun hideProgressbar()
    }

    interface MainPresenter {
        fun loadData(pageNumber: Int)
        fun onStop()
    }

    interface CharView {
        fun getData(): User
        fun presentData(
            name: String,
            status: String,
            gender: String,
            image: String,
            species: String
        )
    }

}