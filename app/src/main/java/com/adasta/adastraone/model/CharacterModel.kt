package com.adasta.adastraone.model

data class Character(var info: Info, var results: ArrayList<Results>) {

    inner class Info(var count: Int, var pages: Int, var next: String, var prev: String)

    inner class Results(
        var id: Int, var name: String, var status: String, var image: String,
        var species: String, var type: String, var gender: String,
    )

}
