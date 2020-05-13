package com.smkcoding.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.myapplication.GithubUserAdapter
import com.smkcoding.myapplication.MyFriend
import com.smkcoding.myapplication.MyFriendAdapter
import com.smkcoding.myapplication.R
import com.smkcoding.myapplication.covid.Attributes
import com.smkcoding.myapplication.data.CovidGlobalDataService
import com.smkcoding.myapplication.data.apiRequest
import com.smkcoding.myapplication.data.httpClient
import com.smkcoding.myapplication.util.dismissLoading
import com.smkcoding.myapplication.util.showLoading
import com.smkcoding.myapplication.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.frame_github.*
import kotlinx.android.synthetic.main.frame_myfriends.*
import retrofit2.*

class MyFriendsFragment : Fragment() {

    //lateinit var listTeman : ArrayList<MyFriend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_myfriends, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callCovidApi()
    }

    private fun callCovidApi() {
        showLoading(context!!,swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<CovidGlobalDataService>(httpClient)

        val call = apiRequest.getAttr()
        call.enqueue(object : Callback<List<Attributes>>{
            override fun onFailure(call: Call<List<Attributes>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<Attributes>>,
                response: Response<List<Attributes>>
            ) {
                dismissLoading(swipeRefreshLayout)

                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 -> tampilDataCovid(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilDataCovid(attr: List<Attributes>) {
        listCovid.layoutManager = LinearLayoutManager(context)
        listCovid.adapter = MyFriendAdapter(context!!, attr) {
            val attr = it
            tampilToast(context!!, attr.countryRegion)
        }
    }

//    private fun simulasiDataTeman() {
//        listTeman = ArrayList()
//        listTeman.add(MyFriend("Hamzah", "laki-laki", "hamzah@gmail.com", "085123456", "Kediri"))
//        listTeman.add(MyFriend("Juliandri", "Laki-laki", "july@gmail.com", "081234567", "Sidoarjo"))
//    }
//
//    private fun tampilTeman() {
//        rv_listMyFriend.layoutManager = LinearLayoutManager(activity)
//        rv_listMyFriend.adapter = MyFriendAdapter(activity!!, listTeman)
//    }
//
//    private fun initView() {
//        simulasiDataTeman()
//        tampilTeman()
//    }

}