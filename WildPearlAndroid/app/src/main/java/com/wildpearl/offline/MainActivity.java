package com.wildpearl.offline;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Window;
import android.view.WindowManager;
public class MainActivity extends Activity {
 private WebView webView;
 @Override protected void onCreate(Bundle state) {
  super.onCreate(state);
  requestWindowFeature(Window.FEATURE_NO_TITLE);
  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
  webView=new WebView(this);
  WebSettings s=webView.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setAllowFileAccess(true);
  webView.setWebViewClient(new WebViewClient()); webView.setWebChromeClient(new WebChromeClient());
  setContentView(webView); webView.loadUrl("file:///android_asset/index.html");
 }
 @Override public void onBackPressed(){if(webView!=null&&webView.canGoBack())webView.goBack();else super.onBackPressed();}
 @Override protected void onDestroy(){if(webView!=null){webView.destroy();webView=null;}super.onDestroy();}
}
