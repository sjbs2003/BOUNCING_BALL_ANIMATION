package com.example.ballanimation

class Ball(var x: Float, var y: Float, var vx: Float, var vy: Float, var radius: Float) {

    fun updatePosition() {
        x += vx
        y += vy
    }

    fun checkCollision(screenWidth: Int, screenHeight: Int) {
        if (x - radius < 0 || x + radius > screenWidth) {
            vx = -vx
        }
        if (y - radius < 0 || y + radius > screenHeight) {
            vy = -vy
        }
    }
}

