package com.arlanov.githubclient.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlanov.githubclient.R
import com.arlanov.githubclient.api.component.DaggerGitHubRepoComponent
import com.arlanov.githubclient.api.model.GitHubRepo
import com.arlanov.githubclient.api.service.GitHubClient
import com.arlanov.githubclient.ui.adapters.GitHubRepoAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_show.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 */
class ShowFragment : Fragment() {
    private lateinit var gitHubClient: GitHubClient

    companion object {
        fun newInstance(param1: String, param2: String): ShowFragment {
            // Bundle args = new Bundle();
            return ShowFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createConnected()
        listView.layoutManager = LinearLayoutManager(activity)
    }

    private fun createConnected(){
        val component = DaggerGitHubRepoComponent.builder().build()
        gitHubClient = component.getGitHubClientService()

        gitHubClient.reposForUser("ArlanchikDrey").enqueue(object : Callback<List<GitHubRepo>>{

            override fun onResponse(
                call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>) {

                val listRepo = response.body()

                val adapter = GitHubRepoAdapter{
                    Snackbar.make(listView, it.name.toString(), Snackbar.LENGTH_SHORT).show()
                }
                listView.adapter = adapter
                if (listRepo != null)
                    adapter.updateData(listRepo)


            }
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {

            }
        })
    }


}
