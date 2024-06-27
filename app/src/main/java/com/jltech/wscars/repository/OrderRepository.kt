package com.jltech.wscars.repository

import DatabaseHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.jltech.wscars.api.service.WSCarsService
import com.jltech.wscars.data.model.Car
import com.jltech.wscars.data.model.Client
import com.jltech.wscars.data.model.Order
import com.jltech.wscars.utils.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class OrderRepository(
    private val dbHelper: DatabaseHelper,
    private val wscarsService: WSCarsService
) {

    fun addOrder(order: Order): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            db.beginTransaction()


            val clientValues = ContentValues().apply {
                put(Constants.CLIENT_NAME, order.client.nome)
                put(Constants.CLIENT_EMAIL, order.client.email)
                put(Constants.CLIENT_PHONE, order.client.phone)
                put(Constants.CLIENT_BIRTHDAY, order.client.birthday)
            }
            val clientId = db.insert(Constants.TABLE_CLIENT, null, clientValues)


            val carValues = ContentValues().apply {
                put(Constants.CAR_ID_MODEL, order.client.car.idModel)
                put(Constants.CAR_YEAR, order.client.car.ano)
                put(Constants.CAR_FUEL_TYPE, order.client.car.fuelType)
                put(Constants.CAR_NUM_DOORS, order.client.car.numDoor)
                put(Constants.CAR_COLOR, order.client.car.color)
                put(Constants.CAR_NAME_MODEL, order.client.car.nameModel)
                put(Constants.CAR_PRICE, order.client.car.price)
                put(Constants.CLIENT_ID_REF, clientId)
            }
            val carId = db.insert(Constants.TABLE_CAR, null, carValues)


            val orderValues = ContentValues().apply {
                put(Constants.CLIENT_ID_REF, clientId)
                put(Constants.ORDER_CREATED_AT, getCurrentDateTime())
            }
            db.insert(Constants.TABLE_ORDER, null, orderValues)

            db.setTransactionSuccessful()
            true
        } catch (e: SQLiteException) {
            e.printStackTrace()
            false
        } finally {
            db.endTransaction()
            db.close()
        }
    }



    fun getOrders(): List<Order> {
        val orders = mutableListOf<Order>()
        val db = dbHelper.readableDatabase

        val selectQuery = """
        SELECT 
            ${Constants.TABLE_ORDER}.${Constants.ORDER_ID},
            ${Constants.TABLE_ORDER}.${Constants.ORDER_CREATED_AT},
            ${Constants.TABLE_CLIENT}.${Constants.CLIENT_ID} AS client_id,
            ${Constants.TABLE_CLIENT}.${Constants.CLIENT_NAME},
            ${Constants.TABLE_CLIENT}.${Constants.CLIENT_EMAIL},
            ${Constants.TABLE_CLIENT}.${Constants.CLIENT_PHONE},
            ${Constants.TABLE_CLIENT}.${Constants.CLIENT_BIRTHDAY},
            ${Constants.TABLE_CAR}.${Constants.CAR_ID} AS car_id,
            ${Constants.TABLE_CAR}.${Constants.CAR_ID_MODEL},
            ${Constants.TABLE_CAR}.${Constants.CAR_YEAR},
            ${Constants.TABLE_CAR}.${Constants.CAR_FUEL_TYPE},
            ${Constants.TABLE_CAR}.${Constants.CAR_NUM_DOORS},
            ${Constants.TABLE_CAR}.${Constants.CAR_COLOR},
            ${Constants.TABLE_CAR}.${Constants.CAR_NAME_MODEL},
            ${Constants.TABLE_CAR}.${Constants.CAR_PRICE}
        FROM ${Constants.TABLE_ORDER}
        INNER JOIN ${Constants.TABLE_CLIENT} 
            ON ${Constants.TABLE_ORDER}.${Constants.CLIENT_ID_REF} = ${Constants.TABLE_CLIENT}.${Constants.CLIENT_ID}
        INNER JOIN ${Constants.TABLE_CAR} 
            ON ${Constants.TABLE_CLIENT}.${Constants.CLIENT_ID} = ${Constants.TABLE_CAR}.${Constants.CLIENT_ID_REF}
    """

        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val orderId = cursor.getInt(cursor.getColumnIndexOrThrow(Constants.ORDER_ID))
                val createdAt = cursor.getString(cursor.getColumnIndexOrThrow(Constants.ORDER_CREATED_AT))

                val clientId = cursor.getInt(cursor.getColumnIndexOrThrow("client_id"))
                val clientName = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CLIENT_NAME))
                val clientEmail = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CLIENT_EMAIL))
                val clientPhone = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CLIENT_PHONE))
                val clientBirthday = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CLIENT_BIRTHDAY))

                val carId = cursor.getInt(cursor.getColumnIndexOrThrow("car_id"))
                val carIdModel = cursor.getInt(cursor.getColumnIndexOrThrow(Constants.CAR_ID_MODEL))
                val carYear = cursor.getInt(cursor.getColumnIndexOrThrow(Constants.CAR_YEAR))
                val carFuelType = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CAR_FUEL_TYPE))
                val carNumDoors = cursor.getInt(cursor.getColumnIndexOrThrow(Constants.CAR_NUM_DOORS))
                val carColor = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CAR_COLOR))
                val carNameModel = cursor.getString(cursor.getColumnIndexOrThrow(Constants.CAR_NAME_MODEL))
                val carPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(Constants.CAR_PRICE))

                val car = Car(carId, carIdModel, carYear, carFuelType, carNumDoors, carColor, carNameModel, carPrice)
                val client = Client(clientId, clientName, clientEmail, clientPhone, clientBirthday, car)
                val order = Order(orderId, client, createdAt)

                orders.add(order)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return orders
    }

    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

     fun postLeads(order: Order) = wscarsService.postLeads(order)

     fun postLeadsList(listOrder: List<Order>) = wscarsService.postLeadsList(listOrder)


}