package com.artist.wea.components

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.artist.wea.R
import com.artist.wea.data.AddressInfo
import com.google.gson.Gson

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressForm(
    modifier: Modifier = Modifier
        .fillMaxWidth()
): AddressInfo? {
    var addressInfo by remember { mutableStateOf<AddressInfo?>(null) }
    val url = "https://address.kabserv.xyz"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 16.dp)
    ) {
        var isVisible by remember { mutableStateOf(false) }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (isVisible) {
                CustomAlertDialog(
                    onDismissRequest = {
                        isVisible = false
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = colorResource(id = R.color.mono50)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Filled.Close,
                            contentDescription = "닫기",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(16.dp)
                                .align(Alignment.End)
                                .clickable {
                                    isVisible = false
                                },
                            tint = colorResource(id = R.color.mono600)
                        )
                        AndroidView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            factory = {
                                WebView(it).apply {
                                    settings.apply {
                                        allowFileAccess = true
                                        allowContentAccess = true
                                        javaScriptEnabled = true
                                        javaScriptCanOpenWindowsAutomatically = true
                                        domStorageEnabled = true
                                    }
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    webViewClient = object: WebViewClient() {
                                        override fun onPageFinished(view: WebView?, url: String?) {
                                            super.onPageFinished(view, url)
                                            loadUrl("javascript:init();")
                                        }
                                    }
                                    addJavascriptInterface(object {
                                        @JavascriptInterface
                                        fun processDATA(data: String?): Unit {
                                            data?.let {
                                                addressInfo = Gson().fromJson(data, AddressInfo::class.java)
                                                isVisible = false
                                            }
                                        }
                                    }, "wea")
                                    loadUrl(url)
                                }
                            }
                        )
                    }
                }
            }
            TextField(
                value = addressInfo?.postalCode ?: "",
                onValueChange = { },
                readOnly = true
            )
            Button(
                onClick = {
                    isVisible = true
                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "우편번호 찾기"
                )
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = addressInfo?.address ?: "",
            onValueChange = { },
            readOnly = true
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = addressInfo?.addressDetail ?: "",
            onValueChange = {
                addressInfo = addressInfo?.copy(
                    addressDetail = it
                )
            },
        )
    }
    return addressInfo
}


@Preview
@Composable
fun AddressFormPreview() {
    AddressForm()
}