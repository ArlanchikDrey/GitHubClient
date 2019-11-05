package com.arlanov.githubclient.api.component

import com.arlanov.githubclient.api.modules.GitHubClientModule
import com.arlanov.githubclient.api.scopes.GitHubClientApplicationScope
import com.arlanov.githubclient.api.service.GitHubClient
import dagger.Component

@GitHubClientApplicationScope
@Component(modules = [GitHubClientModule::class])
interface GitHubRepoComponent {
    fun getGitHubClientService(): GitHubClient
}