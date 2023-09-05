package com.artist.wea.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.artist.wea.R
import com.artist.wea.databinding.ActivityPaymentsBinding
import com.tosspayments.paymentsdk.PaymentWidget
import com.tosspayments.paymentsdk.model.PaymentCallback
import com.tosspayments.paymentsdk.model.PaymentWidgetStatusListener
import com.tosspayments.paymentsdk.model.TossPaymentResult
import com.tosspayments.paymentsdk.view.PaymentMethod

class PaymentsActivity : AppCompatActivity() {

    val binding by lazy {ActivityPaymentsBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)
        setContentView(binding.root)

        val clientKey = "test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq"
        val secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R"
        // "7CP_K-knksQZ966GZAfhm"

        val paymentWidget = PaymentWidget(
            activity = this@PaymentsActivity,
            clientKey = clientKey,
            customerKey = secretKey,
        )

        val paymentMethodWidgetStatusListener = object : PaymentWidgetStatusListener {
            override fun onLoad() {
                val message = "결제위젯 렌더링 완료"
                Log.d("PaymentWidgetStatusListener", message)
            }
        }

        paymentWidget.run {
            renderPaymentMethods(
                method = binding.paymentWidget,
                amount = PaymentMethod.Rendering.Amount(10000),
                paymentWidgetStatusListener = paymentMethodWidgetStatusListener
            )

            renderAgreement(binding.agreementWidget)
        }

        binding.payButton.setOnClickListener {
            paymentWidget.requestPayment(
                paymentInfo = PaymentMethod.PaymentInfo(orderId = "wBWO9RJXO0UYqJMV4er8J", orderName = "wea"),
                paymentCallback = object : PaymentCallback {
                    override fun onPaymentSuccess(success: TossPaymentResult.Success) {
                        Log.i("success:::", success.paymentKey)
                        Log.i("success:::", success.orderId)
                        Log.i("success:::", success.amount.toString())
                    }

                    override fun onPaymentFailed(fail: TossPaymentResult.Fail) {
                        Log.e("fail:::",fail.errorMessage)
                    }
                }
            )
        }
    }
}