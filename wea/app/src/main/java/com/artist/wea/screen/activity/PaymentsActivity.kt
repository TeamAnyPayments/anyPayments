package com.artist.wea.screen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.artist.wea.BuildConfig
import com.artist.wea.R
import com.artist.wea.data.ConcertReceipt
import com.artist.wea.databinding.ActivityPaymentsBinding
import com.artist.wea.util.CommonUtils.Companion.getSerializable
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

    // 뷰바인딩을 적용하였으므로 XXXBiding 클래스를 통해 뷰바인딩
    val binding by lazy {ActivityPaymentsBinding.inflate(layoutInflater)}
    lateinit var concertReceipt:ConcertReceipt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // intent로 부터 데이터 수신
        concertReceipt = getSerializable(this, "prepareReceipt", ConcertReceipt::class.java)

        // 백버튼 활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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
                // 결제 완료시 동작하는 메서드
                // 결제완료 시 버튼 텍스트 변경
                binding.payButton.text = getString(R.string.btn_txt_toss_payments_ready)
            }
        }

        // 페이먼츠 위젯에 결제 방법, 금액 등에 대해 설정할 부분이다.
        // 지금은 샘플 코드라 대부분의 옵션이 생략되어 있다.
        paymentWidget.run {
            renderPaymentMethods(
                method = binding.paymentWidget,
                amount = PaymentMethod.Rendering.Amount(100),
                paymentWidgetStatusListener = paymentMethodWidgetStatusListener
            )

            renderAgreement(binding.agreementWidget)
        }

        // 결제버튼을 누르면 다음 단계로 넘어가게 하는 로직.
        // 이 부분은 토스가 제공하지 않으므로 스스로 xml에 버튼 추가해서 이벤트 리스너 셋팅해줘야 한다.
        binding.payButton.setOnClickListener {
            paymentWidget.requestPayment(
                paymentInfo = PaymentMethod.PaymentInfo(orderId = "wBWO9RJXO0UYqJMV4er8J", orderName = "wea"),
                paymentCallback = object : PaymentCallback {
                    // 결제 프로세스에 대한 콜백 함수이다.
                    override fun onPaymentSuccess(success: TossPaymentResult.Success) {
                        Log.i("success:::", success.paymentKey)
                        Log.i("success:::", success.orderId)
                        Log.i("success:::", success.amount.toString())

                        ToastManager.shortToast(applicationContext, "결제가 완료되었습니다.")
                        // 메인 액티비티로 데이터 전송
                        recordPaymentsResult(
                            applicationContext,
                            concertReceipt,
                            success
                        )

                    }

                    override fun onPaymentFailed(fail: TossPaymentResult.Fail) {
                        Log.e("fail:::",fail.errorMessage)
                    }
                }
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish() // 현재 액티비티 종료
                return true
            }
            // 다른 메뉴 아이템에 대한 처리
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // 결제 정보 저장하고 dto로 저장 후 메인 액티비티로 전달
    fun recordPaymentsResult(context: Context, concertReceipt: ConcertReceipt, success:TossPaymentResult.Success){
        val newConcertReceipt = ConcertReceipt(
            concertId = concertReceipt.concertId,
            minSupportAccount = concertReceipt.minSupportAccount,
            startDate = concertReceipt.startDate,
            endDate = concertReceipt.endDate,
            locations = concertReceipt.locations,
            // toss
            paymentKey = success.paymentKey,
            orderId = success.orderId,
            amount = success.amount
        )

        val intent = Intent(context, MainActivity::class.java);
        intent.putExtra("completeReceipt", newConcertReceipt)
        startActivity(intent)
        finish()
    }
}
