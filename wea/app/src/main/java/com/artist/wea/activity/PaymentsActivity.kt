package com.artist.wea.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.artist.wea.BuildConfig
import com.artist.wea.R
import com.artist.wea.data.TossPaySuccess
import com.artist.wea.databinding.ActivityPaymentsBinding
import com.artist.wea.util.ToastManager
import com.tosspayments.paymentsdk.PaymentWidget
import com.tosspayments.paymentsdk.model.PaymentCallback
import com.tosspayments.paymentsdk.model.PaymentWidgetStatusListener
import com.tosspayments.paymentsdk.model.TossPaymentResult
import com.tosspayments.paymentsdk.view.PaymentMethod

// 페이먼츠와 관련된 UI 를 위한 Activity;
// compose와 별개로 xml을 사용해서 화면을 구성함
// 프로젝트에서는 compose를 사용했으나, 토스 개발자 사이트에서 안내하는 결제 api는 xml 기반이므로
// 확장성 및 레퍼런스 참고의 용이성을 위해 xml 기반의 액티비티 사용함/
class PaymentsActivity : AppCompatActivity() {

    // 뷰바인딩을 적용하였으므로 XXXBiding 클래스를 통해 뷰바인딩을 할 예정
    val binding by lazy {ActivityPaymentsBinding.inflate(layoutInflater)}
    var paymentsState:Boolean = false;

    // 임시로 액티비티 內 전역 변수로 결제 결과 표시
    var tossPaySuccess: TossPaySuccess? = null;

    // 결제 금액 (임시)
    val payAmount:Number = 1000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 초기 버튼에는 ... 표시를 해서 결제가 진행중이지 않음을 표시
        binding.payButton.text = ". . .";
        paymentsState = false;

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 아래의 코드들은 토스 개발자 사이트에서 제공한 샘플 코드임
        val clientKey = BuildConfig.TOSS_CLIENT_KEY
        val secretKey = BuildConfig.TOSS_SECRET_KEY

        // 페이먼츠 위젯에 필요한 기본 속성에 대해 세팅해준다!
        val paymentWidget = PaymentWidget(
            activity = this@PaymentsActivity,
            clientKey = clientKey,
            customerKey = secretKey,
        )

        // 앱 화면에 결제창에 완전히 로딩이 되면 아래의 메세지가 로그에 찍힌다. 꼭 확인 해볼것!
        val paymentMethodWidgetStatusListener = object : PaymentWidgetStatusListener {
            override fun onLoad() {
                val message = "결제위젯 렌더링 완료"
                Log.d("PaymentWidgetStatusListener", message)
                paymentsState = true;
                binding.payButton.text = "결제하기"
            }
        }

        // 페이먼츠 위젯에 결제 방법, 금액 등에 대해 설정할 부분이다.
        // 지금은 샘플 코드라 대부분의 옵션이 생략되어 있다.
        paymentWidget.run {
            renderPaymentMethods(
                method = binding.paymentWidget,
                amount = PaymentMethod.Rendering.Amount(payAmount),
                paymentWidgetStatusListener = paymentMethodWidgetStatusListener
            )

            renderAgreement(binding.agreementWidget)
        }

        // 결제버튼을 누르면 다음 단계로 넘어가게 하는 로직.
        // 이 부분은 토스가 제공하지 않으므로 스스로 xml에 버튼 추가해서 이벤트 리스너 셋팅해줘야 한다.
        binding.payButton.setOnClickListener {
            if(paymentsState){ // 렌더링 완료시에면 결제 프로세스가 가능하도록!
                paymentWidget.requestPayment(
                    paymentInfo = PaymentMethod.PaymentInfo(orderId = "wBWO9RJXO0UYqJMV4er8J", orderName = "wea"),
                    paymentCallback = object : PaymentCallback {
                        // 결제 프로세스에 대한 콜백 함수이다.
                        override fun onPaymentSuccess(success: TossPaymentResult.Success) {
                            Log.i("success:::", success.paymentKey)
                            Log.i("success:::", success.orderId)
                            Log.i("success:::", success.amount.toString())

                            // 결과를 TossPaySuccess 객체에 저장
                            tossPaySuccess?.paymentKey ?: success.paymentKey;
                            tossPaySuccess?.orderId ?: success.orderId;
                            tossPaySuccess?.amount ?: success.amount;

                            ToastManager.shortToast(
                                this@PaymentsActivity,
                                getString(R.string.txt_pay_complete)
                            )
                        }

                        override fun onPaymentFailed(fail: TossPaymentResult.Fail) {
                            Log.e("fail:::",fail.errorMessage)
                            ToastManager.shortToast(
                                this@PaymentsActivity,
                                getString(R.string.txt_pay_fail)
                            )
                        }
                    }
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // 뒤로 가기 동작을 수행하거나 원하는 작업을 수행하세요.
                onBackPressed() // deprecated 되지 않은 메서드로 교체..
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}