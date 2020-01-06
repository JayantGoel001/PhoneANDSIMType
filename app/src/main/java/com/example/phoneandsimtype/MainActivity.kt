package com.example.phoneandsimtype

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.Html
import android.view.Display
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
@SuppressLint("HardwareIds", "MissingPermission")
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val modelNumber=android.os.Build.MODEL
        val Board=android.os.Build.BOARD
        val Brand=android.os.Build.BRAND
        val Display=android.os.Build.DISPLAY
        val FingerPrint=android.os.Build.FINGERPRINT
        val ID=android.os.Build.ID
        val TAGS=android.os.Build.TAGS
        val Type=android.os.Build.TYPE
        val androidVersion=android.os.Build.VERSION.RELEASE
        val APILevel=android.os.Build.VERSION.SDK_INT
        val CodeName=android.os.Build.VERSION.CODENAME
        val incremental=android.os.Build.VERSION.INCREMENTAL

        textView.text= Html.fromHtml("<h1>Phone Type</h1>"
                +"<br/><font color='red';>Model Number : </font>" +modelNumber
                +"<br/><font color='red';>Board : </font>"+Board
                +"<br/><font color='red';>Brand : </font>"+Brand+
                "<br/><font color='red';>Display : </font>"+Display+
                "<br/><font color='red';>FingerPrint : </font>"+FingerPrint+
                "<br/><font color='red';>ID : </font>"+ID+
                "<br/><font color='red';>TAGS : </font>"+TAGS+
                "<br/><font color='red';>Type : </font>"+Type +"<br/>"+
                "<br/><font color='red';>Android Version : </font>"+androidVersion
                +"<br/><font color='red';>API Level : </font>"+APILevel
                +"<br/><font color='red';>Code Name : </font>"+CodeName
                +"<br/><font color='red';>Model Number : </font>"+incremental)

        //SIM Type
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED)
        {
            telPhone()

        }
        else
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_PHONE_STATE),1234)
        }

        //"<br/><font color='green';>Data Enabled : </font>"+tel.isDataEnabled)
    }

    fun telPhone() {

        val tel=getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val x:Boolean
        x = tel.callState != 0
        simType.text=Html.fromHtml("<h1>SIM Type</h1>" +
                "<br/><font Color='green';>Operator Code : </font>"+tel.simOperator.toString()+"" +
                "<br/><font color='green';>Operator Name : </font>"+tel.simOperatorName.toString()+"" +
                "<br/><font color='green';>Country ISO : </font>"+tel.simCountryIso.toString()+"" +
                "<br/><font color='green';>Signal Strength : </font><br/>"+tel.signalStrength+"" +
                "<br/><font color='green';>Call State : <br/></font>"+x+
                "<br/><font color='green';>SIM Serial Number : <br/></font>"+tel.simSerialNumber)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1234)
        {
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED)
            {
                telPhone()
            }
            else
            {
                Toast.makeText(this,"Permission Denied ",Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
