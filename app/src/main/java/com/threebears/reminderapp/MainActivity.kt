package com.threebears.reminderapp

//import androidx.lifecycle.viewmodel.CreationExtras.Empty.map

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    var calFrag = CalendarFragment()
    var timerFrag = TimerFragment()
//    FirebaseApp.initializeApp(this)
    lateinit var firebasedb:FirebaseDatabase
    lateinit var firebasedbRef:DatabaseReference
//    var firebasedb = Firebase.database
//    var firebasedbRef = Firebase.database.reference
    lateinit var sampleData: SampleData
    lateinit var textView:TextView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebasedb = FirebaseDatabase.getInstance()
        firebasedbRef = firebasedb.reference
//        var timePicker: TimePicker = findViewById(R.id.time_picker)
//        var setBtn: Button = findViewById(R.id.set)
//        var cal:Calendar = Calendar.getInstance()
//        var alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

//        setBtn.setOnClickListener {
//            cal.set(Calendar.HOUR_OF_DAY, timePicker.hour)
//            cal.set(Calendar.MINUTE, timePicker.minute)
//            println("set btn clicked")
//
//            var intent = Intent(this,AlarmReceiver::class.java)
//            var pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_MUTABLE)
//
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pendingIntent)
//            println("alarm set")
//            Toast.makeText(this, "ALARM SET", Toast.LENGTH_SHORT).show()
//
//        }
//        /***  firebase database*/
     /*   var editText1:EditText = findViewById(R.id.edit_tx1)
        var editText2:EditText = findViewById(R.id.edit_tx2)
        var btn:Button = findViewById(R.id.btn1)
        var getBtn:Button = findViewById(R.id.btn2)
        var delBtn:Button = findViewById(R.id.btn3)
         textView = findViewById(R.id.txtView1)

        firebasedb.getReference("message")

        btn.setOnClickListener {
          sampleData = (SampleData(editText1.text.toString(),editText2.text.toString()))
            println("sampleData $sampleData")
            addDataToDB(sampleData)
        }
        getBtn.setOnClickListener {
            getDataFromDB()
        }
        delBtn.setOnClickListener {
            deleteDataFromDB()
        }
*/
        /***  firebase database*/
        var shareBtn:FloatingActionButton = findViewById(R.id.share)
        shareBtn.setOnClickListener {
            var intent = Intent(Intent.ACTION_SEND) // intent
            intent.type = "text/plain"
//            intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.threebears.newsapp")
            intent.putExtra(Intent.EXTRA_TEXT, "Dummy test message")
            startActivity(intent)
        }

        var btnNav = findViewById<BottomNavigationView>(R.id.btm_toolbar)
        var addTask:FloatingActionButton = findViewById(R.id.add_task)
        addTask.setOnClickListener {
            replaceFragment(timerFrag)
        }
        btnNav.setOnItemSelectedListener {item ->
            when(item.itemId){
            R.id.calender ->{

//                replaceFragment(calFrag)

                true
            }
                R.id.menu ->{

                    true
                }
            else -> true
            }

        }

    }

    /***  firebase database*/
/*
    private fun addDataToDB(sampleData: SampleData){
     firebasedbRef.addValueEventListener(object :ValueEventListener{
         override fun onDataChange(snapshot: DataSnapshot) {
         firebasedbRef.child("test1") .setValue(sampleData)
             Toast.makeText(this@MainActivity, "data added", Toast.LENGTH_SHORT).show();
            if(isFinishing) {
                firebasedbRef.child("test1").removeEventListener(this)
            }
         }
         override fun onCancelled(error: DatabaseError) {
             Toast.makeText(this@MainActivity, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
         }

     })
    }
    private fun getDataFromDB(){
        println("called getDataFromDB function")
        firebasedbRef.child("test1") .addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val sampleHashMap: HashMap<String, SampleData> = snapshot.value as (HashMap<String, SampleData>)
                val values: Collection<SampleData> = sampleHashMap.values
                val listOfValues = ArrayList(values) // as SampleData
                for (i in 0..values.size-1){
                    println("values ${listOfValues[i]}")
                }
              textView.text = listOfValues[0].toString() +"  " + listOfValues[1].toString()
                Toast.makeText(this@MainActivity, "data retrived", Toast.LENGTH_SHORT).show()
                if(isFinishing) {
                    firebasedbRef.child("test1").removeEventListener(this)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Fail to retrive data " + error, Toast.LENGTH_SHORT).show();
            }

        })
    }
    private fun deleteDataFromDB(){
        println("deleteDataFromDB called")
        firebasedbRef.child("test1").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                firebasedbRef.child("test1").removeValue()
                firebasedbRef.child("test1").removeEventListener(this)
                Toast.makeText(this@MainActivity, "data deleted", Toast.LENGTH_SHORT).show()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "data deleted unsuccess", Toast.LENGTH_SHORT).show()
            }

        })
    }
*/
    /***  firebase database*/

    private fun replaceFragment(frag: Fragment){
        if(frag != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, frag)
            transaction.commit()
        }
    }

}