package com.example.iot.animasi

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.Nullable

class MyView @JvmOverloads constructor(
    context: Context?,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
    persenTextView: TextView? = null
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var wavePaint: Paint? = null
    private var circlePaint: Paint? = null
    private var textPaint: Paint? = null
    private var screenWidth = 0
    private var screenHeight = 0
    private val amplitude = 100
    private var path: Path? = null
    private var progress = 0f
    private var textProgress = 0f
    private val startPoint = Point()
    private var persenTextView: TextView? = null

    init {
        this.persenTextView = persenTextView
        init()
    }

    fun setPercentage(percentage: Float) {
        textProgress = percentage
        if (percentage == 100f) {
            progress = percentage + amplitude
        } else {
            progress = percentage
        }
        invalidate()
        persenTextView?.text = percentage.toInt().toString()
    }

    fun setPercentageFromTextView(text: CharSequence) {
        val persenValue = text.toString().toFloatOrNull() ?: 10.0f
        setPercentage(persenValue)
    }
    fun setProgress(progress: Float) {
        textProgress = progress
        if (progress == 100f) {
            this.progress = progress + amplitude
        } else {
            this.progress = progress
        }
        invalidate()
    }

    private fun init() {
        wavePaint = Paint()
        wavePaint!!.isAntiAlias = true
        wavePaint!!.strokeWidth = 10f

        textPaint = Paint()
        textPaint!!.style = Paint.Style.STROKE
        textPaint!!.isAntiAlias = true
        textPaint!!.color = Color.parseColor("#FF000000")
        textPaint!!.textSize = 50f
        textPaint!!.isFakeBoldText = true

        circlePaint = Paint()
        circlePaint!!.isAntiAlias = true
        circlePaint!!.color = Color.parseColor("#292929")
        circlePaint!!.strokeWidth = 10f
        circlePaint!!.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = Math.min(measureSize(200, widthMeasureSpec), measureSize(200, heightMeasureSpec))
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w
        screenHeight = h
        startPoint.x = -screenWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        clipCircle(canvas)
        drawCircle(canvas)
        drawWave(canvas)
        drawText(canvas)
        postInvalidateDelayed(10)
    }

    private fun drawText(canvas: Canvas) {
        val targetRect = Rect(0, -screenHeight, screenWidth, 0)
        val fontMetrics = textPaint!!.fontMetricsInt
        val baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2
        textPaint!!.textAlign = Paint.Align.CENTER
        canvas.drawText("${textProgress}%", targetRect.centerX().toFloat(), baseline.toFloat(), textPaint!!)
    }

    private fun drawWave(canvas: Canvas) {
        val height = (progress / 100 * screenHeight).toInt()
        startPoint.y = -height
        canvas.translate(0f, screenHeight.toFloat())
        path = Path()
        wavePaint!!.style = Paint.Style.FILL
        wavePaint!!.color = Color.parseColor("#000080")
        val wave = screenWidth / 4
        path!!.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
        for (i in 0..3) {
            val startX = startPoint.x + i * wave * 2
            val endX = startX + 2 * wave
            if (i % 2 == 0) {
                path!!.quadTo(
                    ((startX + endX) / 2).toFloat(),
                    (startPoint.y + amplitude).toFloat(),
                    endX.toFloat(),
                    startPoint.y.toFloat()
                )
            } else {
                path!!.quadTo(
                    ((startX + endX) / 2).toFloat(),
                    (startPoint.y - amplitude).toFloat(),
                    endX.toFloat(),
                    startPoint.y.toFloat()
                )
            }
        }
        path!!.lineTo(screenWidth.toFloat(), (screenHeight / 2).toFloat())
        path!!.lineTo(-screenWidth.toFloat(), (screenHeight / 2).toFloat())
        path!!.lineTo(-screenWidth.toFloat(), 0f)
        path!!.close()
        canvas.drawPath(path!!, wavePaint!!)
        startPoint.x += 10
        if (startPoint.x > 0) {
            startPoint.x = -screenWidth
        }
        path!!.reset()
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(
            (screenWidth / 2).toFloat(),
            (screenHeight / 2).toFloat(),
            (screenHeight / 2).toFloat(),
            circlePaint!!
        )
    }

    private fun clipCircle(canvas: Canvas) {
        val circlePath = Path()
        circlePath.addCircle(
        (screenWidth / 2).toFloat(),
        (screenHeight / 2).toFloat(),
        (screenHeight / 2).toFloat(),
        Path.Direction.CW
        )
        canvas.clipPath(circlePath)
    }

    private fun measureSize(defaultSize: Int, measureSpec: Int): Int {
        var result = defaultSize
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.UNSPECIFIED -> result = defaultSize
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> result = size
            else -> {
            }
        }
        return result
    }

    init {
        init()
    }
}