package swes.swes.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dell on 15/06/2017.
 */

public class WebViewController extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
