package com.vince.starwars.utils.view

import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import com.vince.starwars.utils.ConstantsHelper.DELAY_PREVENT_TWO_CLICKS
import com.vince.starwars.utils.ConstantsHelper.LOG_TAG

class SafeClickListener(
    private val onSafeClick: (View) -> Unit,
    private val param: String? = null
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(view: View?) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < DELAY_PREVENT_TWO_CLICKS) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        view?.let {
            trackOnClickView(view)
            onSafeClick(it)
        }
    }

    private fun trackOnClickView(view: View) {
        val resourceEntryName =
            try {
                view.resources.getResourceEntryName(view.id)
            } catch (t: Throwable) {
                (view as Button).text
            }
        val message = StringBuilder("click_view: $resourceEntryName")
        param?.let { message.append(" - id: $it") }
        Log.d(LOG_TAG, message.toString())
    }
}
