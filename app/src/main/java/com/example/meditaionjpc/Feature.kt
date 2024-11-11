package com.example.meditaionjpc

import androidx.compose.ui.graphics.Color

enum class FeatureType {
    Music,
    VIDEO
}

data class Feature(val name: String, val type: FeatureType, val gradients: List<Color>) {}

