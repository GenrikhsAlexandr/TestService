package com.genrikhs.testservice

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyJobIntentService : JobIntentService() {

    override fun onCreate() {
        super.onCreate()
        log("onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        log("onStartCommand")
            val page = intent.getIntExtra(PAGE, 0)
            for (i in 0 until 3) {
                Thread.sleep(1000)
                log("Timer $i $page")
            }
        }


    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyJobIntentService: $message")
    }

    companion object {

        private const val JOB_ID = 111
        const val PAGE = "page"

        fun enqueue (context: Context, page:Int){
            enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(page)
            )
        }

       private fun newIntent(page: Int): Intent {
            return Intent().apply {
                putExtra(PAGE, page)
            }
        }
    }
}