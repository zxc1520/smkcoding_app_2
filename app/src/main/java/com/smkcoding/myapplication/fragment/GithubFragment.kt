package com.smkcoding.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.myapplication.ProvinsiAdapter
import com.smkcoding.myapplication.R
import com.smkcoding.myapplication.covid.ProvinsiItem
import com.smkcoding.myapplication.data.CovidGlobalDataService
import com.smkcoding.myapplication.data.apiRequest
import com.smkcoding.myapplication.data.httpClient
import com.smkcoding.myapplication.util.dismissLoading
import com.smkcoding.myapplication.util.showLoading
import com.smkcoding.myapplication.util.tampilToast
import kotlinx.android.synthetic.main.frame_provinsi.*
import retrofit2.*

class GithubFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_provinsi, container, false)

    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        //callApiGetGithubUser()
        callApi()
    }

    private fun callApi() {
        showLoading(context!!, swipeRefresLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<CovidGlobalDataService>(httpClient)

        val call = apiRequest.getProv()
        call.enqueue(object : Callback<List<ProvinsiItem>> {
            override fun onFailure(call: Call<List<ProvinsiItem>>, t: Throwable) {
                dismissLoading(swipeRefresLayout)
            }

            override fun onResponse(
                call: Call<List<ProvinsiItem>>,
                response: Response<List<ProvinsiItem>>
            ) {
                dismissLoading(swipeRefresLayout)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilList(response.body()!!)

                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }

                    else -> {
                        tampilToast(context!!,"Gagal")
                    }
                }

            }

        })
    }

    private fun tampilList(provinsiList: List<ProvinsiItem>) {

        listProvinsi.layoutManager = LinearLayoutManager(context)
        listProvinsi.adapter = ProvinsiAdapter(context!!, provinsiList) {
            val provinsiList = it
            tampilToast(context!!, provinsiList.attributes.provinsi)
        }

    }

//    fun callApiGetGithubUser() {
//        showLoading(context!!, swipeRefresLayout)
//
//        val httpClient = httpClient()
//        val apiRequest = apiRequest<GithubService>(httpClient)
//
//        val call = apiRequest.getUsers()
//        call.enqueue(object :
//            Callback<List<GithubUserItem>> {
//            override fun onFailure(call: Call<List<GithubUserItem>>, t: Throwable) {
//                dismissLoading(swipeRefresLayout)
//            }
//
//            override fun onResponse(
//                call: Call<List<GithubUserItem>>,
//                response: Response<List<GithubUserItem>>
//            ) {
//                dismissLoading(swipeRefresLayout)
//
//                when {
//                    response.isSuccessful ->
//                        when {
//                            response.body()?.size != 0 -> tampilGithubUser(response.body()!!)
//                            else -> {
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }
//                    else -> {
//                        tampilToast(context!!, "Gagal")
//                    }
//                }
//            }
//
//        })
//
//    }
//
//    private fun tampilGithubUser(githubUsers: List<GithubUserItem>) {
//        listGithubUser.layoutManager = LinearLayoutManager(context)
//        listGithubUser.adapter = GithubUserAdapter(context!!, githubUsers) {
//            val githubUser = it
//            tampilToast(context!!, githubUser.login)
//        }
//    }

}