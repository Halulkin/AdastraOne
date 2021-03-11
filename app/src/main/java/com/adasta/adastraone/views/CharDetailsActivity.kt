package com.adasta.adastraone.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.adasta.adastraone.R
import com.adasta.adastraone.databinding.ActivityCharDetailsBinding
import com.adasta.adastraone.room.AppDatabase
import com.adasta.adastraone.room.User
import com.adasta.adastraone.presenter.ViewPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast

class CharDetailsActivity : AppCompatActivity(), ViewPresenter.CharView {

    private lateinit var binding: ActivityCharDetailsBinding

    private lateinit var db: AppDatabase
//    private lateinit var newDb: CharacterInfoAppDatabase



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        db = Room.databaseBuilder(
            baseContext,
            AppDatabase::class.java, "user"
        ).build()

//        newDb = Room.databaseBuilder(
//            baseContext,
//            CharacterInfoAppDatabase::class.java, "charactersDb"
//        ).build()


        val user = getData()




        binding.btnSave.setOnClickListener {
            saveToDb(user)
        }


        binding.btnShow.setOnClickListener {
            show()
        }

    }

    private fun saveToDb(user: User) {
        val userDao = db.userDao()

        lifecycleScope.launch {
            userDao.insertUser(user)
            toast(user.name + ", " + user.status)
        }
    }


    private fun show() {

        val userDao = db.userDao()
        val sb = StringBuilder()


        lifecycleScope.launch {
            val newUsers = userDao.getAll()
            for (item in newUsers) {
                sb.append(item.name + " - " + item.status + "\n")
            }
            toast(sb)
        }
    }


    override fun getData(): User {
        val name: String? = intent.getStringExtra("name")
        val status: String? = intent.getStringExtra("status")
        val gender = intent.getStringExtra("gender")
        val image = intent.getStringExtra("image")
        val species = intent.getStringExtra("species")

        presentData(name!!, status!!, gender!!, image!!, species!!)

        return User(null, name, status)
    }

//    override fun getData(): CharacterInfo.Results {
//        val name: String? = intent.getStringExtra("name")
//        val status: String? = intent.getStringExtra("status")
//        val gender = intent.getStringExtra("gender")
//        val image = intent.getStringExtra("image")
//        val species = intent.getStringExtra("species")
//
//        presentData(name!!, status!!, gender!!, image!!, species!!)
//
//        var chars = CharacterInfo.Results(null, name, status, species,  gender, image)
//
//        return CharacterInfo.Results(null, name, status, species,  gender, image )
//    }

    override fun presentData(
        name: String,
        status: String,
        gender: String,
        image: String,
        species: String
    ) {
        binding.tvName.text = name
        binding.tvStatus.text = status
        binding.tvGender.text = gender
        binding.tvSpecies.text = species

        Glide.with(this.applicationContext).load(image).error(R.mipmap.ic_launcher)
            .apply(RequestOptions.circleCropTransform()).into(binding.ivImage)

    }


}