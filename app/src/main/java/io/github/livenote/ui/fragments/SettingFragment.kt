package io.github.livenote.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import io.github.livenote.R
import io.github.livenote.databinding.FragmentMainBinding
import io.github.livenote.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        val sharedPreferences = activity?.getSharedPreferences("SWITCH_COND", Context.MODE_PRIVATE)

        binding.darkThemeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            val switchCond = binding.darkThemeSwitch.isChecked
            val editor = sharedPreferences?.edit()
            editor?.putBoolean("SWITCH_COND", switchCond)
            editor?.apply()
        }
        sharedPreferences?.getBoolean("SWITCH_COND", false)
        return binding.root
    }
}