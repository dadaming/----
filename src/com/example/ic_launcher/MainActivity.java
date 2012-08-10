package com.example.ic_launcher;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity
{
  static final String CER_PATH = Environment.getExternalStorageDirectory() + "/艺术签名豪华版/";
  static final int DOWNLOAD_BACK = 1;
  static final String KEY_DOWNLOAD_STATE = "download_state";
  static final String TEMP_NAME = "temp.sign";
  TextView backBtn;
  int bgColor;
  TextView content1;
  TextView content2;
  TextView content3;
  TextView content4;
  TextView content5;
  TextView content6;
  SharedPreferences.Editor editor;
  String[] fontIds;
  int fontIndex;
  String[] fontNames;
  LinearLayout layout1;
  LinearLayout layout2;
  LinearLayout layout3;
  LinearLayout layout4;
  LinearLayout layout5;
  LinearLayout layout6;
  TextView layout7;
  private final Handler mMainHandler;
  String name;
  TextView nameView;
  ProgressDialog pd;
  SharedPreferences prefs;
  ImageView resultImage;
  ScrollView resultView;
  TextView saveBtn;
  ScrollView setLayout;
  int shadowColor;
  int sizeIndex;
  String[] sizeNames;
  String[] sizeValues;
  int textColor;

  public MainActivity()
  {
    String[] arrayOfString1 = new String[6];
    arrayOfString1[0] = "20";
    arrayOfString1[1] = "40";
    arrayOfString1[2] = "80";
    arrayOfString1[3] = "120";
    arrayOfString1[4] = "160";
    arrayOfString1[5] = "200";
    this.sizeValues = arrayOfString1;
    String[] arrayOfString2 = new String[6];
    arrayOfString2[0] = "迷你";
    arrayOfString2[1] = "小号";
    arrayOfString2[2] = "中等";
    arrayOfString2[3] = "大号";
    arrayOfString2[4] = "特大";
    arrayOfString2[5] = "巨型";
    this.sizeNames = arrayOfString2;
    String[] arrayOfString3 = new String[20];
    arrayOfString3[0] = "a";
    arrayOfString3[1] = "b";
    arrayOfString3[2] = "22";
    arrayOfString3[3] = "6";
    arrayOfString3[4] = "7";
    arrayOfString3[5] = "5";
    arrayOfString3[6] = "4";
    arrayOfString3[7] = "3";
    arrayOfString3[8] = "2";
    arrayOfString3[9] = "1";
    arrayOfString3[10] = "0";
    arrayOfString3[11] = "14";
    arrayOfString3[12] = "16";
    arrayOfString3[13] = "15";
    arrayOfString3[14] = "11";
    arrayOfString3[15] = "12";
    arrayOfString3[16] = "10";
    arrayOfString3[17] = "9";
    arrayOfString3[18] = "13";
    arrayOfString3[19] = "19";
    this.fontIds = arrayOfString3;
    String[] arrayOfString4 = new String[20];
    arrayOfString4[0] = "黑体";
    arrayOfString4[1] = "宋体";
    arrayOfString4[2] = "一笔";
    arrayOfString4[3] = "连笔";
    arrayOfString4[4] = "草书";
    arrayOfString4[5] = "手写";
    arrayOfString4[6] = "艺术";
    arrayOfString4[7] = "娃娃";
    arrayOfString4[8] = "行楷";
    arrayOfString4[9] = "红心";
    arrayOfString4[10] = "公文";
    arrayOfString4[11] = "古文";
    arrayOfString4[12] = "女孩";
    arrayOfString4[13] = "个性";
    arrayOfString4[14] = "咪咪";
    arrayOfString4[15] = "雪峰";
    arrayOfString4[16] = "双线";
    arrayOfString4[17] = "繁体";
    arrayOfString4[18] = "繁草";
    arrayOfString4[19] = "繁隶";
    this.fontNames = arrayOfString4;
    this.mMainHandler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        Bundle localBundle = paramMessage.getData();
        switch (paramMessage.what)
        {
        default:
        case 1:
        }
        while (true)
        {
          return;
          if ((MainActivity.this.pd != null) && (MainActivity.this.pd.isShowing()))
            MainActivity.this.pd.dismiss();
          if (localBundle.getInt("download_state") == 0)
          {
            MainActivity.this.showToast("制作成功");
            Bitmap localBitmap = BitmapFactory.decodeFile(MainActivity.CER_PATH + "temp.sign");
            MainActivity.this.resultImage.setImageBitmap(localBitmap);
            MainActivity.this.setResultShow(true);
            continue;
          }
          MainActivity.this.showToast("制作失败，请检查网络状况");
        }
      }
    };
  }

  void chooseBgColorDialog()
  {
    new ColorPickerDialog(this, this.bgColor, "请选择背景颜色", new ColorPickerDialog.OnColorChangedListener()
    {
      public void colorChanged(int paramInt)
      {
        MainActivity.this.bgColor = paramInt;
        MainActivity.this.saveSetData();
        MainActivity.this.updateLayout6();
      }
    }).show();
  }

  void chooseFontDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(17301659);
    localBuilder.setTitle("选择签名字体");
    localBuilder.setSingleChoiceItems(this.fontNames, this.fontIndex, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        MainActivity.this.fontIndex = paramInt;
        MainActivity.this.saveSetData();
        MainActivity.this.updateLayout2();
      }
    });
    localBuilder.setNegativeButton("取消", null);
    localBuilder.create().show();
  }

  void chooseShadowColorDialog()
  {
    new ColorPickerDialog(this, this.shadowColor, "请选择阴影颜色", new ColorPickerDialog.OnColorChangedListener()
    {
      public void colorChanged(int paramInt)
      {
        MainActivity.this.shadowColor = paramInt;
        MainActivity.this.saveSetData();
        MainActivity.this.updateLayout5();
      }
    }).show();
  }

  void chooseSizeDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(17301659);
    localBuilder.setTitle("选择字体大小");
    localBuilder.setSingleChoiceItems(this.sizeNames, this.sizeIndex, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        MainActivity.this.sizeIndex = paramInt;
        MainActivity.this.saveSetData();
        MainActivity.this.updateLayout3();
      }
    });
    localBuilder.setNegativeButton("取消", null);
    localBuilder.create().show();
  }

  void chooseTextColorDialog()
  {
    new ColorPickerDialog(this, this.textColor, "请选择字体颜色", new ColorPickerDialog.OnColorChangedListener()
    {
      public void colorChanged(int paramInt)
      {
        MainActivity.this.textColor = paramInt;
        MainActivity.this.saveSetData();
        MainActivity.this.updateLayout4();
      }
    }).show();
  }

  public boolean getImage(String paramString)
  {
    int i = 1;
    try
    {
      URL localURL = new URL(paramString);
      try
      {
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
        localHttpURLConnection.setDoInput(true);
        localHttpURLConnection.connect();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        File localFile1 = new File(CER_PATH);
        if (!localFile1.exists())
          localFile1.mkdirs();
        File localFile2 = new File(CER_PATH + "temp.sign");
        if (localFile2.exists())
          localFile2.delete();
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
        byte[] arrayOfByte = new byte[1024];
        while (true)
        {
          int j = localInputStream.read(arrayOfByte);
          if (j == -1)
          {
            localFileOutputStream.flush();
            localFileOutputStream.close();
            localInputStream.close();
            break;
          }
          localFileOutputStream.write(arrayOfByte, 0, j);
        }
      }
      catch (IOException localIOException1)
      {
        label175: localIOException1.printStackTrace();
        i = 0;
      }
    }
    catch (IOException localIOException2)
    {
      break label175;
    }
    return i;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    AlarmReceiver.initAd(this);
    this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
    this.editor = this.prefs.edit();
    this.name = this.prefs.getString("name", "周杰伦");
    this.fontIndex = this.prefs.getInt("fontIndex", 4);
    this.sizeIndex = this.prefs.getInt("sizeIndex", 2);
    this.textColor = this.prefs.getInt("textColor", -1);
    this.shadowColor = this.prefs.getInt("shadowColor", -16777216);
    this.bgColor = this.prefs.getInt("bgColor", -16711936);
    this.setLayout = ((ScrollView)findViewById(2131099649));
    this.layout1 = ((LinearLayout)findViewById(2131099650));
    this.layout2 = ((LinearLayout)findViewById(2131099652));
    this.layout3 = ((LinearLayout)findViewById(2131099653));
    this.layout4 = ((LinearLayout)findViewById(2131099654));
    this.layout5 = ((LinearLayout)findViewById(2131099655));
    this.layout6 = ((LinearLayout)findViewById(2131099656));
    this.layout7 = ((TextView)findViewById(2131099657));
    gridViewOnClickListener localgridViewOnClickListener = new gridViewOnClickListener();
    this.layout1.setOnClickListener(localgridViewOnClickListener);
    this.layout2.setOnClickListener(localgridViewOnClickListener);
    this.layout3.setOnClickListener(localgridViewOnClickListener);
    this.layout4.setOnClickListener(localgridViewOnClickListener);
    this.layout5.setOnClickListener(localgridViewOnClickListener);
    this.layout6.setOnClickListener(localgridViewOnClickListener);
    this.layout7.setOnClickListener(localgridViewOnClickListener);
    this.content1 = ((TextView)this.layout1.findViewById(2131099651));
    this.content2 = ((TextView)this.layout2.findViewById(2131099651));
    this.content3 = ((TextView)this.layout3.findViewById(2131099651));
    this.content4 = ((TextView)this.layout4.findViewById(2131099651));
    this.content5 = ((TextView)this.layout5.findViewById(2131099651));
    this.content6 = ((TextView)this.layout6.findViewById(2131099651));
    this.resultView = ((ScrollView)findViewById(2131099658));
    this.resultImage = ((ImageView)this.resultView.findViewById(2131099659));
    setResultShow(false);
    this.nameView = ((TextView)this.resultView.findViewById(2131099660));
    this.backBtn = ((TextView)this.resultView.findViewById(2131099661));
    this.saveBtn = ((TextView)this.resultView.findViewById(2131099662));
    this.backBtn.setOnClickListener(localgridViewOnClickListener);
    this.saveBtn.setOnClickListener(localgridViewOnClickListener);
    updateLayout1();
    updateLayout2();
    updateLayout3();
    updateLayout4();
    updateLayout5();
    updateLayout6();
  }

  protected void onDestroy()
  {
    AlarmReceiver.sendGetAdMessage(this);
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if ((paramInt == 4) && (this.resultView.getVisibility() == 0))
      setResultShow(false);
    while (true)
    {
      return bool;
      bool = super.onKeyDown(paramInt, paramKeyEvent);
    }
  }

  void saveSetData()
  {
    this.editor.putString("name", this.name);
    this.editor.putInt("fontIndex", this.fontIndex);
    this.editor.putInt("sizeIndex", this.sizeIndex);
    this.editor.putInt("textColor", this.textColor);
    this.editor.putInt("shadowColor", this.shadowColor);
    this.editor.putInt("bgColor", this.bgColor);
    this.editor.commit();
  }

  boolean saveToSdcard()
  {
    int i = 0;
    try
    {
      File localFile1 = new File(CER_PATH + "temp.sign");
      if (localFile1.exists())
      {
        File localFile2 = new File(CER_PATH + this.name + "_" + this.fontNames[this.fontIndex] + ".gif");
        if (localFile2.exists())
          localFile2.delete();
        localFile1.renameTo(localFile2);
        i = 1;
      }
    }
    catch (Exception localException)
    {
    }
    return i;
  }

  void setResultShow(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.setLayout.setVisibility(8);
      this.resultView.setVisibility(0);
    }
    while (true)
    {
      return;
      this.setLayout.setVisibility(0);
      this.resultView.setVisibility(8);
    }
  }

  void showInputNameDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    View localView = LayoutInflater.from(this).inflate(2130903040, null);
    localBuilder.setIcon(17301659);
    localBuilder.setTitle("输入名字");
    localBuilder.setView(localView);
    EditText localEditText = (EditText)localView.findViewById(2131099648);
    localEditText.setText(this.name);
    localBuilder.setPositiveButton("确定",new DialogInterface.OnClickListener(localEditText)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        String str = this.val$inputName.getText().toString();
        if ((str != null) && (str.length() > 0))
        {
          MainActivity.this.name = str;
          MainActivity.this.updateLayout1();
          MainActivity.this.saveSetData();
        }
      }
    });
    localBuilder.setNegativeButton("取消", null);
    localBuilder.create().show();
  }

  void showProgressDialog()
  {
    this.pd = new ProgressDialog(this);
    this.pd.setProgressStyle(0);
    this.pd.setMessage("为您努力制作中...");
    this.pd.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
      {
        if (paramInt == 4);
        for (int i = 1; ; i = 0)
          return i;
      }
    });
    this.pd.show();
    ImageDownTask localImageDownTask = new ImageDownTask();
    localImageDownTask.init(this.mMainHandler);
    new Thread(localImageDownTask).start();
  }

  void showToast(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  void updateLayout1()
  {
    this.content1.setText(this.name);
    this.nameView.setText("名字：" + this.name);
  }

  void updateLayout2()
  {
    this.content2.setText(this.fontNames[this.fontIndex]);
  }

  void updateLayout3()
  {
    this.content3.setText(this.sizeNames[this.sizeIndex]);
  }

  void updateLayout4()
  {
    this.content4.setBackgroundColor(this.textColor);
  }

  void updateLayout5()
  {
    this.content5.setBackgroundColor(this.shadowColor);
  }

  void updateLayout6()
  {
    this.content6.setBackgroundColor(this.bgColor);
  }

  class ImageDownTask
    implements Runnable
  {
    private Handler mHandler;

    ImageDownTask()
    {
    }

    public void init(Handler paramHandler)
    {
      this.mHandler = paramHandler;
    }

    public void run()
    {
      Message localMessage = this.mHandler.obtainMessage(1);
      Bundle localBundle = new Bundle();
      localMessage.setData(localBundle);
      String str1 = Integer.toHexString(MainActivity.this.bgColor);
      String str2 = Integer.toHexString(MainActivity.this.textColor);
      String str3 = Integer.toHexString(MainActivity.this.shadowColor);
      if (str1.length() == 8)
      {
        str1 = str1.substring(2);
        str2 = str2.substring(2);
        str3 = str3.substring(2);
      }
      String str4 = "#" + str1.toUpperCase();
      String str5 = "#" + str2.toUpperCase();
      String str6 = "#" + str3.toUpperCase();
      HttpPost localHttpPost = new HttpPost("http://jiqie.com/a/re2.php");
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("id", MainActivity.this.name));
      localArrayList.add(new BasicNameValuePair("id1", MainActivity.this.sizeValues[MainActivity.this.sizeIndex]));
      localArrayList.add(new BasicNameValuePair("id2", str4));
      localArrayList.add(new BasicNameValuePair("id3", MainActivity.this.fontIds[MainActivity.this.fontIndex]));
      localArrayList.add(new BasicNameValuePair("id4", str5));
      localArrayList.add(new BasicNameValuePair("id5", str6));
      try
      {
        localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
        HttpResponse localHttpResponse = new DefaultHttpClient().execute(localHttpPost);
        if (localHttpResponse.getStatusLine().getStatusCode() == 200)
        {
          String str7 = EntityUtils.toString(localHttpResponse.getEntity());
          int i = str7.indexOf("..");
          if (i > 0)
          {
            String str8 = str7.substring(i + 2);
            String str9 = str8.substring(0, str8.indexOf("')"));
            String str10 = "http://jiqie.com" + str9;
            if (MainActivity.this.getImage(str10))
              localBundle.putInt("download_state", 0);
            while (true)
            {
              this.mHandler.sendMessage(localMessage);
              return;
              localBundle.putInt("download_state", 1);
            }
          }
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localBundle.putInt("download_state", 1);
          localException.printStackTrace();
          continue;
          localBundle.putInt("download_state", 1);
          continue;
          localBundle.putInt("download_state", 1);
        }
      }
    }
  }

  class gridViewOnClickListener
    implements View.OnClickListener
  {
    gridViewOnClickListener()
    {
    }

    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131099651:
      case 2131099658:
      case 2131099659:
      case 2131099660:
      default:
      case 2131099650:
      case 2131099652:
      case 2131099653:
      case 2131099654:
      case 2131099655:
      case 2131099656:
      case 2131099657:
      case 2131099661:
      case 2131099662:
      }
      while (true)
      {
        return;
        MainActivity.this.showInputNameDialog();
        continue;
        MainActivity.this.chooseFontDialog();
        continue;
        MainActivity.this.chooseSizeDialog();
        continue;
        MainActivity.this.chooseTextColorDialog();
        continue;
        MainActivity.this.chooseShadowColorDialog();
        continue;
        MainActivity.this.chooseBgColorDialog();
        continue;
        MainActivity.this.showProgressDialog();
        continue;
        MainActivity.this.setResultShow(false);
        continue;
        if (MainActivity.this.saveToSdcard())
        {
          MainActivity.this.showToast("保存成功");
          continue;
        }
        MainActivity.this.showToast("保存失败,请坚持sdcard是否正常");
      }
    }
  }
}