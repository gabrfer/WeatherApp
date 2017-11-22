package fer.kotlin.weatherapp.data.dbHerriak

/**
 * Created by Default on 06/10/2017.
 */

import android.database.Cursor
import fer.kotlin.weatherapp.domain.model.Herria
import fer.kotlin.weatherapp.domain.model.HerriaKFilter
import fer.kotlin.weatherapp.ui.App

class HerriakDb(
        val herriakDbHelper: HerriakOpenHelper = HerriakOpenHelper(App.instance)) {

    //Indice de las columnas en la tabla Herriak
    companion object {
        val HERRI_KODEA_IDX = 0
        val IZENA_IDX = 1
        val PROBINTZIA_IDX = 2
        val AUTONOMI_ERKIDEGOA_IDX = 3
        val AUTONOMI_ERKIDEGOA_EUS_IDX = 4
        val LONGITUDEA_IDX = 5
        val LATITUDEA_IDX = 6
    }

    fun requestHerriak(filter: HerriaKFilter): MutableList<Herria>  {

        val result: MutableList<Herria> = mutableListOf()
        val db = herriakDbHelper.readableDatabase

        val cur: Cursor = db.rawQuery(getFilterSql(filter), null)
        while (cur.moveToNext()) {
            val herria: Herria = Herria(cur.getString(HERRI_KODEA_IDX),
                                        cur.getString(IZENA_IDX),
                                        cur.getString(PROBINTZIA_IDX),
                                        cur.getString(AUTONOMI_ERKIDEGOA_IDX),
                                        cur.getString(AUTONOMI_ERKIDEGOA_EUS_IDX),
                                        cur.getString(LONGITUDEA_IDX),
                                        cur.getString(LATITUDEA_IDX))

            result.add(herria)
        }
        cur.close()

        return result
    }

    fun requestHerriakAutoComplete(text: String): List<Herria> {

        val result: MutableList<Herria> = mutableListOf()
        val db = herriakDbHelper.readableDatabase

        val cur: Cursor = db.rawQuery("select * from herria where izena like '%$text%'", null)
        while (cur.moveToNext()) {
            val herria: Herria = Herria(cur.getString(HERRI_KODEA_IDX),
                    cur.getString(IZENA_IDX),
                    cur.getString(PROBINTZIA_IDX),
                    cur.getString(AUTONOMI_ERKIDEGOA_IDX),
                    cur.getString(AUTONOMI_ERKIDEGOA_EUS_IDX),
                    cur.getString(LONGITUDEA_IDX),
                    cur.getString(LATITUDEA_IDX))

            result.add(herria)
        }
        cur.close()

        return result

    }

    fun getFilterSql(filter: HerriaKFilter): String {

        var sql = "select * from herria where 1=1 "

        if (filter.FiltrarPorHerriKodea){
            sql += " and herri_kodea = '%${filter.HerriKodea}%'"
        }
        if (filter.FiltrarPorIzena){
            sql += " and izena = '%${filter.Izena}%'"
        }
        if (filter.FiltrarPorProbintzia){
            sql += " and probintzia = '%${filter.Probintzia}%'"
        }
        if (filter.FiltrarPorAutonomiErkidegoa){
            sql += " and autonomi_erkidegoa = '%${filter.AutonomiErkidegoa}%'"
        }
        if (filter.FiltrarPorAutonomiErkidegoaEus){
            sql += " and autonomi_erkidegoa_eus = '%${filter.AutonomiErkidegoaEus}%'"
        }
        if (filter.FiltrarPorLongitudea){
            sql += " and longitudea = '%${filter.Longitudea}%'"
        }
        if (filter.FiltrarPorLatitudea){
            sql += " and latitudea = '%${filter.Latitudea}%'"
        }

        return sql
    }

}