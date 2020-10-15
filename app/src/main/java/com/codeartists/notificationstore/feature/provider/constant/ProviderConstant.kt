package com.codeartists.notificationstore.feature.provider.constant

import com.codeartists.notificationstore.feature.provider.model.Provider

object ProviderConstant {
    var providers: List<Provider> = listOf(
        Provider("Facebook", "com.facebook.orca"),
        Provider("WhatsApp", "com.whatsapp"),
        Provider("Messenger Lite", "com.facebook.mlite")
    )
    val ignoredTitle = listOf("Chat heads active", "WhatsApp")
}