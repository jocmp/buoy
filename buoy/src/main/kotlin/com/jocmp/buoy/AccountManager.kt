package com.jocmp.buoy

import java.io.File
import java.io.FileFilter
import java.net.URI
import java.util.UUID

class AccountManager(
    private val rootFolder: URI,
    private val cacheDirectory: URI,
    private val preferenceStoreProvider: PreferenceStoreProvider,
) {
    fun findByID(
        id: String,
    ): Account? {
        val existingAccount = findAccountFile(id) ?: return null

        return buildAccount(existingAccount)
    }

    fun createAccount(
        username: String,
        token: String,
        url: String,
    ): String {
        val accountID = newAccount()

        preferenceStoreProvider.build(accountID).let { preferences ->
            preferences.username.set(username)
            preferences.token.set(token)
            preferences.url.set(url)
        }

        return accountID
    }

    private fun newAccount(): String {
        val accountID = UUID.randomUUID().toString()

        accountFolder().apply {
            if (!exists()) {
                mkdir()
            }
        }

        accountFile(accountID).apply { mkdir() }

        return accountID
    }

    fun removeAccount(accountID: String) {
        val accountFile = findAccountFile(accountID)

        accountFile?.deleteRecursively()
        preferenceStoreProvider.delete(accountID)
    }

    private fun accountFile(id: String): File {
        return File(accountFolder(), id)
    }

    private fun accountFolder() = File(rootFolder.path, DIRECTORY_NAME)

    private fun buildAccount(
        path: File,
        preferences: AccountPreferences = preferenceStoreProvider.build(path.name)
    ): Account {
        val id = path.name
        val pathURI = path.toURI()
        val cacheDirectory = File(cacheDirectory.path, id).toURI()

        return Account(
            id = id,
            path = pathURI,
            cacheDirectory = cacheDirectory,
            preferences = preferences,
        )
    }

    private fun findAccountFile(id: String): File? {
        return accountFolder().listFiles(AccountFileFilter(id))?.firstOrNull()
    }

    companion object {
        private const val DIRECTORY_NAME = "accounts"
    }
}

private class AccountFileFilter(private val id: String) : FileFilter {
    override fun accept(pathname: File?) = pathname?.name == id
}
