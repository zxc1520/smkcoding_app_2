package com.smkcoding.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.smkcoding.myapplication.MyFriendAdapter
import com.smkcoding.myapplication.R
import com.smkcoding.myapplication.covid.IndonesiaModel
import com.smkcoding.myapplication.covid19.CovidData
import com.smkcoding.myapplication.data.CovidGlobalDataService
import com.smkcoding.myapplication.data.apiRequest
import com.smkcoding.myapplication.data.httpClient
import com.smkcoding.myapplication.util.dismissLoading
import com.smkcoding.myapplication.util.showLoading
import com.smkcoding.myapplication.util.tampilToast
import kotlinx.android.synthetic.main.frame_myfriends.*
import retrofit2.*

class MyFriendsFragment : Fragment() {

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
        getData()
    }

    private fun getData() {
        showLoading(context!!, swipeRefreshLayout)

        val httpClient = httpClient()
        val apiRequest = apiRequest<CovidGlobalDataService>(httpClient)

        val call = apiRequest.getAttr()
        call.enqueue(object : Callback<List<IndonesiaModel>> {
            override fun onFailure(call: Call<List<IndonesiaModel>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<IndonesiaModel>>,
                response: Response<List<IndonesiaModel>>
            ) {
                dismissLoading(swipeRefreshLayout)
                if (response.isSuccessful) {
                    val mainModel: List<IndonesiaModel> = response.body()!!
                    setResponse(mainModel)
                }
            }

        })
    }

    private fun setResponse(model: List<IndonesiaModel>) {

        val result = model[0]
        txtResult.setText(
            "Positif: ${result.positif} \nSembuh: ${result.sembuh} \nMeninggal: ${result.meninggal}"
        )

    }

//    private fun callCovidApi() {
//        showLoading(context!!,swipeRefreshLayout)
//
//        val httpClient = httpClient()
//        val apiRequest = apiRequest<CovidGlobalDataService>(httpClient)
//
//        val call = apiRequest.getAttr()
//        call.enqueue(object : Callback<List<CovidData>>{
//            override fun onFailure(call: Call<List<CovidData>>, t: Throwable) {
//                dismissLoading(swipeRefreshLayout)
//            }
//
//            override fun onResponse(
//                call: Call<List<CovidData>>,
//                response: Response<List<CovidData>>
//            ) {
//                dismissLoading(swipeRefreshLayout)
//
//                when {
//                    response.isSuccessful ->
//                        when {
//                            response.body()?.size != 0 ->
//                                tampilDataCovid(response.body()!!)
//                            else -> {
//                                tampilToast(context!!, "Berhasil")
//                            }
//                        }
//                    else -> {
//                        tampilToast(context!!, "Gagal")
//                    }
//                }
//            }
//        })
//    }
//
//    private fun tampilDataCovid(attr: List<CovidData>) {
//        listCovid.layoutManager = LinearLayoutManager(context)
//        listCovid.adapter = MyFriendAdapter(context!!, attr) {
//            val attr = it
//            tampilToast(context!!, attr.countries)
//        }
//    }

}