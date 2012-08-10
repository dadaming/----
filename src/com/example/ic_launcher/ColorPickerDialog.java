package com.example.ic_launcher;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ColorPickerDialog extends Dialog
{
	private final String TAG = "ColorPicker";
	Context context;
	private final boolean debug = true;
	private int mInitialColor;
	private OnColorChangedListener mListener;
	private String title;

	public ColorPickerDialog(Context paramContext, int paramInt, String paramString, OnColorChangedListener paramOnColorChangedListener)
	{
		super(paramContext);
		this.context = paramContext;
		this.mListener = paramOnColorChangedListener;
		this.mInitialColor = paramInt;
		this.title = paramString;
	}

	public ColorPickerDialog(Context paramContext, String paramString, OnColorChangedListener paramOnColorChangedListener)
	{
		this(paramContext, -16777216, paramString, paramOnColorChangedListener);
	}

	public String getTitle()
	{
		return this.title;
	}

	public int getmInitialColor()
	{
		return this.mInitialColor;
	}

	public OnColorChangedListener getmListener()
	{
		return this.mListener;
	}

	protected void onCreate(Bundle paramBundle)
	{
		super.onCreate(paramBundle);
		WindowManager localWindowManager = getWindow().getWindowManager();
		int i = (int)(0.6F * localWindowManager.getDefaultDisplay().getHeight());
		int j = (int)(0.8F * localWindowManager.getDefaultDisplay().getWidth());
		setContentView(new ColorPickerView(this.context, i, j));
		setTitle(this.title);
	}

	public void setmInitialColor(int paramInt)
	{
		this.mInitialColor = paramInt;
	}

	public void setmListener(OnColorChangedListener paramOnColorChangedListener)
	{
		this.mListener = paramOnColorChangedListener;
	}

	private class ColorPickerView extends View
	{
		private float centerRadius;
		private boolean downInCircle = true;
		private boolean downInRect;
		private boolean highlightCenter;
		private boolean highlightCenterLittle;
		private Paint mCenterPaint;
		private final int[] mCircleColors;
		private int mHeight;
		private Paint mLinePaint;
		private Paint mPaint;
		private final int[] mRectColors;
		private Paint mRectPaint;
		private int mWidth;
		private float r;
		private float rectBottom;
		private float rectLeft;
		private float rectRight;
		private Shader rectShader;
		private float rectTop;

		public ColorPickerView(Context paramInt1, int paramInt2, int arg4)
		{
			super(paramInt1);
			this.mHeight = paramInt2;
			int i = 0;
			this.mWidth = i;
			setMinimumHeight(this.mHeight);
			setMinimumWidth(this.mWidth);
			int[] arrayOfInt1 = new int[7];
			arrayOfInt1[0] = -65536;
			arrayOfInt1[1] = -65281;
			arrayOfInt1[2] = -16776961;
			arrayOfInt1[3] = -16711681;
			arrayOfInt1[4] = -16711936;
			arrayOfInt1[5] = -256;
			arrayOfInt1[6] = -65536;
			this.mCircleColors = arrayOfInt1;
			SweepGradient localSweepGradient = new SweepGradient(0.0F, 0.0F, this.mCircleColors, null);
			this.mPaint = new Paint(1);
			this.mPaint.setShader(localSweepGradient);
			this.mPaint.setStyle(Paint.Style.STROKE);
			this.mPaint.setStrokeWidth(50.0F);
			this.r = (0.7F * (this.mWidth / 2) - 0.5F * this.mPaint.getStrokeWidth());
			this.mCenterPaint = new Paint(1);
			this.mCenterPaint.setColor(ColorPickerDialog.this.mInitialColor);
			this.mCenterPaint.setStrokeWidth(5.0F);
			this.centerRadius = (0.7F * (this.r - this.mPaint.getStrokeWidth() / 2.0F));
			this.mLinePaint = new Paint(1);
			this.mLinePaint.setColor(Color.parseColor("#72A1D1"));
			this.mLinePaint.setStrokeWidth(4.0F);
			int[] arrayOfInt2 = new int[3];
			arrayOfInt2[0] = -16777216;
			arrayOfInt2[1] = this.mCenterPaint.getColor();
			arrayOfInt2[2] = -1;
			this.mRectColors = arrayOfInt2;
			this.mRectPaint = new Paint(1);
			this.mRectPaint.setStrokeWidth(5.0F);
			this.rectLeft = (-this.r - 0.5F * this.mPaint.getStrokeWidth());
			this.rectTop = (15.0F + (this.r + 0.5F * this.mPaint.getStrokeWidth() + 0.5F * this.mLinePaint.getStrokeMiter()));
			this.rectRight = (this.r + 0.5F * this.mPaint.getStrokeWidth());
			this.rectBottom = (50.0F + this.rectTop);
		}

		private int ave(int paramInt1, int paramInt2, float paramFloat)
		{
			return paramInt1 + Math.round(paramFloat * (paramInt2 - paramInt1));
		}

		private int inCenter(float paramFloat1, float paramFloat2, float paramFloat3)
		{
			double d = 3.141592653589793D * paramFloat3 * paramFloat3;
			if (3.141592653589793D * (paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2) < d);
			for (int i = 1; ; i = 0)
				return i;
		}

		private int inColorCircle(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
		{
			double d1 = 3.141592653589793D * paramFloat3 * paramFloat3;
			double d2 = 3.141592653589793D * paramFloat4 * paramFloat4;
			double d3 = 3.141592653589793D * (paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
			if ((d3 < d1) && (d3 > d2));
			for (int i = 1; ; i = 0)
				return i;
		}

		private int inRect(float paramFloat1, float paramFloat2)
		{
			if ((paramFloat1 <= this.rectRight) && (paramFloat1 >= this.rectLeft) && (paramFloat2 <= this.rectBottom) && (paramFloat2 >= this.rectTop));
			for (int i = 1; ; i = 0)
				return i;
		}

		private int interpCircleColor(int[] paramArrayOfInt, float paramFloat)
		{
			int m = 0;
			if (paramFloat <= 0.0F)
				m = paramArrayOfInt[0];
			while (true)
			{
				return m;
				if (paramFloat >= 1.0F)
				{
					m = paramArrayOfInt[(-1 + paramArrayOfInt.length)];
					continue;
				}
				float f1 = paramFloat * (-1 + paramArrayOfInt.length);
				int i = (int)f1;
				float f2 = f1 - i;
				int j = paramArrayOfInt[i];
				int k = paramArrayOfInt[(i + 1)];
				m = Color.argb(ave(Color.alpha(j), Color.alpha(k), f2), ave(Color.red(j), Color.red(k), f2), ave(Color.green(j), Color.green(k), f2), ave(Color.blue(j), Color.blue(k), f2));
			}
		}

		private int interpRectColor(int[] paramArrayOfInt, float paramFloat)
		{
			int i;
			int j;
			float f;
			if (paramFloat < 0.0F)
			{
				i = paramArrayOfInt[0];
				j = paramArrayOfInt[1];
				f = (paramFloat + this.rectRight) / this.rectRight;
			}
			while (true)
			{
				return Color.argb(ave(Color.alpha(i), Color.alpha(j), f), ave(Color.red(i), Color.red(j), f), ave(Color.green(i), Color.green(j), f), ave(Color.blue(i), Color.blue(j), f));
				i = paramArrayOfInt[1];
				j = paramArrayOfInt[2];
				f = paramFloat / this.rectRight;
			}
		}

		protected void onDraw(Canvas paramCanvas)
		{
			paramCanvas.translate(this.mWidth / 2, -50 + this.mHeight / 2);
			paramCanvas.drawCircle(0.0F, 0.0F, this.centerRadius, this.mCenterPaint);
			int i;
			if ((this.highlightCenter) || (this.highlightCenterLittle))
			{
				i = this.mCenterPaint.getColor();
				this.mCenterPaint.setStyle(Paint.Style.STROKE);
				if (!this.highlightCenter)
					break label419;
				this.mCenterPaint.setAlpha(255);
			}
			while (true)
			{
				paramCanvas.drawCircle(0.0F, 0.0F, this.centerRadius + this.mCenterPaint.getStrokeWidth(), this.mCenterPaint);
				this.mCenterPaint.setStyle(Paint.Style.FILL);
				this.mCenterPaint.setColor(i);
				paramCanvas.drawOval(new RectF(-this.r, -this.r, this.r, this.r), this.mPaint);
				if (this.downInCircle)
					this.mRectColors[1] = this.mCenterPaint.getColor();
				this.rectShader = new LinearGradient(this.rectLeft, 0.0F, this.rectRight, 0.0F, this.mRectColors, null, Shader.TileMode.MIRROR);
				this.mRectPaint.setShader(this.rectShader);
				paramCanvas.drawRect(this.rectLeft, this.rectTop, this.rectRight, this.rectBottom, this.mRectPaint);
				float f = this.mLinePaint.getStrokeWidth() / 2.0F;
				paramCanvas.drawLine(this.rectLeft - f, this.rectTop - f * 2.0F, this.rectLeft - f, this.rectBottom + f * 2.0F, this.mLinePaint);
				paramCanvas.drawLine(this.rectLeft - f * 2.0F, this.rectTop - f, this.rectRight + f * 2.0F, this.rectTop - f, this.mLinePaint);
				paramCanvas.drawLine(f + this.rectRight, this.rectTop - f * 2.0F, f + this.rectRight, this.rectBottom + f * 2.0F, this.mLinePaint);
				paramCanvas.drawLine(this.rectLeft - f * 2.0F, f + this.rectBottom, this.rectRight + f * 2.0F, f + this.rectBottom, this.mLinePaint);
				super.onDraw(paramCanvas);
				return;
				label419: if (!this.highlightCenterLittle)
					continue;
				this.mCenterPaint.setAlpha(144);
			}
		}

		protected void onMeasure(int paramInt1, int paramInt2)
		{
			super.onMeasure(this.mWidth, this.mHeight);
		}

		public boolean onTouchEvent(MotionEvent paramMotionEvent)
		{
			float f1 = paramMotionEvent.getX() - this.mWidth / 2;
			float f2 = 50.0F + (paramMotionEvent.getY() - this.mHeight / 2);
			boolean bool1 = inColorCircle(f1, f2, this.r + this.mPaint.getStrokeWidth() / 2.0F, this.r - this.mPaint.getStrokeWidth() / 2.0F);
			boolean bool2 = inCenter(f1, f2, this.centerRadius);
			boolean bool3 = inRect(f1, f2);
			switch (paramMotionEvent.getAction())
			{
			default:
			case 0:
			case 2:
			case 1:
			}
			while (true)
			{
				return true;
				this.downInCircle = bool1;
				this.downInRect = bool3;
				this.highlightCenter = bool2;
				if ((this.downInCircle) && (bool1))
				{
					float f3 = (float)((float)Math.atan2(f2, f1) / 6.283185307179586D);
					if (f3 < 0.0F)
						f3 += 1.0F;
					this.mCenterPaint.setColor(interpCircleColor(this.mCircleColors, f3));
					Log.v("ColorPicker", "色环内, 坐标: " + f1 + "," + f2);
					label228: Log.v("ColorPicker", "[MOVE] 高亮: " + this.highlightCenter + "微亮: " + this.highlightCenterLittle + " 中心: " + bool2);
					if (((!this.highlightCenter) || (!bool2)) && ((!this.highlightCenterLittle) || (!bool2)))
						break label351;
					this.highlightCenter = true;
					this.highlightCenterLittle = false;
				}
				while (true)
				{
					invalidate();
					break;
					if ((!this.downInRect) || (!bool3))
						break label228;
					this.mCenterPaint.setColor(interpRectColor(this.mRectColors, f1));
					break label228;
					label351: if ((this.highlightCenter) || (this.highlightCenterLittle))
					{
						this.highlightCenter = false;
						this.highlightCenterLittle = true;
						continue;
					}
					this.highlightCenter = false;
					this.highlightCenterLittle = false;
				}
				if ((this.highlightCenter) && (bool2) && (ColorPickerDialog.this.mListener != null))
				{
					ColorPickerDialog.this.mListener.colorChanged(this.mCenterPaint.getColor());
					ColorPickerDialog.this.dismiss();
				}
				if (this.downInCircle)
					this.downInCircle = false;
				if (this.downInRect)
					this.downInRect = false;
				if (this.highlightCenter)
					this.highlightCenter = false;
				if (this.highlightCenterLittle)
					this.highlightCenterLittle = false;
				invalidate();
			}
		}
	}

	public static abstract interface OnColorChangedListener
	{
		public abstract void colorChanged(int paramInt);
	}
}