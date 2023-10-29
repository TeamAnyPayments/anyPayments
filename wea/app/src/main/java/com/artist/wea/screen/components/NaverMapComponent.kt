package com.artist.wea.screen.components

import android.app.Activity
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.artist.wea.constants.GlobalState
import com.artist.wea.util.PermissionChecker
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.launch

// 네이버 맵 컴포저블
@Composable
fun NaverMapComponent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    latitude:Double = GlobalState.lat,
    longitude:Double = GlobalState.lon,
    showMarker:Boolean = true
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    /*
     locationSource = rememberFusedLocationSource(),
    properties = MapProperties(
        locationTrackingMode = LocationTrackingMode.Follow,
    ),
    uiSettings = MapUiSettings(
        isLocationButtonEnabled = true,
    )
     */

    val pc = PermissionChecker(context as Activity, context);
    val fls = FusedLocationSource(
        LocalContext.current as Activity,
        pc.PERMISSION_STATE_CODE
    )
    //  mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

    // Lifecycle 이벤트를 수신하기 위해 AndroidView의 밖에서 먼저 선언합니다.
    // recomposition시에도 유지되어야 하기 때문에 remember { } 로 기억합니다.
    val mapView = remember {
        MapView(context).apply {
            getMapAsync { naverMap ->
                // ... 초기 설정 ...
                // 마커 표시하고자 할 떄
                if(showMarker){
                    val marker = Marker()
                    marker.position = LatLng(latitude, longitude)
                    marker.map = naverMap
                }

                // 카메라쪽 설정은 뭔지 모르겠...
                val cameraUpdate = CameraUpdate.scrollTo(LatLng(latitude, longitude))
                naverMap.moveCamera(cameraUpdate)

                // naverMap.locationSource = fls;
                naverMap.cameraPosition = CameraPosition(
                    LatLng(latitude, longitude),
                    12.5 // why?
                );
                naverMap.locationTrackingMode = LocationTrackingMode.Face
                naverMap.locationSource = fls;
           }
        }
    }


    // LifecycleEventObserver를 구현하고, 각 이벤트에 맞게 MapView의 Lifecycle 메소드를 호출합니다.
    val lifecycleObserver = remember {
        LifecycleEventObserver { source, event ->
            // CoroutineScope 안에서 호출해야 정상적으로 동작합니다.
            coroutineScope.launch {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                    Lifecycle.Event.ON_START -> mapView.onStart()
                    Lifecycle.Event.ON_RESUME -> mapView.onResume()
                    Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                    Lifecycle.Event.ON_STOP -> mapView.onStop()
                    Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                    else -> {}
                }
            }
        }
    }

    // 뷰가 해제될 때 이벤트 리스너를 제거합니다.
    DisposableEffect(true) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }


    // 생성된 MapView 객체를 AndroidView로 Wrapping 합니다.
    AndroidView(
        factory = { mapView },
        modifier = modifier
    )
}
