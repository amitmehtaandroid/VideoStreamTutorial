package com.androidbegin.videostreamtutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends Activity {

	Button button;

	String VideoURL = "https://www.youtube.com/watch?v=DKOLynNhWxo";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout from video_main.xml
		setContentView(R.layout.activity_main);

		// Locate the button in activity_main.xml

		WebView mWebView = (WebView)findViewById(R.id.webView);
		button = (Button) findViewById(R.id.MyButton);

		// Capture button clicks
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0)
			{
				// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						VideoViewActivity.class);
				startActivity(myIntent);
			}
		});




		//build your own src link with your video ID
//		String videoStr = "<html><body>Promo video<br><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/47yJ2XCRLZs\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
//
//		mWebView.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				return false;
//			}
//		});
//		WebSettings ws = mWebView.getSettings();
//		ws.setJavaScriptEnabled(true);
//		mWebView.loadData(videoStr, "text/html", "utf-8");
//
//
//
//		mWebView.getSettings().setJavaScriptEnabled(true);
//		mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//		//mWebView.loadUrl("http://www.youtube.com/embed/" + videoID + "?autoplay=1&vq=small");
//		mWebView.loadUrl(VideoURL);
//		mWebView.setWebChromeClient(new WebChromeClient());
	}





}
