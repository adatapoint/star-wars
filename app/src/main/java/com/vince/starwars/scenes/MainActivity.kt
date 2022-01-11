package com.vince.starwars.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vince.starwars.databinding.ActivityMainBinding
import com.vince.starwars.utils.ConstantsHelper.MIN_LENGTH_NAME
import com.vince.starwars.utils.hasMinLength
import com.vince.starwars.utils.hideKeyboard
import com.vince.starwars.utils.setAsInvisible
import com.vince.starwars.utils.setAsVisible
import com.vince.starwars.utils.setSafeOnClickListener
import com.vince.starwars.utils.showToast
import com.vince.starwars.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.run {
            btn.setSafeOnClickListener {
                if (etName.hasMinLength(MIN_LENGTH_NAME)) {
                    hideKeyboard()
                    tvErrorName.setAsInvisible()
                    showToast("Desplegando actividad")
                } else {
                    tvErrorName.setAsVisible()
                }
            }
        }
    }
}
