package com.example.ballanimation


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView


class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var thread: Thread
    private var ball: Ball? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        surfaceView = findViewById(R.id.surfaceView)
        surfaceHolder = surfaceView.holder
        surfaceHolder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        ball = Ball(100f, 100f, 5f, 5f, 50f)
        thread = Thread {
            while (true) {
                val canvas = surfaceHolder.lockCanvas()
                if (canvas != null) {
                    canvas.drawColor(Color.WHITE)
                    drawBall(canvas)
                    updateBall()
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
                try {
                    Thread.sleep(16)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                    break
                }
            }
        }
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Do nothing
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.interrupt()
    }

    private fun drawBall(canvas: Canvas) {
        if (ball != null) {
            val paint = Paint()
            paint.color = Color.BLUE
            canvas.drawCircle(ball!!.x, ball!!.y, ball!!.radius, paint)
        }
    }

    private fun updateBall() {
        if (ball != null) {
            ball!!.updatePosition()
            ball!!.checkCollision(surfaceView.width, surfaceView.height)
        }
    }
}

