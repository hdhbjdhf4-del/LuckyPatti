package com.aedev.zooroulettepredictor

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        val web = WebView(this)
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.allowFileAccess = true
        web.webViewClient = WebViewClient()
        web.webChromeClient = WebChromeClient()
        web.loadUrl("file:///android_asset/index.html")
        setContentView(web)
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        val web = (window.decorView.findViewById<View>(android.R.id.content) as? android.view.ViewGroup)
            ?.getChildAt(0) as? WebView
        if (web != null && web.canGoBack()) web.goBack() else super.onBackPressed()
    }
}
