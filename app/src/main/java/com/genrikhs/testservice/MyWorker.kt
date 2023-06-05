package com.genrikhs.testservice

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlin.math.log

class MyWorker(
    context: Context,
    private val workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        log("onHandleIntent")
        val page = workerParameters.inputData.getInt(PAGE, 0)

        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyWorker: $message")
    }

    companion object {
        const val PAGE = "page"
        const val WORK_NAME = "work name"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeConstraints())
                .build()
        }

        private fun makeConstraints(): Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
    }
}