package com.twcc.data.storage

import android.util.Log
import com.twcc.data.api.DailyService
import com.twcc.data.api.DailyUser
import com.twcc.data.api.GitHubService
import com.twcc.data.api.GitHubUserResponse
import com.twcc.data.api.RetrofitApi
import com.twcc.domain.models.UserDomain
import java.util.concurrent.CancellationException

class UserNetworkStorageImpl(
    private val gitHubApi: RetrofitApi<GitHubService>,
    private val dailyApi: RetrofitApi<DailyService>
) : UserNetworkStorage {

    companion object {
        private const val DEFAULT_STRING_VALUE = ""
        private const val GITHUB_API = "GITHUB_API"
        private const val DAILY_API = "DAILY_API"
    }

    override suspend fun getUsers(): List<UserDomain> {
        val users = mutableListOf<UserDomain>()

        try {
            users.addAll(gitHubApi.service().getUsers().mapGitHubUserResponseToUserDomainList())
        } catch (e: CancellationException) {
            Log.e("gitHubApi CancellationException", e.localizedMessage ?: "")
            throw e
        } catch (e: Exception) {
            Log.e("gitHubApi exception", e.localizedMessage ?: "")
        }

        try {
            users.addAll(dailyApi.service().getUsers().list.mapDailyUsersToUserDomainList())
        } catch (e: CancellationException) {
            Log.e("dailyApi CancellationException", e.localizedMessage ?: "")
            throw e
        } catch (e: Exception) {
            Log.e("dailyApi exception", e.localizedMessage ?: "")
        }

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