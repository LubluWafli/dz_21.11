package com.example.dz_2111

import android.Manifest
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

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
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
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navController = findNavController(R.id.navHostFragment)
        val bottomNav = binding.bottomNavigationView
        NavigationUI.setupWithNavController(bottomNav, navController)
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

