package com.example.dz_2111

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.dz_2111.databinding.ActivityMainBinding
import androidx.activity.result.contract.ActivityResultContracts
import android.location.Location
import android.location.LocationManager
import androidx.annotation.RequiresPermission

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // 3. Получаем ответ пользователя
        if (isGranted) {
            // Разрешение предоставлено
            // Можете начать работу с геолокацией здесь
        } else {
            // Разрешение отклонено
            // Приложение должно обработать этот случай
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.navHostFragment)
        val bottomNav = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNav, navController)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mapsFragment -> {
                    navController.navigate(R.id.mapsFragment)
                    true
                }
                else -> false
            }
        }
        checkAndRequestLocationPermission()
    }

    private fun checkAndRequestLocationPermission() {
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        when {
            // Проверяем, есть ли уже разрешение
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                // Разрешение уже есть, можно работать с геолокацией
            }

            else -> {
                // Разрешения нет — запрашиваем
                requestPermissionLauncher.launch(permission)
            }
        }
    }
}

