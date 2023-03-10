package com.twcc.data.storage

import com.twcc.data.api.GitHubApi
import com.twcc.data.api.GitHubUserResponse
import com.twcc.domain.models.UserDomain

class StorageImpl : Storage {

    companion object {
        private const val DEFAULT_AVATAR_URL = ""
        private const val GITHUB_API = "GITHUB_API"
        private const val DAILY_API = "DAILY_API"
    }

    private val gitHubApi = GitHubApi.retrofit

    override suspend fun getUsers(): List<UserDomain> {
        return gitHubApi.getUsers().mapToUserDomainList()
    }

    private fun List<GitHubUserResponse>.mapToUserDomainList(): List<UserDomain> {
        return this.map {
            UserDomain(
                it.id.toString(),
                it.login.toString(),
                it.avatarUrl ?: DEFAULT_AVATAR_URL,
                GITHUB_API
            )
        }
    }
}