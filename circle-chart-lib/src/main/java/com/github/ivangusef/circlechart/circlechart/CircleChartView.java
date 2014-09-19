package com.github.ivangusef.circlechart.circlechart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ivan on 19.09.14.
 */
public class CircleChartView extends View {

    private final Paint mPaint;
    private final RectF mOval;

    private float[] mSectionPercents;
    private int[] mSectionColors;

    public CircleChartView(Context context) {
        this(context, null);
    }

    public CircleChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mOval = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final float centerX = (float) w / 2;
        final float centerY = (float) h / 2;

        final float radius = w > h ? (float) h / 2 : (float) w / 2;
        mOval.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mSectionColors == null || mSectionPercents == null) {
            return;
        }

        CircleChartView.drawChart(canvas, mSectionPercents, mSectionColors, mOval, mPaint);
    }

    /**
     * Sets data to chart and draws it.
     *
     * @param data   array of doubles for each section
     * @param colors array of colors for each section
     */
    public void setData(@NonNull double[] data, @NonNull int[] colors) {
        if (data.length != colors.length) {
            throw new IllegalArgumentException("Arrays must be the same length");
        }

        mSectionPercents = CircleChartView.calculatePercentage(data);
        mSectionColors = colors;

        invalidate();
    }

    private static float[] calculatePercentage(@NonNull double[] data) {
        final float[] percentage = new float[data.length];

        double sum = 0;
        for (double chunk : data) {
            sum += chunk;
        }

        final double perPercentChunk = sum / 100;
        for (int i = 0; i < data.length; i++) {
            percentage[i] = (float) (data[i] / perPercentChunk);
        }

        return percentage;
    }

    private static void drawChart(@NonNull Canvas canvas, @NonNull float[] percents,
                                  @NonNull int[] colors, @NonNull RectF oval,
                                  @NonNull Paint paint) {
        float prevSectionAngle = 0;
        float anglesPerPercent = (float) 360 / 100;
        for (int i = 0; i < percents.length; i++) {
            paint.setColor(colors[i]);

            float startAngle = prevSectionAngle;
            float sweepAngle = anglesPerPercent * percents[i];
            canvas.drawArc(
                    oval,
                    startAngle,
                    sweepAngle,
                    true,
                    paint
            );
            prevSectionAngle += sweepAngle;
        }
    }
}
