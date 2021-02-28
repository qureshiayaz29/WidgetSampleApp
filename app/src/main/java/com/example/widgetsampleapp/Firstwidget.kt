package com.example.widgetsampleapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class Firstwidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    //1. create remote view
    val remoteViews = RemoteViews(context.packageName, R.layout.firstwidget)

    //2. define intent --> action which will be performed
    val intent = Intent(Intent.ACTION_VIEW)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.parse("https://insideandroid.in")

    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    //3. set pending intent on view
    remoteViews.setOnClickPendingIntent(R.id.openWebButton, pendingIntent)

    //4. update the widget
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}