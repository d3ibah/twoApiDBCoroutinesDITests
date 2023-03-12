package com.twcc.data.storage

import com.twcc.data.api.DailyApi
import com.twcc.data.api.DailyUser
import com.twcc.data.api.GitHubApi
import com.twcc.data.api.GitHubUserResponse
import com.twcc.domain.models.UserDomain

class UserNetworkStorageImpl : UserNetworkStorage {

    companion object {
        private const val DEFAULT_STRING_VALUE = ""
        private const val GITHUB_API = "GITHUB_API"
        private const val DAILY_API = "DAILY_API"
    }

    private val gitHubApi = GitHubApi.retrofit
    private val dailyApi = DailyApi.retrofit

    override suspend fun getUsers(): List<UserDomain> {
        val users = mutableListOf<UserDomain>()
        users.addAll(gitHubApi.getUsers().mapGitHubUserResponseToUserDomainList())
        users.addAll(dailyApi.getUsers().list.mapDailyUsersToUserDomainList())
        return users
    }

    private fun List<GitHubUserResponse>.mapGitHubUserResponseToUserDomainList(): List<UserDomain> {
        return this.map {
            UserDomain(
                id = it.id.toString(),
                name = it.login.toString(),
                api = GITHUB_API,
                image = it.avatarUrl ?: DEFAULT_STRING_VALUE
            )
        }
    }

    private fun List<DailyUser>.mapDailyUsersToUserDomainList(): List<UserDomain> {
        return this.map {
            UserDomain(
                id = it.id.toString(),
                name = it.screenname ?: DEFAULT_STRING_VALUE,
                api = DAILY_API,
                image = DEFAULT_STRING_VALUE
            )
        }
    }
}